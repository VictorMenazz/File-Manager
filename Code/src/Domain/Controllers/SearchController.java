package Code.src.Domain.Controllers;

import Code.src.Domain.Classes.BooleanExpression;
import Code.src.Domain.Classes.Document;
import Code.src.Domain.Classes.Folder;

import java.util.ArrayList;
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


        return null;
    }

    public ArrayList<Document> searchDocuments(Folder rootFolder, String pWords, int k) {
        return null;
    }
}
