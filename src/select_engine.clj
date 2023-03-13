(ns select-engine
  (:require config
            [download-engines :as de]))

(defn get-latest-version
  ; TODO: fix this, this is wrong
  [engines-vector engine-name]
  (let [engines (filter #(= engine-name (:engine %)) engines-vector)]
    (second engines)))

(defn get-version [engines-vector version]
  (filter #(= (:version %) version) engines-vector))

(defn select-1-engine
  "Selects a particular engine, given all the fields to uniquely identify it"
  ([framework p-version gpu?] (select-1-engine (de/os-engines-vector) framework p-version gpu?))
  ([engines-vector framework p-version gpu?]
   (first (filter #(and (= framework (:engine %))
                        (= p-version (:pythonVersion %))
                        (= gpu? (:gpu %)))
                  engines-vector))))

(comment
  (get-version (de/os-engines-vector) "1.15.0")
  ; download every interesting version
  (mapv download-engines/download-jars!
        (flatten (map (partial get-version (de/os-engines-vector)) config/interesting-versions)))
  )