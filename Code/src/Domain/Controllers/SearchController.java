package Code.src.Domain.Controllers;

import Code.src.Domain.Classes.BooleanExpression;
import Code.src.Domain.Classes.Document;
import Code.src.Domain.Classes.Folder;
import Code.src.Domain.Classes.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class SearchController {
    /**
     * @brief Instance of rootFolder
     */
    private Folder rootFolder;

    /**
     * @brief Unordered list of BooleanExpressions
     */
    private LinkedHashSet<BooleanExpression> listBoolExps;

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
    public ArrayList<Document> appearanceSearch(Folder rootFolder, String authorName, String title, int k){
        HashMap<Pair<String, String>, HashMap<String,Integer>> listDocs = rootFolder.getMapsDocs();
        Pair<String, String> docKey = new Pair(title, authorName);
        HashMap<String, Integer> vectorDoc = listDocs.get(docKey);
        //LISTA DONDE GUARDAMOS COSENOS y PAIRS CORRESPONDIENTES

        for(HashMap.Entry<Pair<String, String>, HashMap<String,Integer>> Doc : listDocs.entrySet()) {
            HashMap<String, Integer> vectorConverted = new HashMap<>();
            if(docKey != Doc.getKey()) {
                HashMap<String, Integer> vecAux = Doc.getValue();
                for (HashMap.Entry<String, Integer> auxVector : vectorDoc.entrySet()) {
                    String word = auxVector.getKey();
                    if(vecAux.containsKey(word)) {
                        vectorConverted.put(word, vecAux.get(word));
                    }
                    else vectorConverted.put(word, 0);
                }
            }
            //SACAR COSENO
        }
        //PREPARAR ARRAY QUE DEVOLVEREMOS
        //DEVOLVER CLAVE DE DOCUMENTO MEJOR
        return null;
    }

    public ArrayList<Document> searchDocuments(Folder rootFolder, String pWords, int k) {
        //ELIMINAR STOPWORDS

        return null;
    }
}
