package Code.src.Data;

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
}
