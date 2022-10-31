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

    /**
     * @brief Importa un documento desde fuera de nuestra plataforma
     *
     * @param path directorio del archivo a importar
     */
    //Crear excepcion
    public void importDocument(String path) {

    }

    /**
     * @brief Exporta un documento de nuestra plataforma a fuera
     *
     * @param authorName, name of the author
     * @param title, title of the document
     * @return Document to export
     */
    public Document exportDocument(String authorName, String title) {

        return null;
    }

    //crear excepcion
    public void newDocument(String authorName, String title, String text) {

    }

    //crear excepcion
    public void deleteDocument(String authorName, String title) {

    }

    public void modifyDocument(String authorName, String title, String text) {

    }

    public ArrayList<Document> authorDocuments(String authorName) {

        return  null;
    }

    public ArrayList<String> searchAuthors(String prefix) {

        return null;
    }


}