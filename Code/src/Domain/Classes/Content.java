package Domain.Classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.lang.String;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @file Content.java
 *
 * @brief Class <em>Content</em>
 */

/**
 * @brief Class Content that saves the text of the Document
 *
 * @author Victor Mena Doz
 */
public class Content {
    /**
     * Vector that we will use for obtain cosine similarity between Documents
     */
    private Map<String, Integer> frequency;

    /**
     * ArrayList that contains text of the Document
     */
    private ArrayList<Sentence> text;

    /**
     * @brief Creadora de Content por defecto
     * */
    public Content()
    {
    }

    /**
     * @brief Creation of content with text
     *
     * @param textDoc, content of the document
     * @param language, language of the text (esp, eng, cat)
     * */
    public Content(String textDoc, String language) throws IOException {
        this.frequency = new HashMap<>();
        this.text = new ArrayList<>();

        //Build text
        String auxText = textDoc;
        int posDot = auxText.indexOf('.');
        while(posDot != -1) {
            int jump = auxText.indexOf("\n");
            if(jump != -1 && jump < 2) { //first char is a line break?
                text.add(new Sentence("break")); //ASI GUARDAMOS SALTO DE LINEA
                auxText = auxText.substring(jump+2); //until the end
            }
            String untilDot = auxText.substring(0, posDot); //string that goes to Domain.Domain.Classes.Classes.Sentence
            Sentence aux = new Sentence(untilDot);
            text.add(aux);

            auxText = auxText.substring(posDot+1); //until the end
            posDot = auxText.indexOf('.');
        }
        //Caso en el que no haya punto

        //Build vector of frequency
        //DELETE STOP WORDS
        List<String> stopWords;
        switch (language) {
            case "eng":
                stopWords = Files.readAllLines(Paths.get("en-stopwords.txt"));
                break;
            case "cat":
                stopWords = Files.readAllLines(Paths.get("cat-stopwords.txt"));
                break;
            default:
                stopWords = Files.readAllLines(Paths.get("es-stopwords.txt"));
                break;
        }

        ArrayList<String> allWords = Stream.of(textDoc.toLowerCase().split(" ")).collect(Collectors.toCollection(ArrayList<String>::new));
        allWords.removeAll(stopWords);

        //Build the vector
        while(!allWords.isEmpty()) {
            String auxWord = allWords.get(0);
            if(frequency.containsKey(auxWord)) frequency.compute(auxWord, (Key, Value) -> Value+1);
            else frequency.put(auxWord, 1);
            allWords.remove(0);
        }
    }
    //getters
    public Map<String,Integer> getVector()
    {
        return this.frequency;
    }

    //consultants
    public boolean wordContain(String wSearch)
    {
        for(Sentence aux: text) {
            //CASO DE SALTO DE LINEA
            if(aux.searchWord(wSearch)) return true;
        }
        return false;
    }

}
