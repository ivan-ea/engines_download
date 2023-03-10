(ns de-java-api
  (:gen-class :name download_engines.Download
              :implements [download_engines.DownloadEngines])
  (:require [select-engine :as se]))

(defn -downloadEngine [this parent-folder framework version gpu?]
  (let [engine-entry (se/select-1-engine framework version gpu?)]
    (println "The selected engine is " (select-keys engine-entry [:engine :pythonVersion :os])))
  false)
