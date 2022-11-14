package FONTS.src.Domain.Controllers.Stubs;

import FONTS.src.Domain.Classes.Sentence;

/**
 * @file SentenceStub.java
 * @brief Stub <em>Sentence</em>
 */

/**
 * @brief Stub of Sentence class
 *
 * @author Victor Mena Doz
 */
public class SentenceStub extends Sentence {
    private String sentStub;
    public SentenceStub() {
    }

    public SentenceStub(String nWords) {
        sentStub = nWords;
    }

    public boolean searchWordStub(String wSearch) {
        return false;
    }

    public String getSentenceStub() {
        return sentStub;
    }
}
