package Code.src.Domain.Controllers;

import Code.src.Domain.Classes.BooleanExpression;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class QueryController {
    /**
     * @brief List of BooleanExpressions
     */
    private LinkedHashSet<BooleanExpression> listBoolExps;

    public QueryController() {
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
}