package FONTS.src.Domain.Controllers;

import FONTS.src.DocumentsException;
import FONTS.src.Domain.Classes.Document;
import FONTS.src.Domain.Classes.Folder;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * @file FolderController.java
 * @brief Class <em>Folder Controller</em>
 */

/**
 * Class FolderController that delegates functions to the Domain Object Folder.
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

    /**
     * @brief Auxiliar creation of FoldersController
     */
    public FoldersController(){
        rootFolder = new Folder(0, "/");
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
    public void newDocument(String authorName, String title, String text, String lang, int folderId) throws IOException {
        try {
            rootFolder.addNonConstructedDocument(authorName, title, text, lang, folderId);
        }
        catch (DocumentsException e) { e.printStackTrace(); }
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
        try {
            rootFolder.delDocument(autorName, title);
        }
        catch (DocumentsException e) {
            e.printStackTrace();
        }
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

    /**
     * @brief Saves FoldersController to Persistance/Data layer.
     */
    public String saveFoldersStructure(){
        Gson gson = new Gson();
        String foldersJSON = gson.toJson(this);
        return foldersJSON;
    }

    /**
     * @brief Recovers the last FoldersController saved from Persistance/Data layer.
     * @param data, Representing the JSON containing FoldersController serialized.
     */
    public void recoverFoldersStructure(String data){
        rootFolder = new Folder(0, "/");
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonElement root = parser.parse(data);
        JsonObject detail = root.getAsJsonObject();
        JsonElement rootF = detail.get("rootFolder");
        rootFolder.restoreDocs(rootF.getAsJsonObject());
    }

    /**
     * @brief Recovers the last FoldersController saved from Persistance/Data layer.
     * @param folderIdentifier, An Integer identifying the Folder.
     * @returns A ArrayList<String> representing all the Authors in the Folder identified with folderIdentifier.
     */
    public ArrayList<String> getDocumentAuthors(int folderIdentifier) {
        return rootFolder.getDocumentAuthors(folderIdentifier);
    }

    /**
     * @brief Returns all the titles of this Folder Documents.
     * @param folderIdentifier identifying the folder.
     * @return An ArrayList containing all titles of the Folder.
     */
    public ArrayList<String> getDocumentTitles(int folderIdentifier) {
        return rootFolder.getDocumentTitles(folderIdentifier);
    }

    /**
     * @brief Returns all the identifiers of the subFolders.
     * @param folderIdentifier identifying the folder parent.
     * @return An ArrayList containing all the subFolders.
     */
    public HashMap<Integer, String> getSubFolders(int folderIdentifier) {
        return rootFolder.getSubFolders(folderIdentifier);
    }

    /**
     * @brief Deletes the Folder with  identifier foldId.
     * @param foldId identifies the folder parent.
     */
    public void deleteFolder(Integer foldId) {
        rootFolder.deleteFolder(foldId);
    }

    public String getJSON(String title, String author) {
        return rootFolder.getJSON(title, author);
    }

    public void restoreJSON(String JSON, Integer folderId) {
        rootFolder.restoreJSON(JSON, folderId);
    }
}
