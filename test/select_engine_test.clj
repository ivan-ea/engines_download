(ns select-engine-test
  (:require [clojure.test :refer :all])
  (:require [select-engine :refer [select-1-engine]]))

(deftest select-1-engine-test
  (is (nil? (select-1-engine "some framework" "3.1.16" true)))
  (is (= (select-keys (select-1-engine "pytorch" "1.9.1" true)
                      [:engine :version :pythonVersion :gpu])
         {:engine "pytorch",
          :version "1.9.1",
          :pythonVersion "1.9.1"
          :gpu true})))
