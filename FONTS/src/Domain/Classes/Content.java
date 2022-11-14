package FONTS.src.Domain.Classes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.lang.String;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @file Content.java
 * @brief Class <em>Content</em>
 */

/**
 * @brief Class Content that saves the text of the Document
 *
 * @author Victor Mena Doz
 */
public class Content {
    /**
     * Map that we will use for obtain cosine similarity between Documents
     */
    private HashMap<String, Integer> frequency;

    /**
     * ArrayList that contains text of the Document
     */
    private ArrayList<Sentence> text;

    /**
     * String language of the content
     */
    private String lang;

    /**
     * @brief Default create of Content
     * */
    public Content()
    {
    }

    /**
     * @brief Creation of content with text
     * @param textDoc, content of the document
     * @param language, language of the text (esp, eng, cat)
     * @throws IOException
     * */
    public Content(String textDoc, String language) throws IOException {
        this.frequency = new HashMap<>();
        this.text = new ArrayList<>();

        //Build text
        String auxText = textDoc;
        int posDot = auxText.indexOf('.');
        if(posDot == -1 && !auxText.isEmpty()) { //If there's no dot
            Sentence aux = new Sentence(auxText);
            text.add(aux);
        }
        while(posDot != -1) {
            int jump = auxText.indexOf("\n");
            if(jump != -1 && jump < 2) { //first char is a line break?
                text.add(new Sentence("breakLine")); //ASI GUARDAMOS SALTO DE LINEA
                auxText = auxText.substring(jump+1); //until the end
                posDot = auxText.indexOf('.');
            }
            String untilDot = auxText.substring(0, posDot); //string that goes to Sentence
            Sentence aux = new Sentence(untilDot + ".");
            text.add(aux);

            auxText = auxText.substring(posDot+1); //until the end
            posDot = auxText.indexOf('.');
        }

        //Build vector of frequency
        //DELETE STOP WORDS
        List<String> stopWords;
        switch (language) {
            case "ENG":
                stopWords = Files.readAllLines(Paths.get("FONTS/src/Domain/Classes/StopWords/en-stopwords.txt"), StandardCharsets.UTF_8);
                break;
            case "CAT":
                stopWords = Files.readAllLines(Paths.get("FONTS/src/Domain/Classes/StopWords/cat-stopwords.txt"), StandardCharsets.UTF_8);
                break;
            case "ESP":
                stopWords = Files.readAllLines(Paths.get("FONTS/src/Domain/Classes/StopWords/es-stopwords.txt"), StandardCharsets.UTF_8);
                break;
            default: //GENERATE EXCEPTION BETTER
                stopWords = null;
                System.out.println("Invalid language");
                break;
        }

        ArrayList<String> allWords = Stream.of(textDoc.toLowerCase().split(" ")).collect(Collectors.toCollection(ArrayList<String>::new));
        allWords.removeAll(stopWords);

        //Build the vector
        while(!allWords.isEmpty()) {
            String auxWord = allWords.get(0);
            if(auxWord.contains("\n")) {
                String[] aux = auxWord.split("\n");
                auxWord = aux[0];
                for(int i = 1; i < aux.length; ++i) allWords.add(aux[i]);
            }
            //Clean word
            auxWord = auxWord.replace(".", "");
            auxWord = auxWord.replace("\n", "");
            auxWord = auxWord.replace(",", "");
            auxWord = auxWord.replace("!", "");
            auxWord = auxWord.replace("?", "");
            auxWord = auxWord.replace(";", "");
            auxWord = auxWord.replace(":", "");
            auxWord = auxWord.replace("\"", "");
            if(language == "ESP") {
                auxWord = auxWord.replace("\'", "");
            }

            if(frequency.containsKey(auxWord)) frequency.compute(auxWord, (Key, Value) -> Value+1);
            else frequency.put(auxWord, 1);
            allWords.remove(0);
        }

        //Save language
        lang = language;
    }

    /**
     * @brief Get text write in a String
     * @return text in a string
     */
    public String getText(){
        String cText = "";
        for (Sentence s : text){
            String aux = s.getSentence();
            if (aux == null) cText += "\n";
            else cText += aux;
        }
        return cText;
    }

    /**
     * @brief Get all the sentences of the content
     * @return List of sentences of the content
     */
    public ArrayList<String> getSentences() {
        ArrayList<String> aux = new ArrayList<>();
        for (Sentence s : text){
            String auxSent = s.getSentence();
            if (auxSent == null) aux.add("\n");
            else aux.add(auxSent);
        }
        return aux;
    }
    /**
     * @brief Get language of the context
     * @return String language of the content
     */
    public String getLanguage() {
        switch (lang) {
            case "ENG":
                return "English";
            case "CAT":
                return "Catalan";
            default:
                return "Spanish";
        }
    }

    /**
     * @brief Get vector of important words of the text
     * @return map of frequency
     */
    public HashMap<String,Integer> getVector()
    {
        return this.frequency;
    }

    /**
     * @brief Consult if word or words are on the text
     * @param wSearch, word to look for
     * @return true if word appears in the text, otherwise false
     */
    public boolean wordContain(String wSearch)
    {
        for(Sentence aux: text) {
            if(aux.searchWord(wSearch)) return true;
        }
        return false;
    }
}
