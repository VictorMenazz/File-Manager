package FONTS.src.Domain.Controllers.JUnits;

import FONTS.src.Domain.Classes.Content;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * @file ContentTest.java
 * @brief Test Junit <em>Content</em>
 */

/**
 * Test of the Content class
 *
 * @author Victor Mena Doz
 */
public class ContentTest {

    public static final String text = "Messi is Goat. My favourite football player.\n" +
            "I like to play football with my friends.";

    @Test
    public void Content()  {
        try {
            new Content(text, "ENG");
        }
        catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getText() throws IOException {
        Content c = new Content(text, "ENG");
        assertEquals("Expected equal", text, c.getText());
    }

    @Test
    public void getSentences() throws IOException {
        Content c = new Content(text, "ENG");
        ArrayList<String> aux = new ArrayList<>();
        aux.add("Messi is Goat.");
        aux.add(" My favourite football player.");
        aux.add("\n");
        aux.add("I like to play football with my friends.");
        assertEquals("Expected equal", aux, c.getSentences());
    }

    @Test
    public void getLanguage() throws IOException {
        Content c = new Content(text, "ENG");
        assertEquals("Expected equal", "English", c.getLanguage());
    }

    @Test
    public void getVector() throws IOException {
        Content c = new Content(text, "ENG");
        HashMap<String, Integer> map = new HashMap<>();
        map.put("play", 1);
        map.put("player", 1);
        map.put("i", 1);
        map.put("goat", 1);
        map.put("football", 2);
        map.put("favourite", 1);
        map.put("messi", 1);
        map.put("friends", 1);
        assertEquals("Expected equal", map, c.getVector());
    }

    @Test
    public void wordContain() throws IOException {
        Content c = new Content(text, "ENG");
        assertTrue(c.wordContain("messi"));
        assertFalse(c.wordContain("harry"));
    }
}