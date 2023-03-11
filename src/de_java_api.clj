(ns de-java-api
  (:gen-class :name download_engines.Download
              :implements [download_engines.DownloadEngines])
  (:require
    [babashka.fs :as fs]
    [config]
    [select-engine :as se]
    [download-engines :as de]))

(defn -downloadEngine
  ([this framework version gpu?]
   (-downloadEngine this config/default-engines-folder framework version gpu?))
  ([this parent-folder framework version gpu?]
   (let [engine-entry (se/select-1-engine framework version gpu?)]
     (println "The selected engine is" (select-keys engine-entry [:engine :pythonVersion :os]))
     (println "Files downloading to:" (str (fs/absolutize parent-folder)))
     (de/download-jars! parent-folder engine-entry)
     true)))

(defn -getDefaultEnginesDirectory [this]
  config/default-engines-folder)
