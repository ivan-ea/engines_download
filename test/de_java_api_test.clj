(ns de-java-api-test
  (:require [de-java-api :refer :all]
            [clojure.test :refer :all])
  )

(deftest -downloadEngine-test
  (testing "incorrect arguments to select engine"
    (is (not (-downloadEngine nil "out" "ppytorch" "1.0" false)))
    (is (not (-downloadEngine nil "out" "Pytorch" "1.9.0" false))))
  (testing "correct arguments and download is performed"
    (is (-downloadEngine nil "out" "pytorch" "1.9.0" true))))
