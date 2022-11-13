package Code.src.Domain.Controllers.Drivers;

import Code.src.Domain.Classes.Document;
import Code.src.Domain.Classes.Folder;
import Code.src.Domain.Controllers.DomainController;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @file DomainControllerDriver.java
 * @brief Driver <em>DomainController</em>
 */

/**
 * @brief Driver of class DomainController
 *
 * @author Victor Mena Doz
 * @author Marc Navarro Acosta
 * @author Julia Alice Amenos Dien
 */

public class DomainControllerDriver {

    private static Scanner writer = new Scanner(System.in).useDelimiter(Pattern.compile("[\\r\\n;]+"));

    private static Folder rootFolder;
    private static Document initializeDocument() throws IOException {
        System.out.println("Introduce a title for the Document:");
        String title = readInputString();
        System.out.println("Introduce an author for the Document:");
        String author = readInputString();
        System.out.println("Introduce a Content for the Document:");
        String Content = readContent();
        System.out.println("Introduce a Language for the Document(ENG, CAT or ESP):");
        String lang = readInputString();
        Document doc = new Document(title,author,Content,lang);
        return doc;
    }

    private static void initializeFolder() throws IOException {
        Folder f = new Folder(0,"rootFolder");
        Document d = initializeDocument();
        f.createFolder("subFolder1",0);
        f.addDocumentToSubfolder(d, 1);
        Document d2 = initializeDocument();
        f.addDocument(d2);
        Document d3 = initializeDocument();
        f.addDocumentToSubfolder(d3, 1);
        Document d4 = initializeDocument();
        f.addDocumentToSubfolder(d4, 1);
        Document d5 = initializeDocument();
        f.addDocument(d5);
        rootFolder = f;
    }

    private static DomainController initialCreation() {
        DomainController dC = new DomainController();
        return dC;
    }

    public static void testDomainController(){
        System.out.println("Creating DomainController...");
        DomainController dC = initialCreation();
    }
    public static void testImportDocument(){
        DomainController dC = initialCreation();
        //Data controller implicate
    }
    public static void testExportDocument(){
        DomainController dC = initialCreation();
        //Data controller implicate
    }
    public static void testNewDocument() throws IOException {
        DomainController dC = initialCreation();
        System.out.println("Adding new document. First of all...");
        System.out.println("Introduce a title for the Document:");
        String title = readInputString();
        System.out.println("Introduce an author for the Document:");
        String author = readInputString();
        String content = readContent();
        System.out.println("Introduce a Language for the Document(ENG, CAT or ESP):");
        String lang = readInputString();
        dC.newDocument(author, title, content, lang);
        System.out.println("Added new document with title: " + title + " and author's name " + author);
        System.out.println();
    }
    public static void testModifyDocument() throws IOException {
        DomainController dC = initialCreation();
        System.out.println("Add document to modified later:");
        System.out.println("Introduce a title for the Document:");
        String title = readInputString();
        System.out.println("Introduce an author for the Document:");
        String author = readInputString();
        String content = readContent();
        System.out.println("Introduce a Language for the Document(ENG, CAT or ESP):");
        String lang = readInputString();
        dC.newDocument(author, title, content, lang);
        System.out.println("Select document to modify:");
        System.out.println("Introduce title of the Document:");
        title = readInputString();
        System.out.println("Introduce author of the Document:");
        author = readInputString();
        System.out.println("What do you want to modify: Author=0, Title=1 or Text=2 ?");
        int flag = readInputInteger();
        String newData;
        switch (flag){
            case 0:
                System.out.println("Introduce new Author:");
                newData = readInputString();
                break;
            case 1:
                System.out.println("Introduce new Title:");
                newData = readInputString();
                break;
            default:
                System.out.println("Introduce new Content:");
                newData = readContent();

        }
        dC.modifyDocument(author, title, newData, flag);
        System.out.println("Modified document.");
        System.out.println();
    }
    public static void testAuthorDocuments() throws IOException {
        DomainController dC = initialCreation();
        System.out.println("First add 3 documents:");
        System.out.println("Introduce a title for the first Document:");
        String title = readInputString();
        System.out.println("Introduce an author for the first Document:");
        String author = readInputString();
        String content = readContent();
        System.out.println("Introduce a Language for the first Document(ENG, CAT or ESP):");
        String lang = readInputString();
        dC.newDocument(author, title, content, lang);
        System.out.println("Introduce a title for the second Document:");
        title = readInputString();
        System.out.println("Introduce an author for the second Document:");
        author = readInputString();
        content = readContent();
        System.out.println("Introduce a Language for the second Document(ENG, CAT or ESP):");
        lang = readInputString();
        dC.newDocument(author, title, content, lang);
        System.out.println("Introduce a title for the third Document:");
        title = readInputString();
        System.out.println("Introduce an author for the third Document:");
        author = readInputString();
        content = readContent();
        System.out.println("Introduce a Language for the third Document(ENG, CAT or ESP):");
        lang = readInputString();
        dC.newDocument(author, title, content, lang);
        System.out.println("Introduce author's name to obtain his documents:");
        author = readInputString();
        ArrayList<String> result = dC.authorDocuments(author);
        System.out.println("List of documents of this author:");
        int i = 0;
        for(String tit : result) {
            System.out.println(i + ". " + tit);
            ++i;
        }
        System.out.println();
    }
    public static void testSearchAuthors() throws IOException {
        DomainController dC = initialCreation();
        System.out.println("First add 3 documents:");
        System.out.println("Introduce a title for the first Document:");
        String title = readInputString();
        System.out.println("Introduce an author for the first Document:");
        String author = readInputString();
        String content = readContent();
        System.out.println("Introduce a Language for the first Document(ENG, CAT or ESP):");
        String lang = readInputString();
        dC.newDocument(author, title, content, lang);
        System.out.println("Introduce a title for the second Document:");
        title = readInputString();
        System.out.println("Introduce an author for the second Document:");
        author = readInputString();
        content = readContent();
        System.out.println("Introduce a Language for the second Document(ENG, CAT or ESP):");
        lang = readInputString();
        dC.newDocument(author, title, content, lang);
        System.out.println("Introduce a title for the third Document:");
        title = readInputString();
        System.out.println("Introduce an author for the third Document:");
        author = readInputString();
        content = readContent();
        System.out.println("Introduce a Language for the third Document(ENG, CAT or ESP):");
        lang = readInputString();
        dC.newDocument(author, title, content, lang);
        System.out.println("Introduce prefix to search authors:");
        String prefix = readInputString();
        ArrayList<String> result = dC.searchAuthors(prefix);
        System.out.println("List of authors with this prefix");
        int i = 0;
        for(String aut : result) {
            System.out.println(i + ". " + aut);
            ++i;
        }
        System.out.println();
    }
    public static void testGetDocument() throws IOException {
        DomainController dC = initialCreation();
        System.out.println("Adding new document. First of all...");
        System.out.println("Introduce a title for the Document:");
        String title = readInputString();
        System.out.println("Introduce an author for the Document:");
        String author = readInputString();
        String content = readContent();
        System.out.println("Introduce a Language for the Document(ENG, CAT or ESP):");
        String lang = readInputString();
        dC.newDocument(author, title, content, lang);
        ArrayList<String> d = dC.getDocument(author,title);
        System.out.println("Document's title: " + d.get(0));
        System.out.println("Document's author: " + d.get(1));
        System.out.println("Document's text: " + d.get(2));
        System.out.println();
    }
    public static void testAppearanceSearch() throws IOException {
        initializeFolder();
        DomainController dC = new DomainController(rootFolder);
        System.out.println("Title of the document to get appearance documents:");
        String title = readInputString();
        System.out.println("Author's name of the document to get appearance documents:");
        String author = readInputString();
        System.out.println("Introduce number of documents do you want obtain:");
        int k = readInputInteger();
        HashMap<String, String> result = dC.appearanceSearch(author, title, k);
        System.out.println("More relevant documents:");
        int i = 1;
        for(HashMap.Entry<String, String> doc : result.entrySet()) {
            System.out.println(i + ". " + "Title: " + doc.getKey() + "; Author: " + doc.getValue());
            ++i;
        }
        System.out.println();
    }
    public static void testBooleanExpressionSearch() throws Exception {
        initializeFolder();
        DomainController dC = new DomainController(rootFolder);
        System.out.println("Introduce your boolean expression: ");
        String boolExp = readInputString();
        HashMap<String, String> docs = dC.booleanExpressionSearch(boolExp);
        System.out.println("Documents validated by the expression:");
        for(HashMap.Entry<String, String> doc : docs.entrySet()) {
            System.out.println("Title: " + doc.getKey() + "; Author: " + doc.getValue());
        }
        System.out.println();
    }
    public static void testDocumentsQuery() throws IOException {
        initializeFolder();
        DomainController dC = new DomainController(rootFolder);
        System.out.println("Introduce words to look for:");
        String pWords = readInputString();
        System.out.println("Introduce number of documents do you want obtain:");
        int k = readInputInteger();
        HashMap<String, String> result = dC.documentsQuery(pWords,"ENG", k);
        System.out.println("More relevant documents:");
        int i = 1;
        for(HashMap.Entry<String, String> doc : result.entrySet()) {
            System.out.println(i + ". " + "Title: " + doc.getKey() + "; Author: " + doc.getValue());
            ++i;
        }
        System.out.println();
    }
    public static void testSaveDocument(){
        DomainController dC = initialCreation();
        //Data controller implicate
    }
    public static void testDeleteDocument() throws IOException {
        DomainController dC = initialCreation();
        System.out.println("Adding new document. First of all...");
        System.out.println("Introduce a title for the Document:");
        String title = readInputString();
        System.out.println("Introduce an author for the Document:");
        String author = readInputString();
        String content = readContent();
        System.out.println("Introduce a Language for the Document(ENG, CAT or ESP):");
        String lang = readInputString();
        dC.newDocument(author, title, content, lang);
        System.out.println("Introduce title of the document to delete:");
        title = readInputString();
        System.out.println("Introduce author's name of the document to delete:");
        author = readInputString();
        dC.deleteDocument(author, title);
        System.out.println("Document deleted.");
        System.out.println();
    }
    public static void testProtectDocument() throws IOException {
        DomainController dC = initialCreation();
        System.out.println("Adding new document. First of all...");
        System.out.println("Introduce a title for the Document:");
        String title = readInputString();
        System.out.println("Introduce an author for the Document:");
        String author = readInputString();
        String content = readContent();
        System.out.println("Introduce a Language for the Document(ENG, CAT or ESP):");
        String lang = readInputString();
        dC.newDocument(author, title, content, lang);
        System.out.println("Introduce a title of the Document to protect:");
        title = readInputString();
        System.out.println("Introduce author's name of the Document to protect:");
        author = readInputString();
        System.out.println("Introduce password to protect the document:");
        String password = readInputString();
        dC.protectDocument(author, title, password);
        System.out.println("Document protected");
        System.out.println();
    }
    public static void testNewFolder(){
        Folder root = new Folder(1, "root");
        DomainController dC = new DomainController(root);
        System.out.println("Introduce name for the new folder:");
        String fName = readInputString();
        dC.newFolder(fName, 1);
        System.out.println("Created new folder with name: " + fName);
        System.out.println();
    }
    public static void main(String[] args) throws Exception {
        String functions = "0. All\n" +
                "1. testDomainController\n" +
                "2. testImportDocument\n" +
                "3. testExportDocument\n" +
                "4. testNewDocument\n" +
                "5. testModifyDocument\n" +
                "6. testAuthorDocuments\n" +
                "7. testSearchAuthors\n" +
                "8. testGetDocument\n" +
                "9. testAppearanceSearch\n" +
                "10. testBooleanExpressionSearch\n" +
                "11. testDocumentsQuery\n" +
                "12. testSaveDocument\n" +
                "13. testDeleteDocument\n" +
                "14. testProtectDocument\n" +
                "15. testNewFolder\n" +
                "16. Exit\n";

        System.out.println("FoldersController Driver:");
        System.out.println("Introduce the number allocated to the function you want to test.");
        System.out.println("Functions:");
        System.out.println(functions);
        int code = readInputInteger();
        while (code != 16) {
            switch (code) {
                case 1:
                    System.out.println("testDomainController() choose:");
                    testDomainController();
                    break;
                case 2:
                    System.out.println("testImportDocument() choose:");
                    testImportDocument();
                    break;
                case 3:
                    System.out.println("testExportDocument() choose:");
                    testExportDocument();
                    break;
                case 4:
                    System.out.println("testNewDocument() choose:");
                    testNewDocument();
                    break;
                case 5:
                    System.out.println("testModifyDocument() choose:");
                    testModifyDocument();
                    break;
                case 6:
                    System.out.println("testAuthorDocuments() choose:");
                    testAuthorDocuments();
                    break;
                case 7:
                    System.out.println("testSearchAuthor() choose:");
                    testSearchAuthors();
                    break;
                case 8:
                    System.out.println("testGetDocument() choose:");
                    testGetDocument();
                    break;
                case 9:
                    System.out.println("testAppearanceSearch() choose:");
                    testAppearanceSearch();
                    break;
                case 10:
                    System.out.println("testBooleanExpressionSearch() choose:");
                    testBooleanExpressionSearch();
                    break;
                case 11:
                    System.out.println("testDocumentsQuery() choose:");
                    testDocumentsQuery();
                    break;
                case 12:
                    System.out.println("testSaveDocument() choose:");
                    testSaveDocument();
                    break;
                case 13:
                    System.out.println("testDeleteDocument() choose:");
                    testDeleteDocument();
                    break;
                case 14:
                    System.out.println("testProtectDocument() choose:");
                    testProtectDocument();
                    break;
                case 15:
                    System.out.println("testNewFolder() choose:");
                    testNewFolder();
                    break;
                default:
                    System.out.println("testDomainController() choose:");
                    testDomainController();
                    System.out.println("testImportDocument() choose:");
                    testImportDocument();
                    System.out.println("testExportDocument() choose:");
                    testExportDocument();
                    System.out.println("testNewDocument() choose:");
                    testNewDocument();
                    System.out.println("testModifyDocument() choose:");
                    testModifyDocument();
                    System.out.println("testAuthorDocuments() choose:");
                    testAuthorDocuments();
                    System.out.println("testSearchAuthor() choose:");
                    testSearchAuthors();
                    System.out.println("testGetDocument() choose:");
                    testGetDocument();
                    System.out.println("testAppearanceSearch() choose:");
                    testAppearanceSearch();
                    System.out.println("testBooleanExpressionSearch() choose:");
                    testBooleanExpressionSearch();
                    System.out.println("testDocumentsQuery() choose:");
                    testDocumentsQuery();
                    System.out.println("testSaveDocument() choose:");
                    testSaveDocument();
                    System.out.println("testDeleteDocument() choose:");
                    testDeleteDocument();
                    System.out.println("testProtectDocument() choose:");
                    testProtectDocument();
                    System.out.println("testNewFolder() choose:");
                    testNewFolder();
            }
            System.out.println();
            code = readInputInteger();
        }
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

    private static String readInputString() {
        String inp = writer.next();
        return inp;
    }
}
