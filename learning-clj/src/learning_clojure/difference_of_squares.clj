(ns learning-clojure.difference-of-squares)


 (defn square-of-sum
   "Returns the square of the sum of the numbers up to the given number"
   [n]
   (let [sum (reduce + (range 1 (inc n)))]; sum is the sum of the numbers up to the given number
     (* sum sum)); square the sum
   )

(defn square [x]
  "Helper function to square a number"
  (* x x))

(defn sum-of-squares
  "Returns the sum of the squares of the numbers up to the given number"
  [n]
  (let [sum (reduce + (map #(* % %) (range 1 (inc n))))]; sum is the sum of the squares of the numbers up to the given number
    sum)); return the sum

;; Alternative implementations of sum-of-squares showing different ways to square numbers:

(defn sum-of-squares-v2
  "Using full anonymous function syntax"
  [n]
  (reduce + (map (fn [x] (* x x)) (range 1 (inc n)))))

(defn sum-of-squares-v3
  "Using named helper function"
  [n]
  (reduce + (map square (range 1 (inc n)))))

(defn sum-of-squares-v4
  "Using Math/pow for squaring"
  [n]
  (reduce + (map #(int (Math/pow % 2)) (range 1 (inc n)))))

(defn sum-of-squares-v5
  "Using threading macro for better readability"
  [n]
  (->> (range 1 (inc n))           ; Generate numbers 1 to n
       (map #(* % %))              ; Square each number
       (reduce +)))                ; Sum them all

(defn sum-of-squares-v6
  "Using list comprehension style with for"
  [n]
  (reduce + (for [i (range 1 (inc n))]
              (* i i))))

;; Demonstration function to show all methods give same result
(defn compare-square-methods
  "Compare all different implementations"
  [n]
  (println (format "For n = %d:" n))
  (println (format "  Original:      %d" (sum-of-squares n)))
  (println (format "  Full fn:       %d" (sum-of-squares-v2 n)))
  (println (format "  Named helper:  %d" (sum-of-squares-v3 n)))
  (println (format "  Math/pow:      %d" (sum-of-squares-v4 n)))
  (println (format "  Threading:     %d" (sum-of-squares-v5 n)))
  (println (format "  For loop:      %d" (sum-of-squares-v6 n))))

(defn difference
  "Returns the difference between the square of the sum of numbers up to a given number and the sum of the squares of those numbers"
  [n]
  (- (square-of-sum n) (sum-of-squares n)))

(defn -main [] ; main function
  (println "=== Difference of Squares Results ===")
  (println (format "difference(100) = %d" (difference 100)))
  (println)
  (println "=== Comparing Different Square Methods ===")
  (compare-square-methods 10)
  (println)
  (compare-square-methods 5))
; (-main) is a different way to call the main function but is not required when using lein run
