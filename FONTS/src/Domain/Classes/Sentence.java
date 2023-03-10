package FONTS.src.Domain.Classes;

/**
 * @file Sentence.java
 * Class <em>Sentence</em>
 */

/**
 * Class Sentence that saves one line of the text
 *
 * @author Victor Mena Doz
 */
public class Sentence {
    private String[] words;

    /**
     * Default creator of Sentence
     * */
    public Sentence()
    {
    }

    /**
     * Creation of Sentence with words
     * @param nWords, words of the sentence
     */
    public Sentence(String nWords)
    {
        this.words = nWords.split(" ");
    }

    /**
     * Search word or words in Sentence
     * @param wSearch, word or words to look for them
     * @return true if words found in the sentence, otherwise false
     */
    public boolean searchWord(String wSearch)
    {
        String[] auxSearch = wSearch.split(" ");
        for(String wAux: auxSearch) { //cada iteración es una palabra que queremos buscar
            for(String word: this.words) { //cada palabra de la frase
                if (word.toLowerCase().contains(wAux.toLowerCase())) break;
                else if (word == words[words.length-1]) return false;
            }
        }
        return true;
    }

    /**
     * Reconstruct the sentence
     * @return Sentence in one string
     */
    public String getSentence() {
        if (words.length == 1 & words[0]=="breakLine") {
            return null;
        }
        String aux = "";
        for(String word: words) {
            if (words[words.length-1] == word) aux += word;
            else aux += word+" ";
        }
        return aux;
    }
}
