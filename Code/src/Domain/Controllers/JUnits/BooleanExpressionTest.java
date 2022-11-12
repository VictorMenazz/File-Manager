package Code.src.Domain.Controllers.JUnits;

import Code.src.Domain.Classes.BooleanExpression;
import static org.junit.Assert.*;
import org.junit.Test;

public class BooleanExpressionTest {

    @org.junit.Test
    public void testBooleanExpression() throws Exception {
        //Test1
        try {
            BooleanExpression boolExpr1 = new BooleanExpression("{p1 p2 p3} & (\"hola adeu\" | pep) & !joan");
            //BooleanExpression boolExpr2 = new BooleanExpression("& &");
        } catch(Exception e) {
            System.out.println("Invalid input");
        }
    }

    /*@org.junit.Test
    public void testGetExpression() throws Exception {
        try {
            BooleanExpression boolExpr1 = new BooleanExpression("{p1 p2 p3} & ( \"hola adeu\" | pep ) & !joan");
            BooleanExpression boolExpr2 = new BooleanExpression("& &");
        } catch(Exception e) {
            System.out.println("Invalid input");
        }
    }*/

    @org.junit.Test
    public void testIsDocumentValid(){

    }



}
