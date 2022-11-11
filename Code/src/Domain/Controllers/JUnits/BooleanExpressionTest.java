package Code.src.Domain.Controllers.JUnits;

import Code.src.Domain.Classes.BooleanExpression;
import static org.junit.Assert.*;
import org.junit.Test;

public class BooleanExpressionTest {

    @org.junit.Test
    public void testBooleanExpression() {
        //Test1
        BooleanExpression boolExpr1 = new BooleanExpression("{p1 p2 p3} & (\"hola adeu\" | pep) & !joan");
        BooleanExpression boolExpr2 = new BooleanExpression("& &");
    }

    @org.junit.Test
    public void testGetExpression(){
        BooleanExpression boolExpr1 = new BooleanExpression("{p1 p2 p3} & (\"hola adeu\" | pep) & !joan");
        BooleanExpression boolExpr2 = new BooleanExpression("& &");


    }

    @org.junit.Test
    public void testIsDocumentValid(){

    }



}
