(ns learning-clojure.difference-of-squares-test
  (:require [clojure.test :refer :all]
            [learning-clojure.difference-of-squares :refer :all]))

(deftest test-square-of-sum
  (testing "Square of sum calculations"
    (is (= 1 (square-of-sum 1)))
    (is (= 9 (square-of-sum 2)))
    (is (= 36 (square-of-sum 3)))
    (is (= 3025 (square-of-sum 10)))))

(deftest test-sum-of-squares
  (testing "Sum of squares calculations"
    (is (= 1 (sum-of-squares 1)))
    (is (= 5 (sum-of-squares 2)))
    (is (= 14 (sum-of-squares 3)))
    (is (= 385 (sum-of-squares 10)))))

(deftest test-difference
  (testing "Difference between square of sum and sum of squares"
    (is (= 0 (difference 1)))
    (is (= 4 (difference 2)))
    (is (= 22 (difference 3)))
    (is (= 2640 (difference 10)))
    (is (= 25164150 (difference 100)))))