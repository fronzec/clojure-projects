(ns learning-clojure.raindrops-test
  (:require [clojure.test :refer :all]
            [learning-clojure.raindrops :refer [convert]]))

(deftest raindrops-basic-tests
  (testing "Single factor cases"
    (is (= "Pling" (convert 3)))
    (is (= "Plang" (convert 5)))
    (is (= "Plong" (convert 7))))
  (testing "Multiple factors"
    (is (= "PlingPlang" (convert 15)))
    (is (= "PlingPlong" (convert 21)))
    (is (= "PlangPlong" (convert 35)))
    (is (= "PlingPlangPlong" (convert 105)))))

(deftest raindrops-no-factors
  (testing "No factors, should return number as string"
    (is (= "1" (convert 1)))
    (is (= "34" (convert 34)))
    (is (= "52" (convert 52)))))

(deftest raindrops-edge-cases
  (testing "Edge cases"
    (is (= "PlingPlangPlong" (convert 0)))))
