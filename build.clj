(ns build
  (:require
    [babashka.fs :as fs]
    [clojure.tools.build.api :as b]))

(def lib 'ivan-ea/download-engines)
(def version (format "0.1.%s" (b/git-count-revs nil)))
(def class-dir "target/classes")
(def basis (b/create-basis {:project "deps.edn"}))
(def jar-file (format "target/%s-%s.jar" (name lib) version))
(def uber-file (format "target/%s-%s-standalone.jar" (name lib) version))

(defn clean [_]
  (b/delete {:path "target"}))

(defn jcompile [_]
  ; Does not work on :
  ;   - windows PC:
  ;   - windows laptop:
  ;     + Clojure CLI version 1.11.1.1165
  ;     + Clojure CLI version (deps.clj) 1.11.1.1252
  ; works on linux: Clojure CLI version 1.11.1.1208
  (b/javac {:src-dirs ["java"]
            :class-dir class-dir
            :basis basis
            :javac-opts ["-source" "8" "-target" "8"]}))

(defn jar [_]
  ;(jcompile nil)
  (b/write-pom {:class-dir class-dir
                :lib lib
                :version version
                :basis basis
                :src-dirs ["src"]})
  (b/copy-dir {:src-dirs ["src" "resources"]
               :target-dir class-dir})
  (b/compile-clj {:basis basis
                  :src-dirs ["src"]
                  :class-dir class-dir})
  (b/jar {:class-dir class-dir
          :jar-file jar-file}))

(defn new-root-path
  "Returns a path with a new root"
  [old-root new-root path]
  (apply fs/path (conj (fs/components (fs/relativize old-root path)) new-root)))

(defn copy-classes
  "copy the .class files generated if compiling directly with javac
  e.g. javac /download_engines/*.java"
  [_]
  (mapv #(b/copy-file {:src (str %) :target (str (new-root-path "java" class-dir %))})
        (fs/glob "java" "**.class")))

(defn uber [_]
  (clean nil)
  (if false #_(clojure.string/includes? (System/getProperty "os.name") "Windows")
    (copy-classes nil)
    (jcompile nil))
  (b/copy-dir {:src-dirs ["src" "resources"]
               :target-dir class-dir})
  (b/compile-clj {:basis basis
                  :src-dirs ["src"]
                  :class-dir class-dir})
  (b/uber {:class-dir class-dir
           :uber-file uber-file
           :basis basis
           :main 'my.lib.main}))