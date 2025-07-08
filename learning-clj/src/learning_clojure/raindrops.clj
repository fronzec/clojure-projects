(ns learning-clojure.raindrops)

(defn convert
  "Convert a number to a string of raindrop sounds"
  [num]
  (let [result (str
             (when (zero? (rem num 3)) "Pling")
             (when (zero? (rem num 5)) "Plang")
             (when (zero? (rem num 7)) "Plong"))]
    (if (empty? result)
      (str num)
      result)))

(comment
    (convert 3)
    (convert 5)
    (convert 7)
    (convert 1)
  (convert 15)
    (convert 23)
    )