(ns download-engines-test
  (:require config
            [download-engines :refer :all]
            [clojure.test :refer :all])
  )


(deftest parse-json-test
  (let [parsed (parse-json)
        find-in-json (fn [k] (reduce #(into %1 [(k %2)]) #{} (:versions parsed)))
        engines-found (find-in-json :engine)
        os-found (find-in-json :os)]
    (is (= (keys parsed) [:versions]))
    (is (= engines-found) (set (:engine-names config/valid-names)))
    (is (= os-found) #{"macosx-x86_64" "macosx-arm64" "linux-arm64" "windows-x86_64" "linux-x86_64"})
    ))
