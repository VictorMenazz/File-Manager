package FONTS.src.Interface;

import FONTS.src.Domain.Controllers.DomainController;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PresentationController {
    private DomainController ctrlDomain;

    private Scanner reader = new Scanner(System.in);

    private JFrame mainFrame = new JFrame();

    private static PresentationController ctrlPresent;

    public static PresentationController getInstance() {
        if(ctrlPresent == null) {
            ctrlPresent = new PresentationController();
        }
        return ctrlPresent;
    }

    private MainView main;

    /**
     * @brief Default create of PresentationController
     */
    public PresentationController() {
        ctrlDomain = new DomainController();
    }

    public void run() throws FileNotFoundException {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200,800);
        //mainFrame.setIconImage(Utils.getLogo().getImage());
        ctrlDomain.reconstructFoldersSystem();
        ctrlDomain.reconstructAuthorsSystem();
        ctrlDomain.loadHistorial();
        toMain();
    }

    public void toMain() {
        ArrayList<String> authors = ctrlDomain.getDocumentAuthors(0);
        ArrayList<String> documents = ctrlDomain.getDocumentTitles(0);
        HashMap<Integer, String> subfolders = ctrlDomain.getSubFolders(0);
        main = new MainView(authors, documents, subfolders);
        mainFrame.setTitle("File Manager");
        mainFrame.setContentPane(main.getDefaultPanel());
        mainFrame.setVisible(true);
    }

    public void toDocument() {

    }

    public void toAddNewDocument() {

    }

    public void toModifyContent() {

    }

    public void toModifyDoc() {

    }

    public void toDeleteDoc() {

    }

    public void toAddNewFolder(){

    }

    public void toDeleteFolder() {

    }

    public void toSearch() {

    }

    public void newDocument(String authorName, String title, String text, String lang) throws IOException {
        ctrlDomain.newDocument(authorName,title,text,lang);
    }

    public void modifyAuthor(String authorName, String title, String newData) throws IOException {
        ctrlDomain.modifyDocument(authorName, title, newData, 0);
    }

    public void modifyTitle(String authorName, String title, String newData) throws IOException {
        ctrlDomain.modifyDocument(authorName, title, newData, 1);
    }

    public void modifyContent(String authorName, String title, String newData) throws IOException {
        ctrlDomain.modifyDocument(authorName, title, newData, 2);
    }

    public ArrayList<String> getDocument(String author, String title) {
        return ctrlDomain.getDocument(author, title);
    }

    public void saveDB() throws IOException {
        ctrlDomain.saveFoldersSystem();
        ctrlDomain.saveAuthorsSystem();
        ctrlDomain.saveHistorial();
    }

}
