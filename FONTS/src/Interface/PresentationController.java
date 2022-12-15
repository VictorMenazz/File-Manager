package FONTS.src.Interface;

import FONTS.src.Domain.Controllers.DomainController;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @file PresentationController.java
 * @brief Class <em>Presentation Controller</em>
 */

/**
 * @brief Controller of the layer Interface
 *
 * @author Víctor Mena Doz
 */

public class PresentationController {
    /**
     * @brief Domain controller instance
     */
    private DomainController ctrlDomain;

    /**
     * @brief Scanner to read input
     */
    private Scanner reader = new Scanner(System.in);

    /**
     * @brief Main window of the application
     */
    private JFrame mainFrame = new JFrame();

    /**
     * @brief Presentation controller own instance
     */
    private static PresentationController ctrlPresent;

    /**
     * Instance of the main view for the application
     */
    private MainView main;

    /**
     * @brief Function to get the instance of the controller
     * @return Instance of Presentation Controller
     */
    public static PresentationController getInstance() {
        if(ctrlPresent == null) {
            ctrlPresent = new PresentationController();
        }
        return ctrlPresent;
    }

    /**
     * @brief Default create of PresentationController
     */
    public PresentationController() {
        ctrlDomain = new DomainController();
    }

    /**
     * @brief Principal function of the project for reconstruct database
     * @throws FileNotFoundException
     */
    public void run() throws FileNotFoundException {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200,800);
        //mainFrame.setIconImage(Utils.getLogo().getImage());
        ctrlDomain.reconstructFoldersSystem();
        ctrlDomain.reconstructAuthorsSystem();
        ctrlDomain.loadHistorial();
        toMain();
    }

    /**
     * @brief Function to create the main view
     */
    public void toMain() {
        ArrayList<String> authors = ctrlDomain.getDocumentAuthors(0);
        ArrayList<String> documents = ctrlDomain.getDocumentTitles(0);
        HashMap<Integer, String> subfolders = ctrlDomain.getSubFolders(0);
        main = new MainView(authors, documents, subfolders);
        mainFrame.setTitle("File Manager");
        mainFrame.setContentPane(main.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    /**
     * @brief Create a document view for a specific document, new or save
     * @param author, author's name of the document
     * @param title, title of the document
     */
    public void toDocument(String author, String title) {
        //ctrlDomain.getLanguage(author, title);
        //DocumentView doc = new DocumentView(author, title, );
    }

    /**
     * @brief Create a search view
     */
    public void toSearch() {

    }

    /**
     * @brief Create view for the result of the list of authors given a prefix
     * @param prefix, string contained in authors' names
     */
    public void toResultSearchAuthors(String prefix) {
        ArrayList<String> authors = ctrlDomain.searchAuthors(prefix);
        //INACABADA
    }

    /**
     * @brief Create view for the result of the boolean expression search
     * @param boolExp, string with the boolean expression
     * @throws Exception
     */
    public void toResultboolExp(String boolExp) throws Exception {
        HashMap<String, String> aux = ctrlDomain.booleanExpressionSearch(boolExp);
        ArrayList<String> titles = (ArrayList<String>) aux.keySet();
        ArrayList<String> authors = (ArrayList<String>) aux.values();
        MainView result = new MainView(authors, titles, null);
        JFrame resWindow = new JFrame();
        resWindow.setContentPane(result.getDefaultPanel());
        resWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resWindow.setTitle("Result for boolean expression search");
        resWindow.setVisible(true);
    }

    /**
     * @brief Create view for the result of the appearance search
     * @param authorName, author's name of the document
     * @param title, title of the document
     * @param k, integer of how many appearance documents to return
     */
    public void toResultAppSearch(String authorName, String title, int k) {
        HashMap<String, String> aux = ctrlDomain.appearanceSearch(authorName, title, k);
        ArrayList<String> titles = (ArrayList<String>) aux.keySet();
        ArrayList<String> authors = (ArrayList<String>) aux.values();
        MainView result = new MainView(authors, titles, null);
        JFrame resWindow = new JFrame();
        resWindow.setContentPane(result.getDefaultPanel());
        resWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resWindow.setTitle("Result for boolean expression search");
        resWindow.setVisible(true);
    }

    /**
     * @brief Create view for the result of the search documents given a list of words
     * @param pWords, list of specific words
     * @param language, language of the search
     * @param k, integer of how many relevant documents to return
     * @throws IOException
     */
    public void toResultDocQuery(String pWords, String language, int k) throws IOException {
        HashMap<String, String> aux = ctrlDomain.documentsQuery(pWords, language, k);
        ArrayList<String> titles = (ArrayList<String>) aux.keySet();
        ArrayList<String> authors = (ArrayList<String>) aux.values();
        MainView result = new MainView(authors, titles, null);
        JFrame resWindow = new JFrame();
        resWindow.setContentPane(result.getDefaultPanel());
        resWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resWindow.setTitle("Result for boolean expression search");
        resWindow.setVisible(true);
    }

    /**
     * @brief Create view for the result of the documents given one author
     * @param authorName, references to the name of the Author
     */
    public void toResultAutDocs(String authorName) {
        ArrayList<String> titles = ctrlDomain.authorDocuments(authorName);
        ArrayList<String> authors = new ArrayList<>();
        for(int i = 0; i < titles.size(); ++i) authors.add(authorName);
        MainView result = new MainView(authors, titles, null);
        JFrame resWindow = new JFrame();
        resWindow.setContentPane(result.getDefaultPanel());
        resWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resWindow.setTitle("Result for boolean expression search");
        resWindow.setVisible(true);
    }

    /**
     * @brief Create view for open a folder
     * @param foldID, id of the folder
     */
    public void toFolderView(int foldID) {

    }

    /**
     * @brief Create dialog to add a new document
     */
    public void toAddNewDocument() {

    }

    /**
     * @brief create dialog to modify document
     */
    public void toModifyDoc() {

    }

    /**
     * @brief create dialog to delete specific document
     */
    public void toDeleteDoc() {

    }

    /**
     * @brief create dialog to add new folder
     */
    public void toAddNewFolder(){

    }

    /**
     * @brief create dialog to delete folder
     */
    public void toDeleteFolder() {

    }

    /**
     * @brief Add new document to database
     * @param authorName, author's name of the document
     * @param title, title of the document
     * @param text, text of the document
     * @param lang, language of the document
     * @throws IOException
     */
    public void newDocument(String authorName, String title, String text, String lang) throws IOException {
        ctrlDomain.newDocument(authorName,title,text,lang);
    }

    /**
     * @brief Modify author of specific document
     * @param authorName, author's name of the document to modify
     * @param title, title of the document
     * @param newData, new author's name to change
     * @throws IOException
     */
    public void modifyAuthor(String authorName, String title, String newData) throws IOException {
        ctrlDomain.modifyDocument(authorName, title, newData, 0);
    }

    /**
     * @brief Modify title of specific document
     * @param authorName, author's name of the document to modify
     * @param title, title of the document
     * @param newData, new title to change
     * @throws IOException
     */
    public void modifyTitle(String authorName, String title, String newData) throws IOException {
        ctrlDomain.modifyDocument(authorName, title, newData, 1);
    }

    /**
     * @brief Modify content of specific document
     * @param authorName, author's name of the document to modify
     * @param title, title of the document
     * @param newData, new content to change
     * @throws IOException
     */
    public void modifyContent(String authorName, String title, String newData) throws IOException {
        ctrlDomain.modifyDocument(authorName, title, newData, 2);
    }

    /**
     * @brief Get information of a specific document
     * @param author, author's name of the document
     * @param title, title of the document
     * @return List of the information of the document
     */
    public ArrayList<String> getDocument(String author, String title) {
        return ctrlDomain.getDocument(author, title);
    }

    /**
     * @brief Save all the information of the database
     * @throws IOException
     */
    public void saveDB() throws IOException {
        ctrlDomain.saveFoldersSystem();
        ctrlDomain.saveAuthorsSystem();
        ctrlDomain.saveHistorial();
    }

}
