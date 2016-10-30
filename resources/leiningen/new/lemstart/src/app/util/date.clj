(ns {{name}}.app.util.date
  "namespace to handle date related functionalities"
  (:require [clj-time.core :as t]
            [clj-time.local :as l]
            [clj-time.format :as f]))

(defn now
  "Returns the local timestamp in the format specified"
  [format]
  (f/unparse (f/formatter-local format) (l/local-now)))

(defn format-date
  "Returns a date in the specified format"
  [date format]
  (let [parser (f/formatter (t/default-time-zone) format "dd/MM/YYYY HH:mm:ss")]
    (f/unparse parser (f/parse parser date))))
