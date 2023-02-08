(ns select-engine)

(defn get-latest-version
  [engines-vector engine-name]
  (let [engines (filter #(= engine-name (:engine %)) engines-vector)]
    (first engines)))

(comment
  (download-engines/download-jars! (get-latest-version download-engines/parsed "Pytorch"))
  )