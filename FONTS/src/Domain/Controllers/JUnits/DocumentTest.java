package FONTS.src.Domain.Controllers.JUnits;

import FONTS.src.DocumentsException;
import FONTS.src.Domain.Classes.*;
import FONTS.src.Domain.Controllers.Stubs.ContentStub;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @file DocumentTest.java
 * @brief Test Junit <em>Document</em>
 */

/**
 * @brief Test of Document class
 *
 * @author Marc Navarro Acosta
 */

public class DocumentTest {

    @org.junit.Test
    public void Document(){
        try{
            Document d = new Document("Title1", "Auth1","This is test Content","ENG");
        }
        catch (IOException e) {
            fail(e.getMessage());
        } catch (DocumentsException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void Document2(){
        try{
            Content c = new ContentStub();
            Document d = new Document("Title1", "Auth1",c,"ENG");
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @org.junit.Test
    public void setAuthor() throws IOException, DocumentsException {
        //Test initializing way 1
        Content initialContent = new ContentStub();
        Document d = new Document("Title1", "Author1", initialContent, "ENG");
        d.setAuthor("AuthorChanged");
        assertEquals("AuthorChanged", d.getAuthor());

        //Test initializing way 2
        Document d2 = new Document("Title1", "Author1", "This is simply Text", "ENG");
        d2.setAuthor("AuthorChanged");
        assertEquals("AuthorChanged", d2.getAuthor());
    }

    @org.junit.Test
    public void setTitle() throws IOException, DocumentsException {
        //Test initializing way 1
        Content initialContent = new ContentStub();
        Document d = new Document("Title1", "Author1", initialContent, "ENG");
        d.setTitle("TitleChanged");
        assertEquals("TitleChanged", d.getTitle());


        //Test initializing way 2
        Document d2 = new Document("Title1", "Author1", "This is simply Text", "ENG");
        d2.setTitle("TitleChanged");
        assertEquals("TitleChanged", d2.getTitle());
    }

    @org.junit.Test
    public void setContent() throws IOException, DocumentsException {
        //Test initializing way 1
        Content initialContent = new ContentStub();
        Document d = new Document("Title1", "Author1", initialContent, "ENG");
        Content newCont = new ContentStub();
        d.setContent(newCont);
        assertEquals(newCont.getText(), d.getContent());


        //Test initializing way 2
        Document d2 = new Document("Title1", "Author1", "Initial Text", "ENG");
        d2.setContent(newCont);
        assertEquals("Simple Content Test", d2.getContent());
    }

    @org.junit.Test
    public void setLanguage() throws IOException, DocumentsException {
        //Test initializing way 1
        Content initialContent = new ContentStub();
        Document d = new Document("Title1", "Author1", initialContent, "ENG");
        d.setLanguage("CAT");
        assertEquals("CAT", d.getLanguage());


        //Test initializing way 2
        Document d2 = new Document("Title1", "Author1", "Initial Text", "ENG");
        d2.setLanguage("CAT");
        assertEquals("CAT", d2.getLanguage());
    }

    @org.junit.Test
    public void protectDocument() throws IOException, DocumentsException {
        //Test initializing way 1
        Content initialContent = new ContentStub();
        Document d = new Document("Title1", "Author1", initialContent, "ENG");
        assertFalse(d.isProtected());
        d.protectDocument("admin");
        assertTrue(d.isProtected());


        //Test initializing way 2
        Document d2 = new Document("Title1", "Author1", "Initial Text", "ENG");
        assertFalse(d2.isProtected());
        d2.protectDocument("admin");
        assertTrue(d2.isProtected());
        }

    @org.junit.Test
    public void getTitle() throws IOException, DocumentsException {
        //Test initializing way 1
        Content initialContent = new ContentStub();
        Document d = new Document("Title1", "Author1", initialContent, "ENG");
        assertEquals("Title1", d.getTitle());


        //Test initializing way 2
        Document d2 = new Document("Title1", "Author1", "Initial Text", "ENG");
        assertEquals("Title1", d2.getTitle());
    }

    @org.junit.Test
    public void getAuthor() throws IOException, DocumentsException {
        //Test initializing way 1
        Content initialContent = new ContentStub();
        Document d = new Document("Title1", "Author1", initialContent, "ENG");
        assertEquals("Author1", d.getAuthor());


        //Test initializing way 2
        Document d2 = new Document("Title1", "Author1", "Initial Text", "ENG");
        assertEquals("Author1", d2.getAuthor());
    }

    @org.junit.Test
    public void getContent() throws IOException, DocumentsException {
        //Test initializing way 1
        Content initialContent = new ContentStub();
        Document d = new Document("Title1", "Author1", initialContent, "ENG");
        assertEquals(initialContent.getText(), d.getContent());

/*        //Test initializing way 2
        Document d2 = new Document("Title1", "Author1", "Initial Text", "ENG");
        assertSame(new Content("Initial Text", "ENG"), d2.getContent());*/
    }

    @org.junit.Test
    public void getLanguage() throws IOException, DocumentsException {
       //Test initializing way 1
        Content initialContent = new ContentStub();
        Document d = new Document("Title1", "Author1", initialContent, "ENG");
        assertEquals("ENG", d.getLanguage());


        //Test initializing way 2
        Document d2 = new Document("Title1", "Author1", "Initial Text", "ENG");
        assertEquals("ENG", d2.getLanguage());
    }

    @org.junit.Test
    public void isProtected() throws IOException, DocumentsException {
        //Test initializing way 1
        Content initialContent = new ContentStub();
        Document d = new Document("Title1", "Author1", initialContent, "ENG");
        d.protectDocument("admin");
        assertTrue(d.isProtected());


        //Test initializing way 2
        Document d2 = new Document("Title1", "Author1", "Initial Text", "ENG");
        assertEquals("ENG", d2.getLanguage());
    }
}