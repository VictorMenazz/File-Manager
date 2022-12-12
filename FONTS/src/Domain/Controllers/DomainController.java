
package FONTS.src.Domain.Controllers;
import FONTS.src.Data.DataController;
import FONTS.src.DocumentsException;
import FONTS.src.Domain.Classes.*;
import FONTS.src.Domain.Controllers.Drivers.FoldersControllerDriver;
import com.google.gson.Gson;

import javax.print.Doc;
import java.io.IOException;
import java.util.*;

/**
 * @file DomainController.java
 * @brief Class <em>Domain Controller</em>
 */

/**
 * @brief Controller of the layer Domain
 *
 * @author Marc Navarro Acosta
 * @author Victor Mena Doz
 * @author Jordi Soley Masats
 * @author Julia Alice Amenos Dien
 */
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

    /**
     * @brief Default creation of DomainController
     */
    public DomainController() {
        //data = DataController.getInstance();
        /*  Possibly initialize the FolderController?
            We've to check if the Data Layer has elements, passing them by parameter at the
            initialization or creating an empty Folder otherwise. (1st Run)
            We supose now that it is empty as we don't have Persistance/Data Layer.
        */
        //FALTA COGER INSTANCIA DE ROOT SI EXISTE
        //if(data == null) {
            folders = new FoldersController();
            System.out.println("Creation correct " + folders.getRoot().getDocumentAmount());
            data = new DataController();
            ctrlSearch = new SearchController();
            ctrlAuthors = new AuthorsController();
        /*}
        else { //AVERIGUAR FORMA DE COGER CARPETA ROOT
            String JSON = data.getRootFolder();
            folders = reconstructFoldersSystem(JSON);;
            ctrlSearch = new SearchController();
            ctrlAuthors = new AuthorsController();
        }*/
    }

    /**
     * @brief Auxiliary creation of DomainController
     * @param rootFolder, References instance of root folder
     */
    public DomainController(Folder rootFolder) {
        folders = new FoldersController(rootFolder);
        ctrlSearch = new SearchController();
        ctrlAuthors = new AuthorsController();
    }

    /**
     * @brief Import a document from outside our platform
     * @param path directory of the file to import
     */
    //Create excepyion
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
        //Data and Interface??
        return null;
    }

    //create exception

    /**
     * @brief Creates a new document in the system
     * @param authorName name of the author of the document
     * @param title document title
     * @param text document content
     * @param lang document language
     */
    public void newDocument(String authorName, String title, String text, String lang) throws IOException {
        try {
            folders.newDocument(authorName, title, text, lang);
            ctrlAuthors.addTitleAuthor(authorName, title);
        }
        catch (DocumentsException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Modify specific document
     * @param authorName if flag = 0, contains the new authorName
     * @param title if flag = 1, contains the new title
     * @param newData, needed to replace the attribute.
     * @param flag if it's 0,
     */
    public void modifyDocument(String authorName, String title, String newData, int flag) throws IOException {
        try {
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
        catch (DocumentsException e) {
            e.printStackTrace();
        }

    }

    /**
     * @brief Returns a list of documents titles that its author matches a given name
     * @param authorName, References to the name of the Author
     * @return Arraylist of a documents titles
     */
    public ArrayList<String> authorDocuments(String authorName) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            result = ctrlAuthors.searchAuthorDocuments(authorName);
            return result;
        }
        catch (DocumentsException e) { e.printStackTrace(); }
        return result;
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
     * @brief Get information of the selected document
     * @param authorName, References author's name of the document
     * @param title, References title of the document
     * @return list that contains required information:
     *          - First element of the array = title;
     *          - Second element of the array = author;
     *          - Third element of the array = text;
     */
    public ArrayList<String> getDocument(String authorName, String title) {
        return folders.getDocument(authorName, title);
    }
    /*
        We don't have to return Domain objects to the upper layers, so
        maybe implementation would be better if:

        public ArrayList<String> getDocument(String authorName, String title){
            folders.getContent(authorName, Title);
        }
    */


    //public ArrayList<Document> o Path inicial? INITIALQUERY()
    //My recommendation  is:

    public ArrayList<String> getDocumentAuthors(int folderIdentifier){
        return null;
    }

    public ArrayList<String> getDocumentTitles(int folderIdentifier){
        return null;
    }

    public HashMap<Integer, String> getSubFolders(int folderIdentifier){
        return null;
    }

    //It would return an ArrayList of n elements with the i elements of the first vector related with the i
    //element of the other vector.


    /**
     * @brief Search of k documents that appears to specific document
     * @param authorName, References author's name of the specific document
     * @param title, title of the specific document
     * @param k, integer of how many appearance documents to return
     * @return list of k documents listed with key
     */
    public HashMap<String, String> appearanceSearch(String authorName, String title, int k) {
        Folder rootFolder = folders.getRoot();
        return ctrlSearch.appearanceSearch(rootFolder, authorName, title, k);
    }

    /**
     * @brief Search of a list of Documents who satisfy the boolean expression
     * @param boolExp, instance of the boolean expression to use
     * @return list of documents that satisfy boolean expression
     * @throws Exception
     */
    public HashMap<String, String> booleanExpressionSearch(String boolExp) throws Exception {
        Folder rootFolder = folders.getRoot();
        return ctrlSearch.booleanExpressionSearch(rootFolder, boolExp);
    }

    /**
     * @brief Search of k documents more relevant for a list of specific words
     * @param pWords, list of specific words
     * @param language, language of the search
     * @param k, integer of how many relevant documents to return
     * @return  list of k documents listed with key
     * @throws IOException
     */
    public HashMap<String, String> documentsQuery(String pWords, String language, int k) throws IOException {
        Folder rootFolder = folders.getRoot();
        return ctrlSearch.searchDocuments(rootFolder, pWords, language, k);
    }

    /** Saves a Document with changes in the Data Layer.
     * @param authorName, Represents the Author of the Document.
     * @param title, Represents the Title of the Document.
     */
    public void saveDocument(String authorName, String title) {
        //Communication with Data Layer.
        //ArrayList<String> Cont = folders.getContent(authorName, title);
        //data.saveDocument();
    }

    /**
     * @brief Delete specific document of the system
     * @param authorName, Represents the Author of the Document.
     * @param title, Represents the Title of the Document.
     */
    //crear excepcion
    public void deleteDocument(String authorName, String title) {
        //comunicacion con capa data
        // FALTA ELIMINAR EL FITXER

        try {
            folders.deleteDocument(authorName, title);
            // if Author runs out of documents, it's deleted

            ctrlAuthors.delTitleAuthor(authorName, title);
        }
        catch (DocumentsException e) {
            e.printStackTrace();
        }

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


    /** COMUNICATION WITH DATA LAYER **/
    /**
     * @brief Restores the Folders System once the Main System is running.
     */
    public void reconstructFoldersSystem(){
        String JSON = data.restoreFolders();
        folders.recoverFoldersStructure(JSON);
    }

    /**
     * @brief Saves the Folders System before the Main System is closed.
     */
    public void saveFoldersSystem() throws IOException {
        String foldersJSON = folders.saveFoldersStructure();
        data.saveFolders(foldersJSON);
    }

    /**
     * @brief Restores the Authors System once the Main System is running.
     */
    public void reconstructAuthorsSystem(){
        String JSON = data.restoreAuthors();
        ctrlAuthors.recoverFoldersStructure(JSON);
    }

    /**
     * @brief Saves the Authors System before the Main System is closed.
     */
    public void saveAuthorsSystem() throws IOException {
        String authorsJSON = ctrlAuthors.saveAuthorsStructure();
        data.saveAuthors(authorsJSON);
    }

    public Boolean saveHistorial() {
        LinkedHashSet<String> historial = ctrlSearch.getBoolExps();
        Gson gson = new Gson();
        String result = gson.toJson(historial);
        return data.saveHistorial(result);
    }

}