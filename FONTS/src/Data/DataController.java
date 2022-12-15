package FONTS.src.Data;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class DataController {
    /**
     * dataController is the instance of DataController
     */
    private static DataController dataController;

    private static DataAuthorsController ctrlAuthors;

    private static DataFoldersController ctrlFolder;

    private static DataBooleanExpressionController ctrlBoolean;

    /**
     * @brief Get instance of DataController, if doesn't exist create one
     * @return instance of DataController
     */
    public static DataController getInstance() {
        if(dataController == null) {
            dataController = new DataController();
        }
        return dataController;
    }

    /**
     * @brief Initializes the Data Controller.
     */
    public DataController() {
        ctrlAuthors = new DataAuthorsController();
        ctrlFolder = new DataFoldersController();
        ctrlBoolean = new DataBooleanExpressionController();
    }

    /**
     * @brief Saves physically the serialized Folder System in JSON format.
     */
    public void saveFolders(String folders) throws IOException {
        ctrlFolder.saveFolder(folders);
    }

    /**
     * @brief Transfers to DomainController the last log about Folders System.
     * @return A String representing the Folders System in JSON.
     */
    public String restoreFolders(){
        return ctrlFolder.getFoldersSerialized();
    }

    /**
     * @brief Saves physically the serialized Authors System in JSON format.
     */
    public void saveAuthors(String authorsJSON) throws IOException { ctrlAuthors.saveAuthors(authorsJSON); }

    /**
     * @brief Transfers to DomainController the last log about Authors System.
     * @return A String representing the Authors System in JSON.
     */
    public String restoreAuthors() { return ctrlAuthors.getAuthorsSerialized(); }

    /**
     * @brief Saves the Boolean Expression System.
     * @param jsonHistorial identifying the JSON Object.
     * @return A Boolean indicating if saving is correct.
     */
    public Boolean saveHistorial(String jsonHistorial){
        return ctrlBoolean.saveHistorial(jsonHistorial);
    }

    /**
     * @brief Recovers the Boolean Expression System from the System last log.
     * @return A String containing the JSON that Domain Layer will recover.
     * @throws FileNotFoundException
     */
    public String loadHistorial() throws FileNotFoundException {
        return ctrlBoolean.loadHistorial();
    }
}
