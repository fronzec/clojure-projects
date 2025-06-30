(ns learning-clojure.complex-numbers)

(defn real [[a b]] ;; arglist goes here
  a)

(defn imaginary [[a b]] ;; arglist goes here
  b)

(defn absolute [[a b]] ;; <- arglist goes here
  (Math/sqrt (+ (Math/pow a 2) (Math/pow b 2)))
  )

(defn conjugate [[a b]] ;; <- arglist goes here
  [a (- b)]
  )

(defn add [[a b] [c d]] ;; <- arglist goes here
  [(+ a c) (+ b d)]
  )

(defn sub [[a b] [c d]] ;; <- arglist goes here
  [(- a c) (- b d)]
  )

(defn mul [[a b] [c d]] ;; <- arglist goes here
  [(- (* a c) (* b d)) (+ (* b c) (* a d))]
  )

(defn div [[a b] [c d]] ;; <- arglist goes here
  [(/ (+ (* a c) (* b d)) (+ (* c c) (* d d)))
   (/ (- (* b c) (* a d)) (+ (* c c) (* d d)))]
  )
