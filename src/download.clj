(ns download
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [babashka [curl :as curl] [fs :as fs]]))

(defmacro my-time
  "Variation on clojure.core/time: https://github.com/clojure/clojure/blob/clojure-1.10.1/src/clj/clojure/core.clj#L3884
  This macro returns a map with the time taken (duration) and the return value of the expression.
  Useful when timing side effects, when further composition is not usually needed (but still possible)"
  [expr]
  `(let [start# (java.time.Instant/ofEpochMilli (System/currentTimeMillis))
         ret# ~expr ;; evaluates the argument expression
         end# (java.time.Instant/ofEpochMilli (System/currentTimeMillis))
         duration# (java.time.Duration/between start# end#)]
     (hash-map :duration duration# :iso (str duration#)  :return ret#)))

(defn get-url-response
  "Gets the response of a URL as a hash map"
  [^String url]
  (let [basic-opts {:as :bytes :throw false},
        opts (if (str/includes? (System/getProperty "os.name") "Windows")
               (assoc basic-opts :compressed false)
               basic-opts)]
    (curl/get url opts)))

(defn get-url-filename
  "Gets the filename from a url"
  [^String url]
  (->> (io/as-url url) (.getPath) fs/path fs/file-name))

(defn byte-arr->file!
  "Save a byte-array as a file. Ref source https://gist.github.com/philippkueng/11377226"
  [directory b-arr file-name]
  (let [dest-file (fs/file directory file-name)]
    (io/copy b-arr (fs/file directory file-name))
    dest-file))
