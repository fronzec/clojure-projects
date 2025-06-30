(ns learning-clojure.complex-numbers-test
  (:require [clojure.test :refer :all]
            [learning-clojure.complex-numbers :refer :all]))

;; Helper function for floating point comparison
(defn approximately-equal?
  ([expected actual tolerance]
   (if (vector? expected)
     (and (approximately-equal? (first expected) (first actual) tolerance)
          (approximately-equal? (second expected) (second actual) tolerance))
     (< (Math/abs (- expected actual)) tolerance)))
  ([expected actual]
   (approximately-equal? expected actual 0.0001)))

(deftest test-real
  (testing "Real part extraction"
    (is (= 1 (real [1 2])))
    (is (= -3 (real [-3 4])))
    (is (= 0 (real [0 5])))
    (is (= 2.5 (real [2.5 -1.5])))))

(deftest test-imaginary
  (testing "Imaginary part extraction"
    (is (= 2 (imaginary [1 2])))
    (is (= 4 (imaginary [-3 4])))
    (is (= 5 (imaginary [0 5])))
    (is (= -1.5 (imaginary [2.5 -1.5])))))

(deftest test-absolute
  (testing "Absolute value (magnitude) calculation"
    (is (= 5.0 (absolute [3 4])))                    ; 3-4-5 triangle
    (is (= 13.0 (absolute [5 12])))                  ; 5-12-13 triangle
    (is (= 1.0 (absolute [1 0])))                    ; Real number
    (is (= 1.0 (absolute [0 1])))                    ; Pure imaginary
    (is (= 0.0 (absolute [0 0])))                    ; Zero
    (is (= 5.0 (absolute [-3 -4])))                  ; Negative components
    (is (approximately-equal? 2.236 (absolute [1 2]) 0.001))))  ; √5 ≈ 2.236

(deftest test-conjugate
  (testing "Complex conjugate calculation"
    (is (= [1 -2] (conjugate [1 2])))
    (is (= [-3 -4] (conjugate [-3 4])))
    (is (= [0 -5] (conjugate [0 5])))
    (is (= [2.5 1.5] (conjugate [2.5 -1.5])))
    (is (= [1 0] (conjugate [1 0])))))          ; Real number conjugate

(deftest test-add
  (testing "Complex number addition"
    (is (= [4 6] (add [1 2] [3 4])))
    (is (= [0 0] (add [1 2] [-1 -2])))
    (is (= [1 2] (add [1 2] [0 0])))
    (is (= [-2 2] (add [-3 4] [1 -2])))
    (is (= [3.5 1.5] (add [2 3] [1.5 -1.5])))))

(deftest test-sub
  (testing "Complex number subtraction"
    (is (= [-2 -2] (sub [1 2] [3 4])))
    (is (= [2 4] (sub [1 2] [-1 -2])))
    (is (= [1 2] (sub [1 2] [0 0])))
    (is (= [0 0] (sub [1 2] [1 2])))
    (is (= [-4 6] (sub [-3 4] [1 -2])))))

(deftest test-mul
  (testing "Complex number multiplication"
    ; (1+2i) * (3+4i) = (3-8) + (6+4)i = -5+10i
    (is (= [-5 10] (mul [1 2] [3 4])))

    ; (2+3i) * (1-2i) = (2+6) + (-4+3)i = 8-i
    (is (= [8 -1] (mul [2 3] [1 -2])))

    ; Multiplication by zero
    (is (= [0 0] (mul [1 2] [0 0])))

    ; Multiplication by real number
    (is (= [2 4] (mul [1 2] [2 0])))

    ; i * i = -1
    (is (= [-1 0] (mul [0 1] [0 1])))

    ; Complex conjugate multiplication: (a+bi)(a-bi) = a²+b²
    (is (= [5 0] (mul [1 2] [1 -2])))))

(deftest test-div
  (testing "Complex number division"
    ; (1+2i) / (1-2i) = (1+2i)(1+2i) / ((1)²+(2)²) = (-3+4i)/5
    (is (approximately-equal? [-0.6 0.8] (div [1 2] [1 -2]) 0.001))

        ; (8+6i) / (2+0i) = 4+3i
    (is (= [4 3] (div [8 6] [2 0])))

    ; Division by 1
    (is (= [1 2] (div [1 2] [1 0])))

    ; (3+4i) / (3+4i) = 1
    (is (approximately-equal? [1.0 0.0] (div [3 4] [3 4]) 0.001))

    ; (0+0i) / (1+2i) = 0
    (is (= [0 0] (div [0 0] [1 2])))))

;; Integration tests combining multiple operations
(deftest test-complex-operations-integration
  (testing "Complex operations working together"
    (let [z1 [3 4]
          z2 [1 -2]
          z3 [2 1]]

      ; Test that (z1 + z2) - z2 = z1
      (is (= z1 (sub (add z1 z2) z2)))

      ; Test that z1 * conjugate(z1) = |z1|²
      (let [z1-conj (conjugate z1)
            product (mul z1 z1-conj)
            abs-squared (* (absolute z1) (absolute z1))]
        (is (approximately-equal? [abs-squared 0] product 0.001)))

      ; Test that (z1 * z2) / z2 ≈ z1 (when z2 ≠ 0)
      (let [product (mul z1 z2)
            quotient (div product z2)]
        (is (approximately-equal? z1 quotient 0.001))))))