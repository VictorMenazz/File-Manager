package FONTS.src.Data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DataFoldersController {

    public void saveFolder(String docs) throws IOException {
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
    }

    private String getTitle(String doc) {
        /** Need to parse the JSON and extract the name and author */
        return null;
    }

    public boolean existFolder(){
        return false;
    }

    public boolean existDocument(){
        return false;
    }

}
