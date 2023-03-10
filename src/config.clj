(ns config
  (:require [babashka.fs :as fs]))

; new namings
(def input-json-url
  "https://raw.githubusercontent.com/bioimage-io/model-runner-java/main/src/main/resources/availableDLVersions.json"
  ;"https://raw.githubusercontent.com/bioimage-io/model-runner-java/1bf3fd683cf670beeab303c8085f7b9d722217a2/src/main/resources/availableDLVersions.json"
  )
(def default-engines-folder (fs/file (System/getProperty "user.home")
                                     "blank_fiji" "Fiji.app" "engines"))

(def valid-names
  {:os           ["windows" "linux" "macosx"]               ; this may not be needed
   :java-os      ["Windows 10" "Linux" "MAC OS X"]
   :engine-names ["Tensorflow" "Pytorch" "onnx"]})

(def framework-names
  {:pt   {:json "pytorch" :folder "Pytorch"}
   :tf   {:json "tensorflow" :folder "Tensorflow"}
   :onnx {:json "onnx" :folder "onnx"}})

(def os-java-names
  {"Windows 10" "windows-x86_64"
   "Linux"      "linux-x86_64"
   "MAC OS X"   "macosx-x86_64"})

(def os-string (get os-java-names (System/getProperty "os.name")))

(def interesting-versions ["1.15.0" "0.2.0" "0.3.3" "0.4.2" "1.13.0" "1.9.0" "1.9.1"])
