(defproject todo-list "0.1.0-SNAPSHOT"
  :description "A simple web app using Ring"

  :url "http://example.com/FIXME"

  :license {:name "Creative Commons Attribution Share-Alike 4.0 International"
            :url "https://creativecommons.org"}

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [ring                "1.7.1"]
                 [compojure           "1.3.4"]
                 [hiccup              "1.0.5"]]

  :repl-options {:init-ns todo-list.core}
  
  :main todo-list.core
  :min-lein-version "2.0.0"
  :uberjar-name "todo-list.jar"

  :profiles {:dev
             {:main todo-list.core/-dev-main}})
