package FONTS.src.Domain.Controllers.JUnits;

import FONTS.src.Domain.Classes.Sentence;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @file SentenceTest.java
 * @brief Test Junit <em>Sentence</em>
 */

/**
 * @brief Test of Sentence class
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