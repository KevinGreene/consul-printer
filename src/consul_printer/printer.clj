(ns consul-printer.printer
  (:require [mount.core :as mount :refer [defstate]]
            [envoy.core :as envoy]
            [clj-time.core :as t]
            [chime :refer [chime-at]]
            [clj-time.periodic :refer [periodic-seq]]
            [environ.core :refer [env]]))


(defstate config
  :start (envoy/consul->map (env :consul-url)))

(defn periodic-print [interval phrase]
  (chime-at (periodic-seq (t/now) (-> interval t/millis))
            (fn [time]
              (println (str time) phrase))))

(defstate timer
  :start (let [{:keys [interval phrase]} (:printer config)]
           {:interval interval
            :phrase phrase
            :running? true
            :cancel-fn (periodic-print interval phrase)})
  :stop (do
          ((:cancel-fn timer))
          {:running? false}))

(def listener
  (let [restart-vec [#'consul-printer.printer/config #'consul-printer.printer/timer]
        watchers {:printer/interval restart-vec
                  :printer/phrase restart-vec}]
    (mount/restart-listener watchers)))

(defn watch-consul [path]
  (envoy/watch-path path #(mount/on-change listener (keys %))))

(defstate consul-watcher :start (watch-consul (str (env :consul-url) "/printer"))
  :stop (envoy/stop consul-watcher))
