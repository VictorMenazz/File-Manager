package FONTS.src.Data;

import java.io.*;

public class DataAuthorsController {
    /**
     * @brief Writes on a physic File all Authors System.
     * @param newLog representing the Information that will be written on the file.
     * @return A Boolean, indicating if the process was correct.
     */
    public Boolean saveAuthors(String newLog){
        String basicPath = "data/Authors";
        File authorsFolder = new File(basicPath);
        if(!authorsFolder.exists()) authorsFolder.mkdir();
        File[] authorsLogs = authorsFolder.listFiles();
        String identifier = String.format("%04d", authorsLogs.length);
        FileWriter file = null;
        try {
            file = new FileWriter(basicPath + File.separator + identifier + "Authors.json");
        } catch (IOException e) {
            return false;
        }
        PrintWriter documentWr = new PrintWriter(file);
        documentWr.print(newLog);
        documentWr.close();
        return true;
    }

    /**
     * @brief Recovers/Reads System from the physic File.
     * @return A String containing all Authors System that must be recovered by the Domain Layer.
     */
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
            return result;
        } catch (IOException ex){
            return result;
        }
        return result;
    }
}
