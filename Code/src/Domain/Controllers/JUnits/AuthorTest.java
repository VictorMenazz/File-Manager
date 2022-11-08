package Code.src.Domain.Controllers.JUnits;

import Code.src.Domain.Classes.Author;
import Code.src.Domain.Classes.Content;
import Code.src.Domain.Classes.Document;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class AuthorTest {

    @Test
    public static void TestGetName() {
        String new_name = "Jordi";
        Author instance = new Author();
        instance.setName(new_name);
        assertEquals(new_name, instance.getName());
    }

    @Test
    public static void TestSetName() {
        String new_name = "Jordi";
        Author instance = new Author();
        instance.setName(new_name);
        assertEquals(new_name, instance.getName());
    }

    @Test
    public static void TestGetDocuments() {
        Author instance = new Author("Jordi");
        Document doc1 = new Document("DocEn","Jordi","This is the content of the document in english", "en");
        Document doc2 = new Document("DocCat","Jordi","Aquest es el contingut del document en catala", "cat");
        Document doc3 = new Document("DocEs","Jordi","Este es el contenido el documento en español", "es");
        instance.addDocument(doc1);
        instance.addDocument(doc2);
        instance.addDocument(doc3);

        ArrayList<Document> expResult = new ArrayList<>();
        expResult.add(doc1);
        expResult.add(doc2);
        expResult.add(doc3);
        assertEquals(expResult, instance.getDocuments());
    }

    @Test
    public static void TestAddDocument() {
        Author instance = new Author("Jordi");
        Document doc1 = new Document("DocEn","Jordi","This is the content of the document in english", "en");
        Document doc2 = new Document("DocCat","Jordi","Aquest es el contingut del document en catala", "cat");
        Document doc3 = new Document("DocEs","Jordi","Este es el contenido el documento en español", "es");
        instance.addDocument(doc1);
        instance.addDocument(doc2);
        instance.addDocument(doc3);

        ArrayList<Document> expResult = new ArrayList<>();
        expResult.add(doc1);
        expResult.add(doc2);
        expResult.add(doc3);
        assertEquals(expResult, instance.getDocuments());
    }

    @Test
    public static boolean TestMatchesPrefix() {
        return false;
    }
}