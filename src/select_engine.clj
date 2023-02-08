(ns select-engine
  (:require config
            [download-engines :as de]))


(defn get-latest-version
  [engines-vector engine-name]
  (let [engines (filter #(= engine-name (:engine %)) engines-vector)]
    (second engines)))

(defn get-version [engines-vector version]
  (filter #(= (:version %) version) engines-vector))

(comment
  (get-version (de/os-engines-vector) "1.15.0")
  ; download every interesting version
  (mapv download-engines/download-jars!
        (flatten (map (partial get-version (de/os-engines-vector)) config/interesting-versions)))
  )