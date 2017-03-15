(defproject lemstart/lein-template "1.5"
  :description "A Leiningen template for starting REST API web server running on httpkit and postgres database"
  :url "https://github.com/lemaiyan/lemstart.git"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :eval-in-leiningen true
  :deploy-repositories
  [["clojars"
    {:sign-releases false
     :url "https://clojars.org/repo"}]])
