(ns learning-clojure.log-levels-test
  (:require [clojure.test :refer :all]
            [learning-clojure.log-levels :refer :all]))

(deftest test-message
  (testing "Message extraction from log lines"
    (testing "Basic message extraction"
      (is (= "User order completed" 
             (message "[INFO]: User order completed")))
      
      (is (= "An error occurred" 
             (message "[ERROR]: An error occurred")))
      
      (is (= "Debug info here" 
             (message "[DEBUG]: Debug info here"))))
    
    (testing "Message extraction with whitespace trimming"
      (is (= "Message with leading space" 
             (message "[INFO]:   Message with leading space")))
      
      (is (= "Message with trailing space" 
             (message "[WARN]: Message with trailing space  ")))
      
      (is (= "Message with both spaces" 
             (message "[ERROR]:   Message with both spaces  "))))
    
    (testing "Empty or minimal messages"
      (is (= "" 
             (message "[INFO]: ")))
      
      (is (= "" 
             (message "[DEBUG]:   ")))
      
      (is (= "X" 
             (message "[FATAL]: X"))))
    
    (testing "Messages with special characters"
      (is (= "User@domain.com logged in" 
             (message "[INFO]: User@domain.com logged in")))
      
      (is (= "Error: 404 - Not Found!" 
             (message "[ERROR]: Error: 404 - Not Found!")))
      
      (is (= "Process #1234 terminated" 
             (message "[WARN]: Process #1234 terminated"))))))

(deftest test-log-level
  (testing "Log level extraction and normalization"
    (testing "Standard log levels"
      (is (= "info" 
             (log-level "[INFO]: Message here")))
      
      (is (= "error" 
             (log-level "[ERROR]: Error message")))
      
      (is (= "debug" 
             (log-level "[DEBUG]: Debug information")))
      
      (is (= "warn" 
             (log-level "[WARN]: Warning message")))
      
      (is (= "fatal" 
             (log-level "[FATAL]: Critical error"))))
    
    (testing "Case normalization"
      (is (= "info" 
             (log-level "[info]: lowercase level")))
      
      (is (= "error" 
             (log-level "[Error]: mixed case level")))
      
      (is (= "debug" 
             (log-level "[DEBUG]: uppercase level"))))
    
    (testing "Custom log levels"
      (is (= "trace" 
             (log-level "[TRACE]: Trace message")))
      
      (is (= "security" 
             (log-level "[SECURITY]: Security alert")))
      
      (is (= "audit" 
             (log-level "[AUDIT]: Audit log entry"))))
    
    (testing "Single character levels"
      (is (= "i" 
             (log-level "[I]: Info shorthand")))
      
      (is (= "e" 
             (log-level "[E]: Error shorthand"))))))

(deftest test-reformat
  (testing "Log line reformatting"
    (testing "Standard reformatting"
      (is (= "User login successful (info)"
             (reformat "[INFO]: User login successful")))
      
      (is (= "Database connection failed (error)" 
             (reformat "[ERROR]: Database connection failed")))
      
      (is (= "Cache miss for key user:123 (debug)" 
             (reformat "[DEBUG]: Cache miss for key user:123"))))
    
    (testing "Reformatting with whitespace handling"
      (is (= "Message with spaces (warn)" 
             (reformat "[WARN]:   Message with spaces  ")))
      
      (is (= "Clean message (info)" 
             (reformat "[INFO]: Clean message"))))
    
    (testing "Complex messages"
      (is (= "HTTP 500: Internal server error at /api/users (error)" 
             (reformat "[ERROR]: HTTP 500: Internal server error at /api/users")))
      
      (is (= "User admin@company.com performed action DELETE on resource /users/123 (audit)" 
             (reformat "[AUDIT]: User admin@company.com performed action DELETE on resource /users/123"))))
    
    (testing "Edge cases"
      (is (= " (info)" 
             (reformat "[INFO]: ")))
      
      (is (= "Single (x)" 
             (reformat "[X]: Single"))))))

(deftest test-integration
  (testing "Integration of all functions working together"
    (let [log-line "[WARN]: Memory usage above 80%"]
      (is (= "Memory usage above 80%" (message log-line)))
      (is (= "warn" (log-level log-line)))
      (is (= "Memory usage above 80% (warn)" (reformat log-line))))

    (let [log-line "[SECURITY]:   Unauthorized access attempt from IP 192.168.1.100  "]
      (is (= "Unauthorized access attempt from IP 192.168.1.100" (message log-line)))
      (is (= "security" (log-level log-line)))
      (is (= "Unauthorized access attempt from IP 192.168.1.100 (security)" (reformat log-line))))))

(deftest test-edge-cases
  (testing "Edge cases and error scenarios"
    (testing "Current behavior with edge cases"
      (is (string? (message "[INFO]: Normal message")))
      (is (string? (log-level "[INFO]: Normal message")))
      (is (string? (reformat "[INFO]: Normal message"))))))

(deftest test-performance
  (testing "Performance with multiple log lines"
    (let [sample-logs ["[INFO]: User logged in"
                       "[ERROR]: Database timeout" 
                       "[DEBUG]: Processing request 123"
                       "[WARN]: High memory usage"
                       "[FATAL]: System shutdown initiated"]]
      
      (testing "Batch processing messages"
        (let [messages (map message sample-logs)]
          (is (= 5 (count messages)))
          (is (every? string? messages))))
      
      (testing "Batch processing log levels"
        (let [levels (map log-level sample-logs)]
          (is (= 5 (count levels)))
          (is (every? string? levels))))
      
      (testing "Batch reformatting"
        (let [reformatted (map reformat sample-logs)]
          (is (= 5 (count reformatted)))
          (is (every? string? reformatted)))))))