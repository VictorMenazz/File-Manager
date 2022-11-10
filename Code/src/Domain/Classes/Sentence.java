package Code.src.Domain.Classes;

/**
 * @file Sentence.java
 *
 * @brief Class <em>Sentence</em>
 */

/**
 * @brief Class Sentence that saves one line of the text
 *
 * @author Victor Mena Doz
 */
public class Sentence {
    private String[] words;

    /**
     * @brief Default create of Sentence
     * */
    public Sentence()
    {
    }

    /**
     * @brief Creation of Sentence with words
     *
     * @param nWords, words of the sentence
     */
    public Sentence(String nWords)
    {
        this.words = nWords.split(" ");
    }

    /**
     * @brief Search word or words in Sentence
     *
     * @param wSearch, word or words to look for them
     *
     * @return true if found, false if not
     */
    public boolean searchWord(String wSearch)
    {
        String[] auxSearch = wSearch.split(" ");
        for(String wAux: auxSearch) { //cada iteración es una palabra que queremos buscar
            for(String word: this.words) { //cada palabra de la frase
                if (word.contains(wAux)) return true;
            }
        }
        return false;
    }

    /**
     * @brief Reconstruct the sentence
     * @return Sentence in ona string
     */
    public String getSentence() {
        if (words.length == 1 & words[0]=="breakLine") {
            return null;
        }
        String aux = "";
        for(String word: words) {
            aux += word+" ";
        }
        return aux;
    }
}
