package example;

import download_engines.Download;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Download d = new Download();
        File f = new File("./out/");

        // Download all the jars in parallel
        Boolean b = d.downloadEngine(f,"pytorch", "1.9.1", true);

        System.out.println("output is " + b);

        System.out.println("default engines folder is " + d.getDefaultEnginesDirectory());

        // Download all the jars in parallel, to the default directory
        d.downloadEngine("tensorflow", "2.7.4", false);

    }
}