package FONTS.src.Data;

import FONTS.src.Domain.Classes.Folder;

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

    }
    public Folder getRootFolder() {
        return null; //MIRAR COMO SOLUCIONAR EST0, para marc: HOW TO REPAIR THIS FUNCTIONALITY? IT'S NOT POSSIBLE TO SEND A SPECIFIC TYPE
    }
}
