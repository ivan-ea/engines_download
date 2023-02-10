(ns download-engines-test
  (:require config
            [download-engines :refer :all]
            [clojure.test :refer :all]
            [babashka.fs :as fs]))

(deftest parse-json-test
  (let [parsed (parse-json)
        find-in-json (fn [k] (reduce #(into %1 [(k %2)]) #{} (:versions parsed)))
        engines-found (find-in-json :engine)
        os-found (find-in-json :os)]
    (is (= (keys parsed) [:versions]))
    (is (= engines-found) (set (:engine-names config/valid-names)))
    (is (= os-found) #{"macosx-x86_64" "macosx-arm64" "linux-arm64" "windows-x86_64" "linux-x86_64"})))

(deftest build-folder-name-test
  (let [parsed (:versions (parse-json))]
    (is (= (build-folder-name (first parsed))
           "Tensorflow-1.12.0-1.12.0-windows-x86_64-cpu"))
    (is (= (build-folder-name (second parsed))
           "Tensorflow-1.12.0-1.12.0-windows-x86_64-cpu-gpu"))
    (is (= (build-folder-name {:engine "Tensorflow",
                               :version "0.3.1",
                               :pythonVersion "2.4.1",
                               :os "windows-x86_64",
                               :gpu false,
                               :cpu true,
                               :rosetta false})
           "Tensorflow-2.4.1-0.3.1-windows-x86_64-cpu"))))

(deftest download-file!-test
  (testing "check 1 arg (url)"
    (let [file (download-file! config/input-json-url)]
      (is (fs/exists? file config/input-json-url))))
  )

(deftest print-download-info-test
  (let [info-dict (-> (download-file! config/input-json-url)
                          download/my-time
                          download-info)]
    (is (= (set (keys info-dict)) #{:file-name :time-taken :file-size}))
    (is (= (:file-name info-dict) "availableDLVersions.json"))
    (is (= (:file-size info-dict) (format "%.2f MB" 0.12)))))
