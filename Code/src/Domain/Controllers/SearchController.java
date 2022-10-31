package Code.src.Domain.Controllers;

import Code.src.Domain.Classes.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchController {
    /**
     * @brief Instance of QueryController
     */
    private QueryController query;

    public Document searchDocument(String authorName, String title) {

        return null;
    }

    public ArrayList<Document> searchAuthorDocuments(String authorName) {

        return  null;
    }

    public ArrayList<String> searchAuthorsPrefix(String prefix) {

        return null;
    }

    public ArrayList<Document> booleanExpressionSearch(String boolExp) {

        return null;
    }

    public ArrayList<Document> appearanceSearch(Map<String, Integer> freq, int k) {

        return null;
    }
}
