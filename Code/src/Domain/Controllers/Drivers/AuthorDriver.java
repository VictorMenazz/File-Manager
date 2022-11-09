package Code.src.Domain.Controllers.Drivers;

import Code.src.Domain.Classes.Author;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class AuthorDriver {
    private static Scanner writer = new Scanner(System.in);

    public static Author initialCreation1() {
        System.out.println("Introduce a name for the Author");
        String authorName = readInputString();
        Author aut = new Author(authorName);
        return aut;
    }

    public static void testSetName() {
        Author aut = initialCreation1();
        System.out.println("Author created -> Name:" + aut.getName() + "and " + aut.getNumTitles() + "docs");
        System.out.println("Introduce a new name for the Author");
        String new_name = readInputString();
        aut.setName(new_name);
    }

    public static void testGetName() {
        Author aut = initialCreation1();
        System.out.println("Author's name: " + aut.getName());
    }

    public static void testGetTitles() {

    }

    public static void testGetNumTitles() {

    }

    public static void testAddTitle() {

    }

    public static void testDelTitle() {

    }

    public static void testMatchesPrefix() {

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
