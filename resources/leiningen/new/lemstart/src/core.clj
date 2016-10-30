(ns {{name}}.core
  (:use [[compojure.route :only [files not-found]]
        [compojure.handler :only [site]]                    ; form, query params decode; cookie; session, etc
        [compojure.core :refer :all]
        org.httpkit.server
        {{name}}.app.config.core)
  (:gen-class)
  (:require [{{name}}.routes :refer :all]
            [{{name}}.app.util.core :as util]))

(defn -main []
  (util/mytime (run-server (site #'all-routes) {:port (Integer/parseInt (.trim (:server-port @*props-map*)))})
               :desc (format "Server Started at port %s" (.trim (:server-port @*props-map*)))))