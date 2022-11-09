package Code.src.Domain.Controllers.Drivers;

import Code.src.Domain.Classes.BooleanExpression;

import java.io.IOException;
import java.util.Scanner;


public class BooleanExpressionDriver {

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
        BooleanExpression.inorder(be.getExpression());
    }

    private static void testGetExpression(String s){
        BooleanExpression be = new BooleanExpression(s);
        System.out.println("The root of the Expression Tree is " + be.getExpression());
    }


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce your boolean expression:");

        while(true){
            //Read expression
            String input = scanner.next();
            if (input.equals("exit")) break;

            //For each expression, choose method to test
            while (true) {
                int op = scanner.nextInt();
                switch (op){
                    case 1:
                        testBooleanExpression(input);
                    case 2:
                        testInorder(input);
                    case 3:
                        testGetExpression(input);
                    default:
                        break;
                }
            }
        }
    }
}
