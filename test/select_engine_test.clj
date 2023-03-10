(ns select-engine-test
  (:require [clojure.test :refer :all])
  (:require [select-engine :refer [select-1-engine]]))

(deftest select-1-engine-test
  (is (nil? (select-1-engine "some framework" "3.1.16" true)))
  (is (= (select-1-engine "pytorch" "1.9.1" true)
         {:engine "pytorch",
          :version "1.9.1",
          :pythonVersion "1.9.1",
          :os "windows-x86_64",
          :gpu true,
          :cpu true,
          :rosetta false,
          :jars ["https://repo1.maven.org/maven2/ai/djl/pytorch/pytorch-native-auto/1.9.1/pytorch-native-auto-1.9.1.jar"
                 "https://repo1.maven.org/maven2/ai/djl/pytorch/pytorch-engine/0.14.0/pytorch-engine-0.14.0.jar"
                 "https://repo1.maven.org/maven2/ai/djl/api/0.14.0/api-0.14.0.jar"
                 "https://repo1.maven.org/maven2/org/slf4j/slf4j-simple/1.7.25/slf4j-simple-1.7.25.jar"
                 "https://maven.scijava.org/content/repositories/releases/io/bioimage/dl-modelrunner-pytorch/0.2.0/dl-modelrunner-pytorch-0.2.0.jar"
                 "https://repo1.maven.org/maven2/net/java/dev/jna/jna/5.12.1/jna-5.12.1.jar"
                 "https://repo1.maven.org/maven2/org/apache/commons/commons-compress/1.21/commons-compress-1.21.jar"
                 "https://repo1.maven.org/maven2/org/slf4j/slf4j-api/2.0.6/slf4j-api-2.0.6.jar"]})))
