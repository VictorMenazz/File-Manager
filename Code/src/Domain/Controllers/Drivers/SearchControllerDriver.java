package Code.src.Domain.Controllers.Drivers;

import Code.src.Domain.Classes.Document;
import Code.src.Domain.Classes.Folder;
import Code.src.Domain.Controllers.SearchController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @file SearchControllerDriver.java
 * @brief Test <em>Search Controller</em>
 */

/**
 * @brief Driver of the SearchController class
 *
 * @author Júlia Amenós Dien
 * @author Victor Mena Doz
 * @author Marc Navarro Acosta
 */
public class SearchControllerDriver {

    private static Scanner writer = new Scanner(System.in).useDelimiter(Pattern.compile("[\\r\\n;]+"));

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

    private static Folder initializeFolder() throws IOException {
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
        return f;
    }

    private static SearchController initialCreation() {
        SearchController sC = new SearchController();
        return sC;
    }

    public static void testConstructor() {
        System.out.println("Creating SearchController...");
        SearchController sC = initialCreation();
    }

    public static void testAddExpression() throws Exception {
        SearchController sC = initialCreation();
        System.out.println("Introduce new boolean expression:");
        String boolExp = readInputString();
        sC.addExpression(boolExp);
        System.out.println("Added new boolean expression: " + boolExp + " to list");
        System.out.println();
    }

    public static void testModifyExpression() throws Exception {
        SearchController sC = initialCreation();
        System.out.println("Introduce new boolean expression:");
        String oldBoolExp = readInputString();
        sC.addExpression(oldBoolExp);
        System.out.println("Introduce boolean expression to modify: ");
        String newBoolExp = readInputString();
        sC.modifyExpression(oldBoolExp, newBoolExp);
        System.out.println("Modified: " + newBoolExp);
        System.out.println();
    }

    public static void testDeleteExpression() throws Exception {
        SearchController sC = initialCreation();
        System.out.println("Add a boolean expression first: ");
        String boolExp = readInputString();
        sC.addExpression(boolExp);
        System.out.println("Introduce boolean expression to delete: ");
        String boolExp2 = readInputString();
        sC.deleteExpression(boolExp2);
    }

    public static void testGetListExp() {
        SearchController sC = initialCreation();
        String list = sC.getListExp();
        System.out.println(list);
    }

    public static void testBooleanExpressionSearch() throws Exception {
        SearchController sC = initialCreation();
        Folder f = initializeFolder();
        System.out.println("Introduce your boolean expression: ");
        String boolExp = readInputString();
        HashMap<String, String> docs = sC.booleanExpressionSearch(f, boolExp);
        System.out.println("Documents validated by the expression:");
        for(HashMap.Entry<String, String> doc : docs.entrySet()) {
            System.out.println("Title: " + doc.getKey() + "; Author: " + doc.getValue());
        }
        System.out.println();
    }


    public static void testAppearanceSearch() throws IOException {
        SearchController sC = initialCreation();
        Folder f = initializeFolder();
        System.out.println("Enter the title of the document: ");
        String title = readInputString();
        System.out.println("Enter the author's name of the document: ");
        String authorName = readInputString();
        System.out.println("Introduce number of documents do you want obtain:");
        int k = readInputInteger();
        HashMap<String, String> result = sC.appearanceSearch(f,authorName,title, k);
        System.out.println("More relevant documents:");
        int i = 1;
        for(HashMap.Entry<String, String> doc : result.entrySet()) {
            System.out.println(i + ". " + "Title: " + doc.getKey() + "; Author: " + doc.getValue());
            ++i;
        }
        System.out.println();
    }

    public static void testSearchDocument() throws IOException {
        SearchController sC = initialCreation();
        Folder f = initializeFolder();
        System.out.println("Introduce words to look for:");
        String pWords = readInputString();
        System.out.println("Introduce number of documents do you want obtain:");
        int k = readInputInteger();
        HashMap<String, String> result = sC.searchDocuments(f,pWords,"ENG", k);
        System.out.println("More relevant documents:");
        int i = 1;
        for(HashMap.Entry<String, String> doc : result.entrySet()) {
            System.out.println(i + ". " + "Title: " + doc.getKey() + "; Author: " + doc.getValue());
            ++i;
        }
        System.out.println();
    }


    public static void main(String[] args) throws Exception {
        String functions = "0. All\n" +
                "1. testConstructor\n" +
                "2. testAddExpression\n" +
                "3. testModifyExpression\n" +
                "4. testDeleteExpression\n" +
                "5. testGetListExp\n" +
                "6. testBooleanExpressionSearch\n" +
                "7. testAppearanceSearch\n" +
                "8. testSearchDocuments\n" +
                "9. Exit\n";
        System.out.println("Search Controller Driver:");
        System.out.println("Introduce the number allocated to the function you want to test.");
        System.out.println("Functions:");
        System.out.println(functions);
        int code = readInputInteger();
        while(code < 9 & code >= 0) {
            switch (code) {
                case 1:
                    System.out.println("testConstructor() choose:");
                    testConstructor();
                    break;
                case 2:
                    System.out.println("testAddExpression() choose:");
                    testAddExpression();
                    break;
                case 3:
                    System.out.println("testModifyExpression() choose:");
                    testModifyExpression();
                    break;
                case 4:
                    System.out.println("testDeleteExpression() choose:");
                    testDeleteExpression();
                    break;
                case 5:
                    System.out.println("testGetListExp() choose:");
                    testGetListExp();
                    break;
                case 6:
                    System.out.println("testBooleanExpression() choose:");
                    testBooleanExpressionSearch();
                    break;
                case 7:
                    System.out.println("testAppearanceSearch() choose:");
                    testAppearanceSearch();
                    break;
                case 8:
                    System.out.println("testSearchDocuments() choose:");
                    testSearchDocument();
                    break;
                default:
                    System.out.println("testConstructor() choose:");
                    testConstructor();
                    System.out.println("testAddExpression() choose:");
                    testAddExpression();
                    System.out.println("testModifyExpression() choose:");
                    testModifyExpression();
                    System.out.println("testDeleteExpression() choose:");
                    testDeleteExpression();
                    System.out.println("testGetListExp() choose:");
                    testGetListExp();
                    System.out.println("testBooleanExpression() choose:");
                    testBooleanExpressionSearch();
                    System.out.println("testAppearanceSearch() choose:");
                    testAppearanceSearch();
                    System.out.println("testSearchDocuments() choose:");
                    testSearchDocument();
            }
            code = readInputInteger();
        }
    }

    private static String readInputString() {
        String inp = writer.next();
        return inp;
    }

    private static String readContent() {
        System.out.println("Introduce text for the Content, return carrier when you finish and write _end_:");
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

    private static int readInputInteger() {
        Integer inp = writer.nextInt();
        return inp;
    }
}
