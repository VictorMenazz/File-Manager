package FONTS.src.Domain.Controllers.Drivers;

import FONTS.src.DocumentsException;
import FONTS.src.Domain.Classes.Author;
import FONTS.src.Domain.Classes.Content;
import FONTS.src.Domain.Classes.Document;
import FONTS.src.Domain.Controllers.AuthorsController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @file AuthorsControllerDriver.java
 * @brief Test <em>AuthorsController</em>
 */

/**
 * Driver of class AuthorsController
 *
 * @author Jordi Soley Masats
 */

public class AuthorsControllerDriver {
    private static Scanner writer = new Scanner(System.in).useDelimiter(Pattern.compile("[\\r\\n;]+"));

    private static void showAuthors(AuthorsController ctrlAuthors) {
        ArrayList<String> authors = ctrlAuthors.getAuthorsName();
        for (String aut : authors) {
            System.out.println(aut);
        }
        if (authors.size() == 0) System.out.println("No authors");
    }

    private static int addNAuthors(AuthorsController ctrlAuthors) throws DocumentsException {
        System.out.print("Enter the number of Authors to be added:");
        int n = readInputInteger();
        System.out.println();
        String newName;
        for (int i = 1; i <= n; ++i) {
            System.out.print("Enter the Author name to be added:");
            newName = readInputString();
            System.out.println();
            ctrlAuthors.addAuthor(newName, new Author(newName));
        }
        return n;
    }

    public static void testAuthorsControllerConstruct() {
        AuthorsController ctrlAuthors = new AuthorsController();
        System.out.println("AuthorsController has been created");
    }

    public static void testAddAuthor() throws DocumentsException {
        AuthorsController ctrlAuthors = new AuthorsController();
        System.out.println("List of the names of the authors before adding the Author:");
        showAuthors(ctrlAuthors);
        System.out.print("Enter the Author name to be added:");
        String newAuthor = readInputString();
        ctrlAuthors.addAuthor(newAuthor, new Author(newAuthor));
        System.out.println();
        System.out.println("List of the names of the authors after adding the Author:");
        showAuthors(ctrlAuthors);
    }

    public static void testDelAuthor() throws DocumentsException {
        AuthorsController ctrlAuthors = new AuthorsController();
        System.out.print("Enter the author name to be added:");
        String newAuthor = readInputString();
        System.out.println();
        ctrlAuthors.addAuthor(newAuthor, new Author(newAuthor));
        System.out.println("List of the names of the authors after adding the Author:");
        showAuthors(ctrlAuthors);
        ctrlAuthors.delAuthor(newAuthor);
        System.out.println("List of the names of the authors after deleting the author \"" + newAuthor + "\":");
        showAuthors(ctrlAuthors);
    }

    public static void testSize() throws DocumentsException {
        AuthorsController ctrlAuthors = new AuthorsController();
        int n = addNAuthors(ctrlAuthors);
        System.out.println("List of the names of the authors after adding " + n + " Authors:");
        showAuthors(ctrlAuthors);
        System.out.println("-> There are " + ctrlAuthors.size() + " Authors");
    }

    public static void testSearchAuthorsPrefix() throws DocumentsException {
        AuthorsController ctrlAuthors = new AuthorsController();
        System.out.println("Created a AuthorsController with 0 Authors:");
        int n = addNAuthors(ctrlAuthors);
        System.out.print("Enter the prefix on which you want to get the authors starting with this prefix:");
        String prefix = readInputString();
        System.out.println();
        ArrayList<String> namesMatches = ctrlAuthors.searchAuthorsPrefix(prefix);
        System.out.println("The following Authors begin with prefix \"" + prefix + "\":");
        ArrayList<String> allNames = ctrlAuthors.getAuthorsName();
        for (String name : namesMatches) {
            System.out.println(name);
            allNames.remove(name);
        }
        System.out.println("The following Authors DON'T begin with this prefix:");
        for (String name : allNames) {
            System.out.println(name);
        }
    }

    public static void testSearchAuthorDocuments() throws DocumentsException {
        AuthorsController ctrlAuthors = new AuthorsController();
        Author aut = new Author("Jordi");
        System.out.println("Jordi doesn't have documents");
        System.out.print("Enter the number titles to be added to Author \"Jordi\":");
        int n = readInputInteger();
        System.out.println();
        String titleToAdd;
        for (int i = 1; i <= n; ++i) {
            System.out.print("Enter a title to be added (num " + i + "):");
            titleToAdd = readInputString();
            System.out.println();
            ctrlAuthors.addTitleAuthor("Jordi", titleToAdd);
        }
        System.out.println("Jordi's documents are the following:");
        ArrayList<String> titlesJordi = ctrlAuthors.searchAuthorDocuments("Jordi");
        for (String t : titlesJordi) {
            System.out.println(t);
        }
    }

    public static void testAddTitleAuthor() throws DocumentsException {
        AuthorsController ctrlAuthors = new AuthorsController();
        Author aut = new Author("Jordi");
        ctrlAuthors.addAuthor("Jordi", aut);
        System.out.println("Author Jordi has been added");
        System.out.print("Enter the title to be added to Jordi:");
        String newTitle = readInputString();
        System.out.println("");
        ctrlAuthors.addTitleAuthor("Jordi", newTitle);
        System.out.println("Titles of Jordi's documents after adding the title \"" + newTitle + "\":");
        ArrayList<String> titlesJordi = ctrlAuthors.searchAuthorDocuments("Jordi");
        for (String t : titlesJordi) {
            System.out.println(t);
        }
    }

    public static void testDelTitleAuthor() throws DocumentsException {
        AuthorsController ctrlAuthors = new AuthorsController();
        Author aut = new Author("Jordi");
        aut.addTitle("testTitle");
        ctrlAuthors.addAuthor("Jordi", aut);
        System.out.println("Author Jordi has been added");
        System.out.print("Enter the title to be added to Jordi:");
        String newTitle = readInputString();
        System.out.println("");
        ctrlAuthors.addTitleAuthor("Jordi", newTitle);
        System.out.println("Titles of Jordi's documents after adding the title \"" + newTitle + "\":");
        ArrayList<String> titlesJordi = ctrlAuthors.searchAuthorDocuments("Jordi");
        for (String t : titlesJordi) {
            System.out.println(t);
        }
        System.out.print("Enter the title you want to delete to Jordi:");
        String titleToDelete = readInputString();
        System.out.println();
        if (titlesJordi.contains(titleToDelete)) {
            ctrlAuthors.delTitleAuthor("Jordi", titleToDelete);
            titlesJordi = ctrlAuthors.searchAuthorDocuments("Jordi");
            System.out.println("Titles of Jordi's documents after deleting the title \"" + titleToDelete + "\":");
            if (titlesJordi.size() != 0) {
                for (String t : titlesJordi) {
                    System.out.println(t);
                }
            }
            else System.out.println("Jordi doesn't have documents");
        }
        else System.out.println("The title doesn't belong to the author...");
    }

    public static void getJSON() throws IOException, DocumentsException {
        AuthorsController aC = new AuthorsController();
        System.out.println("Introduce Author name:");
        String name = readInputString();
        Author a = new Author(name);
        aC.addAuthor(name, a);
        System.out.println("Introduce the number of Author Docs:");
        int n = readInputInteger();
        for (int i = 0; i < n; ++i){
            System.out.println("Introduce the title of the Doc:");
            String title = readInputString();
            aC.addTitleAuthor(name, title);
        }
        System.out.println(aC.saveAuthorsStructure());
    }

    public static void recoverFromJSON() throws IOException, DocumentsException {
        System.out.println("Introduce JSON:");
        String JSON = readInputString();
        AuthorsController aC = new AuthorsController();
        aC.recoverFoldersStructure(JSON);
        for(String s : aC.searchAuthorDocuments("Marc")){
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws IOException, DocumentsException {
        //getJSON();
        recoverFromJSON();
        String functions = "0. All\n" +
                "1. testAuthorsControllerConstruct\n" +
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
                    System.out.println("testAuthorsControllerConstruct() choose:");
                    testAuthorsControllerConstruct();
                    break;
                case 2:
                    System.out.println("testAddAuthor() choose:");
                    testAddAuthor();
                    break;
                case 3:
                    System.out.println("testDelAuthor() choose:");
                    testDelAuthor();
                    break;
                case 4:
                    System.out.println("testSize() choose:");
                    testSize();
                    break;
                case 5:
                    System.out.println("testSearchAuthorsPrefix() choose:");
                    testSearchAuthorsPrefix();
                    break;
                case 6:
                    System.out.println("testSearchAuthorDocuments choose:");
                    testSearchAuthorDocuments();
                    break;
                case 7:
                    System.out.println("testAddTitleAuthor choose:");
                    testAddTitleAuthor();
                    break;
                case 8:
                    System.out.println("testDelTitleAuthor choose:");
                    testDelTitleAuthor();
                    break;
                default:
                    System.out.println("testAuthorConstruct() test:");
                    testAuthorsControllerConstruct();
                    System.out.println();
                    System.out.println("testAddAuthor() test:");
                    testAddAuthor();
                    System.out.println();
                    System.out.println("testDelAuthor() test:");
                    testDelAuthor();
                    System.out.println();
                    System.out.println("testSize() test:");
                    testSize();
                    System.out.println();
                    System.out.println("testSearchAuthorsPrefix() test:");
                    testSearchAuthorsPrefix();
                    System.out.println();
                    System.out.println("testSearchAuthorDocuments test:");
                    testSearchAuthorDocuments();
                    System.out.println();
                    System.out.println("testAddTitleAuthor test:");
                    testAddTitleAuthor();
                    System.out.println();
                    System.out.println("testDelTitleAuthor test:");
                    testDelTitleAuthor();
                    System.out.println();
                    System.out.println("testDelTitleAuthor test 2:");
                    testDelTitleAuthor();
                    break;
            }
            System.out.println();
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
