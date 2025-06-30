(ns learning-clojure.log-levels
  (:require [clojure.string :as str])) ; import the str namespace to use the str/trim function for example

(defn message
  "Takes a string representing a log line
     and returns its message with whitespace trimmed."
  [s]
  (str/trim (subs s (+ 2 (str/index-of s "]:")))))

(defn log-level
  "Takes a string representing a log line
     and returns its level in lower-case. E.g. [INFO]: Message here -> info"
  [s]
  (str/lower-case (subs s 1 (str/index-of s "]")))
  )

(defn reformat
  "Takes a string representing a log line and formats it
     with the message first and the log level in parentheses."
  [s]
  (str (message s) " (" (log-level s) ")"))