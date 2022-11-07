package Code.src.Domain.Classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @file Folder.java
 * @brief Class <em>Folder</em>
 */

/**
 * @brief Class Folder that saves the Documents.
 * @author Marc Navarro Acosta
 */
public class Folder {
    /**
     * @brief Unique identifier for the folder.
     * @param folderId, an Integer that represents the unique ID.
     * */
    private int folderId;

    /**
     * @brief Last Identifier assigned. Value shared by all the Folder instances.
     * @param lastFolderId, an Integer that represents the last assigned ID.
     * */
    private static int lastFolderId;

    /**
     * @brief Name of the folder.
     * @param folderName, String allocated to the name of this folder.
     * */
    private String folderName;

    /**
     * @brief Represent the amount of Documents contained in the folder.
     * @param docAmount, Number of documents in this folder.
     * */
    private int docAmount;

    /**
     * @brief Represent the subfolders contained in this Folder.
     * @integer Representing the unique identifier of the Folder.
     * @param subFolders, ArrayList with all the Folder objects included in this folder.
     * */
    private HashMap<Integer, Folder> subFolders;

    /**
     * @brief Contains all Documents in the folder
     * @param documents, Contains the Objects allocated to every Document present in this folder.
     * */
    private HashMap<Pair<String, String>, Document> documents;

    //Creators
    /**
     * @brief Constructor for the Object Folder
     * @param id, Identifier given by the FoldersController to the Folder.
     * @param fName, Represents the name of the folder.
     * */
    public Folder(int id, String fName){
        folderId = id;
        folderName = fName;
        lastFolderId = folderId;
    }

    //Setters
    /**
     * @brief Set the name of the Folder
     * @param name, String that will be allocated to this folder name.
     * */
    public void setFolderName(String name){
        folderName = name;
    }

    /** Creates a new Folder.
     * @param fName, String that will be allocated to this folder name.
     * @param foldId, Integer that identifies the father Folder of the new Folder.
     * */
    public void createFolder(String fName, Integer foldId) {
        if(foldId == this.folderId) {
            Folder f = new Folder(lastFolderId+1, fName);
        }
        else{
            int nextId = getNextFolderParent(foldId);
            Folder f = subFolders.get(nextId);
            f.createFolder(fName, foldId);
        }
    }


    /**
     * @brief Add a Document to this folder
     * @param newD, Document that will be associated to this folder.
     * */
    public void addDocument(Document newD){
        String auth = newD.getAuthor();
        String titl = newD.getTitle();
        Pair<String, String> docKey = new Pair(titl, auth);
        documents.put(docKey, newD);
    }

    /**
     * @brief Constructs the Document and adds it to the Folder.
     * @param authorName, References the Author of a Document.
     * @param title, Represents the title of the Document.
     * @param text, Represents the Content of the Document on plain text.
     * @param lang, Represents the Language of the Document.
     * */
    public void addNonConstructedDocument(String authorName, String title, String text, String lang) throws IOException {
        Document doc = new Document(title, authorName, text, lang);
        this.addDocument(doc);
    }

    /**
     * @brief Modifies the Content of a certain Document.
     * @param authorName, References the Author of a Document.
     * @param title, Represents the title of the Document.
     * @param text, Represents the Content of the Document on plain text.
     * */
    public void modifyContent(String authorName, String title, String text) throws IOException {
        if(this.documentContained(title, authorName)){
            //Search of the old Instance.
            Pair<String, String> docKey = new Pair(title, authorName);
            Document d = documents.get(docKey);

            //Modification of the Instance.
            String lang = d.getLanguage();
            Content cont = new Content(text, lang);
            d.setContent(cont);
        }
        else {
            int nextFolder = getNextFolder(title, authorName);
            //throw Exception if Doc is not contained in the System.
            Folder f = subFolders.get(nextFolder);
            f.modifyContent(authorName, title, text);
        }
    }

    /**
     * @brief Modifies the author of a certain Document.
     * @param authorName, References the Author of a Document.
     * @param title, Represents the title of the Document.
     * @param newAuthor, Represents the new Author associated to the Document.
     * */
    public void modifyAuthor(String authorName, String title, String newAuthor) {
        if(this.documentContained(title, authorName)){
            //Search of the old Instance.
            Pair<String, String> docKey = new Pair(title, authorName);
            Document d = documents.get(docKey);

            //Modification of the Instance.
            d.setAuthor(newAuthor);

            //Modification of the search elements.
            documents.remove(docKey);
            Pair<String, String> newDocKey = new Pair(title, newAuthor);
            documents.put(newDocKey, d);
        }
        else {
            int nextFolder = getNextFolder(title, authorName);
            //throw Exception if Doc is not contained in the System.
            Folder f = subFolders.get(nextFolder);
            f.modifyAuthor(authorName, title, newAuthor);
        }
    }

    /**
     * @brief Modifies the title of a certain Document.
     * @param authorName, References the Author of a Document.
     * @param title, Represents the title of the Document.
     * @param newTitle, Represents the Content of the Document on plain text.
     * */
    public void modifyTitle(String authorName, String title, String newTitle) {
        if(this.documentContained(title, authorName)){
            //Search of the old Instance.
            Pair<String, String> docKey = new Pair(title, authorName);
            Document d = documents.get(docKey);

            //Modification of the Instance.
            d.setAuthor(newTitle);

            //Modification of the Search elements.
            documents.remove(docKey);
            Pair<String, String> newDocKey = new Pair(newTitle, authorName);
            documents.put(newDocKey, d);
        }
        else {
            int nextFolder = getNextFolder(title, authorName);
            //throw Exception if Doc is not contained in the System.
            Folder f = subFolders.get(nextFolder);
            f.modifyTitle(authorName, title, newTitle);
        }
    }

    public void protectDocument(String authorName, String title, String password) {
        if(this.documentContained(title, authorName)){
            Pair<String, String> docKey = new Pair(title, authorName);
            Document d = documents.get(docKey);
            d.protectDocument(password);
        }
        else{
            int nextFolder = getNextFolder(title, authorName);
            //throw Exception if Doc is not contained in the System.
            Folder f = subFolders.get(nextFolder);
            f.protectDocument(authorName, title, password);
        }
    }

    //Getters

    /**
     * @brief Gets the name of this Folder.
     * @return folderName, String representing the name of the Folder.
     * */
    public String getName() {
        return folderName;
    }

    public ArrayList<String> getDocumentsName(){
        ArrayList<String> docs = new ArrayList<String>();
        for(Pair<String, String> key : documents.keySet()){
            docs.add(key.first);
        }
        return docs;
    }

    /**
     * @brief Gets the amount of documents contained in this folder.
     * @return docAmount, Integer that represents the amount of documents contained in the folder.
     * */
    public int getDocumentAmount(){
        return docAmount;
    }

    /**
     * @brief Gets the Document identified by his Author&Title.
     * @param authorName, Represents the writer of the Document.
     * @param title, Represents the title of a Document.
     * @return A Document identified by an Author and a Title.
     * */
    public Document getDocument(String authorName, String title) {
        Pair<String, String> keyDoc = new Pair(title, authorName);
        if(documents.containsKey(keyDoc)) return documents.get(keyDoc);
        else {
            int foldId = getNextFolder(title, authorName);
            Folder f = subFolders.get(foldId);
            return f.getDocument(authorName, title);
        }
    }

    //Consultants

    /**
     * @brief Consult if a certain document is in the folder.
     * @return A boolean that indicates true if the document identified by a title and an author is contained, false otherwise.
     * */
    public boolean documentContained(String titl, String auth){
        Pair<String, String> key = new Pair(titl, auth);
        return documents.containsKey(key);
    }

    /**
     * @brief Consult if a certain folder is a Subfolder of this Instance.
     * @return A boolean that indicates true if the Folder identified by an Id is contained in subFolders.
     * */
    public boolean folderContained(Integer foldId) {
        return subFolders.containsKey(foldId);
    }

    /** Recursive functions involved on the efficient Search of Documents and Folders **/

    /**
     * @brief Recursive Search to verify the Document is included in this or one of the subFolders.
     * @param ttl, Represents the title of the Document.
     * @param auth, Represents the author of the Document.
     * */
    private boolean documentSubContained(String ttl, String auth){
        boolean found = false;
        if(this.documentContained(ttl, auth)) return true;
        else {
            for (int subId : subFolders.keySet()) {
                Folder fold = subFolders.get(subId);
                if (fold.documentSubContained(ttl, auth)) found = true;
            }
            return found;
        }
    }

    /**
     * @brief Recursive Search to find the next Folder to reach the Document.
     * @param ttl, Represents the title of the Document.
     * @param auth, Represents the author of the Document.
     * @return An Integer that identifies the next Folder to visit.
     * */
    private Integer iGetNextFolder(String ttl, String auth){
        int identifier = -1;
        for (int subId : subFolders.keySet()) {
            if(identifier == -1){
                Folder fold = subFolders.get(subId);
                if (fold.documentSubContained(ttl, auth)){
                    identifier = folderId;
                }
            }
        }
        return identifier;
    }

    /**
     * @brief Search to find the next Folder to visit.
     * @param ttl, Represents the title of the Document.
     * @param auth, Represents the author of the Document.
     * @return An Integer with the next folderId to visit.
     * */
    private Integer getNextFolder(String ttl, String auth){
        if(this.documentContained(ttl, auth)) return folderId;
        else return iGetNextFolder(ttl, auth);
    }

    /**
     * @brief Recursive Search to verify the Folder is included in this or one of the subFolders.
     * @param foldId, Represents the id of the Folder.
     * */
    private boolean folderSubContained(Integer foldId){
        boolean found = false;
        if(this.folderContained(foldId)) return true;
        else {
            for (int subId : subFolders.keySet()) {
                Folder fold = subFolders.get(subId);
                if (fold.folderSubContained(foldId)) found = true;
            }
            return found;
        }
    }

    /**
     * @brief Recursive Search to find the next Folder to reach the Folder desired.
     * @param foldId, Represents the id of the Folder.
     * @return An Integer that identifies the next Folder to visit.
     * */
    private Integer iGetNextFolderParent(Integer foldId){
        int identifier = -1;
        for (int subId : subFolders.keySet()) {
            if(identifier == -1){
                Folder fold = subFolders.get(subId);
                if (fold.folderSubContained(foldId)){
                    identifier = folderId;
                }
            }
        }
        return identifier;
    }

    /**
     * @brief Search to find the next Folder to visit.
     * @param foldId, Represents the id of the Folder desired.
     * @return An Integer with the next folderId to visit.
     * */
    private Integer getNextFolderParent(Integer foldId) {
        if(this.folderContained(foldId)) return folderId;
        else return iGetNextFolderParent(foldId);
    }

}

