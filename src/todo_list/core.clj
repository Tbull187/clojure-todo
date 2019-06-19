(ns todo-list.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]))

(defn welcome
  "A ring handler to respond with a simple welcome message"
  [request]
  {:status 200
   :body "<h1>Hello, Clojure World</h1>
     <p>Welcome to your first Clojure app, I now update automatically</p>
     <p>I use Compojure's defroutes to manage incoming requests</p>"
   :headers {}})


(defn goodbye
  "Tell the user, BYE BYE"
  [request]
  {:status 200
   :body "<div>something something, kthxbye</div>"
   :header {}})


(defn about
  "Information about the website developer"
  [request]
  {:status 200
   :body "I am an awesome Clojure developer."
   :headers {}})


(defn request-info
  "View the information contained in the request, useful for debugging"
  [request]
  {:status 200
   :body (pr-str request)
   :headers {}})


(defn hello
  "A simple greeting to the user"
  [request]
  (let [name (get-in request [:route-params :name])]
    {:status 200
     :body (str "Hello " name ".  I got your name from the web URL")
     :headers {}}))


(def operands {"+" + 
               "-" - 
               "*" * 
               ":" /})
(defn calculator
  [request]
  (let [a (Integer. (get-in request [:route-params :a]))
        b (Integer. (get-in request [:route-params :b]))
        op (get-in request [:route-params :op])
        f (get operands op)]
    (if f
      {:status 200
       :body (str "Calculated result: " (f a b))
       :headers {}}
      {:status 404
       :body "Sorry, unknown operator.  I only recognise + - * : (: is for division)"
       :headers {}})))


(defroutes app
  (GET "/" [] welcome)
  (GET "/goodbye" [] goodbye)
  (GET "/about" [] about)
  (GET "/request-info" [] request-info)
  (GET "/hello/:name" [] hello)
  (GET "/calculator/:a/:op/:b" [] calculator)
  (not-found "<p>Page not found!</p>"))

(defn -main
  "A very simple web server using Ring & Jetty"
  [port-number]
  (jetty/run-jetty app
                   {:port (Integer. port-number)}))

(defn -dev-main
  "A very simple web server using Ring & Jetty that reloads code changes via the development profile of Leiningen"
  [port-number]
  (jetty/run-jetty (wrap-reload #'app)
                   {:port (Integer. port-number)}))