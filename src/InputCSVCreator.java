import com.opencsv.CSVWriter;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class InputCSVCreator {

    public void writeInputCsv(String searchPath, String outputPath) throws IOException {
        List<String[]> result = prepareCsvContent(readFolder(searchPath), searchPath);
        PrintWriter writer = new PrintWriter(outputPath + "\\input.csv", "windows-1252");
        CSVWriter csvWriter = new CSVWriter(writer, ';');
        csvWriter.writeAll(result);
        csvWriter.close();
    }

    private List<File> readFolder(String path) {
        ArrayList<File> result = new ArrayList<File>();
        File readFolder = new File(path);
        for (File file : readFolder.listFiles()) {
            if(file.isDirectory()) {
                result.addAll(readFolder(file.toString()));
            } else {
                result.add(file);
            }
        }
        return result;
    }

    private List<String[]> prepareCsvContent(List<File> files, String basepath) {
        List<String[]> result = new ArrayList<String[]>();
        String[] headline = {"Path", "Filename", "Filetype", "Filesize", "Directory 1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        result.add(headline);
        for (File file : files) {
            String[] filePaths = file.toString().replace(basepath + '\\', "").split("(\\\\)");
            String[] fileNameAndAppendix = filePaths[filePaths.length - 1].split("(\\.)");
            String[] resultLine = new String[(4 + filePaths.length - 1)];
            resultLine[0] = file.toString().replace("\\", "\\\\");
            resultLine[1] = fileNameAndAppendix[0];
            resultLine[2] = fileNameAndAppendix[1];
            resultLine[3] = "" + file.length();
            for (int i = 0; i < filePaths.length - 1; i++) {
                resultLine[4+i] = filePaths[i];
            }
            result.add(resultLine);
        }
        return result;
    }
}
