package FONTS.src.Data.Drivers;

import FONTS.src.Data.DataController;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DataControllerDriver {

    private static Scanner writer = new Scanner(System.in).useDelimiter(Pattern.compile("[\\r\\n;]+"));

    public static void testSaveDocuments() throws IOException {
        DataController dataC = new DataController();
        System.out.println("Introduce JSON: ");
        String JSON = readInputString();
        dataC.saveFolders(JSON);
        File physicDataFolder = new File("data/Folders/rootFolder");
        File[] files = physicDataFolder.listFiles();
        System.out.println("Folder length: " + files.length);
    }

    public static void main(String[] args) throws Exception {
        testSaveDocuments();
    }

    private static String readInputString() {
        String inp = writer.next();
        return inp;
    }
}
