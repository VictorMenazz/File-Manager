package Code.src.Domain.Controllers;

import Code.src.Domain.Classes.BooleanExpression;
import Code.src.Domain.Classes.Document;
import Code.src.Domain.Classes.Folder;
import Code.src.Domain.Classes.Pair;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @file SearchController.java
 *
 * @brief Controller <em>SearchController</em>
 */

/**
 * @brief Controller of searches to get documents that meet conditions
 *
 * @author Victor Mena Doz and Julia Alice Amenos Dien
 */
public class SearchController {
    /**
     * @brief Instance of rootFolder
     */
    private Folder rootFolder;

    /**
     * @brief Unordered list of BooleanExpressions
     */
    private HashSet<BooleanExpression> listBoolExps;

    /**
     * @brief Default creator
     * @param root: the instance of rootFolder
     */
    public SearchController(Folder root) {
        rootFolder = root;
        listBoolExps = new LinkedHashSet<>();
    }

    /***
     * @brief Saves a boolean expression to the historial.
     * @param boolExp, string with the boolean expression.
     */
    public void addExpression(String boolExp) {
        BooleanExpression bExpr = new BooleanExpression(boolExp);
        listBoolExps.add(bExpr);
    }

    /***
     * @brief Modifies an existing boolean expression
     * @param oldExpr, old boolean expression
     * @param newExpr, new boolean expression
     */
    public void modifyExpression(String oldExpr, String newExpr) {
        BooleanExpression bold = new BooleanExpression(oldExpr);
        listBoolExps.remove(bold);

        BooleanExpression bnew = new BooleanExpression(newExpr);
        listBoolExps.add(bnew);
    }

    public void deleteQuery(String boolExp){
        BooleanExpression be = new BooleanExpression(boolExp);
        listBoolExps.remove(be);
    }

    /**
     * @brief gets a string with all the boolean expressions saved.
     * @return String with all the saved boolean expressions
     */
    public String getListExp(){
        String list = "";
        for (BooleanExpression be : listBoolExps){
            String aux = be.getExpression();
            list += aux + "\n";
        }
        return list;
    }

    /**
     * @brief
     * @param rootFolder
     * @param expression
     * @return
     */
    public HashMap<String, String> booleanExpressionSearch(Folder rootFolder, String expression) {
        BooleanExpression boolExpr = new BooleanExpression(expression);
        ArrayList<Document> list;





        return null;
    }


    /***
     * @brief Search of k documents that appears to specific document
     * @param rootFolder, instance of the root folder
     * @param authorName, name of the author of the specific document
     * @param title, title of the specific document
     * @param k, integer of how many appearance documents to return
     * @return, list of k documents listed with key
     */
    public HashMap<String, String> appearanceSearch(Folder rootFolder, String authorName, String title, int k){
        HashMap<Pair<String, String>, HashMap<String,Integer>> listDocs = rootFolder.getMapsDocs();
        Pair<String, String> docKey = new Pair(title, authorName);
        HashMap<String, Integer> vectorDoc = listDocs.get(docKey); //vector of the document

        Map<Double, Pair<String, String>> listSimilarity = new LinkedHashMap<>();

        for(HashMap.Entry<Pair<String, String>, HashMap<String,Integer>> Doc : listDocs.entrySet()) {
            HashMap<String, Integer> vectorConverted = new HashMap<>();
            if(docKey != Doc.getKey()) { //create a Map adapted to the length and content of the Map of the document
                HashMap<String, Integer> vecAux = Doc.getValue();
                for (HashMap.Entry<String, Integer> auxVector : vectorDoc.entrySet()) {
                    String word = auxVector.getKey();
                    if(vecAux.containsKey(word)) {
                        vectorConverted.put(word, vecAux.get(word));
                    }
                    else vectorConverted.put(word, 0);
                }
            }
            //calc cosine similarity
            double sim = calculateCosineSimilarity(vectorDoc, vectorConverted);
            //add similarity to list of all similarities
            listSimilarity.put(sim, Doc.getKey());
        }
        //Prepare map to return
        HashMap<String, String> result = new HashMap<>();
        Set<Double> dkeys = listSimilarity.keySet();
        Iterator<Double> it = dkeys.iterator();
        for(int i = 0; i < k; ++i) {
            //if(!it.hasNext()) throw IOException; FOR THE FUTURE
            Pair<String, String> pair = listSimilarity.get(it.next());
            result.put(pair.first, pair.second);
        }
        return result;
    }

    /**
     * @brief Search of k documents more relevant for a list of specific words
     * @param rootFolder, instance of the root folder
     * @param pWords, list of specific words
     * @param language, language of the search
     * @param k, integer of how many relevant documents to return
     * @return  list of k documents listed with key
     * @throws IOException
     */
    public HashMap<String, String> searchDocuments(Folder rootFolder, String pWords, String language, int k) throws IOException {
        //Delete stopwords
        List<String> stopWords;
        switch (language) {
            case "ENG":
                stopWords = Files.readAllLines(Paths.get("Code/src/Domain/Classes/StopWords/en-stopwords.txt"), StandardCharsets.UTF_8);
                break;
            case "CAT":
                stopWords = Files.readAllLines(Paths.get("Code/src/Domain/Classes/StopWords/cat-stopwords.txt"), StandardCharsets.UTF_8);
                break;
            default:
                stopWords = Files.readAllLines(Paths.get("Code/src/Domain/Classes/StopWords/es-stopwords.txt"), StandardCharsets.UTF_8);
                break;
        }

        ArrayList<String> allWords = Stream.of(pWords.toLowerCase().split(" ")).collect(Collectors.toCollection(ArrayList<String>::new));
        allWords.removeAll(stopWords);

        HashMap<Pair<String, String>, HashMap<String,Integer>> listDocs = rootFolder.getMapsDocs(); //obtain all documents

        Map<Pair<String, String>, Integer> listRanking = new LinkedHashMap<>(); //aux to do ranking

        for(String word : allWords) {
            for(HashMap.Entry<Pair<String, String>, HashMap<String,Integer>> Doc : listDocs.entrySet()) {
                HashMap<String, Integer> vecAux = Doc.getValue();
                if(vecAux.containsKey(word)) { //If document have this word
                    if(listRanking.containsKey(Doc.getKey())) { //if exist add value
                        listRanking.compute(Doc.getKey(), (Key, Value) -> Value + vecAux.get(word));
                    }
                    else { //if not add new instance
                        listRanking.put(Doc.getKey(), vecAux.get(word));
                    }
                }
            }
        }
        //Order map using value
        LinkedHashMap<Pair<String, String>, Integer> listRankingOrdered = new LinkedHashMap<>();
        listRanking.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> listRankingOrdered.put(x.getKey(), x.getValue()));

        //Select k first documents as result
        HashMap<String, String> result = new HashMap<>();
        Set<Pair<String, String>> dkeys = listRankingOrdered.keySet();
        Iterator<Pair<String, String>> it = dkeys.iterator();
        for(int i = 0; i < k; ++i) {
            //if(!it.hasNext()) throw IOException; FOR THE FUTURE
            result.put(it.next().first, it.next().second);
        }

        return result;
    }

    /**
     * @brief Function to calculate Cosine similarity between maps
     * @param first, first map
     * @param second, second map
     * @return value of cosine similarity between first and second maps
     */
    private Double calculateCosineSimilarity(HashMap<String, Integer> first, HashMap<String, Integer> second) {
        Double sum = 0.0;
        for(HashMap.Entry<String, Integer> auxVector : first.entrySet()) {
            sum += auxVector.getValue() * second.get(auxVector.getKey());
        }
        double fnorm = calculateNorm(first);
        double snorm = calculateNorm(second);
        double similarity = sum / (fnorm * snorm);
        return similarity;
    }

    /**
     * @brief Function to calculate normalize of a map
     * @param map
     * @return Normalize value of the map
     */
    private Double calculateNorm(HashMap<String, Integer> map) {
        Double norm = 0.0;
        for(HashMap.Entry<String, Integer> auxVector : map.entrySet()) {
            norm += Math.pow(auxVector.getValue(), 2);
        }
        return Math.sqrt(norm);
    }
}
