package FONTS.src.Data;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

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

    public JsonReader[] getListOfAuthors(){ return null;}

    public Boolean saveHistorial(String jsonHistorial){
        return ctrlBoolean.saveHistorial(jsonHistorial);
    }

    public String loadHistorial(){
        return ctrlBoolean.loadHistorial();
    }
}
