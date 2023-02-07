(ns download-engines
  (:require config
            download
            [clojure.string :as str]
            [babashka.fs :as fs]
            [cheshire.core :as json]))

(defn fetch-json "download the json as a string"
  []
  (slurp config/input-json-url))

(defn parse-json "parse the json string"
  []
  (json/parse-string (fetch-json) true))

(defn select-entry
  "Selects the right entry, given the args (model I want)
  Input: dict of args"
  [])

(defn build-folder-name
  "Generates the folder name for a json entry"
  [engine-entry]
  (let [{:keys [engine version pythonVersion os gpu cpu]} engine-entry
        separator "-"
        base-name (str/join separator [engine version pythonVersion os "cpu"])]
    (if gpu (str base-name separator "gpu") base-name)))

(defn download-file!
  ([url] (download-file! (download/get-url-filename url) url))
  ([file-name url] (download-file!  config/default-engines-folder file-name url))
  ([folder file-name url]
   (let [byte-array (:body (download/get-url-response url))]
     (fs/create-dirs folder)
     (download/byte-arr->file! folder byte-array file-name))))

(defn download-files
  [json-entry]
  "download the jars from a json entry"
  (  ))


(defn build-engine-files
  [engine-name ]
  "Creates the correct folder
  Downloads correct files")