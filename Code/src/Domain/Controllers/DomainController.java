
package Code.src.Domain.Controllers;
import Code.src.Data.DataController;
import Code.src.Domain.Classes.*;

import java.io.IOException;
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

    /**
     * @brief Instance of the search controller
     */
    private SearchController ctrlSearch;

    /**
     * @brief Instance of the Authors controller
     */
    private AuthorsController ctrlAuthors;


    public DomainController() {
        data = DataController.getInstance();
        /*  Possibly initialize the FolderController?
            We've to check if the Data Layer has elements, passing them by parameter at the
            initialization or creating an empty Folder otherwise. (1st Run)
            We supose now that it is empty as we don't have Persistance/Data Layer.
        */
        //FALTA COGER INSTANCIA DE ROOT SI EXISTE
        if(data == null) {
            folders = new FoldersController(null);
            ctrlSearch = new SearchController(null);
            ctrlAuthors = new AuthorsController();
        }
        else { //AVERIGUAR FORMA DE COGER CARPETA ROOT
            folders = new FoldersController(data.getRootFolder());
            ctrlSearch = new SearchController(data.getRootFolder());
            ctrlAuthors = new AuthorsController();
        }
    }

    /**
     * @brief Import a document from outside our platform
     * @param path directory of the file to import
     */
    //Crear excepcion
    public void importDocument(String path) {
        //Interface y data?
        //Lectures a la capa de Data millor no?
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
    public void newDocument(String authorName, String title, String text, String lang) throws IOException {
        folders.newDocument(authorName, title, text, lang);
        ctrlAuthors.addTitleAuthor(authorName, title);
    }

    /**
     * @brief Modify
     * @param authorName if flag = 0, contains the new authorName
     * @param title if flag = 1, contains the new title
     * @param text if flag = 2, contains the new text
     * @param newData, needed to replace the attribute.
     * @param flag if it's 0,
     */
    public void modifyDocument(String authorName, String title, String text, String newData, int flag) throws IOException {
        /** Switch that differs the many possibilities to modify a Document. **/
        switch (flag) {
            case 0:
                folders.modifyAuthor(authorName, title, newData);
                ctrlAuthors.delTitleAuthor(authorName, title);
                ctrlAuthors.addTitleAuthor(newData, title);
                break;
            case 1:
                folders.modifyTitle(authorName, title, newData);
                ctrlAuthors.delTitleAuthor(authorName, title);
                ctrlAuthors.addTitleAuthor(authorName, newData);
                break;
            case 2:
                folders.modifyContent(authorName, title, newData);
                break;
        }
    }

    /**
     * @brief Returns a list of documents titles that its author matches a given name
     * @param authorName, References to the name of the Author
     * @return Arraylist of a documents titles
     */
    public ArrayList<String> authorDocuments(String authorName) {
        return ctrlAuthors.searchAuthorDocuments(authorName);
    }

    /**
     * @brief Returns a list of names of Authors that begins with a given prefix
     * @param prefix, References to the prefix
     * @return List of Authors names that begins with "prefix"
     */
    public ArrayList<String> searchAuthors(String prefix) {
        return ctrlAuthors.searchAuthorsPrefix(prefix);
    }

    /**
     *
     * @param authorName
     * @param title
     * @return
     */
    public Document getDocument(String authorName, String title) {
        folders.getDocument(authorName, title);
        return null;
    }
    /*
        We don't have to return Domain objects to the upper layers, so
        maybe implementation would be better if:

        public ArrayList<String> getDocument(String authorName, String title){
            folders.getContent(authorName, Title);
        }
    */

    /*
    public ArrayList<Document> o Path inicial? INITIALQUERY()
    My recommendation  is:

    public ArrayList<String> initialQueryAuthors(){
    }

    public ArrayList<String> initialQueryTitle(){
    }

    It would return an ArrayList of n elements with the i elements of the first vector related with the i
    element of the other vector.
     */

    /**
     *
     * @param authorName
     * @param title
     * @param k
     * @return
     */
    //Maybe: The i element related in the three ArrayLists.
    // public ArrayList<String> appearenceSearchTitles(String authorName, String title, int k);
    // public ArrayList<String> appearenceSearchAuthors(String authorName, String title, int k);
    // public ArrayList<String> appearenceSearchContent(String authorName, String title, int k);
    public ArrayList<Document> appearanceSearch(String authorName, String title, int k) {
        Folder rootFolder = folders.getRoot();
        return ctrlSearch.appearanceSearch(rootFolder, authorName, title, k);
    }

    /**
     *
     * @return
     */
    public ArrayList<Document> booleanExpressionSearch(String boolExp) {
        Folder rootFolder = folders.getRoot();
        return ctrlSearch.booleanExpressionSearch(rootFolder, boolExp);
    }

    /**
     *
     * @param pWords
     * @param k
     * @return
     */
    public ArrayList<Document> documentsQuery(String pWords, int k) {
        Folder rootFolder = folders.getRoot();
        return ctrlSearch.searchDocuments(rootFolder, pWords, k);
    }

    /** Saves a Document with changes in the Data Layer.
     * @param authorName, Represents the Author of the Docuemnt.
     * @param title, Represents the Title of the Document.
     */
    public void saveDocument(String authorName, String title) {
        //Communication with Data Layer.
        //ArrayList<String> Cont = folders.getContent(authorName, title);
        //data.saveDocument();
    }

    /**
     *
     * @param authorName
     * @param title
     */
    //crear excepcion
    public void deleteDocument(String authorName, String title) {
        //comunicacion con capa data
        // FALTA ELIMINAR EL FITXER

        folders.deleteDocument(authorName, title);
        // if Author runs out of documents, it's deleted
        ctrlAuthors.delTitleAuthor(authorName, title);
    }

    /**
     * @brief Protects a Document with the password.
     * @param authorName, Represents the author of the Document.
     * @param title, Represents the title of the Document.
     * @param password, Represents the password of the Document.
     */
    public void protectDocument(String authorName, String title, String password) {
        folders.protectDocument(authorName,  title, password);
    }

    /**
     * EXIT???
     */

    /**
     * @brief Creates a new Folder.
     * @param fName, Representing the name of the new Folder.
     * @param foldId, Representing the folderId Parent of the allocation.
     */
    public void newFolder(String fName, int foldId) {
        folders.newFolder(fName, foldId);

    }
}