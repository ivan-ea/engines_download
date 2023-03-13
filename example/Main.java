package example;

import download_engines.Download;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Download d = new Download();
        File f = new File("./out/");

        // What if selected engine is not found?
        Boolean c = d.downloadEngine(f,"ppytorch", "1.9.1", false);
        System.out.println("return is " + c);

        // Download all the jars in parallel
        Boolean b = d.downloadEngine(f,"pytorch", "1.9.1", true);
        System.out.println("return is " + b);

        // Default engines folder
        System.out.println("default engines folder is " + d.getDefaultEnginesDirectory());

        // Download all the jars in parallel, to the default directory
        d.downloadEngine("tensorflow", "2.7.4", false);

    }
}