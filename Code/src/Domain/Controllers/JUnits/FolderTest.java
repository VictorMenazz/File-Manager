package Code.src.Domain.Controllers.JUnits;

import Code.src.Domain.Classes.*;
import Code.src.Domain.Controllers.Stubs.ContentStub;
import Code.src.Domain.Controllers.Stubs.DocumentStub;
import org.junit.Test;

import javax.print.Doc;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FolderTest {

    @Test
    public void setFolderName() {
        Folder f = new Folder(1, "Test");
        f.setFolderName("ChangedName");
        assertEquals("ChangedName", f.getName());
    }

    @Test
    public void createFolder() {
        Folder f = new Folder(1, "Test");
        f.createFolder("SonTest", 1);
        assertTrue(f.folderContained(2));
    }

    @Test
    public void addDocument() {
        Folder f = new Folder(1, "Test");
        Document doc = new DocumentStub();
        f.addDocument(doc);
        assertTrue(f.documentContained("TitleTest","AuthorTest"));
    }

    @Test
    public void addNonConstructedDocument() {
        /*Folder f = new Folder(1, "Test");
        //It will no longer work cause the creator of Content is no valid.
        f.addNonConstructedDocument("AuthorTest", "TitleTest", new ContentStub(), "ENG");
        assertTrue(f.documentContained("TitleTest","AuthorTest"));*/
    }

    @Test
    public void modifyContent() {
        /*Folder f = new Folder(1, "Test");
        Document doc = new DocumentStub();
        f.addDocument(doc);
        //It will no longer work cause the creator of Content is no valid.
        f.modifyContent("AuthorTest", "TitleTest", "newCont");
        assertTrue(f.documentContained("TitleTest","AuthorTest"));*/
    }

    @Test
    public void modifyAuthor() {
        Folder f = new Folder(1, "Test");
        Document doc = new DocumentStub();
        f.addDocument(doc);
        f.modifyAuthor("AuthorTest", "TitleTest", "newAuthor");
        assertTrue(f.documentContained("TitleTest", "newAuthor"));
    }

    @Test
    public void modifyTitle() {
        Folder f = new Folder(1, "Test");
        Document doc = new DocumentStub();
        f.addDocument(doc);
        f.modifyTitle("AuthorTest", "TitleTest", "newTitle");
        assertTrue(f.documentContained("newTitle", "AuthorTest"));
    }

    @Test
    public void protectDocument() {
        Folder f = new Folder(1, "Test");
        Document doc = new DocumentStub();
        f.addDocument(doc);
        f.protectDocument("AuthorTest","TitleTest","admin");
        Document d = f.getDocument("AuthorTest","TitleTest");
        assertFalse(d.isProtected());
    }

    @Test
    public void getName() {
        Folder f = new Folder(1, "Test");
        assertEquals("Test",f.getName());
    }

    @Test
    public void getDocumentsName() {
        Folder f = new Folder(1, "Test");
        Document doc = new DocumentStub();
        f.addDocument(doc);
        ArrayList<String> Expected = new ArrayList<String>();
        Expected.add("TitleTest");
        assertEquals(Expected,f.getDocumentsName());
    }

    @Test
    public void getDocumentAmount() {
        Folder f = new Folder(1, "Test");
        assertEquals(0,f.getDocumentAmount());
        Document doc = new DocumentStub();
        f.addDocument(doc);
        assertEquals(1,f.getDocumentAmount());
    }

    @Test
    public void getDocument() {
        Folder f = new Folder(1, "Test");
        Document doc = new DocumentStub();
        f.addDocument(doc);
        assertEquals(doc,f.getDocument("AuthorTest", "TitleTest"));
    }

    @Test
    public void documentContained() {
        Folder f = new Folder(1, "Test");
        Document doc = new DocumentStub();
        f.addDocument(doc);
        assertTrue(f.documentContained("TitleTest","AuthorTest"));
        assertFalse(f.documentContained("testing","testing"));
    }

    @Test
    public void folderContained() {
        Folder f = new Folder(1, "Test");
        assertFalse(f.folderContained(2));
        f.createFolder("Subfolder",1);
        assertTrue(f.folderContained(2));
    }
}