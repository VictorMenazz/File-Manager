package FONTS.src.Data;

import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.util.ArrayList;

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
    public DataController() {
        ctrlAuthors = new DataAuthorsController();
        ctrlFolder = new DataFoldersController();
        ctrlBoolean = new DataBooleanExpressionController();
    }

    public void saveFolders(String folders) throws IOException {
        ctrlFolder.saveFolder(folders);
    }

    //It returns String pointing to domain.
    public String restoreFolders(){
        return ctrlFolder.getFoldersSerialized();
    }

    public JsonReader[] getListOfAuthors(){ return null;}

    public Boolean saveHistorial(String jsonHistorial){
        return ctrlBoolean.saveHistorial(jsonHistorial);
    }

}
