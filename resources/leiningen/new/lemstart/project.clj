(defproject "{{name}}"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [http-kit "2.1.18"]
                 [compojure "1.5.1"]
                 [yesql "0.5.0"]
                 [org.clojure/tools.macro "0.1.5"]
                 [clout "2.1.2"]
                 [medley "0.7.3"]
                 [clj-time "0.9.0"]
                 [org.postgresql/postgresql "9.4-1206-jdbc41"]
                 [cheshire "5.5.0"]
                 [environ "1.0.0"]
                 [org.clojure/java.jdbc "0.4.1"]
                 [c3p0/c3p0 "0.9.1.2"]
                 [clojurewerkz/scrypt "1.2.0"]
                 [digest "1.4.4"]
                 [ring/ring-defaults "0.1.5"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.apache.commons/commons-io "1.3.2"]
                 [overtone/at-at "1.2.0"]
                 [org.slf4j/slf4j-log4j12 "1.7.3"]
                 [com.googlecode.libphonenumber/libphonenumber "7.4.0"]
                 [overtone/at-at "1.2.0"]
                 [schejulure "1.0.1"]]
  :main ^:skip-aot {{name}}.core
  :target-path "target/%s"
  :uberjar-name "{{name}}.jar"
  :jvm-opts ["-Dconfig=path to project/{{name}}/app.properties"]
  :omit-source true
  :profiles {:uberjar {:aot :all}})
