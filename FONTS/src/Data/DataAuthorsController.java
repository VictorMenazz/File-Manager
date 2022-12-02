package FONTS.src.Data;

import java.io.*;

public class DataAuthorsController {
    public void saveAuthors(String newLog) throws IOException {
        String basicPath = "data/Authors";
        File authorsFolder = new File(basicPath);
        if(!authorsFolder.exists()) authorsFolder.mkdir();
        File[] authorsLogs = authorsFolder.listFiles();
        String identifier = String.format("%04d", authorsLogs.length);
        FileWriter file = new FileWriter(basicPath + File.separator + identifier + "Authors.json");
        PrintWriter documentWr = new PrintWriter(file);
        documentWr.print(newLog);
        documentWr.close();
    }

    public String getAuthorsSerialized() {
        String basicPath = "data/Authors";
        File authorsFolder = new File(basicPath);
        if(!authorsFolder.exists()) authorsFolder.mkdir();
        File[] authorsLog = authorsFolder.listFiles();
        String identifier = String.format("%04d", authorsLog.length-1);
        FileReader reader = null;
        String result = "";
        try {
            reader = new FileReader(basicPath + File.separator + identifier + "Authors.json");
            BufferedReader br = new BufferedReader(reader);
            String iterator = "";
            while((iterator = br.readLine()) != null){
                result += iterator;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException ex){
            throw new RuntimeException(ex);
        }
        return result;
    }
}
