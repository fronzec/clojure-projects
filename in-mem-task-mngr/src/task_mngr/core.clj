(ns task-mngr.core
  (:require
   [task-mngr.logic.my-functions :as logic.my-functions]
   [clj-ulid :as ulid]))

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

(defn generate-keyword-id
    "Generate the next id for the task using ULID instead of integer or UUID, return as a keyword"
  []
  (keyword (ulid/ulid))

  )

(defn say-hello
  "this function was created only to test REPL reload all namespace and how works with deep dependencies between namespaces functions"
  [name]
  (logic.my-functions/my-function2 name)
  )

; usually we use ! to indicate that a function will modify the state
(defn add-task!
  "Add a new task to the tasks atom"
  [task]
  (swap! tasks assoc (generate-keyword-id) task))

(defn list-tasks
  "List all tasks"
  []
  ; here we are dereferencing the atom to get the value
  @tasks)


(defn get-task-by-string-key
  "Get a task by id"
  [id]
  (get @tasks id)
  )

(defn get-task-by-string-key
  "Get a task by keyword id"
  [id]
  (id @tasks)
  )


; we are using ulid to generate unique ids for the tasks
(comment
  (print (generate-id))
  (print (generate-keyword-id))
  (add-task! {:description "practice clojure" :status :pending :priority :high :dude-date "2025-07-10"})
  (list-tasks)

  ;; get a task by id as keyword
  (get @tasks :01k4phhyz74z4bas10kt4ns676 )
  ;; get a task by its id as a string
  (get @tasks "01k4phhyz74z4bas10kt4ns676" )

  ;; lookup function using the keyword as a function
  (:01k4phhyz74z4bas10kt4ns676 @tasks)
  )
