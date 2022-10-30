package Domain.Classes;

import java.util.ArrayList;
import java.util.HashMap;

public class Folder {
    /**
     * @brief Unique identifier for the folder.
     * @param folderId, an Integer that represents the unique ID.
     * */
    private int folderId;

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
     * @param subFolders, ArrayList with all the Folder objects included in this folder.
     * */
    private ArrayList<Folder> subFolders;

    /**
     * @brief Contains all Documents in the folder
     * @param documents, Contains the Objects allocated to every Document present in this folder.
     * */
    private HashMap<Pair<String, String>, Document> documents;

    //Creators
    /**
     * @brief Constructor for the Object Folder
     * @param fName, Represents the name of the folder.
     * */
    public Folder(String fName){

    }

    //Setters
    /**
     * @brief Set the name of the Folder
     * @param name, String that will be allocated to this folder name.
     * */
    public void setFolderName(String name){
        folderName = name;
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

    //Getters
    /**
     * @brief Gets the amount of documents contained in this folder.
     * @return docAmount, Integer that represents the amount of documents contained in the folder.
     * */
    public int getDocumentAmount(){
        return docAmount;
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

}

