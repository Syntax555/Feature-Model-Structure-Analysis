import java.io.File;
import java.util.List;

import de.ovgu.featureide.fm.core.analysis.cnf.CNF;
import de.ovgu.featureide.fm.core.base.IFeatureModel;
import util.FMUtils;
import util.FileUtils;

public class CnfTranslator {
    

    public static void main(String[] args) {
        FMUtils.installLibraries();
        String csvContent = "model;translation time(milliseconds)\n";
        FileUtils.createDirIfItDoesntExist("cnf/evo");
        
        for (File file : FileUtils.getFileList("evolution")) {
            csvContent += handleFile(file);
        }
        FileUtils.writeContentToFileAndCreateDirs("cnf" + File.separator + "runtimes_evo.csv", csvContent, true);
        
    }



    private static String handleFile(File file) {
        if (!FileUtils.getExtension(file.getPath()).equals("xml")) {
            return "";
        }
        IFeatureModel model = FMUtils.readFeatureModel(file.getPath());
        long startTime = System.nanoTime();
        CNF cnf = FMUtils.getCNF(model);
        long endTime = System.nanoTime();
        String[] split = file.getPath().split(File.separator);
        String dirName = split[split.length - 2];
        if (!dirName.equals("data")) {
            FileUtils.createDirIfItDoesntExist("cnf/evo" + File.separator + dirName);
            FMUtils.saveCNF(cnf, "cnf" + File.separator + "evo" + File.separator + dirName + File.separator + file.getName());
        } else {
            FMUtils.saveCNF(cnf, "cnf" + File.separator + file.getName());
        }

        return file.getPath() + ";" + ((endTime - startTime) / 1000000) + "\n";
    }

}