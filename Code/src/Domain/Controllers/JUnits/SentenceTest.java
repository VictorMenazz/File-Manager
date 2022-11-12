package Code.src.Domain.Controllers.JUnits;

import Code.src.Domain.Classes.Content;
import Code.src.Domain.Classes.Sentence;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @file ContentTest.java
 *
 * @brief Test <em>ContentTest</em>
 */

/**
 * @brief Test of the Content class
 *
 * @author Victor Mena Doz
 */
public class SentenceTest {

    public static final String text = "Messi is Goat.";
    public static final String text2 = "Messi  is Goat.";
    @Test
    public void Sentence() {
        try {
            new Sentence(text);
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void searchWord() {
        Sentence s = new Sentence(text);
        assertTrue(s.searchWord("messi"));
        assertTrue(s.searchWord("go"));
        assertFalse(s.searchWord("gao"));
        assertFalse(s.searchWord("messi leo"));
    }

    @Test
    public void getSentence() {
        Sentence s = new Sentence(text);
        assertEquals("Expected equal", text, s.getSentence());

        Sentence s2 = new Sentence(text2);
        assertNotEquals("Expected not equals", text, s2.getSentence());

    }
}