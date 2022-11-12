package Code.src.Domain.Controllers.Drivers;

import Code.src.Domain.Classes.BooleanExpression;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @file BooleanExpressionDriver.java
 * @brief Test <em>Boolean Expression</em>
 */

/**
 * @brief Driver of the BooleanExpression class
 * @author Júlia Amenós Dien
 */
public class BooleanExpressionDriver {

    private static Scanner scanner = new Scanner(System.in).useDelimiter(Pattern.compile("[\\r\\n;]+"));
    private static String input = "";
    private static BooleanExpression boolExpr;


    private static void testBooleanExpression() throws Exception {
        try {
            System.out.println("Introduce new boolean expression: ");
            input = scanner.next();
            boolExpr = new BooleanExpression(input);
            System.out.println("Boolean expression has been created: "+ input);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void testGetExpression() {
        System.out.println("Boolean expression is: "+ boolExpr.getExpression());
    }


    private static void testIsDocumentValid() {
        System.out.print("Insert sentence to check boolean expression: ");
        String sentence = scanner.next();
        System.out.println(boolExpr.isDocumentValid(sentence));
    }


    public static void main(String[] args) throws Exception {
        String functions =
                "BOOLEAN EXPRESSION DRIVER:\n" +
                "0. All\n" +
                "1. testBooleanExpression\n" +
                "2. testGetExpression\n" +
                "3. testIsDocumentValid\n" +
                "4. Exit\n";
        System.out.println(functions);

        int op = 1;

        while(op != 4){
            switch (op) {
                case 0:
                    testBooleanExpression();
                    testGetExpression();
                    testIsDocumentValid();
                    break;
                case 1:
                    testBooleanExpression();
                    break;
                case 2:
                    testGetExpression();
                    break;
                case 3:
                    testIsDocumentValid();
                    break;
                default:
                    break;
            }
            //System.out.println("Insert new command:");
            op = scanner.nextInt();
        }
    }
}
