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
     * @brief List of BooleanExpressions
     */
    private LinkedHashSet<BooleanExpression> listBoolExps;

    public SearchController(Folder root) {
        rootFolder = root;
        listBoolExps = new LinkedHashSet<>();
    }
    public void addExpression(String boolExp) {
        BooleanExpression bExpr = new BooleanExpression(boolExp);
        listBoolExps.add(bExpr);
    }
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

    public String getListExp(){
        String list = "";
        for (BooleanExpression be : listBoolExps){
            String aux = be.getExpression();
            list += aux + "\n";
        }
        return list;
    }


    public ArrayList<Document> booleanExpressionSearch(Folder rootFolder, String expression) {
        BooleanExpression boolExpr = new BooleanExpression(expression);
        ArrayList<Document> list;





        return null;
    }

    public ArrayList<Document> appearanceSearch(Folder rootFolder, String authorName, String title, int k){
        HashMap<Pair<String, String>, HashMap<String,Integer>> listDocs = rootFolder.getMapsDocs();
        Pair<String, String> docKey = new Pair(title, authorName);
        HashMap<String, Integer> vectorDoc = listDocs.get(docKey);
        //LISTA DONDE GUARDAMOS COSENOS y PAIRS CORRESPONDIENTES

        for(HashMap.Entry<Pair<String, String>, HashMap<String,Integer>> Doc : listDocs.entrySet()) {
            HashMap.Entry<String, Integer> vectorConverted;
            if(docKey != Doc.getKey()) {
                for (HashMap.Entry<String, Integer> auxVector : vectorDoc.entrySet()) {

                    //RECORRER POSICIONES HASHMAP Y CREAR EL AUXILIAR
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
