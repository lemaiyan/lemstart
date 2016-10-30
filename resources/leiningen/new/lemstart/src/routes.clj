(ns {{name}}.routes
(:use [compojure.route :only [files not-found]]
        [compojure.handler :only [site]]                    ; form, query params decode; cookie; session, etc
        [compojure.core :refer :all]))


(declare show-landing-page get-segment)

(defroutes all-routes
           (GET "/" [] show-landing-page))


(defn- show-landing-page [req]
  ;; nothing much to show here just an empty page
  )

(defn- get-segment
  [path]
  (let [segments (next (next (next path)))
        str-segment (atom "")]
    (doseq [seg segments]
      (reset! str-segment (format "%s/%s" @str-segment seg))
      )
    @str-segment
    ))