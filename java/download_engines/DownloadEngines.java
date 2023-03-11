package download_engines;

import java.io.File;

public interface DownloadEngines {

    public Boolean downloadEngine(File enginesParentFolder, String frameworkName,
                                  String pythonVersion, Boolean isGPU);

    public Boolean downloadEngine(String frameworkName, String pythonVersion, Boolean isGPU);

    public File getDefaultEnginesDirectory();

    //public void downloadManyEngines();
}
