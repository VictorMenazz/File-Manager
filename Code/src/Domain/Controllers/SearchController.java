package Code.src.Domain.Controllers;

import Code.src.Domain.Classes.BooleanExpression;
import Code.src.Domain.Classes.Document;
import Code.src.Domain.Classes.Folder;

import java.util.ArrayList;

public class SearchController {
    /**
     * @brief Instance of QueryController
     */
    private QueryController query;

    /**
     * @brief Instance of rootFolder
     */
    private Folder rootFolder;

    public SearchController(QueryController instance, Folder root) {
        query = instance;
        rootFolder = root;
    }

    public ArrayList<Document> searchAuthorDocuments(Folder rootFolder, String authorName) {

        return  null;
    }

    public ArrayList<String> searchAuthorsPrefix(Folder rootFolder, String prefix) {

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
