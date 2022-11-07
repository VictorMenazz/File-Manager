package Code.src.Domain.Controllers.Drivers;

import java.io.IOException;
import java.util.Scanner;

public class DocumentDriver {
    private static Scanner writer = new Scanner(System.in);

    public static void testDocumentConstruct1(){

    }

    public static void testDocumentConstruct2(){

    }

    public static void testSetAuthor(){

    }

    public static void testSetTitle(){

    }

    public static void testSetContent(){

    }

    public static void testSetLanguage(){

    }

    public static void testProtectDocument() {

    }

    public static void testGetTitle(){

    }

    public static void testGetAuthor(){

    }

    public static void testGetContent(){

    }

    public static void testGetLanguage() {

    }

    //Private functions not need to be proved.
    public static void main(String[] args) throws IOException {
        String functions = "0.All\n" +
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
                "11. testGetLanguage\n";

        System.out.println("Document Driver: ");
        System.out.println("Introduce the number allocated to the function you want to test.");
        System.out.println("Functions: ");
        System.out.println(functions);
        int code = readInputInteger();
        switch (code){
            case 1:
                System.out.println("testDocumentConstruct1() choose: ");
                testDocumentConstruct1();
                break;
            case 2:
                System.out.println("testDocumentConstruct2() choose: ");
                testDocumentConstruct2();
                break;
            case 3:
                System.out.println("testSetAuthor() choose: ");
                testSetAuthor();
                break;
            case 4:
                System.out.println("testSetTitle() choose: ");
                testSetTitle();
                break;
            case 5:
                System.out.println("testSetContent() choose: ");
                testSetContent();
                break;
            case 6:
                System.out.println("testSetLanguage() choose: ");
                testSetLanguage();
                break;
            case 7:
                System.out.println("testProtectDocument() choose: ");
                testProtectDocument();
                break;
            case 8:
                System.out.println("testGetTitle() choose: ");
                testGetTitle();
                break;
            case 9:
                System.out.println("testGetAuthor() choose: ");
                testGetAuthor();
                break;
            case 10:
                System.out.println("testGetContent() choose: ");
                testGetContent();
                break;
            case 11:
                System.out.println("testGetLanguage() choose: ");
                testGetLanguage();
                break;
            default:
                System.out.println("testDocumentConstruct1() test: ");
                testDocumentConstruct1();
                System.out.println("testDocumentConstruct2() test: ");
                testDocumentConstruct2();
                System.out.println("testSetAuthor() test: ");
                testSetAuthor();
                System.out.println("testSetTitle() test: ");
                testSetTitle();
                System.out.println("testSetContent() test: ");
                testSetContent();
                System.out.println("testSetLanguage() test: ");
                testSetLanguage();
                System.out.println("testProtectDocument() test: ");
                testProtectDocument();
                System.out.println("testGetTitle() test: ");
                testGetTitle();
                System.out.println("testGetAuthor() test: ");
                testGetAuthor();
                System.out.println("testGetContent() test: ");
                testGetContent();
                System.out.println("testGetLanguage() test: ");
                testGetLanguage();
        }
    }

    private static String readInputString() {
        String inp = writer.nextLine();
        return inp;
    }

    private static int readInputInteger() {
        Integer inp = writer.nextInt();
        return inp;
    }

}
