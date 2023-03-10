(ns download-engines
  (:require config
            download
            [clojure.string :as str]
            [clojure.pprint :as ppr]
            [babashka.fs :as fs]
            [cheshire.core :as json]))

(defn fetch-json "download the json as a string" []
  (slurp config/input-json-url))

(defn parse-json "parse the json string" []
  (json/parse-string (fetch-json) true))

(defn os-engines-vector []
  "vector of engine entries, os compatible only"
  (let [parsed (:versions (download-engines/parse-json))]
    (filter #(= (:os %) config/os-string) parsed)))

(defn build-folder-name
  "Generates the folder name for a json entry"
  [engine-entry]
  (let [{:keys [engine version pythonVersion os gpu cpu]} engine-entry
        separator "-"
        base-name (str/join separator [(str/capitalize engine) pythonVersion version os "cpu"])]
    (if gpu (str base-name separator "gpu") base-name)))

(defn download-file!
  "Download url file in a folder"
  [parent-folder url]
  (let [file-name (download/get-url-filename url)
        byte-array (:body (download/get-url-response url))]
    (fs/create-dirs parent-folder)
    (download/byte-arr->file! parent-folder byte-array file-name)))

(defn download-info
  "Downloads the file, build download information in a dict"
  [timed-download]
  (let [downloaded-file (:return timed-download)]
    {:file-name  (fs/file-name downloaded-file)
     :time-taken (:iso timed-download)
     :file-size  (format "%.2f MB"
                         (/ (.length downloaded-file) (Math/pow 2 20)))}))

(defn download-jars!
  "Download the jars from a json entry. Print info verbosely"
  ([engine-entry] (download-jars! config/default-engines-folder engine-entry))
  ([engines-folder {links :jars :as engine-entry}]
   (let [sub-folder-name (build-folder-name engine-entry)]
     (pmap #(-> (download-file! (fs/file engines-folder sub-folder-name) %)
                download/my-time
                download-info
                ppr/pprint) links))))


