(ns {{name}}.app.db.core
  (:require
    [yesql.core :refer [defqueries]]
    [clojure.java.jdbc :as jdbc]
    [cheshire.core :as json])
  (:import [java.sql PreparedStatement])
  (:import [org.postgresql.util PGobject])
  (:import com.mchange.v2.c3p0.ComboPooledDataSource)
  (:use {{name}}.app.config.core))

(defn pool
  "fn for creating a pooled db source"
  []
  (def db-spec
    {:classname (:jdbc-driver @*props-map*)
     :url       (:jdbc-url @*props-map*)
     :user      (:db-user @*props-map*)
     :password  (:db-password @*props-map*)})
  (let [cpds (doto (ComboPooledDataSource.)
               (.setDriverClass (:classname db-spec))
               (.setJdbcUrl (:url db-spec))
               (.setUser (:user db-spec))
               (.setPassword (:password db-spec)))]
    {:datasource cpds}))
(def pooled-db (delay (pool)))


(defqueries "sql/queries.sql" {:connection @pooled-db})

(defn to-date [sql-date]
  (-> sql-date (.getTime) (java.util.Date.)))

(extend-protocol jdbc/IResultSetReadColumn
  java.sql.Date
  (result-set-read-column [v _ _] (to-date v))

  java.sql.Timestamp
  (result-set-read-column [v _ _] (to-date v))

  org.postgresql.jdbc4.Jdbc4Array
  (result-set-read-column [v _ _] (vec (.getArray v)))

  org.postgresql.util.PGobject
  (result-set-read-column [v _ _] (str v)))

(extend-type java.util.Date
  jdbc/ISQLParameter
  (set-parameter [v ^PreparedStatement stmt idx]
    (.setTimestamp stmt idx (java.sql.Timestamp. (.getTime v)))))

(extend-type org.postgresql.util.PGobject
  jdbc/IResultSetReadColumn
  (result-set-read-column [val rsmeta idx]
    (let [colType (.getColumnTypeName rsmeta idx)]
      (if (or (= colType "json")
              (= colType "jsonb"))
        (json/parse-string (.getValue val) true) val))))
