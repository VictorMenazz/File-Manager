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
        subFolders = new HashMap<Integer, Folder>();
        documents = new HashMap<Pair<String, String>, Document>();
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
            subFolders.put(lastFolderId,f);
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
        docAmount += 1;
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
     * @brief Delete a Document from this folder
     * @param authorName, References to the name of the Document's Author
     * @param title, References to the Document's title
     */
    public void delDocument(String authorName, String title) {
        Pair<String, String> docKey = new Pair(title, authorName);
        if (documents.containsKey(docKey)) {
            documents.remove(docKey);
        }
        else {
            // exception the document you are trying to delete does not exist
        }
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
            d.setTitle(newTitle);

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

    public void addDocumentToSubfolder(Document newD, int foldId){
        if(foldId == folderId) this.addDocument(newD);
        else if(subFolders.containsKey(foldId)){
            Folder f = subFolders.get(foldId);
            f.addDocument(newD);
        }
        else{
            int nextF = getNextFolderParent(foldId);
            Folder f = subFolders.get(nextF);
            f.addDocumentToSubfolder(newD, foldId);
        }
    }

    /**
     * @brief Gets the Document identified by his Author&Title.
     * @param authorName, Represents the writer of the Document.
     * @param title, Represents the title of a Document.
     * @return A Document identified by an Author and a Title.
     * */
    public ArrayList<String> getDocument(String authorName, String title) {
        Pair<String, String> keyDoc = new Pair(title, authorName);
        if(documents.containsKey(keyDoc)) {
            ArrayList<String> result = new ArrayList<String>();
            result.add(title);
            result.add(authorName);
            result.add(documents.get(keyDoc).getContent());
            return result;
        }
        else {
            int foldId = getNextFolder(title, authorName);
            Folder f = subFolders.get(foldId);
            return f.getDocument(authorName, title);
        }
    }

    public HashMap<Pair<String, String>, HashMap<String,Integer>> getMapsDocs(){
        HashMap<Pair<String, String>, HashMap<String,Integer>> result = new HashMap<Pair<String, String>, HashMap<String,Integer>>();
        if(subFolders.isEmpty()) result = igetMapsDocs();
        else{
            result = igetMapsDocs();
            for (Folder f : subFolders.values()){
                HashMap<Pair<String, String>, HashMap<String,Integer>> subFolderResult = f.getMapsDocs();
                for(HashMap.Entry<Pair<String, String>, HashMap<String,Integer>> entry : subFolderResult.entrySet()){
                    Pair<String, String> docId = entry.getKey();
                    HashMap<String,Integer> docMap = entry.getValue();
                    result.put(docId,docMap);
                }
            }
        }
        return result;
    }

    private HashMap<Pair<String, String>, HashMap<String,Integer>> igetMapsDocs(){
        HashMap<Pair<String, String>, HashMap<String,Integer>> result = new HashMap<Pair<String, String>, HashMap<String,Integer>>();
        for (Document d : documents.values()){
            Pair<String, String> key = new Pair<String, String>(d.getTitle(),d.getAuthor());
            HashMap<String,Integer> value = d.contentSearch();
            result.put(key,value);
        }
        return result;
    }

    public HashMap<Pair<String, String>, ArrayList<String>> getAllContent(){
        HashMap<Pair<String, String>, ArrayList<String>> result = iGetAllContent();
        if(!subFolders.isEmpty()) {
            for(Folder f : subFolders.values()){
                HashMap<Pair<String, String>, ArrayList<String>> subDocs = f.getAllContent();
                for (HashMap.Entry<Pair<String, String>, ArrayList<String>> entries : subDocs.entrySet()){
                    result.put(entries.getKey(),entries.getValue());
                }
            }
        }
        return result;
    }

    private HashMap<Pair<String, String>, ArrayList<String>> iGetAllContent(){
        HashMap<Pair<String, String>, ArrayList<String>> folderContents = new HashMap<Pair<String, String>, ArrayList<String>>();
        for (Document d : documents.values()){
            Pair<String, String> key = new Pair<String, String>(d.getTitle(),d.getAuthor());
            Content cont = d.getContentInstance();
            ArrayList<String> value = cont.getSentences();
            folderContents.put(key,value);
        }
        return folderContents;
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

    public boolean isProtected(String author, String title) {
        Pair<String, String> key = new Pair<String, String>(title,author);
        return documents.get(key).isProtected();
    }
}

