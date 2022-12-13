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
     * @brief Operación de guardar historial expresiones booleanas
     * booleanExpressions, string formato JSON que incluye
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


    /***
     * @brief Retorna el último historial guardado en formato string
     */
    public String loadHistorial() {
        File file = new File(path);
        return file.toString();
    }

}
