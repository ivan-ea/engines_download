(ns sandbox
  (:require [download-engines :as d-e]
            [clojure.reflect :as cr]
            [clojure.pprint :as pp]
            [clojure.test :refer :all]
            [babashka.fs :as fs])
  (:import [download_engines Greeter]))

(defn members-table [class]
  (->> class cr/reflect :members pp/print-table)
  )

(.great (Greeter.))

(comment
  (def j (d-e/os-engines-vector))
  (def tf (filter #(and (not (:gpu %)) (= "tensorflow" (:engine %))) j))
  (count tf)
  )