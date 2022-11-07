package Code.src.Domain.Controllers.Drivers;

import Code.src.Domain.Classes.Content;
import Code.src.Domain.Classes.Document;
import Code.src.Domain.Classes.Folder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FolderDriver {
    private static Scanner writer = new Scanner(System.in);

    /** @brief Creator test. */
    public static void testFolder() {
        System.out.println("Introduce a name for the Folder: ");
        String name = readInputString();
        Folder folder = new Folder(1, name);
        System.out.println("Folder " + folder.getName() + " created!");
        System.out.println("Testing 'testFolder()' finished.");
    }

    /** @brief Check the function SetFolderName of Folder class. */
    public static void testSetFolderName() {
        System.out.println("It will initialize a Folder with name 'Folder1'");
        System.out.println("Introduce a name to test function: ");
        String inputName = readInputString();
        System.out.println("Will change the name to " + inputName);
        Folder folder = new Folder(1, "Folder1");
        System.out.println("Checking initial name: " + folder.getName());
        folder.setFolderName(inputName);
        System.out.println("New name: " + folder.getName());
        System.out.println("Testing 'testSetFolderName()' finished.");
    }

    /** @brief Check the creation of a SubFolder */
    public static void testCreateFolder() {
        System.out.println("Introduce a name for the new Folder: ");
        String inputName = readInputString();
        System.out.println("It will create a Folder with name: " + inputName);
        Folder folder = new Folder(1, "FolderParent");
        folder.createFolder(inputName, 1);
        System.out.println("Checking correct creation: " + folder.getName() + ", " + folder.getDocumentAmount());
        System.out.println("Testing 'testCreateFolder()' finished.");
    }

    /** @brief Check the addition of a Document. */
    public static void testAddDocument() throws IOException {
        System.out.println("Introduce the lang(ESP, CAT or ENG) for the Document: ");
        String language = readInputString();
        System.out.println("Introduce the title: ");
        String title = readInputString();
        System.out.println("Introduce the author: ");
        String author = readInputString();
        System.out.println("Introduce the Content: ");
        String ContentText = readInputString();

        //Testing a Document initialized without Content, plain text.
        System.out.println("Checking initializing method 1: ");
        Folder f = new Folder(1, "Folder1");
        Document d = new Document(title, author, ContentText, language);
        System.out.println("List of Docs before addition: ");
        showDocs(f);
        //Addition
        f.addDocument(d);
        System.out.println("List of Docs after addition: ");
        showDocs(f);
        System.out.println("Initializing method 1 finished.");

        //Testing a Document initialized with Content.
        System.out.println("Checking initializing method 2: ");
        Content c = new Content(ContentText, language);
        Folder f2 = new Folder(2, "Folder2");
        Document d2 = new Document(title, author, c, language);
        System.out.println("List of Docs before addition: ");
        showDocs(f2);
        //Addition
        f2.addDocument(d2);
        System.out.println("List of Docs after addition: ");
        showDocs(f2);
        System.out.println("Initializing method 2 finished.");
        System.out.println("Testing 'testAddDocument()' finished.");
    }

    public static void testAddNonConstructedDocument() throws IOException {
        System.out.println("Introduce the lang(ESP, CAT or ENG) for the Document: ");
        String language = readInputString();
        System.out.println("Introduce the title: ");
        String title = readInputString();
        System.out.println("Introduce the author: ");
        String author = readInputString();
        System.out.println("Introduce the Content: ");
        String ContentText = readInputString();

        //Testing a Document initialized without Content, plain text.
        System.out.println("Checking initializing method 1: ");
        Folder f = new Folder(1, "Folder1");
        System.out.println("List of Docs before addition: ");
        showDocs(f);
        //Addition
        f.addNonConstructedDocument(author, title, ContentText, language);
        System.out.println("List of Docs after addition: ");
        showDocs(f);
        System.out.println("Testing 'testAddNonConstructedDocument()' finished.");
    }

    public static void testModifyContent() throws IOException {
        /* STUB MISSING DOCUMENT MAYBE?
        //We assume that oldContentLanguage = newContentLanguage;
        System.out.println("Introduce the lang(ESP, CAT or ENG) for the new Content: ");
        String language = readInputString();

        System.out.println("Creating a Doc to test function, information: ");
        Document d = new Document("Doc1", "Author1", "Content Example", language);
        System.out.println("Title: " + d.getTitle());
        System.out.println("Author: " + d.getAuthor());
        //Content on plain text return missing.
        System.out.println("Content: " + d.getContent());

        System.out.println("Introduce the new Content: ");
        String ContentText = readInputString();

        */
    }

    public static void testModifyAuthor() {

    }

    public static void testModifyTitle() {

    }

    public static void testProtectDocument() {

    }

    public static void testGetName(){

    }

    public static void testGetDocumentsName(){

    }

    public static void testGetDocumentAmount() {

    }

    public static void testGetDocument() {

    }

    public static void testDocumentContained() {

    }

    public static void testFolderContained() {

    }


    //Private functions not need to be proved.
    public static void main(String[] args) throws IOException {
        String functions = "0.All\n" +
                "1. testFolder\n" +
                "2. testSetFolderName\n" +
                "3. testCreateFolder\n" +
                "4. testAddDocument\n" +
                "5. testAddNonConstructedDocument\n" +
                "6. testModifyContent\n" +
                "7. testModifyAuthor\n" +
                "8. testModifyTitle\n" +
                "9. testProtectDocument\n" +
                "10. testGetName\n" +
                "11. testGetDocumentsName\n" +
                "12. testGetDocumentAmount\n" +
                "13. testGetDocument\n" +
                "14. testDocumentContained\n" +
                "15. testFolderContained\n";

        System.out.println("Folder Driver: ");
        System.out.println("Introduce the number allocated to the function you want to test.");
        System.out.println("Functions: ");
        System.out.println(functions);
        int code = readInputInteger();
        switch (code){
            case 1:
                System.out.println("testFolder() choose: ");
                testFolder();
                break;
            case 2:
                System.out.println("testSetFolderName() choose: ");
                testSetFolderName();
                break;
            case 3:
                System.out.println("testCreateFolder() choose: ");
                testCreateFolder();
                break;
            case 4:
                System.out.println("testAddDocument() choose: ");
                testAddDocument();
                break;
            case 5:
                System.out.println("testAddNonConstructedDocument() choose: ");
                testAddNonConstructedDocument();
                break;
            case 6:
                System.out.println("testModifyContent() choose: ");
                testModifyContent();
                break;
            case 7:
                System.out.println("testModifyAuthor() choose: ");
                testModifyAuthor();
                break;
            case 8:
                System.out.println("testModifyTitle() choose: ");
                testModifyTitle();
                break;
            case 9:
                System.out.println("testProtectDocument() choose: ");
                testProtectDocument();
                break;
            case 10:
                System.out.println("testGetName() choose: ");
                testGetName();
                break;
            case 11:
                System.out.println("testGetDocumentsName() choose: ");
                testGetDocumentsName();
                break;
            case 12:
                System.out.println("testGetDocumentAmount() choose: ");
                testGetDocumentAmount();
                break;
            case 13:
                System.out.println("testGetDocument() choose: ");
                testGetDocument();
                break;
            case 14:
                System.out.println("testDocumentContained() choose: ");
                testDocumentContained();
                break;
            case 15:
                System.out.println("testFolderContained() choose: ");
                testFolderContained();
                break;
            default:
                System.out.println("testFolder() test: ");
                testFolder();
                System.out.println("testSetFolderName() test: ");
                testSetFolderName();
                System.out.println("testCreateFolder() test: ");
                testCreateFolder();
                System.out.println("testAddDocument() test: ");
                testAddDocument();
                System.out.println("testAddNonConstructedDocument() test: ");
                testAddNonConstructedDocument();
                System.out.println("testModifyContent() test: ");
                testModifyContent();
                System.out.println("testModifyAuthor() test: ");
                testModifyAuthor();
                System.out.println("testModifyTitle() test: ");
                testModifyTitle();
                System.out.println("testProtectDocument test: ");
                testProtectDocument();
                System.out.println("testGetName() test: ");
                testGetName();
                System.out.println("testGetDocumentsName() test: ");
                testGetDocumentsName();
                System.out.println("testGetDocumentAmount() test: ");
                testGetDocumentAmount();
                System.out.println("testGetDocument() test: ");
                testGetDocument();
                System.out.println("testDocumentContained() test: ");
                testDocumentContained();
                System.out.println("testFolderContained() test: ");
                testFolderContained();
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
