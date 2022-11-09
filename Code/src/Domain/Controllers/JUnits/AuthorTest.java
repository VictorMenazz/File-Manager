package Code.src.Domain.Controllers.JUnits;

import Code.src.Domain.Classes.Author;
import Code.src.Domain.Classes.Content;
import Code.src.Domain.Classes.Document;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * @author Jordi Soley Masats
 */
public class AuthorTest {
    @Test
    public static void TestSetName() {
        String new_name = "Jordi";
        Author instance = new Author();
        instance.setName(new_name);
        assertEquals(new_name, instance.getName());
    }

    @Test
    public static void TestGetName() {
        Author instance = new Author("Jordi");
        assertEquals("Jordi",instance.getName());
    }

    @Test
    public static void TestGetTitles() {
        Author instance = new Author("Jordi");
        String doc1 = "DocEn";
        String doc2 = "DocCat";
        String doc3 = "DocEs";
        instance.addTitle(doc1);
        instance.addTitle(doc2);
        instance.addTitle(doc3);
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add(doc1);
        expResult.add(doc2);
        expResult.add(doc3);
        assertEquals(expResult, instance.getTitles());
    }

    @Test
    public static void TestGetNumTitles() {
        Author instance = new Author("Jordi");
        instance.addTitle("Doc1");
        instance.addTitle("Doc2");
        assertEquals(2,instance.getNumTitles());
    }

    @Test
    public static void TestAddTitle() {
        Author instance = new Author("Jordi");
        String doc1 = "DocEn";
        String doc2 = "DocCat";
        String doc3 = "DocEs";
        instance.addTitle(doc1);
        instance.addTitle(doc2);
        instance.addTitle(doc3);
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add(doc1);
        expResult.add(doc2);
        expResult.add(doc3);
        assertEquals(expResult, instance.getTitles());
    }

    @Test
    public static void TestDelTitle() {
        Author instance = new Author("Jordi");
        String doc1 = "DocEn";
        String doc2 = "DocCat";
        String doc3 = "DocEs";
        instance.addTitle(doc1);
        instance.addTitle(doc2);
        instance.addTitle(doc3);
        instance.delTitle(doc3);
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add(doc1);
        expResult.add(doc2);
        assertEquals(expResult, instance.getTitles());
    }

    @Test
    public static boolean TestMatchesPrefix() {
        Author instance = new Author("Jordi");
        assertEquals(true,"Jor");
        assertEquals(true, "J");
        assertEquals(false, "ordi");
        assertEquals(false, "marc");
    }

}