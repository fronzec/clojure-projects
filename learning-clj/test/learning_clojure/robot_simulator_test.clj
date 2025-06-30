(ns learning-clojure.robot-simulator-test
  (:require [clojure.test :refer :all]
            [learning-clojure.robot-simulator :refer :all]))

(deftest test-robot-creation
  (testing "Robot creation with coordinates and direction"
    (let [r (robot {:x 0, :y 0} :north)]
      (is (= {:x 0, :y 0} (:coordinates r)))
      (is (= :north (:bearing r))))
    
    (let [r (robot {:x 3, :y 5} :south)]
      (is (= {:x 3, :y 5} (:coordinates r)))
      (is (= :south (:bearing r))))))

(deftest test-advance
  (testing "Robot advancing in different directions"
    (is (= {:coordinates {:x 0, :y 1}, :bearing :north} 
           (advance (robot {:x 0, :y 0} :north))))
    
    (is (= {:coordinates {:x 1, :y 0}, :bearing :east} 
           (advance (robot {:x 0, :y 0} :east))))
    
    (is (= {:coordinates {:x 0, :y -1}, :bearing :south} 
           (advance (robot {:x 0, :y 0} :south))))
    
    (is (= {:coordinates {:x -1, :y 0}, :bearing :west} 
           (advance (robot {:x 0, :y 0} :west))))))

(deftest test-turn-left
  (testing "Robot turning left from all directions"
    (is (= {:coordinates {:x 0, :y 0}, :bearing :west} 
           (turn-left (robot {:x 0, :y 0} :north))))
    
    (is (= {:coordinates {:x 0, :y 0}, :bearing :south} 
           (turn-left (robot {:x 0, :y 0} :west))))
    
    (is (= {:coordinates {:x 0, :y 0}, :bearing :east} 
           (turn-left (robot {:x 0, :y 0} :south))))
    
    (is (= {:coordinates {:x 0, :y 0}, :bearing :north} 
           (turn-left (robot {:x 0, :y 0} :east))))))

(deftest test-turn-right
  (testing "Robot turning right from all directions"
    (is (= {:coordinates {:x 0, :y 0}, :bearing :east} 
           (turn-right (robot {:x 0, :y 0} :north))))
    
    (is (= {:coordinates {:x 0, :y 0}, :bearing :south} 
           (turn-right (robot {:x 0, :y 0} :east))))
    
    (is (= {:coordinates {:x 0, :y 0}, :bearing :west} 
           (turn-right (robot {:x 0, :y 0} :south))))
    
    (is (= {:coordinates {:x 0, :y 0}, :bearing :north} 
           (turn-right (robot {:x 0, :y 0} :west))))))

(deftest test-simulate-simple
  (testing "Simple robot simulation"
    ; Test single instructions
    (is (= {:coordinates {:x 0, :y 1}, :bearing :north}
           (simulate "A" (robot {:x 0, :y 0} :north))))
    
    (is (= {:coordinates {:x 0, :y 0}, :bearing :west}
           (simulate "L" (robot {:x 0, :y 0} :north))))
    
    (is (= {:coordinates {:x 0, :y 0}, :bearing :east}
           (simulate "R" (robot {:x 0, :y 0} :north))))))

(deftest test-simulate-complex
  (testing "Complex robot simulation sequences"
    ; AARA - Advance, Advance, turn Right, Advance
    (is (= {:coordinates {:x 1, :y 2}, :bearing :east}
           (simulate "AARA" (robot {:x 0, :y 0} :north))))
    
    ; LLLL - Turn left 4 times (full circle)
    (is (= {:coordinates {:x 0, :y 0}, :bearing :north}
           (simulate "LLLL" (robot {:x 0, :y 0} :north))))
    
    ; RRRR - Turn right 4 times (full circle)
    (is (= {:coordinates {:x 0, :y 0}, :bearing :north}
           (simulate "RRRR" (robot {:x 0, :y 0} :north))))
    
    ; Complex path: RRAAAAALA
    ; R: {:x 0, :y 0} :north -> {:x 0, :y 0} :east -> {:x 0, :y 0} :south (RR)
    ; A: {:x 0, :y 0} :south -> {:x 0, :y -1} -> {:x 0, :y -2} -> {:x 0, :y -3} -> {:x 0, :y -4} -> {:x 0, :y -5} :south (5 A's)
    ; L: {:x 0, :y -5} :south -> {:x 0, :y -5} :east (turn left)
    ; A: {:x 0, :y -5} :east -> {:x 1, :y -5} :east (move east)
    (is (= {:coordinates {:x 1, :y -5}, :bearing :east}
           (simulate "RRAAAAALA" (robot {:x 0, :y 0} :north))))))

(deftest test-simulate-edge-cases
  (testing "Edge cases for robot simulation"
    ; Empty instructions
    (is (= {:coordinates {:x 0, :y 0}, :bearing :north}
           (simulate "" (robot {:x 0, :y 0} :north))))
    
    ; Invalid instructions should be ignored
    (is (= {:coordinates {:x 0, :y 0}, :bearing :north}
           (simulate "XYZ" (robot {:x 0, :y 0} :north))))
    
    ; Mixed valid and invalid
    ; A: {:x 0, :y 0} :north -> {:x 0, :y 1} :north (advance)
    ; X: {:x 0, :y 1} :north -> {:x 0, :y 1} :north (ignored)
    ; Y: {:x 0, :y 1} :north -> {:x 0, :y 1} :north (ignored)  
    ; A: {:x 0, :y 1} :north -> {:x 0, :y 2} :north (advance again)
    (is (= {:coordinates {:x 0, :y 2}, :bearing :north}
           (simulate "AXYA" (robot {:x 0, :y 0} :north))))))



(deftest robot-simulation-test
  (testing "Simulation 1"
    (is (= (simulate "RAALAL" (robot {:x 7, :y 3} :north)) (robot {:x 9, :y 4} :west)))))



(testing "Create robot at origin facing north" 
  (let [molly (robot {:x 0, :y 0} :north)] 
    (is (= {:coordinates {:x 0, :y 0}, :bearing :north} molly))))
