(ns config
  (:require [babashka.fs :as fs]))

(def input-json-url
  ;"https://raw.githubusercontent.com/bioimage-io/model-runner-java/main/src/main/resources/availableDLVersions.json"
 "https://raw.githubusercontent.com/bioimage-io/model-runner-java/1bf3fd683cf670beeab303c8085f7b9d722217a2/src/main/resources/availableDLVersions.json"
  )

(def default-engines-folder (fs/file (System/getProperty "user.home")
                                     "blank_fiji" "Fiji.app" "engines"))

(def valid-names
  {:os           ["windows" "linux" "macosx"]  ; this may not be needed
   :java-os      ["Windows 10" "Linux" "MAC OS X"]
   :engine-names ["Tensorflow" "Pytorch" "onnx"]})
