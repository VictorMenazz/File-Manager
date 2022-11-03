package Code.src.Domain.Controllers;

import Code.src.Domain.Classes.Content;
import Code.src.Domain.Classes.Document;
import Code.src.Domain.Classes.Folder;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @file FolderController.java
 * @brief Class <em>FolderController</em>
 */

/**
 * @brief Class FolderController that delegates functions to the Domain Object Folder.
 * @author Victor Mena Doz
 * @author Marc Navarro Acosta
 */
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

    /**
     * @brief Adds new Document to the collection.
     * @param authorName, References the Author of a Document.
     * @param title, Represents the title of the Document.
     * @param text, Represents the Content of the Document on plain text.
     * @param lang, Represents the Language of the Document.
     * */
    public void newDocument(String authorName, String title, String text, String lang) throws IOException {
        rootFolder.addNonConstructedDocument(authorName, title, text, lang);
    }

    /**
     * @brief Modifies the Content of a Document identified by <title, authorName>.
     * @param authorName, References the Author of a Document.
     * @param title, Represents the title of the Document.
     * @param text, Represents the Content of the Document on plain text.
     * */
    public void modifyContent(String authorName, String title, String text) throws IOException {
        rootFolder.modifyContent(authorName,title,text);
    }

    /**
     * @brief Modifies the author of a concrete Document.
     * @param authorName, References the Author of a Document.
     * @param title, Represents the title of the Document.
     * @param newAuthor, Represents the new Author of the Document.
     * */
    public void modifyAuthor(String authorName, String title, String newAuthor) {
        rootFolder.modifyAuthor(authorName, title, newAuthor);
    }

    /**
     * @brief Modifies the title of the Document.
     * @param authorName, References the Author of a Document.
     * @param title, Represents the title of the Document.
     * @param newTitle, Represents the new Title of the Document.
     * */
    public void modifyTitle(String authorName, String title, String newTitle) {
        rootFolder.modifyTitle(authorName, title, newTitle);
    }

    /**
     * @brief Constructs the Document and adds it to the Folder.
     * @param authorName, References the Author of a Document.
     * @param title, Represents the title of the Document.
     * @return A concrete Document identified by a <title, authorName>.
     * */
    public Document getDocument(String authorName, String title) {
        return rootFolder.getDocument(authorName, title);
    }

    /**
     * @brief Return a list of Documents written by a certain Author.
     * @param authorName, References the Author of the Documents.
     * */
    public ArrayList<Document> authorDocuments(String authorName) {
        return ctrlSearch.searchAuthorDocuments(authorName);
    }

    /**
     * @brief Check if some author match with a String input.
     * @param prefix, Represents the input String.
     * @return A Set of Authors that match with the prefix.
     */
    public ArrayList<String> searchAuthors(String prefix) {
        return ctrlSearch.searchAuthorsPrefix(prefix);
    }

    /**
     * @brief Search the k the Documents more similar to the one identified by <title, authorName>.
     * @param authorName, References the Author of a Document.
     * @param title, Represents the title of the Document.
     * @param k, Represent the number of Documents wanted related with the one identified <title, authorName>.
     * */
    public ArrayList<Document> appearanceSearch(String authorName, String title, int k) {
        return ctrlSearch.appearanceSearch();
    }

    /**
     * @brief Search Documents according to a Boolean Expression.
     * @param boolExp, References the Boolean Expression.
     * */
    public ArrayList<Document> booleanExpressionSearch(String boolExp) {
        return ctrlSearch.booleanExpressionSearch(boolExp);
    }

    /**
     * @brief Search the k Documents that satisfy the Query.
     * @param pWords, References the words the Document have to match with.
     * @param k, Represents the number of Documents we have to list.
     */
    public ArrayList<Document> documentsQuery(String pWords, int k) {
        return null;
    }

    /**
     * @brief Protects the Document identified by <title, authorName> with password.
     * @param authorName, References the Author of a Document.
     * @param title, Represents the title of the Document.
     * @param password, Represents the Content of the Document on plain text.
     * */
    public void protectDocument(String authorName, String title, String password) {
        rootFolder.protectDocument(authorName, title, password);
    }

    /**
     * @brief Creates a new subFolder.
     * @param fName, References the name of a Folder.
     * @param foldId, Represents the identifier of the Folder Parent.
     * */
    public void newFolder(String fName, Integer foldId) {
        rootFolder.createFolder(fName, foldId);
    }
}
