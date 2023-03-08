(ns sandbox
  (:require [download-engines :as d-e]
            [borkdude.deflet :refer [deflet]]
            [clojure.test :refer :all]))

(deflet
  (def j (d-e/os-engines-vector))
  (def tf (filter #(and (not (:gpu %)) (= "tensorflow" (:engine %))) j))
  (count tf)
  )