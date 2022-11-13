package Code.src.Domain.Controllers;

import Code.src.Domain.Classes.Content;
import Code.src.Domain.Classes.Document;
import Code.src.Domain.Classes.Folder;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @file FolderController.java
 * @brief Class <em>Folder Controller</em>
 */

/**
 * @brief Class FolderController that delegates functions to the Domain Object Folder.
 *
 * @author Victor Mena Doz
 * @author Marc Navarro Acosta
 */
public class FoldersController {
    /**
     * @brief Instancia de la carpeta origen
     */
    private Folder rootFolder;

    /**
     * @brief Default creation of FoldersController
     * @param root, Instance of the root folder
     */
    public FoldersController(Folder root){
        if(root == null) rootFolder = new Folder(0, "/");
        else rootFolder = root;
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
     * @throws IOException
     * */
    public void newDocument(String authorName, String title, String text, String lang) throws IOException {
        rootFolder.addNonConstructedDocument(authorName, title, text, lang);
    }

    /**
     * @brief Creates a new subFolder.
     * @param fName, References the name of a Folder.
     * @param foldId, Represents the identifier of the Folder Parent.
     * */
    public void newFolder(String fName, Integer foldId) {
        rootFolder.createFolder(fName, foldId);
    }

    /**
     * @brief Modifies the Content of a Document identified by <title, authorName>.
     * @param authorName, References the Author of a Document.
     * @param title, Represents the title of the Document.
     * @param text, Represents the Content of the Document on plain text.
     * @throws IOException
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
     * @brief Get instance of the root Folder
     * @return Folder, Instance of the root Folder
     */
    public Folder getRoot() {
        return rootFolder;
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
        return rootFolder.getDocument(authorName, title);
    }

    /**
     * @brief Deletes a Document of the collection.
     * @param autorName, References to the name of the Document's Author
     * @param title, References to the Document's title
     */
    public void deleteDocument(String autorName, String title) {
        rootFolder.delDocument(autorName, title);
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
     * @brief Obtain if the Document identified by <title, authorName> is protected
     * @param author, References the Author of a Document
     * @param title, Represents the title of the Document
     * @return true if Document is protected, otherwise false
     */
    public boolean isProtected(String author, String title) {
        return rootFolder.isProtected(author, title);
    }
}
