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
        /*  Possibly initialize the FolderController?
            We've to check if the Data Layer has elements, passing them by parameter at the
            initialization or creating an empty Folder otherwise. (1st Run)
            We supose now that it is empty as we don't have Persistance/Data Layer.
        */
        folders = new FoldersController();
    }

    /**
     * @brief Importa un documento desde fuera de nuestra plataforma
     *
     * @param path directorio del archivo a importar
     */
    //Crear excepcion
    public void importDocument(String path) {
        //Interface y data?
    }

    /**
     * @brief Exporta un documento de nuestra plataforma a fuera
     *
     * @param authorName, name of the author
     * @param title, title of the document
     * @return Document to export
     */
    public Document exportDocument(String authorName, String title) {
        //Data e Interface??
        return null;
    }

    //crear excepcion
    public void newDocument(String authorName, String title, String text, String lang) {

    }

    public void modifyDocument(String authorName, String title, String text, int flag) {
        //usar switch
    }

    public ArrayList<Document> authorDocuments(String authorName) {

        return  null;
    }

    public ArrayList<String> searchAuthors(String prefix) {

        return null;
    }

    public Document getDocument(String authorName, String title) {

        return null;
    }

    /*
    public ArrayList<Document> o Path inicial? INITIALQUERY()
     */

    public ArrayList<Document> appearanceSearch(String authorName, String title, int k) {

        return null;
    }

    public ArrayList<Document> booleanExpressionSearch(/*String boolExp????*/) {

        return null;
    }

    public ArrayList<Document> documentsQuery(String pWords, int k) {

        return null;
    }

    public void saveDocument(String authorName, String title) {
        //comunicacion con capa data
    }

    //crear excepcion
    public void deleteDocument(String authorName, String title) {
        //comunicacion con capa data
    }

    public void protectDocument(String authorName, String title, String password) {

    }

    /**
     * EXIT???
     */

    public void newFolder(String fName) {

    }
}