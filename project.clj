(defproject consul-printer "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [mount "0.1.11-SNAPSHOT"]
                 [tolitius/envoy "0.0.1-SNAPSHOT"]
                 [clj-time "0.12.2"]
                 [jarohen/chime "0.1.9"]
                 [environ "1.1.0"]]
  :plugins [[lein-environ "1.1.0"]]
  :main ^:skip-aot consul-printer.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
