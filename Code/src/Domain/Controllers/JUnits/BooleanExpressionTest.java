package Code.src.Domain.Controllers.JUnits;

import Code.src.Domain.Classes.BooleanExpression;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class BooleanExpressionTest {
    ArrayList<String> expressions = new ArrayList<String>();

    @org.junit.Test
    public void testBooleanExpression() {
        expressions.add("{p1 p2 p3} & (\"hola adeu\" | pep) & !joan");
        expressions.add(" vermell | groc");
        expressions.add("\"&\" & \"|\"");
        expressions.add("& & |");
        expressions.add("{p1 p2 p3 p3} | \"bon dia\" & !casa");
        expressions.add("vermell & !groc | ()");
        expressions.add("vermell & !groc | \"()\"");
        expressions.add("(\"blau mari\" & verd)) | groc");
        expressions.add("( hola & (adeu)");

        for (int i = 0; i < expressions.size(); ++i){
            System.out.println("Expression " + i);
            BooleanExpression be = new BooleanExpression(expressions.get(i));
        }
    }

    @org.junit.Test
    public void testGetExpression() throws Exception {
        try {
            BooleanExpression boolExpr1 = new BooleanExpression("{p1 p2 p3} & ( \"hola adeu\" | pep ) & !joan");
            BooleanExpression boolExpr2 = new BooleanExpression("& &");
        } catch(Exception e) {
            System.out.println("Invalid input");
        }
    }

    @org.junit.Test
    public void testIsDocumentValid(){


    }



}
