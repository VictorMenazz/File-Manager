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
     * Get instance of DataController, if doesn't exist create one
     * @return instance of DataController
     */
    public static DataController getInstance() {
        if(dataController == null) {
            dataController = new DataController();
        }
        return dataController;
    }

    /**
     * Initializes the Data Controller.
     */
    public DataController() {
        ctrlAuthors = new DataAuthorsController();
        ctrlFolder = new DataFoldersController();
        ctrlBoolean = new DataBooleanExpressionController();
    }

    /**
     * Saves physically the serialized Folder System in JSON format.
     */
    public Boolean saveFolders(String folders){
        return ctrlFolder.saveFolder(folders);
    }

    /**
     * Transfers to DomainController the last log about Folders System.
     * @return A String representing the Folders System in JSON.
     */
    public String restoreFolders(){
        return ctrlFolder.getFoldersSerialized();
    }

    /**
     * Saves physically the serialized Authors System in JSON format.
     */
    public Boolean saveAuthors(String authorsJSON) {
        return ctrlAuthors.saveAuthors(authorsJSON);
    }

    /**
     * Transfers to DomainController the last log about Authors System.
     * @return A String representing the Authors System in JSON.
     */
    public String restoreAuthors() { return ctrlAuthors.getAuthorsSerialized(); }

    /**
     * Saves the Boolean Expression System.
     * @param jsonHistorial identifying the JSON Object.
     * @return A Boolean indicating if saving is correct.
     */
    public Boolean saveHistorial(String jsonHistorial){
        return ctrlBoolean.saveHistorial(jsonHistorial);
    }

    /**
     * Recovers the Boolean Expression System from the System last log.
     * @return A String containing the JSON that Domain Layer will recover.
     * @throws FileNotFoundException
     */
    public String loadHistorial() throws FileNotFoundException {
        return ctrlBoolean.loadHistorial();
    }

    /**
     * Reads a Document physic File and loads it in Domain.
     * @param path, Representing the physic path to read.
     */
    public ArrayList<String> importDocument(String path, String docT) {
        return ctrlFolder.importDocument(path, docT);
    }

    /**
     * Writes a Document onto a physic File out of the System.
     * @param path, Representing the physic path to write the Doc.
     * @param Content, Representing the Content to write onto the new physic File.
     */
    public Boolean exportDocument(String path, String author, String title, String Content, String docT){
        return ctrlFolder.exportDocument(path, author, title, Content, docT);
    }

}
