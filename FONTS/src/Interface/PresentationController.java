package FONTS.src.Interface;

import FONTS.src.Domain.Controllers.DomainController;

import javax.swing.*;
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

    public void run() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200,800);
        //mainFrame.setIconImage(Utils.getLogo().getImage());
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

    public void addDocument(String authorName, String title, String text, String lang) throws IOException {
        ctrlDomain.newDocument(authorName,title,text,lang);
    }
}
