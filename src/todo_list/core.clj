(ns todo-list.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]
            [todo-list.handlers.base :refer :all]))

(defroutes app
  (GET "/" [] welcome)
  (GET "/goodbye" [] goodbye)
  (GET "/about" [] about)
  (GET "/request-info" [] request-info)
  (GET "/hello/:name" [] hello)
  (GET "/calculator/:a/:op/:b" [] calculator)
  (not-found "<p>Page not found!</p>"))

(defn -main
  "Simple web server using Ring & Jetty"
  [port-number]
  (jetty/run-jetty app
                   {:port (Integer. port-number)}))

(defn -dev-main
  "Simple web server using Ring & Jetty.
  Reloads code changes via the development profile of Leiningen"
  [port-number]
  (jetty/run-jetty (wrap-reload #'app)
                   {:port (Integer. port-number)}))