(ns todo.core)

(require '[clj-ulid :as ulid])

; definiendo la variable tasks como un atom de un mapa
(def tasks (atom {}))

; definiendo la tarea
{
 :id 1                                                      ; id tarea
 :description "practice clojure"
 :status :pending
 :priority :high
 :dude-date "2025-07-10"
 }


; definiendo la funcion generate-id
(defn generate-id
  "Generate the next id for the task using ULID instead of integer or UUID"
  []
  (ulid/ulid))

(defn add-task
  "Add a new task to the tasks atom"
  [task]
  (swap! tasks assoc (generate-id) task))

; definiendo la funcion generate-id
(comment
  (println :true)
  (print (generate-id))
  (add-task {:description "practice clojure" :status :pending :priority :high :dude-date "2025-07-10"})
  @tasks

  )
