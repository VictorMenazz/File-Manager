package FONTS.src.Interface;

import FONTS.src.Domain.Controllers.DomainController;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * @file PresentationController.java
 * Class <em>Presentation Controller</em>
 */

/**
 * Controller of the layer Interface
 *
 * @author Víctor Mena Doz
 */

public class PresentationController {
    /**
     * Domain controller instance
     */
    private DomainController ctrlDomain;

    /**
     * Scanner to read input
     */
    private Scanner reader = new Scanner(System.in);

    /**
     * Main window of the application
     */
    private JFrame mainFrame = new JFrame();

    /**
     * Presentation controller own instance
     */
    private static PresentationController ctrlPresent;

    /**
     * Instance of the main view for the application
     */
    private MainView main;

    /**
     * Function to get the instance of the controller
     * @return Instance of Presentation Controller
     */
    public static PresentationController getInstance() {
        if(ctrlPresent == null) {
            ctrlPresent = new PresentationController();
        }
        return ctrlPresent;
    }

    /**
     * Default create of PresentationController
     */
    public PresentationController() {
        ctrlDomain = new DomainController();
    }

    /**
     * Principal function of the project for reconstruct database
     * @throws FileNotFoundException
     */
    public void run() throws FileNotFoundException {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200,800);
        ctrlDomain.loadHistorial();
        ctrlDomain.reconstructFoldersSystem();
        ctrlDomain.reconstructAuthorsSystem();
        toMain();
    }

    /**
     * Function to create the main view
     */
    public void toMain() {
        main = new MainView();
    }

    public Boolean existsExpression(String boolExpr){
        return ctrlDomain.existsExpression(boolExpr);
    }

    public String addExpression(String boolExpr){
        return ctrlDomain.addExpression(boolExpr);
    }

    public String[] getBoolExpr(){
        return ctrlDomain.getBoolExpr();
    }

    public String modifyExpression(String oldExpr, String newExpr){
        return ctrlDomain.modifyExpression(oldExpr, newExpr);
    }

    public Boolean deleteExpression(String boolExpr){
        return ctrlDomain.deleteExpression(boolExpr);
    }

    public ArrayList<String> getDocumentAuthors(int id) {
        return ctrlDomain.getDocumentAuthors(id);
    }

    public ArrayList<String> getDocumentTitles(int id) {
        return ctrlDomain.getDocumentTitles(id);
    }

    public HashMap<Integer, String> getSubFolders(int id) {
        return ctrlDomain.getSubFolders(id);
    }

    public ArrayList<String> getAuthorsName(){
        return ctrlDomain.getAuthorsName();
    }

    public ArrayList<String> getAuthorDocuments(String author){
        return ctrlDomain.authorDocuments(author);
    }

    public void importDocument(String path, Integer folderID, String lang, String docType) {
        ctrlDomain.importDocument(path, folderID, lang, docType);
    }

    public void exportDocument(String title, String author, String path, String docType) {
        ctrlDomain.exportDocument(title, author, path, docType);
    }

    /**
     * Create a document view for a specific document, new or save
     * @param author, author's name of the document
     * @param title, title of the document
     * @param newDoc, if the document is new
     */
    public void toDocument(String author, String title, String language, boolean newDoc, boolean modi) {
        DocumentView document =  new DocumentView(author, title, language, newDoc, modi);
    }

    /**
     * Create view for the result of the list of authors given a prefix
     * @param prefix, string contained in authors' names
     */
    public ArrayList<String> toResultSearchAuthors(String prefix) {
        return ctrlDomain.searchAuthors(prefix);
    }

    /**
     * Create view for the result of the boolean expression search
     * @param boolExp, string with the boolean expression
     * @throws Exception
     */
    public HashMap<String, String> toResultboolExp(String boolExp) throws Exception {
        return ctrlDomain.booleanExpressionSearch(boolExp);
    }

    /**
     * Create view for the result of the appearance search
     * @param authorName, author's name of the document
     * @param title, title of the document
     * @param k, integer of how many appearance documents to return
     */
    public HashMap<String, String> toResultAppSearch(String authorName, String title, int k) {
        return ctrlDomain.appearanceSearch(authorName, title, k);
    }

    /**
     * Create view for the result of the search documents given a list of words
     * @param pWords, list of specific words
     * @param language, language of the search
     * @param k, integer of how many relevant documents to return
     * @throws IOException
     */
    public HashMap<String, String> toResultDocQuery(String pWords, String language, int k) throws IOException {
        return ctrlDomain.documentsQuery(pWords, language, k);
    }

    /**
     * Create view for the result of the documents given one author
     * @param authorName, references to the name of the Author
     */
    public ArrayList<String> toResultAutDocs(String authorName) {
        return ctrlDomain.authorDocuments(authorName);
    }


    /**
     * Add new document to database
     * @param authorName, author's name of the document
     * @param title, title of the document
     * @param text, text of the document
     * @param lang, language of the document
     * @throws IOException
     */
    public void newDocument(String authorName, String title, String text, String lang) throws IOException {
        ctrlDomain.newDocument(authorName,title,text,lang,0);
    }

    public void newFolder(String fName, int foldId){
        ctrlDomain.newFolder(fName, foldId);
    }

    public void deleteDocument(String author, String title) {
        ctrlDomain.deleteDocument(author, title);
    }

    public void deleteFolder(Integer folderID) {
        ctrlDomain.deleteFolder(folderID);
    }

    /**
     * Modify author of specific document
     * @param authorName, author's name of the document to modify
     * @param title, title of the document
     * @param newData, new author's name to change
     * @throws IOException
     */
    public void modifyAuthor(String authorName, String title, String newData) throws IOException {
        ctrlDomain.modifyDocument(authorName, title, newData, 0);
    }

    /**
     * Modify title of specific document
     * @param authorName, author's name of the document to modify
     * @param title, title of the document
     * @param newData, new title to change
     * @throws IOException
     */
    public void modifyTitle(String authorName, String title, String newData) throws IOException {
        ctrlDomain.modifyDocument(authorName, title, newData, 1);
    }

    /**
     * Modify content of specific document
     * @param authorName, author's name of the document to modify
     * @param title, title of the document
     * @param newData, new content to change
     * @throws IOException
     */
    public void modifyContent(String authorName, String title, String newData) throws IOException {
        ctrlDomain.modifyDocument(authorName, title, newData, 2);
    }

    /**
     * Get information of a specific document
     * @param author, author's name of the document
     * @param title, title of the document
     * @return List of the information of the document
     */
    public ArrayList<String> getDocument(String author, String title) {
        return ctrlDomain.getDocument(author, title);
    }

    /**
     * Save all the information of the database
     * @throws IOException
     */
    public void saveDB() throws IOException {
        ctrlDomain.saveFoldersSystem();
        ctrlDomain.saveAuthorsSystem();
        ctrlDomain.saveHistorial();
    }

}
