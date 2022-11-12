package Code.src.Domain.Controllers.Drivers;

import Code.src.Domain.Classes.Document;
import Code.src.Domain.Classes.Folder;
import Code.src.Domain.Controllers.FoldersController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FoldersControllerDriver {

    private static Scanner writer = new Scanner(System.in).useDelimiter(Pattern.compile("[\\r\\n;]+"));

    private static Folder createTestFolder(){
        System.out.println("Introduce a name for the Folder:");
        String fname = readInputString();
        Folder f = new Folder(1,fname);
        return f;
    }

    private static FoldersController initializeFController(){
        Folder f = createTestFolder();
        FoldersController fCont = new FoldersController(f);
        return fCont;
    }

    private static Document initializeDoc() throws IOException {
        System.out.println("Introduce a title for the Document:");
        String title = readInputString();
        System.out.println("Introduce an author for the Document:");
        String author = readInputString();
        System.out.println("Introduce a Content for the Document:");
        String Content = readInputString();
        System.out.println("Introduce a Language for the Document:");
        String lang = readInputString();
        Document doc = new Document(title,author,Content,lang);
        return doc;
    }

    public static void testFoldersController(){
        System.out.println("Creating the rootFolder...");
        FoldersController fCont = initializeFController();
        System.out.println("Root Folder created -> Name: " + fCont.getRoot().getName());
    }
    public static void testGetRoot(){
        System.out.println("Creating the rootFolder...");
        FoldersController fCont = initializeFController();
        System.out.println("Root Folder created -> Name: " + fCont.getRoot().getName() + " Docs: " + fCont.getRoot().getDocumentAmount());
    }
    public static void testNewDocument() throws IOException {
        FoldersController fCont = initializeFController();
        Document d = initializeDoc();
        fCont.newDocument(d.getAuthor(), d.getTitle(), d.getContent(), d.getLanguage());
        System.out.println("Document added -> Name: " + d.getTitle() + " Author: " + d.getAuthor() + " Contained: " + fCont.getRoot().documentContained(d.getAuthor(), d.getTitle()));
    }
    public static void testDeleteDocument() throws IOException {
        FoldersController fCont = initializeFController();
        Document d = initializeDoc();
        fCont.newDocument(d.getAuthor(), d.getTitle(), d.getContent(), d.getLanguage());
        System.out.println("Document added -> Name: " + d.getTitle() + " Author: " + d.getAuthor() + " Contained: " + fCont.getRoot().documentContained(d.getAuthor(), d.getTitle()));
        System.out.println("Deleting...");
        fCont.newDocument(d.getAuthor(), d.getTitle(), d.getContent(), d.getLanguage());
        System.out.println("Document added -> Name: " + d.getTitle() + " Author: " + d.getAuthor() + " Contained: " + fCont.getRoot().documentContained(d.getAuthor(), d.getTitle()));
    }
    public static void testModifyContent() throws IOException {
        FoldersController fCont = initializeFController();
        Document d = initializeDoc();
        fCont.newDocument(d.getAuthor(), d.getTitle(), d.getContent(), d.getLanguage());
        System.out.println("Document added -> Name: " + d.getTitle() + " Author: " + d.getAuthor() + " Contained: " + fCont.getRoot().documentContained(d.getAuthor(), d.getTitle()));
        System.out.println("Deleting...");
        fCont.deleteDocument(d.getAuthor(), d.getTitle());
        System.out.println("Document added -> Name: " + d.getTitle() + " Author: " + d.getAuthor() + " Contained: " + fCont.getRoot().documentContained(d.getAuthor(), d.getTitle()));
    }
    public static void testModifyAuthor() throws IOException {
        FoldersController fCont = initializeFController();
        Document d = initializeDoc();
        fCont.newDocument(d.getAuthor(), d.getTitle(), d.getContent(), d.getLanguage());
        System.out.println("Introduce new Author: ");
        String newAuth = readInputString();
        System.out.println("InitialDoc -> Name: " + d.getTitle() + " Author: " + d.getAuthor() + " Content: " + fCont.getRoot().getDocument(d.getAuthor(), d.getTitle()).getContent());
        fCont.modifyAuthor(d.getAuthor(),d.getTitle(),newAuth);
        System.out.println("ModifiedDoc -> Name: " + d.getTitle() + " Author: " + fCont.getDocument(newAuth, d.getTitle()).getAuthor() + " Content: " + fCont.getDocument(newAuth, d.getTitle()).getContent());
    }
    public static void testModifyTitle() throws IOException {
        FoldersController fCont = initializeFController();
        Document d = initializeDoc();
        fCont.newDocument(d.getAuthor(), d.getTitle(), d.getContent(), d.getLanguage());
        System.out.println("Introduce new Title: ");
        String newTitle = readInputString();
        System.out.println("InitialDoc -> Name: " + d.getTitle() + " Author: " + d.getAuthor() + " Content: " + fCont.getRoot().getDocument(d.getAuthor(), d.getTitle()).getContent());
        fCont.modifyTitle(d.getAuthor(), d.getTitle(), newTitle);
        System.out.println("ModifiedDoc -> Name: " + fCont.getDocument(d.getAuthor(),newTitle).getTitle() + " Author: " + d.getAuthor() + " Content: " + fCont.getDocument(d.getAuthor(), newTitle).getContent());
    }
    public static void testGetDocument() throws IOException {
        FoldersController fCont = initializeFController();
        Document d = initializeDoc();
        fCont.newDocument(d.getAuthor(), d.getTitle(), d.getContent(), d.getLanguage());
        System.out.println("Document -> Name: " + fCont.getDocument(d.getAuthor(),d.getTitle()).getTitle() + " Author: " + fCont.getDocument(d.getAuthor(),d.getTitle()).getAuthor() + " Content: " + fCont.getDocument(d.getAuthor(),d.getTitle()).getContent());
    }
    public static void testProtectDocument() throws IOException {
        FoldersController fCont = initializeFController();
        Document d = initializeDoc();
        fCont.newDocument(d.getAuthor(), d.getTitle(), d.getContent(), d.getLanguage());
        System.out.println("Introduce password to protect the Document:");
        String passwd = readInputString();
        System.out.println("Before Protecting Document -> Name: " + fCont.getDocument(d.getAuthor(),d.getTitle()).getTitle() + " Author: " + fCont.getDocument(d.getAuthor(),d.getTitle()).getAuthor() + " Protected: " + fCont.getDocument(d.getAuthor(),d.getTitle()).isProtected());
        fCont.protectDocument(d.getAuthor(),d.getTitle(),passwd);
        System.out.println("After Protecting Document -> Name: " + fCont.getDocument(d.getAuthor(),d.getTitle()).getTitle() + " Author: " + fCont.getDocument(d.getAuthor(),d.getTitle()).getAuthor() + " Protected: " + fCont.getDocument(d.getAuthor(),d.getTitle()).isProtected());
    }
    public static void testNewFolder(){
        FoldersController fCont = initializeFController();
        System.out.println("Folder Contained: " + fCont.getRoot().folderContained(2));
        fCont.newFolder("Test", 1);
        System.out.println("Folder Contained: " + fCont.getRoot().folderContained(2));
    }

    public static void main(String[] args) throws IOException {
        String functions = "0. All\n" +
                "1. testFoldersController\n" +
                "2. testGetRoot\n" +
                "3. testNewDocument\n" +
                "4. testDeleteDocument\n" +
                "5. testModifyContent\n" +
                "6. testModifyAuthor\n" +
                "7. testModifyTitle\n" +
                "8. testGetDocument\n" +
                "9. testProtectDocument\n" +
                "10. testNewFolder\n" +
                "11. Exit\n";

        System.out.println("FoldersController Driver:");
        System.out.println("Introduce the number allocated to the function you want to test.");
        System.out.println("Functions:");
        System.out.println(functions);
        int code = readInputInteger();
        while (code != 11) {
            switch (code) {
                case 1:
                    System.out.println("testFoldersController() choose:");
                    testFoldersController();
                    break;
                case 2:
                    System.out.println("testGetRoot() choose:");
                    testGetRoot();
                    break;
                case 3:
                    System.out.println("testNewDocument() choose:");
                    testNewDocument();
                    break;
                case 4:
                    System.out.println("testDeleteDocument() choose:");
                    testDeleteDocument();
                    break;
                case 5:
                    System.out.println("testModifyContent() choose:");
                    testModifyContent();
                    break;
                case 6:
                    System.out.println("testModifyAuthor() choose:");
                    testModifyAuthor();
                    break;
                case 7:
                    System.out.println("testModifyTitle() choose:");
                    testModifyTitle();
                    break;
                case 8:
                    System.out.println("testGetDocument() choose:");
                    testGetDocument();
                    break;
                case 9:
                    System.out.println("testProtectDocument() choose:");
                    testProtectDocument();
                    break;
                case 10:
                    System.out.println("testNewFolder() choose:");
                    testNewFolder();
                    break;
                default:
                    System.out.println("testFoldersController() test:");
                    testFoldersController();
                    System.out.println("testGetRoot() test:");
                    testGetRoot();
                    System.out.println("testNewDocument() test:");
                    testNewDocument();
                    System.out.println("testDeleteDocument() test:");
                    testDeleteDocument();
                    System.out.println("testModifyContent() test:");
                    testModifyContent();
                    System.out.println("testModifyAuthor() test:");
                    testModifyAuthor();
                    System.out.println("testModifyTitle() test:");
                    testModifyTitle();
                    System.out.println("testGetDocument() test:");
                    testGetDocument();
                    System.out.println("testProtectDocument() test:");
                    testProtectDocument();
                    System.out.println("testNewFolder() test:");
                    testNewFolder();
            }
            System.out.println();
            code = readInputInteger();
        }
    }

    //PRIVATE METHODS

    private static String readInputString() {
        String inp = writer.next();
        return inp;
    }

    private static int readInputInteger() {
        Integer inp = writer.nextInt();
        return inp;
    }

    private static void showDocs(Folder f) {
        ArrayList<String> docNames = f.getDocumentsName();
        for (String name : docNames){
            System.out.println("New doc: " + name);
        }
    }
}
