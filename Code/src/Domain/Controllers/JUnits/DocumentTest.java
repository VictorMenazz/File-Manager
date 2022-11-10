package Code.src.Domain.Controllers.JUnits;

import Code.src.Domain.Classes.*;
import Code.src.Domain.Controllers.Stubs.ContentStub;

import java.io.IOException;

import static org.junit.Assert.*;

public class DocumentTest {

    @org.junit.Test
    public void setAuthor() throws IOException {
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
    public void setTitle() throws IOException {
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
    public void setContent() throws IOException {
        //Test initializing way 1
        Content initialContent = new ContentStub();
        Document d = new Document("Title1", "Author1", initialContent, "ENG");
        Content newCont = new ContentStub();
        d.setContent(newCont);
        assertEquals(newCont, d.getContent());


        //Test initializing way 2
        Document d2 = new Document("Title1", "Author1", "Initial Text", "ENG");
        d2.setContent(newCont);
        assertEquals(newCont, d2.getContent());
    }

    @org.junit.Test
    public void setLanguage() throws IOException {
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
    public void protectDocument() throws IOException {
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
    public void getTitle() throws IOException {
        //Test initializing way 1
        Content initialContent = new ContentStub();
        Document d = new Document("Title1", "Author1", initialContent, "ENG");
        assertEquals("Title1", d.getTitle());


        //Test initializing way 2
        Document d2 = new Document("Title1", "Author1", "Initial Text", "ENG");
        assertEquals("Title1", d2.getTitle());
    }

    @org.junit.Test
    public void getAuthor() throws IOException {
        //Test initializing way 1
        Content initialContent = new ContentStub();
        Document d = new Document("Title1", "Author1", initialContent, "ENG");
        assertEquals("Author1", d.getAuthor());


        //Test initializing way 2
        Document d2 = new Document("Title1", "Author1", "Initial Text", "ENG");
        assertEquals("Author1", d2.getAuthor());
    }

    @org.junit.Test
    public void getContent() throws IOException {
        //Test initializing way 1
        Content initialContent = new ContentStub();
        Document d = new Document("Title1", "Author1", initialContent, "ENG");
        assertEquals(initialContent, d.getContent());

/*        //Test initializing way 2
        Document d2 = new Document("Title1", "Author1", "Initial Text", "ENG");
        assertSame(new Content("Initial Text", "ENG"), d2.getContent());*/
    }

    @org.junit.Test
    public void getLanguage() throws IOException {
       //Test initializing way 1
        Content initialContent = new ContentStub();
        Document d = new Document("Title1", "Author1", initialContent, "ENG");
        assertEquals("ENG", d.getLanguage());


        //Test initializing way 2
        Document d2 = new Document("Title1", "Author1", "Initial Text", "ENG");
        assertEquals("ENG", d2.getLanguage());
    }

    @org.junit.Test
    public void isProtected() throws IOException {
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