(ns config)

(def input-json-url
  ;"https://raw.githubusercontent.com/bioimage-io/model-runner-java/main/src/main/resources/availableDLVersions.json"
 "https://raw.githubusercontent.com/bioimage-io/model-runner-java/1bf3fd683cf670beeab303c8085f7b9d722217a2/src/main/resources/availableDLVersions.json"
  )

(def valid-names
  {:os      ["windows" "linux" "macosx"]
   :java-os ["Windows 10" "Linux" "MAC OS X"]
   :engines #{"Tensorflow" "Pytorch"}})
