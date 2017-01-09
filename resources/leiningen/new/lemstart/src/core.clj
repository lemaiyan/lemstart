(ns {{name}}.core
  (:use [compojure.route :only [files not-found]]
        [compojure.handler :only [site]]                    ; form, query params decode; cookie; session, etc
        [compojure.core :refer :all]
        org.httpkit.server
        {{name}}.app.config.core)
  (:require [{{name}}.routes :refer :all]
            [{{name}}.app.util.core :as util]
            [ring.middleware.reload :as reload])
  (:gen-class))

(defn -main [& args]
  (util/mytime (run-server (reload/wrap-reload (site #'all-routes)) {:port (Integer/parseInt (.trim (:server-port @*props-map*)))})
               :desc (format "Server Started at port %s" (.trim (:server-port @*props-map*)))))