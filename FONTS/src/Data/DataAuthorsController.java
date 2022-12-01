package FONTS.src.Data;

import java.io.*;

public class DataAuthorsController {
    public void saveAuthors(String docs) throws IOException {
        /*
        String basicPath = "data/Folders/rootFolder";
        File folder = new File(basicPath);
        if(!folder.exists()) folder.mkdir();
        System.out.println(folder.isDirectory() + " " + folder.exists());
        File[] folders = folder.listFiles();
        System.out.println("OK");
        String identifier = String.format("%04d", folders.length);
        FileWriter file = new FileWriter(basicPath + File.separator + identifier + "rootFolder.json");
        PrintWriter documentWr = new PrintWriter(file);
        documentWr.print(docs);
        documentWr.close();
        */
    }

    public String getAuthorsSerialized() {
        /*String basicPath = "data/Folders/rootFolder";
        File folder = new File(basicPath);
        if(!folder.exists()) folder.mkdir();
        File[] folders = folder.listFiles();
        String identifier = String.format("%04d", folders.length-1);
        FileReader reader = null;
        String result = "";
        try {
            reader = new FileReader(basicPath + File.separator + identifier + "rootFolder.json");
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
        return result;*/
        return null;
    }
}
