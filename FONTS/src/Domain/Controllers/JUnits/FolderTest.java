package FONTS.src.Domain.Controllers.JUnits;

import FONTS.src.DocumentsException;
import FONTS.src.Domain.Classes.*;
import FONTS.src.Domain.Controllers.Stubs.DocumentStub;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @file FolderTest.java
 * @brief Test Junit <em>Folder</em>
 */

/**
 * Test of Folder class
 *
 * @author Marc Navarro Acosta
 */

public class FolderTest {

    public void Folder(){
        try{
            Folder f = new Folder(1, "Fold1");
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void setFolderName() {
        Folder f = new Folder(1, "Test");
        assertNotEquals("ChangedName", f.getName());
        f.setFolderName("ChangedName");
        assertEquals("ChangedName", f.getName());
    }

    @Test
    public void createFolder() {
        Folder f = new Folder(1, "Test");
        assertFalse(f.folderContained(2));
        f.createFolder("SonTest", 1);
        assertTrue(f.folderContained(2));
    }

    @Test
    public void addDocument() throws DocumentsException {
        Folder f = new Folder(1, "Test");
        Document doc = new DocumentStub();
        assertFalse(f.documentContained("TitleTest","AuthorTest"));
        f.addDocument(doc);
        assertTrue(f.documentContained("TitleTest","AuthorTest"));
    }

    @Test
    public void addNonConstructedDocument() {
        Folder f = new Folder(1, "Test");
        assertFalse(f.documentContained("TitleTest","AuthorTest"));
        try {
            f.addNonConstructedDocument("AuthorTest", "TitleTest", "new Content", "ENG", f.getId());
        } catch (IOException e) {
            fail(e.getMessage());
        } catch (DocumentsException e) {
            throw new RuntimeException(e);
        }
        assertTrue(f.documentContained("TitleTest","AuthorTest"));
    }

    @Test
    public void delDocument() throws DocumentsException {
        Folder f = new Folder(1, "Test");
        Document doc = new DocumentStub();
        f.addDocument(doc);
        f.delDocument("AuthorTest", "TitleTest");
        assertFalse(f.documentContained("titleTest", "authorTest"));
    }

    @Test
    public void modifyContent() throws DocumentsException {
        Folder f = new Folder(1, "Test");
        Document doc = new DocumentStub();
        assertFalse(f.documentContained("TitleTest","AuthorTest"));
        f.addDocument(doc);
        try {
            f.modifyContent("AuthorTest", "TitleTest", "newCont");
        } catch (IOException e) {
            fail(e.getMessage());
        }
        assertTrue(f.documentContained("TitleTest","AuthorTest"));
    }

    @Test
    public void modifyAuthor() throws DocumentsException {
        Folder f = new Folder(1, "Test");
        Document doc = new DocumentStub();
        assertFalse(f.documentContained("TitleTest","AuthorTest"));
        assertFalse(f.documentContained("TitleTest","newAuthor"));
        f.addDocument(doc);
        assertTrue(f.documentContained("TitleTest","AuthorTest"));
        assertFalse(f.documentContained("TitleTest","newAuthor"));
        f.modifyAuthor("AuthorTest", "TitleTest", "newAuthor");
        assertFalse(f.documentContained("TitleTest","AuthorTest"));
        assertTrue(f.documentContained("TitleTest", "newAuthor"));
    }

    @Test
    public void modifyTitle() throws DocumentsException {
        Folder f = new Folder(1, "Test");
        Document doc = new DocumentStub();
        assertFalse(f.documentContained("TitleTest", "AuthorTest"));
        assertFalse(f.documentContained("newTitle", "AuthorTest"));
        f.addDocument(doc);
        assertTrue(f.documentContained("TitleTest", "AuthorTest"));
        assertFalse(f.documentContained("newTitle", "AuthorTest"));
        f.modifyTitle("AuthorTest", "TitleTest", "newTitle");
        assertFalse(f.documentContained("TitleTest", "AuthorTest"));
        assertTrue(f.documentContained("newTitle", "AuthorTest"));
    }

    @Test
    public void protectDocument() throws DocumentsException {
        Folder f = new Folder(1, "Test");
        Document doc = new DocumentStub();
        f.addDocument(doc);
        assertFalse(doc.isProtected());
        f.protectDocument("AuthorTest","TitleTest","admin");
        assertFalse(doc.isProtected()); //Cause the Stub is always returning false.
    }

    @Test
    public void getName() {
        Folder f = new Folder(1, "Test");
        assertEquals("Test",f.getName());
    }

    @Test
    public void getDocumentsName() throws DocumentsException {
        Folder f = new Folder(1, "Test");
        Document doc = new DocumentStub();
        f.addDocument(doc);
        ArrayList<String> Expected = new ArrayList<String>();
        Expected.add("TitleTest");
        assertEquals(Expected,f.getDocumentsName());
    }

    @Test
    public void getDocumentAmount() throws DocumentsException {
        Folder f = new Folder(1, "Test");
        assertEquals(0,f.getDocumentAmount());
        Document doc = new DocumentStub();
        f.addDocument(doc);
        assertEquals(1,f.getDocumentAmount());
    }

    @Test
    public void getDocument() throws DocumentsException {
        Folder f = new Folder(1, "Test");
        Document doc = new DocumentStub();
        f.addDocument(doc);
        ArrayList<String> res = new ArrayList<String>();
        res.add("TitleTest");
        res.add("AuthorTest");
        res.add("Simple Content Test");
        res.add("ENG");
        assertEquals(res,f.getDocument("AuthorTest", "TitleTest"));
    }

    @Test
    public void documentContained() throws DocumentsException {
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