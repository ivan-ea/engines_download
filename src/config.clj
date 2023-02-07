(ns config)

(def input-json-url
  "https://github.com/bioimage-io/model-runner-java/blob/main/src/main/resources/availableDLVersions.json")

(def valid-names
  {:os      ["windows" "linux" "macosx"]
   :java-os ["Windows 10" "Linux" "MAC OS X"]
   :engines {"Tensorflow" "Pytorch"}})
