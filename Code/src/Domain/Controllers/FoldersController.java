package Code.src.Domain.Controllers;

import Code.src.Domain.Classes.Document;
import Code.src.Domain.Classes.Folder;

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

    public void newDocument(String authorName, String title, String text) {

    }
    public void modifyContent(String authorName, String title, String text) {

    }

    public void modifyAuthor(String authorName, String title, String text) {

    }
    public void modifyTitle(String authorName, String title, String text) {

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

    }

    public void newFolder(String fName) {

    }
}
