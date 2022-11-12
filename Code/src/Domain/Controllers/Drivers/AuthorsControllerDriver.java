package Code.src.Domain.Controllers.Drivers;

import Code.src.Domain.Classes.Author;
import Code.src.Domain.Controllers.AuthorsController;

import java.util.ArrayList;
import java.util.Scanner;

public class AuthorsControllerDriver {
    /*
    private static Scanner writer = new Scanner(System.in).useDelimiter("\n");

    public static void testAuthorsControllerConstruct() {
        AuthorsController ctrlAuthors = new AuthorsController();
    }

    public static void addAuthor(String name, Author a) {

    }

    public static void delAuthor(String name) {

    }

    public static int size() {

    }

    public static ArrayList<String> searchAuthorsPrefix(String prefix) {

    }


    public static ArrayList<String> searchAuthorDocuments(String authorName) {

    }


    public static void addTitleAuthor(String authorName, String title) {

    }

    public static void delTitleAuthor(String authorName, String title) {

    }

    public static void main(String[] args) {
        String functions = "0. All\n" +
                "1. testAuthorControllerConstruct\n" +
                "2. testAddAuthor\n" +
                "3. testDelAuthor\n" +
                "4. testSize\n" +
                "5. testSearchAuthorsPrefix\n" +
                "6. testSearchAuthorDocuments\n" +
                "7. testAddTitleAuthor\n" +
                "8. testDelTitleAuthor\n" +
                "9. Exit\n";

        System.out.println("Author Driver:");
        System.out.println("Introduce the number allocated to the function you want to test.");
        System.out.println("Functions:");
        System.out.println(functions);
        int code = readInputInteger();
        while (code != 9) {
            switch (code) {
                case 1:
                    System.out.println("testAuthorConstruct() choose:");
                    testAuthorConstruct();
                    break;
                case 2:
                    System.out.println("testSetName() choose:");
                    testSetName();
                    break;
                case 3:
                    System.out.println("testGetName() choose:");
                    testGetName();
                    break;
                case 4:
                    System.out.println("testGetTitles() choose:");
                    testGetTitles();
                    break;
                case 5:
                    System.out.println("testGetNumTitles() choose:");
                    testGetNumTitles();
                    break;
                case 6:
                    System.out.println("testAddTitle() choose:");
                    testAddTitle();
                    break;
                case 7:
                    System.out.println("testDelTitle() choose:");
                    testDelTitle();
                    break;
                case 8:
                    System.out.println("testMatchesPrefix() choose:");
                    testMatchesPrefix();
                    break;
                default:
                    System.out.println("testAuthorConstruct() test:");
                    testAuthorConstruct();
                    System.out.println("");
                    System.out.println("testSetName() test:");
                    testSetName();
                    System.out.println("");
                    System.out.println("testGetName() test:");
                    testGetName();
                    System.out.println("");
                    System.out.println("testGetTitles() test:");
                    testGetTitles();
                    System.out.println("");
                    System.out.println("testGetNumTitles() test:");
                    testGetNumTitles();
                    System.out.println("");
                    System.out.println("testAddTitle() test:");
                    testAddTitle();
                    System.out.println("");
                    System.out.println("testDelTitle() test:");
                    testDelTitle();
                    System.out.println("");
                    System.out.println("testMatchesPrefix() test:");
                    testMatchesPrefix();
                    System.out.println("");
                    System.out.println("testMatchesPrefix() test 2:");
                    testMatchesPrefix();
                    break;
            }
            System.out.println("Enter the number of the function to be tested");
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
*/
}
