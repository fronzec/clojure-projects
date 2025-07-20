(ns task-mngr.core)

(require '[clj-ulid :as ulid])

; we are using an atom to store the tasks
(def tasks (atom {}))

; we are using ulid to generate unique ids for the tasks
{
 :id 1                                                      ; id tarea
 :description "practice clojure"
 :status :pending
 :priority :high
 :dude-date "2025-07-10"
 }


; we are using ulid to generate unique ids for the tasks
(defn generate-id
  "Generate the next id for the task using ULID instead of integer or UUID"
  []
  (ulid/ulid))

(defn add-task
  "Add a new task to the tasks atom"
  [task]
  (swap! tasks assoc (generate-id) task))

(defn list-tasks
  "List all tasks"
  []
  ; here we are dereferencing the atom to get the value
  @tasks)

; we are using ulid to generate unique ids for the tasks
(comment

  (print (generate-id))
  (add-task {:description "practice clojure" :status :pending :priority :high :dude-date "2025-07-10"})
  (list-tasks)

  )
