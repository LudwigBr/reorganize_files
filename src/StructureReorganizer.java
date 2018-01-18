import com.opencsv.CSVReader;

import java.io.*;
import java.util.List;

public class StructureReorganizer {

    public void applyCSVStructure(String resultPath, String csvPath) throws Exception {
        copyAndRenameFiles(resultPath, readFilesFromCSV(csvPath));
    }

    private List<String[]> readFilesFromCSV(String path) throws Exception {
        CSVReader reader = new CSVReader(new InputStreamReader( new FileInputStream(path), "windows-1252"), ';');
        return reader.readAll();
    }

    private void copyAndRenameFiles(String destinationBasePath, List<String[]> csvData) throws Exception {
        for (int row = 1; row < csvData.size(); row++) {
            File source = new File(csvData.get(row)[0]);
            StringBuilder destinationPath = new StringBuilder(destinationBasePath);
            for(int column = 4; column < Math.max(csvData.get(row).length, 14); column++) {
                if(!csvData.get(row)[column].isEmpty()) {
                    destinationPath.append("\\" + csvData.get(row)[column]);
                    new File(destinationPath.toString()).mkdirs();
                }
            }
            destinationPath.append("\\" + csvData.get(row)[1] + "." + csvData.get(row)[2]);
            System.out.println(destinationPath);
            copyFileUsingStream(source, new File(destinationPath.toString()));
        }
    }

    private void copyFileUsingStream(File source, File dest) throws IOException {
        try (InputStream is = new FileInputStream(source); OutputStream os = new FileOutputStream(dest)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }
}
