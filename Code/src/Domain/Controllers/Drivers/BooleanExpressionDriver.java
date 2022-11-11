package Code.src.Domain.Controllers.Drivers;

import Code.src.Domain.Classes.BooleanExpression;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BooleanExpressionDriver {

    private static Scanner scanner;
    /***
     * Checks if the boolean expression is correctly created
     * Print the tree
     * @param s
     */
    private static void testBooleanExpression(String s){
        BooleanExpression be = new BooleanExpression(s);
        System.out.println("Boolean expression has been created");
    }

    private static void testInorder(String s){
        BooleanExpression be = new BooleanExpression(s);
        System.out.print("Boolean expression tree is: " );
        BooleanExpression.inorder(be.getExpTree());
    }

    private static void testGetExpression(String s){
        BooleanExpression be = new BooleanExpression(s);
        System.out.println(be.getExpression());
    }

    //JUNIT O STUB
    private static void testIsDocumentValid(String sentence, String boolExpr){
        BooleanExpression be = new BooleanExpression(boolExpr);
        System.out.println(be.isDocumentValid(sentence));
    }


    public static void main(String[] args) throws IOException {
        scanner = new Scanner(System.in).useDelimiter("\\n");
        String functions = "0.Introduce new boolean expression\n" +
                "1. testBooleanExpression\n" +
                "2. testInorder\n" +
                "3. testGetExpression\n" +
                "4. Exit\n";
        System.out.println(functions);

        //int op = scanner.nextInt();
        String input = "";

        /*String input = "{p1 p2 p3 p3}";*/
        int op = 0;

        input = scanner.next();
        System.out.println(input);

        while(op != 4){
            op = scanner.nextInt();

            switch (op) {
                case 0:
                    System.out.println("Introduce your boolean expression:");
                    break;
                case 1:
                    testBooleanExpression(input);
                    break;
                case 2:
                    testInorder(input);
                    break;
                case 3:
                    testGetExpression(input);
                    break;
                default:
                    break;
            }
        }
    }
}
