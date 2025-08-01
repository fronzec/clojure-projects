(ns task-mngr.core-test
  (:require
   [clojure.test :refer [deftest is testing use-fixtures]]
   [task-mngr.core :as core]))

;; Fixture to reset the tasks atom before each test
(defn reset-tasks-fixture [f]
  (reset! core/tasks {})
  (f))

(use-fixtures :each reset-tasks-fixture)

(deftest test-generate-id
  (testing "generate-id produces a unique ULID"
    (let [id1 (core/generate-id)
          id2 (core/generate-id)]
      (is (string? id1) "ID should be a string")
      (is (string? id2) "ID should be a string")
      (is (not= id1 id2) "Generated IDs should be unique")
      (is (= 26 (count id1)) "ULID should be 26 characters long")
      (is (= 26 (count id2)) "ULID should be 26 characters long"))))

(deftest test-add-task!
  (testing "add-task! adds a task to the tasks atom"
    (let [task {:description "Test task" :status :pending :priority :high :due-date "2025-08-01"}
          initial-count (count (core/list-tasks))]
      (core/add-task! task)
      (let [tasks-after (core/list-tasks)]
        (is (= (inc initial-count) (count tasks-after)) "Task count should increase by 1")
        (is (some #(= (:description (val %)) "Test task") tasks-after) "Task should be present in tasks")))))

(deftest test-add-task!-with-multiple-tasks
  (testing "add-task! can add multiple tasks"
    (let [task1 {:description "Task 1" :status :pending :priority :high :due-date "2025-08-01"}
          task2 {:description "Task 2" :status :completed :priority :low :due-date "2025-08-02"}]
      (core/add-task! task1)
      (core/add-task! task2)
      (let [tasks (core/list-tasks)]
        (is (= 2 (count tasks)) "Should have 2 tasks")
        (is (some #(= (:description (val %)) "Task 1") tasks) "Task 1 should be present")
        (is (some #(= (:description (val %)) "Task 2") tasks) "Task 2 should be present")))))

(deftest test-list-tasks
  (testing "list-tasks returns empty map when no tasks"
    (is (= {} (core/list-tasks)) "Should return empty map when no tasks"))

  (testing "list-tasks returns all tasks"
    (let [task1 {:description "Task 1" :status :pending :priority :high :due-date "2025-08-01"}
          task2 {:description "Task 2" :status :completed :priority :low :due-date "2025-08-02"}]
      (core/add-task! task1)
      (core/add-task! task2)
      (let [tasks (core/list-tasks)]
        (is (= 2 (count tasks)) "Should return 2 tasks")
        (is (map? tasks) "Should return a map")
        (is (every? string? (keys tasks)) "All keys should be strings (ULIDs)")))))

(deftest test-task-structure
  (testing "added tasks maintain their structure"
    (let [task {:description "Structured task"
                :status :in-progress
                :priority :medium
                :due-date "2025-08-15"}]
      (core/add-task! task)
      (let [tasks (core/list-tasks)
            added-task (first (vals tasks))]
        (is (= "Structured task" (:description added-task)) "Description should match")
        (is (= :in-progress (:status added-task)) "Status should match")
        (is (= :medium (:priority added-task)) "Priority should match")
        (is (= "2025-08-15" (:due-date added-task)) "Due date should match")))))

(deftest test-tasks-atom-isolation
  (testing "tasks atom is properly isolated between tests"
    ;; This test verifies that the fixture is working correctly
    (is (= {} (core/list-tasks)) "Tasks should be empty at start of each test")
    (core/add-task! {:description "Isolation test" :status :pending :priority :high})
    (is (= 1 (count (core/list-tasks))) "Should have one task after adding")))
