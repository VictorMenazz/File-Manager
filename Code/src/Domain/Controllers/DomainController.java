package Code.src.Domain.Controllers;
import Code.src.Data.DataController;
import Code.src.Domain.Classes.*;
import java.util.*;

public class DomainController {

    /**
     * @brief Persistence controller instance
     */
    private DataController data;

    /**
     * @brief Instance of the folder controller
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
     * @brief Import a document from outside our platform
     * @param path directory of the file to import
     */
    //Crear excepcion
    public void importDocument(String path) {
        //Interface y data?
    }

    /**
     * @brief Export a document from our platform to outside
     * @param authorName, name of the author
     * @param title, title of the document
     * @return Document to export
     */
    public Document exportDocument(String authorName, String title) {
        //Data e Interface??
        return null;
    }

    //crear excepcion

    /**
     * @brief Creates a new document in the system
     * @param authorName name of the author of the document
     * @param title document title
     * @param text document content
     * @param lang document language
     */
    public void newDocument(String authorName, String title, String text, String lang) {

    }

    /**
     * @brief Modify
     * @param authorName if flag = 0, contains the new authorName
     * @param title if flag = 1, contains the new title
     * @param text if flag = 2, contains the new text
     * @param flag if it's 0,
     */
    public void modifyDocument(String authorName, String title, String text, int flag) {
        //usar switch
        switch (flag) {
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;
        }
    }

    /**
     * @brief Returns a list of documents that its author matches a given name
     * @param authorName
     * @return Arraylist of a Documents
     */
    public ArrayList<Document> authorDocuments(String authorName) {

        return  null;
    }

    /**
     * @brief Returns a list of names of Authors that begins with a given prefix
     * @param prefix
     * @return
     */
    public ArrayList<String> searchAuthors(String prefix) {

        return null;
    }

    /**
     *
     * @param authorName
     * @param title
     * @return
     */
    public Document getDocument(String authorName, String title) {

        return null;
    }

    /*
    public ArrayList<Document> o Path inicial? INITIALQUERY()
     */

    /**
     *
     * @param authorName
     * @param title
     * @param k
     * @return
     */
    public ArrayList<Document> appearanceSearch(String authorName, String title, int k) {

        return null;
    }

    /**
     *
     * @return
     */
    public ArrayList<Document> booleanExpressionSearch(/*String boolExp????*/) {

        return null;
    }

    /**
     *
     * @param pWords
     * @param k
     * @return
     */
    public ArrayList<Document> documentsQuery(String pWords, int k) {

        return null;
    }

    /**
     *
     * @param authorName
     * @param title
     */
    public void saveDocument(String authorName, String title) {
        //comunicacion con capa data
    }

    /**
     *
     * @param authorName
     * @param title
     */
    //crear excepcion
    public void deleteDocument(String authorName, String title) {
        //comunicacion con capa data
    }

    /**
     * @brief
     * @param authorName
     * @param title
     * @param password
     */
    public void protectDocument(String authorName, String title, String password) {

    }

    /**
     * EXIT???
     */

    /**
     * @brief Creates a new Folder
     * @param fName
     */
    public void newFolder(String fName) {

    }
}