(defproject learning-clojure "0.1.0-SNAPSHOT"
  :description "Learning Clojure through algorithm implementations"
  :url "https://github.com/yourusername/learning-clojure"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.11.1"]]
  :source-paths ["src"]
  :test-paths ["test"]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
