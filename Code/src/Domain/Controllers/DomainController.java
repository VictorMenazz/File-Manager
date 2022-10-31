package Code.src.Domain.Controllers;
import Code.src.Data.DataController;
import Code.src.Domain.Classes.*;
import java.util.*;

public class DomainController {

    /**
     * @brief Instancia del controlador persistencia
     */
    private DataController data;

    /**
     * @brief Instancia del controlador de folders
     */
    private FoldersController folders;


    public DomainController() {
        data = DataController.getInstance();
    }


}