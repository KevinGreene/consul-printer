(ns consul-printer.core
  (:require [mount.core :as mount]
            [consul-printer.printer :refer [config timer consul-watcher]]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (mount/start))
