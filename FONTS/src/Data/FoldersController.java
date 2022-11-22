package FONTS.src.Data;

import jdk.jshell.spi.ExecutionControl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class FoldersController {

    public void saveFolder(int n, String name, String[] docs) throws IOException {
        String idNumber = String.format("%04d", n);
        String basicPath = "data/Folders/"+n+name;
        File folder = new File(basicPath);
        File[] folders = folder.listFiles();
        if(existFolder()){
            /** do stufff */
        }
        else {
            for(String doc : docs){
                String title = getTitle(doc);
                FileWriter file = new FileWriter(basicPath + File.separator + title + ".json");
                PrintWriter documentWr = new PrintWriter(file);
                documentWr.print(doc);
                documentWr.close();
            }
        }
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
