package FONTS.src.Domain.Controllers.Drivers;

import FONTS.src.DocumentsException;
import FONTS.src.Domain.Classes.Content;
import FONTS.src.Domain.Classes.Document;
import FONTS.src.Domain.Classes.Folder;
import FONTS.src.Domain.Classes.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @file FolderDriver.java
 * Driver <em>Folder</em>
 */

/**
 * Driver of class Folder
 *
 * @author Marc Navarro Acosta
 */

public class FolderDriver {
    private static Scanner writer = new Scanner(System.in).useDelimiter(Pattern.compile("[\\r\\n;]+"));

    /** Creator test. */
    public static void testFolder() {
        System.out.println("Introduce a name for the Folder:");
        String name = readInputString();
        Folder folder = new Folder(1, name);
        System.out.println("Folder " + folder.getName() + " created!");
        System.out.println("Testing 'testFolder()' finished.");
        System.out.println();
    }

    /** Check the function SetFolderName of Folder class. */
    public static void testSetFolderName() {
        System.out.println("It will initialize a Folder with name 'Folder1'");
        System.out.println("Introduce a name to test function:");
        String inputName = readInputString();
        System.out.println("Will change the name to " + inputName);
        Folder folder = new Folder(1, "Folder1");
        System.out.println("Checking initial name: " + folder.getName());
        folder.setFolderName(inputName);
        System.out.println("New name:" + folder.getName());
        System.out.println("Testing 'testSetFolderName()' finished.");
        System.out.println();
    }

    /** Check the creation of a SubFolder */
    public static void testCreateFolder() {
        System.out.println("Introduce a name for the new Folder:");
        String inputName = readInputString();
        System.out.println("It will create a Folder with name:" + inputName);
        Folder folder = new Folder(1, "FolderParent");
        folder.createFolder(inputName, 1);
        System.out.println("Checking correct creation:" + folder.getName() + ", " + folder.getDocumentAmount());
        System.out.println("Testing 'testCreateFolder()' finished.");
        System.out.println();
    }

    /** Check the addition of a Document.
     * @throws IOException
     * @throws DocumentsException
     * */
    public static void testAddDocument() throws IOException, DocumentsException {
        System.out.println("Introduce the lang(ESP, CAT or ENG) for the Document:");
        String language = readInputString();
        System.out.println("Introduce the title:");
        String title = readInputString();
        System.out.println("Introduce the author:");
        String author = readInputString();
        System.out.println("Introduce the Content:");
        String ContentText = readContent();

        //Testing a Document initialized without Content, plain text.
        System.out.println("Checking initializing method 1:");
        Folder f = new Folder(1, "Folder1");
        Document d = new Document(title, author, ContentText, language);
        System.out.println("List of Docs before addition:");
        showDocs(f);
        //Addition
        f.addDocument(d);
        System.out.println("List of Docs after addition:");
        showDocs(f);
        System.out.println("Initializing method 1 finished.");

        //Testing a Document initialized with Content.
        System.out.println("Checking initializing method 2:");
        Content c = new Content(ContentText, language);
        Folder f2 = new Folder(2, "Folder2");
        Document d2 = new Document(title, author, c, language);
        System.out.println("List of Docs before addition:");
        showDocs(f2);
        //Addition
        f2.addDocument(d2);
        System.out.println("List of Docs after addition:");
        showDocs(f2);
        System.out.println("Initializing method 2 finished.");
        System.out.println("Testing 'testAddDocument()' finished.");
        System.out.println();
    }

    public static void testAddNonConstructedDocument() throws IOException, DocumentsException {
        System.out.println("Introduce the lang(ESP, CAT or ENG) for the Document:");
        String language = readInputString();
        System.out.println("Introduce the title:");
        String title = readInputString();
        System.out.println("Introduce the author:");
        String author = readInputString();
        System.out.println("Introduce the Content:");
        String ContentText = readContent();

        //Testing a Document initialized without Content, plain text.
        System.out.println("Checking initializing method 1:");
        Folder f = new Folder(1, "Folder1");
        System.out.println("List of Docs before addition:");
        showDocs(f);
        //Addition
        f.addNonConstructedDocument(author, title, ContentText, language, f.getId());
        System.out.println("List of Docs after addition:");
        showDocs(f);
        System.out.println("Testing 'testAddNonConstructedDocument()' finished.");
        System.out.println();
    }

    public static void testDelDocument() throws IOException, DocumentsException {
        Folder f = new Folder(1, "Testname");

        System.out.println("Introduce the lang(ESP, CAT or ENG) for the Document:");
        String language = readInputString();
        System.out.println("Introduce the title:");
        String title = readInputString();
        System.out.println("Introduce the author:");
        String author = readInputString();
        System.out.println("Introduce the Content:");
        String ContentText = readContent();

        Document d = new Document(title, author, ContentText, language);
        f.addDocument(d);
        System.out.println("List of Docs after addition:");
        showDocs(f);
        f.delDocument(author,title);
        System.out.println("List of Docs after deletion:");
        showDocs(f);
        System.out.println();
    }

    public static void testModifyContent() throws IOException, DocumentsException {
        //Testing a Document initialized without Content, plain text.
        System.out.println("Checking initializing method 1:");
        Folder f = new Folder(1, "Folder1");
        System.out.println("Creating a Doc to test function, information:");
        System.out.println("Title:");
        String title = readInputString();
        System.out.println("Author:");
        String author = readInputString();
        //Content on plain text return missing.
        System.out.println("Content:");
        String cont = readContent();
        System.out.println("Introduce the lang(ESP, CAT or ENG) for the new Content:");
        String language = readInputString();

        Document d = new Document(title,author,cont,language);
        f.addDocument(d);

        System.out.println("Introduce the new Content:");
        String ContentText = readContent();

        f.modifyContent(author,title, ContentText);

        ArrayList<String> doc = f.getDocument(author, title);
        System.out.println(doc.get(2));
        System.out.println();
    }

    public static void testModifyAuthor() throws IOException, DocumentsException {
        //Testing a Document initialized without Content, plain text.
        System.out.println("Checking initializing method 1:");
        Folder f = new Folder(1, "Folder1");
        System.out.println("Creating a Doc to test function, information:");
        System.out.println("Title:");
        String title = readInputString();
        System.out.println("Author:");
        String author = readInputString();
        //Content on plain text return missing.
        System.out.println("Content:");
        String cont = readContent();
        System.out.println("Introduce the lang(ESP, CAT or ENG) for the new Content:");
        String language =  readInputString();

        Document d = new Document(title,author,cont,language);
        f.addDocument(d);

        System.out.println("Introduce the new Author:");
        String AuthorN = readInputString();

        f.modifyAuthor(author,title, AuthorN);

        ArrayList<String> doc = f.getDocument(AuthorN, title);
        System.out.println("New Author: " + doc.get(1));
        System.out.println();
    }

    public static void testModifyTitle() throws IOException, DocumentsException {
//Testing a Document initialized without Content, plain text.
        System.out.println("Checking initializing method 1:");
        Folder f = new Folder(1, "Folder1");
        System.out.println("Creating a Doc to test function, information:");
        System.out.println("Title:");
        String title = readInputString();
        System.out.println("Author:");
        String author = readInputString();
        //Content on plain text return missing.
        System.out.println("Content:");
        String cont = readContent();
        System.out.println("Introduce the lang(ESP, CAT or ENG) for the new Content:");
        String language =  readInputString();

        Document d = new Document(title,author,cont,language);
        f.addDocument(d);

        System.out.println("Introduce the new Title:");
        String newTitle = readInputString();

        f.modifyTitle(author,title, newTitle);

        ArrayList doc = f.getDocument(author, newTitle);
        System.out.println("New Title: " + doc.get(0));
        System.out.println();
    }

    public static void testProtectDocument() throws IOException, DocumentsException {
//Testing a Document initialized without Content, plain text.
        System.out.println("Checking initializing method 1:");
        Folder f = new Folder(1, "Folder1");
        System.out.println("Creating a Doc to test function, information:");
        System.out.println("Title:");
        String title = readInputString();
        System.out.println("Author:");
        String author = readInputString();
        //Content on plain text return missing.
        System.out.println("Content:");
        String cont = readContent();
        System.out.println("Introduce the lang(ESP, CAT or ENG) for the new Content:");
        String language =  readInputString();

        Document d = new Document(title,author,cont,language);
        f.addDocument(d);

        System.out.println("Introduce the password to protect:");
        String password = readInputString();

        f.protectDocument(author, title, password);

        boolean b = f.isProtected(author,  title);
        String is = "";
        if(b) is = "is";
        else is = "isn't";
        System.out.println("Document " + is + " protected.");
        System.out.println();
    }

    public static void testGetName(){
        System.out.println("Introduce a name for the Folder:");
        String name = readInputString();
        Folder f = new Folder(1, name);
        System.out.println("Folder Name:" + f.getName());
        System.out.println();
    }

    public static void testGetDocumentsName() throws IOException, DocumentsException {
        System.out.println("Introduce number of Documents:");
        int number = readInputInteger();
        Folder f = new Folder(1, "Folder");
        for (int i = 0; i < number; ++i){
            System.out.println("New Document");
            System.out.println("Title:");
            String title = readInputString();
            System.out.println("Author:");
            String author = readInputString();
            //Content on plain text return missing.
            System.out.println("Content:");
            String cont = readContent();
            System.out.println("Introduce the lang(ESP, CAT or ENG) for the new Content:");
            String language =  readInputString();

            Document d = new Document(title,author,cont,language);
            f.addDocument(d);
        }
        for(String t : f.getDocumentsName()){
            System.out.println("Doc: " + t);
        }
        System.out.println();
    }

    public static void testGetDocumentAmount() throws IOException, DocumentsException {
        System.out.println("Introduce number of Documents:");
        int number = readInputInteger();
        Folder f = new Folder(1, "Folder");
        for (int i = 0; i < number; ++i){
            System.out.println("New Document");
            System.out.println("Title:");
            String title = readInputString();
            System.out.println("Author:");
            String author = readInputString();
            //Content on plain text return missing.
            System.out.println("Content:");
            String cont = readContent();
            System.out.println("Introduce the lang(ESP, CAT or ENG) for the new Content:");
            String language =  readInputString();

            Document d = new Document(title,author,cont,language);
            f.addDocument(d);
        }
        System.out.println("Number of Docs: " + f.getDocumentAmount());
        System.out.println();
    }

    public static void testGetDocument() throws IOException, DocumentsException {
        Folder f = new Folder(1,"Folder");
        System.out.println("New Document");
        System.out.println("Title:");
        String title = readInputString();
        System.out.println("Author:");
        String author = readInputString();
        //Content on plain text return missing.
        System.out.println("Content:");
        String cont = readContent();
        System.out.println("Introduce the lang(ESP, CAT or ENG) for the new Content:");
        String language =  readInputString();

        Document d = new Document(title,author,cont,language);
        f.addDocument(d);
        ArrayList<String> d2 = f.getDocument(author, title);
        System.out.println("Checking Doc -> Title: " + d2.get(0) + " Author: " + d2.get(1));
        System.out.println();
    }

    public static void testGetMapsDocs() throws IOException, DocumentsException {
        Folder f = new Folder(1,"Test");
        System.out.println("New Document");
        System.out.println("Title:");
        String title = readInputString();
        System.out.println("Author:");
        String author = readInputString();
        //Content on plain text return missing.
        System.out.println("Content:");
        String cont = readContent();
        System.out.println("Introduce the lang(ESP, CAT or ENG) for the new Content:");
        String language = readInputString();

        Document d = new Document(title,author,cont,language);
        f.addDocument(d);

        f.createFolder("SubFolder",1);
        System.out.println("New Document");
        System.out.println("Title:");
        String title2 = readInputString();
        System.out.println("Author:");
        String author2 = readInputString();
        //Content on plain text return missing.
        System.out.println("Content:");
        String cont2 = readContent();
        System.out.println("Introduce the lang(ESP, CAT or ENG) for the new Content:");
        String language2 = readInputString();

        Document d2 = new Document(title2,author2,cont2,language2);
        f.addDocumentToSubfolder(d2, 2);

        HashMap<Pair<String, String>, HashMap<String,Integer>> result = f.getMapsDocs();
        for(HashMap.Entry<Pair<String, String>, HashMap<String,Integer>> entry : result.entrySet()){
            Pair<String, String> docId = entry.getKey();
            HashMap<String,Integer> docMap = entry.getValue();
            System.out.println("New Doc: " + docId.first + docId.second);
            for(HashMap.Entry<String, Integer> doc : docMap.entrySet()){
                System.out.println(doc.getKey() + ':' + doc.getValue());
            }
            System.out.println();
        }
    }

    public static void testDocumentContained() throws IOException, DocumentsException {
        System.out.println("Introduce number of Documents:");
        int number = readInputInteger();
        Folder f = new Folder(1, "Folder");
        System.out.println("New Document");
        System.out.println("Title:");
        String title = readInputString();
        System.out.println("Author:");
        String author = readInputString();
        //Content on plain text return missing.
        System.out.println("Content:");
        String cont = readContent();
        System.out.println("Introduce the lang(ESP, CAT or ENG) for the new Content:");
        String language =  readInputString();

        Document d = new Document(title,author,cont,language);
        f.addDocument(d);
        System.out.println("Document Contained: " + f.documentContained(title,author));
        System.out.println();
    }

    public static void testFolderContained() {
        System.out.println("Introduce name of SubFolder:");
        String name = readInputString();
        Folder f = new Folder(1, "Folder");
        f.createFolder(name, 1);
        System.out.println("Folder " + name + " contained: " + f.folderContained(2));
        System.out.println();
    }


    //Private functions not need to be proved.
    public static void main(String[] args) throws IOException, DocumentsException {
        String functions = "0. All\n" +
                "1. testFolder\n" +
                "2. testSetFolderName\n" +
                "3. testCreateFolder\n" +
                "4. testAddDocument\n" +
                "5. testAddNonConstructedDocument\n" +
                "6. testDelDocument\n" +
                "7. testModifyContent\n" +
                "8. testModifyAuthor\n" +
                "9. testModifyTitle\n" +
                "10. testProtectDocument\n" +
                "11. testGetName\n" +
                "12. testGetDocumentsName\n" +
                "13. testGetDocumentAmount\n" +
                "14. testGetDocument\n" +
                "15. testGetMapsDoc\n" +
                "16. testDocumentContained\n" +
                "17. testFolderContained\n" +
                "18. Exit\n";

        System.out.println("Folder Driver:");
        System.out.println("Introduce the number allocated to the function you want to test.");
        System.out.println("Functions:");
        System.out.println(functions);
        int code = readInputInteger();
        while (code != 18) {
            switch (code) {
                case 1:
                    System.out.println("testFolder() choose:");
                    testFolder();
                    break;
                case 2:
                    System.out.println("testSetFolderName() choose:");
                    testSetFolderName();
                    break;
                case 3:
                    System.out.println("testCreateFolder() choose:");
                    testCreateFolder();
                    break;
                case 4:
                    System.out.println("testAddDocument() choose:");
                    testAddDocument();
                    break;
                case 5:
                    System.out.println("testAddNonConstructedDocument() choose:");
                    testAddNonConstructedDocument();
                    break;
                case 6:
                    System.out.println("testDelDocument() choose:");
                    testDelDocument();
                    break;
                case 7:
                    System.out.println("testModifyContent() choose:");
                    testModifyContent();
                    break;
                case 8:
                    System.out.println("testModifyAuthor() choose:");
                    testModifyAuthor();
                    break;
                case 9:
                    System.out.println("testModifyTitle() choose:");
                    testModifyTitle();
                    break;
                case 10:
                    System.out.println("testProtectDocument() choose:");
                    testProtectDocument();
                    break;
                case 11:
                    System.out.println("testGetName() choose:");
                    testGetName();
                    break;
                case 12:
                    System.out.println("testGetDocumentsName() choose:");
                    testGetDocumentsName();
                    break;
                case 13:
                    System.out.println("testGetDocumentAmount() choose:");
                    testGetDocumentAmount();
                    break;
                case 14:
                    System.out.println("testGetDocument() choose:");
                    testGetDocument();
                    break;
                case 15:
                    System.out.println("testGetMapsDoc() choose:");
                    testGetMapsDocs();
                    break;
                case 16:
                    System.out.println("testDocumentContained() choose:");
                    testDocumentContained();
                    break;
                case 17:
                    System.out.println("testFolderContained() choose:");
                    testFolderContained();
                    break;
                default:
                    System.out.println("testFolder() test:");
                    testFolder();
                    System.out.println("testSetFolderName() test:");
                    testSetFolderName();
                    System.out.println("testCreateFolder() test:");
                    testCreateFolder();
                    System.out.println("testAddDocument() test:");
                    testAddDocument();
                    System.out.println("testAddNonConstructedDocument() test:");
                    testAddNonConstructedDocument();
                    System.out.println("testDelDocument() choose:");
                    testDelDocument();
                    System.out.println("testModifyContent() test:");
                    testModifyContent();
                    System.out.println("testModifyAuthor() test:");
                    testModifyAuthor();
                    System.out.println("testModifyTitle() test:");
                    testModifyTitle();
                    System.out.println("testProtectDocument test:");
                    testProtectDocument();
                    System.out.println("testGetName() test:");
                    testGetName();
                    System.out.println("testGetDocumentsName() test:");
                    testGetDocumentsName();
                    System.out.println("testGetDocumentAmount() test:");
                    testGetDocumentAmount();
                    System.out.println("testGetDocument() test:");
                    testGetDocument();
                    System.out.println("testGetMapsDoc() choose:");
                    testGetMapsDocs();
                    System.out.println("testDocumentContained() test:");
                    testDocumentContained();
                    System.out.println("testFolderContained() test:");
                    testFolderContained();
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

    private static String readContent() {
        System.out.println("Introduce text for the Content, return carrier when you finish and write _end_");
        String inp = "";
        while(writer.hasNext()) {
            String aux = writer.next();
            if(aux.equals("_end_")) break;
            else {
                inp += (aux + "\n");
            }
        }
        return inp;
    }
}
