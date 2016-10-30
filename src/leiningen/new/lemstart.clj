(ns leiningen.new.lemstart
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "lemstart"))

(defn lemstart
  "Starter project that contains everything you need to have your project good to go."
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' lemstart project.")
    (->files data
             ["resources/sql/queries.sql" (render "resources/sql/queries.sql" data)]
             ["resources/log4j.properties" (render "resources/log4j.properties" data)]
             ["logs/test.log" (render "logs/test.log")]
             ["src/{{sanitized}}/core.clj" (render "src/core.clj" data)]
             ["src/{{sanitized}}/routes.clj" (render "src/routes.clj" data)]
             ["src/{{sanitized}}/app/config/core.clj" (render "src/app/config/core.clj" data)]
             ["src/{{sanitized}}/app/db/core.clj" (render "src/app/db/core.clj" data)]
             ["src/{{sanitized}}/app/models/users/core.clj" (render "src/app/models/users/core.clj" data)]
             ["src/{{sanitized}}/app/util/core.clj" (render "src/app/util/core.clj" data)]
             ["src/{{sanitized}}/app/util/date.clj" (render "src/app/util/date.clj" data)]
             ["src/{{sanitized}}/app/util/phonenumbers.clj" (render "src/app/util/phonenumbers.clj" data)]
             ["src/{{sanitized}}/app/util/x2j.clj" (render "src/app/util/x2j.clj" data)]
             ["project.clj" (render "project.clj" data)]
             [".gitignore" (render ".gitignore")]
             [".hgignore" (render ".hgignore")]
             ["app.sql" (render "app.sql" data)]
             ["README.md" (render "README.md" data)]
             ["LICENSE" (render "LICENSE" data)]
             ["CHANGELOG.md" (render "CHANGELOG.md" data)]
             ["app.properties" (render "app.properties" data)])))
