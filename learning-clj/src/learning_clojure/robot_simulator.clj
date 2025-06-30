(ns learning-clojure.robot-simulator)

(defn robot
  "Creates a robot at the given coordinates, facing the given direction."
  [coordinates bearing]
  {:coordinates coordinates
   :bearing bearing})

(defn advance
  "Moves the robot forward one step in the direction it's facing"
  [robot]
  (case (:bearing robot)
    :north (update-in robot [:coordinates :y] inc)
    :south (update-in robot [:coordinates :y] dec)
    :east (update-in robot [:coordinates :x] inc)
    :west (update-in robot [:coordinates :x] dec)))

(defn turn-left  
  "Turns the robot 90 degrees to the left Update :bearing (north->west, west->south, south->east, east->north)"
  [robot]
  (case (:bearing robot)
    :north (assoc robot :bearing :west)
    :west (assoc robot :bearing :south)
    :south (assoc robot :bearing :east)
    :east (assoc robot :bearing :north)))

(defn turn-right
  "Turns the robot 90 degrees to the right Update :bearing (north->east, east->south, south->west, west->north)"
  [robot]
  (case (:bearing robot)
    :north (assoc robot :bearing :east)
    :east (assoc robot :bearing :south)
    :south (assoc robot :bearing :west)
    :west (assoc robot :bearing :north)))


(defn simulate
  "Simulates the robot's movements based on the given instructions
  and updates its state."
  [instructions robot-state]
  ;; Use reduce to iterate over each instruction and accumulate the robot state
  (reduce (fn [current-robot instruction]
            (case instruction
              \A (advance current-robot)
              \L (turn-left current-robot)
              \R (turn-right current-robot)
              current-robot))
          robot-state
          instructions))

;; Example usage and testing:
;; (def my-robot (robot {:x 0, :y 0} :north))
;; (simulate "AARA" my-robot)
;; Should move: Advance, Advance, turn Right, Advance
;; Expected result: robot at {:x 1, :y 2} facing :east

