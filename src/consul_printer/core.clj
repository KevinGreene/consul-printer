(ns consul-printer.core
  (:require [mount.core :as mount]
            [consul-printer.printer :refer [timer]]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (mount/start))
