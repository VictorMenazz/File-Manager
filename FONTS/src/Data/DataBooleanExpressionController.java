package FONTS.src.Data;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class DataBooleanExpressionController {

    static private String path = "data/BooleanExpression/historial.json";

    /**
     * @brief Operation to save the historial of booleanExpressions
     * @param booleanExpressions, string that includes the JSON of the boolean expression list.
     * @return A Boolean, that indicates if the process was correct.
     */
    public Boolean saveHistorial(String booleanExpressions) {
        File file = new File("data/BooleanExpression/");
        if (!file.exists()) file.mkdir();

        try {
            File historial = new File(path);
            FileWriter fileWriter = new FileWriter(path);

            if (!historial.exists()) historial.createNewFile(); //Primera vez que se guarda el historial
            else fileWriter.write("");

            fileWriter.write(booleanExpressions);
            fileWriter.close();
            return true;

        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    /**
     * @brief Returns the last historial saved, in JSON.
     */
    public String loadHistorial() throws FileNotFoundException{
        try {
            File historial = new File(path);
            Scanner scanner = new Scanner(historial);
            scanner.useDelimiter("\\Z");
            return scanner.next();
        } catch(FileNotFoundException e){
            return "";
        }
    }

}
