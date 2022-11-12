package Code.src.Domain.Controllers.Drivers;

import Code.src.Domain.Classes.Author;

import java.util.ArrayList;
import java.util.Scanner;

public class AuthorDriver {
    private static Scanner writer = new Scanner(System.in).useDelimiter("\n");

    private static void addNtitles(Author aut) {
        System.out.print("Enter the number of titles to be added: ");
        int n = readInputInteger();
        System.out.println("");
        String title;
        for (int i = 1; i <= n; ++i) {
            System.out.print("Write the next title (num "+ i +"): ");
            title = readInputString();
            System.out.println("");
            aut.addTitle(title);
        }
    }

    public static Author initialCreation1() {
        System.out.print("Introduce a name for the Author: ");
        String authorName = readInputString();
        System.out.println("");
        Author aut = new Author(authorName);
        return aut;
    }

    public static void testAuthorConstruct() {
        Author aut = initialCreation1();
        System.out.println("Author created -> Name: " + aut.getName() + " with " + aut.getNumTitles() + " docs");
    }

    public static void testSetName() {
        Author aut = initialCreation1();
        System.out.println("Author created -> Name: " + aut.getName());
        System.out.print("Introduce a new name for the Author: ");
        String new_name = readInputString();
        System.out.println("");
        aut.setName(new_name);
        System.out.println("The name of the Author has been changed to " + aut.getName());
    }

    public static void testGetName() {
        Author aut = initialCreation1();
        System.out.println("Author's name: " + aut.getName());
    }

    public static void testGetTitles() {
        Author aut = initialCreation1();
        addNtitles(aut);
        ArrayList<String> titles = aut.getTitles();
        System.out.println("Author have the following titles: ");
        for (String t : titles) {
            System.out.println(t);
        }
        System.out.println("-------");
    }

    public static void testGetNumTitles() {
        Author aut = new Author("Jordi");
        addNtitles(aut);
        System.out.println(aut.getNumTitles() + " titles have been added");
    }

    public static void testAddTitle() {
        Author aut = new Author("Jordi");
        System.out.print("Enter the title to be added: ");
        String title = readInputString();
        System.out.println("");
        aut.addTitle(title);
        if (aut.getTitles().contains(title)) {
            System.out.println("The title -> " + title + " <- has been added");
        }
    }

    public static void testDelTitle() {
        Author aut = new Author("Jordi");
        System.out.print("Enter the title to be added: ");
        String title = readInputString();
        System.out.println("");
        aut.addTitle(title);
        if (aut.getTitles().contains(title)) {
            System.out.println("The title -> " + title + " <- has been added");
        }
        aut.delTitle(title);
        if (!aut.getTitles().contains(title)) {
            System.out.println("The title -> " + title + " <- has been deleted correctly");
        }
    }

    public static void testMatchesPrefix() {
        Author aut = initialCreation1();
        System.out.print("Enter the prefix you want to check with the author : ");
        String prefix = readInputString();
        System.out.println("");
        if (aut.matchesPrefix(prefix)) {
            System.out.println("Author \""+ aut.getName() +"\" BEGINS with: " + prefix);
        }
        else {
            System.out.println("Author \""+ aut.getName() +"\" DOESN'T begins with \"" + prefix +"\"");
        }
    }

    public static void main(String[] args) {
        String functions = "0. All\n" +
                "1. testAuthorConstruct\n" +
                "2. testSetName\n" +
                "3. testGetName\n" +
                "4. testGetTitles\n" +
                "5. testGetNumTitles\n" +
                "6. testAddTitle\n" +
                "7. testDelTitle\n" +
                "8. testMatchesPrefix\n" +
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
}
