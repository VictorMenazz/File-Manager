
package FONTS.src.Domain.Controllers;
import FONTS.src.Data.DataController;
import FONTS.src.DocumentsException;
import FONTS.src.Domain.Classes.*;
import FONTS.src.Domain.Controllers.Drivers.FoldersControllerDriver;
import com.google.gson.Gson;

import javax.print.Doc;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * @file DomainController.java
 * Class <em>Domain Controller</em>
 */

/**
 * Controller of the layer Domain
 *
 * @author Marc Navarro Acosta
 * @author Victor Mena Doz
 * @author Jordi Soley Masats
 * @author Julia Alice Amenos Dien
 */
public class DomainController {

    /**
     * Persistence controller instance
     */
    private DataController data;

    /**
     * Instance of the folder controller
     */
    private FoldersController folders;

    /**
     * Instance of the search controller
     */
    private SearchController ctrlSearch;

    /**
     * Instance of the Authors controller
     */
    private AuthorsController ctrlAuthors;

    /**
     * Default creation of DomainController
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
     * Auxiliary creation of DomainController
     * @param rootFolder, References instance of root folder
     */
    public DomainController(Folder rootFolder) {
        folders = new FoldersController(rootFolder);
        ctrlSearch = new SearchController();
        ctrlAuthors = new AuthorsController();
    }

    public Boolean existsExpression(String boolExpr){
        return ctrlSearch.existsExpression(boolExpr);
    }

    public String addExpression(String boolExpr) {
        return ctrlSearch.addExpression(boolExpr);

    }

    public String[] getBoolExpr() {
        return ctrlSearch.getBoolExps();
    }

    public String modifyExpression(String oldExpr, String newExpr) {
        return ctrlSearch.modifyExpression(oldExpr, newExpr);
    }

    public Boolean deleteExpression(String boolExpr){
        return ctrlSearch.deleteExpression(boolExpr);
    }

    public ArrayList<String> getAuthorsName() {
        return ctrlAuthors.getAuthorsName();
    }

    /**
     * Creates a new document in the system
     * @param authorName name of the author of the document
     * @param title document title
     * @param text document content
     * @param lang document language
     * @throws IOException
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
     * Modify specific document
     * @param authorName if flag = 0, contains the new authorName
     * @param title if flag = 1, contains the new title
     * @param newData, needed to replace the attribute.
     * @param flag if it's 0,
     * @throws IOException
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
     * Returns a list of documents titles that its author matches a given name
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
     * Returns a list of names of Authors that begins with a given prefix
     * @param prefix, References to the prefix
     * @return List of Authors names that begins with "prefix"
     */
    public ArrayList<String> searchAuthors(String prefix) {
        return ctrlAuthors.searchAuthorsPrefix(prefix);
    }

    /**
     * Get information of the selected document
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

    /**
     * Gives all the authors of the Document Folder.
     * @param folderIdentifier, Representing the identifier of the Folder.
     * @return An ArrayList<String> containing all the Documents Authors.
     */
    public ArrayList<String> getDocumentAuthors(int folderIdentifier){
        return folders.getDocumentAuthors(folderIdentifier);
    }

    /**
     * Gives all the titles of the Document Folder.
     * @param folderIdentifier, Representing the identifier of the Folder.
     * @return An ArrayList<String> containing all the Documents Titles.
     */
    public ArrayList<String> getDocumentTitles(int folderIdentifier){
        return folders.getDocumentTitles(folderIdentifier);
    }

    /**
     * Gives all the subFolders of the Document Folder.
     * @param folderIdentifier, Representing the identifier of the Folder.
     * @return An ArrayList<String> containing all the identifiers of the SubFolders.
     */
    public HashMap<Integer, String> getSubFolders(int folderIdentifier){
        return folders.getSubFolders(folderIdentifier);
    }

    //It would return an ArrayList of n elements with the i elements of the first vector related with the i
    //element of the other vector.


    /**
     * Search of k documents that appears to specific document
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
     * Search of a list of Documents who satisfy the boolean expression
     * @param boolExp, instance of the boolean expression to use
     * @return list of documents that satisfy boolean expression
     * @throws Exception
     */
    public HashMap<String, String> booleanExpressionSearch(String boolExp) throws Exception {
        Folder rootFolder = folders.getRoot();
        return ctrlSearch.booleanExpressionSearch(rootFolder, boolExp);
    }

    /**
     * Search of k documents more relevant for a list of specific words
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

    /**
     * Delete specific document of the system
     * @param authorName, Represents the Author of the Document.
     * @param title, Represents the Title of the Document.
     */
    //crear excepcion
    public void deleteDocument(String authorName, String title) {
        try {
            folders.deleteDocument(authorName, title);
            // if Author runs out of documents, it's deleted

            ctrlAuthors.delTitleAuthor(authorName, title);
        }
        catch (DocumentsException e) {
            e.printStackTrace();
        }

    }

    public void deleteFolder(Integer foldId){
        folders.deleteFolder(foldId);
    }

    /**
     * Protects a Document with the password.
     * @param authorName, Represents the author of the Document.
     * @param title, Represents the title of the Document.
     * @param password, Represents the password of the Document.
     */
    public void protectDocument(String authorName, String title, String password) {
        folders.protectDocument(authorName,  title, password);
    }

    /**
     * Creates a new Folder.
     * @param fName, Representing the name of the new Folder.
     * @param foldId, Representing the folderId Parent of the allocation.
     */
    public void newFolder(String fName, int foldId) {
        folders.newFolder(fName, foldId);
    }

    /** COMUNICATION WITH DATA LAYER **/

    /**
     * Restores the Folders System once the Main System is running.
     * @return A Boolean indicating if the process is correct.
     */
    public Boolean reconstructFoldersSystem(){
        String JSON = data.restoreFolders();
        if(!JSON.equals("")){
            folders.recoverFoldersStructure(JSON);
            return true;
        }
        else return false;
    }

    /**
     * Saves the Folders System before the Main System is closed.
     * @return A Boolean that indicates if the process was correct or not.
     */
    public Boolean saveFoldersSystem(){
        String foldersJSON = folders.saveFoldersStructure();
        return data.saveFolders(foldersJSON);
    }

    /**
     * Restores the Authors System once the Main System is running.
     * @return A Boolean, indicating if the process is correct.
     */
    public Boolean reconstructAuthorsSystem(){
        String JSON = data.restoreAuthors();
        if(!JSON.equals("")){
            ctrlAuthors.recoverFoldersStructure(JSON);
            return true;
        }
        else return false;
    }

    /**
     * Saves the Authors System before the Main System is closed.
     * @return A Boolean, indicating if the process is correct.
     */
    public Boolean saveAuthorsSystem() {
        String authorsJSON = ctrlAuthors.saveAuthorsStructure();
        return data.saveAuthors(authorsJSON);
    }

    /**
     * Saves the Boolean Expression System before the Main System is closed.
     * @return A Boolean expressing the State of the process.
     */
    public Boolean saveHistorial() {
        LinkedHashSet<String> historial = ctrlSearch.getList();

        Gson gson = new Gson();
        String result = gson.toJson(historial);
        return data.saveHistorial(result);
    }

    /**
     * Recovers the Boolean Expression System before the Main System is closed.
     * @return A Boolean, indicating if the process is correct.
     */
    public Boolean loadHistorial() {
        Gson gson = new Gson();
        String list = null;
        Boolean res = true;
        try {
            list = data.loadHistorial();
        } catch (FileNotFoundException e) {
            res = false;
        }
        LinkedHashSet<String> result = gson.fromJson(list, LinkedHashSet.class);
        ctrlSearch.setListBoolExps(result);
        return res;
    }

    /**
     * Reads a Document physic File and loads it in Domain.
     * @param path, Representing the physic path to read.
     * @param foldId, Representing the identifier of the Folder.
     * @param lang, Representing the language of Document.
     * @return The result of the process (false, process failed, true process correct).
     */
    public Boolean importDocument(String path, Integer foldId, String lang, String docType){
        ArrayList<String> doc = data.importDocument(path, docType);
        if(doc.size() == 0) return false;
        else if(docType.equals("json")){
            folders.restoreJSON(doc.get(0), foldId);
            return true;
        }
        else if (docType.equals("xml") || docType.equals("txt")){
            try {
                folders.newDocument(doc.get(0), doc.get(1), doc.get(2), lang);
            } catch (IOException e) {
                return false;
            }
            return true;
        }
        else return false;
    }

    /**
     * Writes a Document onto a physic File out of the System.
     * @param title, Representing the title of the Document.
     * @param author, Representing the author of the Document.
     * @param path, Representing the physic path to write the Doc.
     * @return The result of the process (false, process failed, true process correct).
     */
    public Boolean exportDocument(String title, String author, String path, String docT){
        if(docT.equals("json")){
            String docJSON = folders.getJSON(title, author);
            return data.exportDocument(path, "null", title, docJSON, docT);
        }
        else{
            ArrayList<String> document = folders.getDocument(author, title);
            return data.exportDocument(path, title, author, document.get(2), docT);
        }
    }
}