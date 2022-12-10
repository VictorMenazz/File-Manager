package FONTS.src.Domain.Controllers.JUnits;

import FONTS.src.DocumentsException;
import FONTS.src.Domain.Classes.Author;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @file AuthorTest.java
 * @brief Test Junit <em>Author</em>
 */

/**
 * @brief Test of class Author
 *
 * @author Jordi Soley Masats
 */

public class AuthorTest {
    @Test
    public void testSetName() {
        String new_name = "Jordi";
        Author instance = new Author(null);
        instance.setName(new_name);
        assertEquals(new_name, instance.getName());
    }

    @Test
    public void testGetName() {
        Author instance = new Author("Jordi");
        assertEquals("Jordi",instance.getName());
    }

    @Test
    public void testGetTitles() throws DocumentsException {
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
    public void testGetNumTitles() throws DocumentsException {
        Author instance = new Author("Jordi");
        instance.addTitle("Doc1");
        instance.addTitle("Doc2");
        assertEquals(2,instance.getNumTitles());
    }

    @Test
    public void testAddTitle() throws DocumentsException {
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
    public void testDelTitle() throws DocumentsException {
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
    public void testMatchesPrefix() {
        Author instance = new Author("Jordi");
        assertTrue(instance.matchesPrefix("Jor"));
        assertTrue(instance.matchesPrefix("J"));
        assertFalse(instance.matchesPrefix("ordi"));
        assertFalse(instance.matchesPrefix("marc"));
    }

}