(ns de-java-api
  (:gen-class :name download_engines.Download
              :implements [download_engines.DownloadEngines])
  )

(defn -downloadEngine [this parent-folder framework version gpu?]
  (println "hello world")
  false)
