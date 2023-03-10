# Commands to generate a jar usable by another Java-based project
1. Compile the java source code (generates .class in the same folder)
```
javac .\java\download_engines\*.java
```
2. Build the uberjar
````
clj -T:build uber
````


# Resources on How to handle polyglot (Java/Clojure) projects

## with tools.build
https://andersmurphy.com/2021/12/12/clojure-compiling-java-source-with-tools-build.html

## just compiling
https://blog.agical.se/en/posts/mixed-clojure-and-java/

## leiningen
https://github.com/technomancy/leiningen/blob/master/doc/MIXED_PROJECTS.md#source-layout

