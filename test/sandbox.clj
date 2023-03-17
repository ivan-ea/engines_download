(ns sandbox
  (:require [config]
            [download-engines :as d-e]
            [de-java-api :as java-api]
            [clojure.reflect :as cr]
            [clojure.pprint :as pp]
            [clojure.test :refer :all]
            [babashka.fs :as fs])
  (:import [download_engines Greeter]))

(defn members-table [class]
  (->> class cr/reflect :members pp/print-table)
  )

(.great (Greeter.))

(defn display-entry
  "select the relevant keys for display"
  ([entry]
   (display-entry entry [:engine :version :pythonVersion :os :gpu :rossetta]))
  ([entry selection-vector]
   (select-keys entry selection-vector)))

; query the engines in the json
(comment
  (def j (d-e/os-engines-vector))
  (def tf (filter #(and (not (:gpu %)) (= "tensorflow" (:engine %))) j))
  (def tf-gpu (filter #(and (:gpu %) (= "tensorflow" (:engine %))) j))
  (def pt (filter #(and (:gpu %) (= "pytorch" (:engine %))) j))
  (def onnx (filter #(and (:gpu %) (= "onnx" (:engine %))) j))
  (count tf)
  (map display-entry pt)
  )

; test java api calls
(comment
  (java-api/-downloadEngine nil "tensorflow" "2.7.4" false)
  )

(comment
  (map #(.substring % 0 (.lastIndexOf % "_")) (keys config/engines-versions))
  (map #() config/engine-names)
  )