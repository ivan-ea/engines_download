# Download engines for the java-model-runner

# Commands to generate a jar usable by another Java-based project
1. Compile the java source code (generates .class in the same folder)
```
javac .\java\download_engines\*.java
```
2. Build the uberjar
````
clj -T:build uber
````


# Todos

- [x] Create engines folder if not exists