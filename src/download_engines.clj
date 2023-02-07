(ns download-engines
  (:require config
            download
            [cheshire.core :as json]))

(defn fetch-json
  "download the json as a string"
  []
  (slurp config/input-json-url))

(defn parse-json
  "parse the json string"
  []
  (json/parse-string (fetch-json) true))

(defn build-folder-name [])