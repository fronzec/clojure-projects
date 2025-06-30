(ns learning-clojure.core
  (:require [learning-clojure.difference-of-squares :as diff-squares]
            [learning-clojure.complex-numbers :as complex]
            [learning-clojure.robot-simulator :as robot]
            [learning-clojure.log-levels :as log])
  (:gen-class))

(def algorithms
  "Available algorithms in this project"
  {:difference-of-squares {:ns 'learning-clojure.difference-of-squares
                          :description "Calculate difference between square of sum and sum of squares"}
   :complex-numbers {:ns 'learning-clojure.complex-numbers
                    :description "Complex number arithmetic operations"}
   :robot-simulator {:ns 'learning-clojure.robot-simulator
                    :description "Robot movement simulation with instructions"}
   :log-levels {:ns 'learning-clojure.log-levels
               :description "Parse and reformat log lines with different levels"}})

(defn list-algorithms
  "List all available algorithms"
  []
  (println "Available algorithms:")
  (doseq [[name {:keys [description]}] algorithms]
    (println (format "  %s - %s" (clojure.string/replace (str name) ":" "") description))))

(defn run-algorithm
  "Run a specific algorithm by name"
  [algorithm-name & args]
  (case algorithm-name
    :difference-of-squares (diff-squares/-main)
    :complex-numbers (println "Complex numbers module loaded. Use REPL to test functions like (add [1 2] [3 4])")
    :robot-simulator (do
                       (println "Robot Simulator Demo:")
                       (let [my-robot (robot/robot {:x 0, :y 0} :north)]
                         (println (format "Initial robot: %s" my-robot))
                         (let [final-robot (robot/simulate "AARA" my-robot)]
                           (println (format "After 'AARA' instructions: %s" final-robot)))))
    :log-levels (do
                  (println "Log Levels Demo:")
                  (let [sample-logs ["[INFO]: User login successful"
                                     "[ERROR]: Database connection failed"
                                     "[DEBUG]:   Processing request 123  "
                                     "[WARN]: Memory usage above 80%"]]
                    (doseq [log-line sample-logs]
                      (println (format "Original: %s" log-line))
                      (println (format "Message: \"%s\"" (log/message log-line)))
                      (println (format "Level: \"%s\"" (log/log-level log-line)))
                      (println (format "Reformatted: %s" (log/reformat log-line)))
                      (println))))
    (do
      (println (format "Algorithm '%s' not found." algorithm-name))
      (list-algorithms))))

(defn -main
  "Main entry point - shows available algorithms"
  [& args]
  (println "🚀 Learning Clojure - Algorithm Implementations")
  (println)
  (if (empty? args)
    (list-algorithms)
    (apply run-algorithm (map keyword args))))