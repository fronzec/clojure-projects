(ns todo.core)

(def tasks (atom []))

{
 :id 1                                                      ; id tarea
 :description "practice clojure"
 :status :pending
 :priority :high
 :dude-date "2025-07-10"
 }


(defn generate-id
  "Generate the next id for the task"
  []
  (inc (or (-> @tasks last :id) 0)))

(comment
  (println :true)
  (inc 1)
  (generate-id)
  )
