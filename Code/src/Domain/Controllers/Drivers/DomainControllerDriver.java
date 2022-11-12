package Code.src.Domain.Controllers.Drivers;

import Code.src.Domain.Classes.Folder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DomainControllerDriver {

    private static Scanner writer = new Scanner(System.in).useDelimiter(Pattern.compile("[\\r\\n;]+"));

    public static void testDomainController(){}
    public static void testImportDocument(){}
    public static void testExportDocument(){}
    public static void testNewDocument(){}
    public static void testModifyDocument(){}
    public static void testAuthorDocuments(){}
    public static void testSearchAuthors(){}
    public static void testGetDocument(){}
    public static void testAppearanceSearch(){}
    public static void testBooleanExpressionSearch(){}
    public static void testDocumentsQuery(){}
    public static void testSaveDocument(){}
    public static void testDeleteDocument(){}
    public static void testProtectDocument(){}
    public static void testNewFolder(){}
    public static void main(String[] args) throws IOException {
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
}
