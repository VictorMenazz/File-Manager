package FONTS.src.Data.Drivers;

import FONTS.src.Data.DataController;
import FONTS.src.Domain.Controllers.FoldersController;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashSet;
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

    public static void testRecoverFolders(){
        DataController dC = new DataController();
        dC.restoreFolders();
    }

    public static void testSaveAuthors() throws IOException {
        DataController dC = new DataController();
        System.out.println("Introduce JSON:");
        String JSON = readInputString();
        dC.saveAuthors(JSON);
    }

    public static void testRecoverAuthors(){
        DataController dC = new DataController();
        System.out.println(dC.restoreAuthors());
    }

    public static Boolean testSaveHistoral() {
        DataController dataC = new DataController();
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduce a 3 Boolean Expressions:");
        LinkedHashSet<String> historial = new LinkedHashSet<>();
        for (int i = 0; i < 3; ++i){
            String aux = scan.nextLine();
            historial.add(aux);
        }
        scan.close();

        Gson gson = new Gson();
        String result = gson.toJson(historial);
        return dataC.saveHistorial(result);
    }

    public static void main(String[] args) throws Exception {
        //testRecoverAuthors();
        //testSaveAuthors();
        //testRecoverFolders();
        //testSaveDocuments();
        //System.out.println(testSaveHistoral());
    }

    private static String readInputString() {
        String inp = writer.next();
        return inp;
    }
}
