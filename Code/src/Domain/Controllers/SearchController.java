package Code.src.Domain.Controllers;

import Code.src.Domain.Classes.BooleanExpression;
import Code.src.Domain.Classes.Document;
import Code.src.Domain.Classes.Folder;
import Code.src.Domain.Classes.Pair;

import java.io.IOException;
import java.util.*;

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
    public ArrayList<Document> booleanExpressionSearch(Folder rootFolder, String expression) {
        BooleanExpression boolExpr = new BooleanExpression(expression);
        ArrayList<Document> list;





        return null;
    }


    /***
     * @brief
     * @param rootFolder
     * @param authorName
     * @param title
     * @param k
     * @return
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
            //calcul cosine similarity
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
        //DEVOLVER CLAVE DE DOCUMENTO MEJOR
        return result;
    }

    public ArrayList<Document> searchDocuments(Folder rootFolder, String pWords, int k) {
        //ELIMINAR STOPWORDS

        return null;
    }

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

    private Double calculateNorm(HashMap<String, Integer> map) {
        Double norm = 0.0;
        for(HashMap.Entry<String, Integer> auxVector : map.entrySet()) {
            norm += Math.pow(auxVector.getValue(), 2);
        }
        return Math.sqrt(norm);
    }
}
