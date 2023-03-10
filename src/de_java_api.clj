(ns de-java-api
  (:gen-class :name download_engines.Download
              :implements [download_engines.DownloadEngines])
  )

(defn -downloadEngine [parent-folder framework version gpu?]
  (println "hello world")
  false)
