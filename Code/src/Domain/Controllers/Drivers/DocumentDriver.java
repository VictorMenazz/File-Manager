package Code.src.Domain.Controllers.Drivers;

import Code.src.Domain.Classes.Content;
import Code.src.Domain.Classes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @file DocumentDriver.java
 * @brief Test <em>Document</em>
 */

/**
 * @brief Driver of class Document
 *
 * @author Marc Navarro Acosta
 */

public class DocumentDriver {
    private static Scanner writer = new Scanner(System.in).useDelimiter(Pattern.compile("[\\r\\n;]+"));

    public static Document initialCreation1() throws IOException {
        System.out.println("Introduce a title for the Document:");
        String title = readInputString();
        System.out.println("Introduce an author for the Document:");
        String author = readInputString();
        System.out.println("Introduce a Content for the Document:");
        String Content = readContent();
        System.out.println("Introduce a Language for the Document:");
        String lang = readInputString();
        Document doc = new Document(title,author,Content,lang);
        return doc;
    }

    public static void testDocumentConstruct1() throws IOException {
        //Testing initializing way1
        Document d = initialCreation1();
        System.out.println("Document created -> Title:" + d.getTitle() + " Author:" + d.getAuthor());
        System.out.println();
    }

    public static void testDocumentConstruct2() throws IOException {
        //Testing initializing way1
        System.out.println("Introduce a title for the Document:");
        String title = readInputString();
        System.out.println("Introduce an author for the Document:");
        String author = readInputString();
        System.out.println("Introduce a Content for the Document:");
        String ContentText = readContent();
        System.out.println("Introduce a Language for the Document:");
        String lang = readInputString();

        Content Content = new Content(ContentText, lang);
        Document d = new Document(title, author, Content, lang);
        System.out.println("Document created -> Title:" + d.getTitle() + " Author:" + d.getAuthor());

        System.out.println();
    }

    public static void testSetAuthor() throws IOException {
        Document d = initialCreation1();
        System.out.println("Introduce the new Author for the Document:");
        String newAuth = readInputString();
        d.setAuthor(newAuth);
        System.out.println("Author changed to: " + d.getAuthor());
        System.out.println();
    }

    public static void testSetTitle() throws IOException {
        Document d = initialCreation1();
        System.out.println("Introduce the new Title for the Document:");
        String newTitle = readInputString();
        d.setTitle(newTitle);
        System.out.println("Title changed to: " + d.getTitle());
        System.out.println();
    }

    public static void testSetContent() throws IOException {
        Document d = initialCreation1();
        System.out.println("Introduce the new Content for the Document:");
        String newCont = readContent();
        Content c = new Content(newCont,d.getLanguage());
        d.setContent(c);
        //Falta getContentOnPlainText
        System.out.println("Checking the new Content: " + d.getContent());
        System.out.println();
    }

    public static void testSetLanguage() throws IOException {
        Document d = initialCreation1();
        System.out.println("Introduce the new Language for the Document:");
        String newLang = readInputString();
        d.setLanguage(newLang);
        System.out.println("Language changed to: " + d.getLanguage());
        System.out.println();
    }

    public static void testProtectDocument() throws IOException {
        Document d = initialCreation1();
        System.out.println("Introduce the password for the Document:");
        String passw = readInputString();
        d.protectDocument(passw);
        System.out.println("Document is protected: " + d.isProtected());
        System.out.println();
    }

    public static void testGetTitle() throws IOException {
        Document d = initialCreation1();
        System.out.println("Title: " + d.getTitle());
        System.out.println();
    }

    public static void testGetAuthor() throws IOException {
        Document d = initialCreation1();
        System.out.println("Author: " + d.getTitle());
        System.out.println();
    }

    public static void testGetContent() throws IOException {
        Document d = initialCreation1();
        System.out.println("Content: " + d.getContent());
        System.out.println();
    }

    public static void testGetLanguage() throws IOException {
        Document d = initialCreation1();
        System.out.println("Language: " + d.getLanguage());
        System.out.println();
    }


    public static void testContentSearch() throws IOException {
        Document d = initialCreation1();
        System.out.println("Checking frequency internal vector:");
        HashMap<String,Integer> docMap = d.contentSearch();
        for(HashMap.Entry<String, Integer> doc : docMap.entrySet()){
                System.out.println(doc.getKey() + ':' + doc.getValue());
        }
        System.out.println();
    }

    public static void testIsProtected() throws IOException {
        Document d = initialCreation1();
        System.out.println("Document Status, Protected: " + d.isProtected());
        System.out.println("Introduce new password:");
        d.protectDocument(readInputString());
        System.out.println("Document Status, Protected: " + d.isProtected());
        System.out.println();
    }


    //Private functions not need to be proved.
    public static void main(String[] args) throws IOException {
        String functions = "0. All\n" +
                "1. testDocumentConstruct1\n" +
                "2. testDocumentConstruct2\n" +
                "3. testSetAuthor\n" +
                "4. testSetTitle\n" +
                "5. testSetContent\n" +
                "6. testSetLanguage\n" +
                "7. testProtectDocument\n" +
                "8. testGetTitle\n" +
                "9. testGetAuthor\n" +
                "10. testGetContent\n" +
                "11. testGetLanguage\n" +
                "12. testContentSearch\n" +
                "13. testIsProtected\n" +
                "14. Exit\n";

        System.out.println("Document Driver:");
        System.out.println("Introduce the number allocated to the function you want to test.");
        System.out.println("Functions:");
        System.out.println(functions);
        int code = readInputInteger();
        while(code != 14) {
            switch (code) {
                case 1:
                    System.out.println("testDocumentConstruct1() choose:");
                    testDocumentConstruct1();
                    break;
                case 2:
                    System.out.println("testDocumentConstruct2() choose:");
                    testDocumentConstruct2();
                    break;
                case 3:
                    System.out.println("testSetAuthor() choose:");
                    testSetAuthor();
                    break;
                case 4:
                    System.out.println("testSetTitle() choose:");
                    testSetTitle();
                    break;
                case 5:
                    System.out.println("testSetContent() choose:");
                    testSetContent();
                    break;
                case 6:
                    System.out.println("testSetLanguage() choose:");
                    testSetLanguage();
                    break;
                case 7:
                    System.out.println("testProtectDocument() choose:");
                    testProtectDocument();
                    break;
                case 8:
                    System.out.println("testGetTitle() choose:");
                    testGetTitle();
                    break;
                case 9:
                    System.out.println("testGetAuthor() choose:");
                    testGetAuthor();
                    break;
                case 10:
                    System.out.println("testGetContent() choose:");
                    testGetContent();
                    break;
                case 11:
                    System.out.println("testGetLanguage() choose:");
                    testGetLanguage();
                    break;
                case 12:
                    System.out.println("testContentSearch() choose:");
                    testContentSearch();
                    break;
                case 13:
                    System.out.println("testIsProtected() choose:");
                    testIsProtected();
                    break;
                default:
                    System.out.println("testDocumentConstruct1() test:");
                    testDocumentConstruct1();
                    System.out.println("testDocumentConstruct2() test:");
                    testDocumentConstruct2();
                    System.out.println("testSetAuthor() test:");
                    testSetAuthor();
                    System.out.println("testSetTitle() test:");
                    testSetTitle();
                    System.out.println("testSetContent() test:");
                    testSetContent();
                    System.out.println("testSetLanguage() test:");
                    testSetLanguage();
                    System.out.println("testProtectDocument() test:");
                    testProtectDocument();
                    System.out.println("testGetTitle() test:");
                    testGetTitle();
                    System.out.println("testGetAuthor() test:");
                    testGetAuthor();
                    System.out.println("testGetContent() test:");
                    testGetContent();
                    System.out.println("testGetLanguage() test:");
                    testGetLanguage();
                    System.out.println("testContentSearch() test:");
                    testContentSearch();
                    System.out.println("testIsProtected() test:");
                    testIsProtected();
            }
            code = readInputInteger();
        }
    }

    private static String readInputString() {
        String inp = writer.next();
        return inp;
    }

    private static int readInputInteger() {
        Integer inp = writer.nextInt();
        return inp;
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
