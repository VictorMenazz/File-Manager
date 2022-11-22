package FONTS.src.Data;

import com.google.gson.stream.JsonReader;

public class DataController {
    /**
     * dataController is the instance of DataController
     */
    private static DataController dataController;

    /**
     * @brief Get instance of DataController, if doesn't exist create one
     *
     * @return instance of DataController
     */
    public static DataController getInstance() {
        if(dataController == null) {
            dataController = new DataController();
        }
        return dataController;
    }
    public DataController() {
        AuthorsController ctrlAuthors = new AuthorsController();
        FoldersController ctrlFolder = new FoldersController();
        BooleanExpressionController ctrlBoolean = new BooleanExpressionController();
    }

    public JsonReader[] getListOfDocuments(){ return null;}

    public JsonReader[] getListOfAuthors(){ return null;}

    public JsonReader[] getListOfFolders(){ return null;}

    public String getRootFolder() {
        return null; //MIRAR COMO SOLUCIONAR EST0, para marc: HOW TO REPAIR THIS FUNCTIONALITY? IT'S NOT POSSIBLE TO SEND A SPECIFIC TYPE
    }
}
