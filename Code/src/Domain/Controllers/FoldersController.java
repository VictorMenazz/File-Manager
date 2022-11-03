package Code.src.Domain.Controllers;

import Code.src.Domain.Classes.Content;
import Code.src.Domain.Classes.Document;
import Code.src.Domain.Classes.Folder;

import java.io.IOException;
import java.util.ArrayList;

public class FoldersController {
    /**
     * @brief Instancia de la carpeta origen
     */
    private Folder rootFolder;

    /**
     * @brief Instance of SearchController
     */
    private SearchController ctrlSearch;

    public FoldersController(){
        rootFolder = new Folder(0, "/");
        ctrlSearch = new SearchController();
    }

    /* Initialitzation for the n time we run the program, passing the Data layer elements by parameter.
    public FoldersController( sth DataInfo){

    }*/

    public void newDocument(String authorName, String title, String text, String lang) throws IOException {
        rootFolder.addNonConstructedDocument(authorName, title, text, lang);
    }
    public void modifyContent(String authorName, String title, String text) throws IOException {
        rootFolder.modifyContent(authorName,title,text);
    }

    public void modifyAuthor(String authorName, String title, String newAuthor) {
        rootFolder.modifyAuthor(authorName, title, newAuthor);
    }
    public void modifyTitle(String authorName, String title, String newTitle) {
        rootFolder.modifyTitle(authorName, title, newTitle);
    }

    public ArrayList<Document> authorDocuments(String authorName) {

        return  null;
    }

    public ArrayList<String> searchAuthors(String prefix) {

        return null;
    }

    public Document getDocument(String authorName, String title) {
        return rootFolder.getDocument(authorName, title);
    }

    public ArrayList<Document> appearanceSearch(String authorName, String title, int k) {

        return null;
    }

    public ArrayList<Document> booleanExpressionSearch(/*String boolExp????*/) {

        return null;
    }

    public ArrayList<Document> documentsQuery(String pWords, int k) {

        return null;
    }

    public void protectDocument(String authorName, String title, String password) {
        rootFolder.protectDocument(authorName, title, password);
    }

    public void newFolder(String fName, Integer foldId) {
        rootFolder.createFolder(fName, foldId);
    }
}
