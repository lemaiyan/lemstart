(ns {{name}}.app.util.core
  (:require [cheshire.core :refer :all]
            [clojure.tools.logging :as log]
            [clojurewerkz.scrypt.core :as sc]
            [{{name}}.app.config.core :refer :all])
  (:import [java.security MessageDigest]
           [java.math BigInteger]
           [com.sun.org.apache.xerces.internal.impl.dv.util Base64])
  (:use [digest] :reload-all))

(defmacro mytime
  "Evaluates expr and prints the time it took.  Returns the value of
 expr."
  {:added "1.0"}
  [expr & {:keys [desc] :or {desc ""}}]
  `(let [start# (. System (nanoTime))
         ret# ~expr]
     (log/infof "%s executed in %s" ~desc (str  (/ (double (- (. System (nanoTime)) start#)) 1000000.0) " msecs"))
     ret#))




(defn success
  "Alter the server headers"
  [body & {:keys [content-type status] :or {content-type "text/json" status 200}}]
  {:status  status
   :headers {"Content-Type" content-type
             "Access-Control-Allow-Methods" "GET,PUT,POST,DELETE"
             "Access-Control-Allow-Headers" "Content-Type"
             "Allow" "GET,PUT,POST,DELETE"
             "Access-Control-Allow-Origin" "*"}
   :body (if (= content-type "text/json") (generate-string body) body)})

(defn create-hash
  ^{:author "Lemaiyan"
    :description "Fn for creating password hash"
    :added "1.0"
    :created "Fri, August 21, 2015"}
  [password]
  (sc/encrypt password 16384 8 1))

(defn authenticate
  ^{:author "Lemaiyan"
    :description "Fn forauthenticating user"
    :added "1.0"
    :created "Fri, August 21, 2015"}
  [password hash]
  (sc/verify password hash))

(defn generate-sess-id
  ^{:author "Lemaiyan"
    :description "Fn for genarating an api key"
    :added "1.0"
    :created "Fri, August 21, 2015"}
  []
  (digest "sha-256" (format "%d" (System/currentTimeMillis))))

(defn generate-online-password
  "Returns a Base64 encoded string"
  [timestamp]
  (let [password (str (:online-checkout-merchant-id @*props-map*) (:online-checkout-passkey @*props-map*) timestamp)]
    ; encrypt password
    (let [message-digest (MessageDigest/getInstance "SHA-256")]
      (doto message-digest
        (.update (.getBytes password) 0 (.length password)))
      (let [SHA-string (.toString (BigInteger. 1 (.digest message-digest)) 16)]
        (Base64/encode (.getBytes SHA-string))))))