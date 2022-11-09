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
    public void addQuery(String boolExp) {

    }
    public void modifyQuery(int pos) {

    }

    public void deleteQuery(int pos){

    }

    public BooleanExpression getInstance(int pos) {

        return null;
    }

    public ArrayList<Document> booleanExpressionSearch(Folder rootFolder, String expression) {
        BooleanExpression boolExpr = new BooleanExpression(expression);
        ArrayList<Document> list;
        BooleanExpression.Node root = boolExpr.getExpression();





        return null;
    }

    public ArrayList<Document> appearanceSearch(Folder rootFolder, String authorName, String title, int k){
        return null;
    }

    public ArrayList<Document> searchDocuments(Folder rootFolder, String pWords, int k) {
        return null;
    }
}
