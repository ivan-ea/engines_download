package download_engines;

import java.io.File;

public interface DownloadEngines {

    public Boolean downloadEngine(File enginesParentFolder, String frameworkName,
                                  String pythonVersion, Boolean isGPU);

    //public void downloadManyEngines();
}
