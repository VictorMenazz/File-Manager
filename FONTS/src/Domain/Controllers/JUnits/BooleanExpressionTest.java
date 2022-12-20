package FONTS.src.Domain.Controllers.JUnits;

import FONTS.src.Domain.Classes.BooleanExpression;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * @file BooleanExpressionTest.java
 * @brief Test Junit <em>Boolean Expression</em>
 */

/**
 * Test of the BooleanExpression class
 * @author Júlia Amenós Dien
 */

public class BooleanExpressionTest {

    @org.junit.Test
    public void testBooleanExpression() {
        ArrayList<String> expressions = new ArrayList<String>();

        //Commented expressions are not valid, so they do not pass the test
        expressions.add("{p1 p2 p3} & (\"hola adeu\" | pep) & !joan");
        //expressions.add(" vermell | groc");
        expressions.add("\"&\" & \"|\"");
        //expressions.add("& & |");
        expressions.add("{p1 p2 p3 p3} | \"bon dia\" & !casa");
        //expressions.add("vermell & !groc | ()");
        //expressions.add("vermell & !groc | \"()\"");
        expressions.add("(\"blau marí\" & verd) | groc");
        //expressions.add("( hola & (adeu)");

        for (int i = 0; i < expressions.size(); ++i){
            try {
                BooleanExpression be = new BooleanExpression(expressions.get(i));
            } catch(Exception e){
                fail(e.getMessage());
            }
        }
    }

    @org.junit.Test
    public void testGetExpression() throws Exception {
        BooleanExpression expr = new BooleanExpression("{p1 p2 p3} & (\"hola adeu\" | pep) & !joan");
        assertEquals("{p1 p2 p3} & (\"hola adeu\" | pep) & !joan", expr.getExpression());
    }

    @org.junit.Test
    public void testIsDocumentValid(){
        String sentence = "tinc un cotxe vermell";
        BooleanExpression expr = null;
        try {
            expr = new BooleanExpression("\"cotxe vermell\" & tinc");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        BooleanExpression expr2 = null;
        try {
            expr2 = new BooleanExpression("vermell & blau & \"tinc casa\"");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        BooleanExpression expr3 = null;
        try {
            expr3 = new BooleanExpression("{tinc cotxe cotxe vermell}");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Boolean result = expr.isDocumentValid(sentence);
        Boolean result2 = expr2.isDocumentValid(sentence);
        Boolean result3 = expr3.isDocumentValid(sentence);
        assertEquals(Boolean.TRUE, result);
        assertEquals(Boolean.FALSE, result2);
        assertEquals(Boolean.TRUE, result3);
    }


}
