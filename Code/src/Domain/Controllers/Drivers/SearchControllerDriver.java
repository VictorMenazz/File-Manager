package Code.src.Domain.Controllers.Drivers;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @file SearchControllerDriver.java
 * @brief Test <em>Search Controller</em>
 */

/**
 * @brief Driver of the SearchController class
 * @author Júlia Amenós Dien and Victor Mena Doz
 */
public class SearchControllerDriver {

    private static Scanner writer = new Scanner(System.in).useDelimiter(Pattern.compile("[\\r\\n;]+"));

    private static void testConstructor() {
        //instancia rootfolder

    }

    private static void testAddExpression() {

    }

    private static void testModifyExpression() {

    }

    private static void testDeleteExpression() {

    }

    private static void testGetListExp() {

    }

    private static void testBooleanExpressionSearch() {
        
    }

    private static void testAppearanceSearch() {

    }

    private static void testSearchDocument() {

    }


    public static void main(String[] args) throws Exception {
        String functions =
                "SEARCH CONTROLLER DRIVER:\n" +
                "0. All\n" +
                "1. testConstructor\n" +
                "2. testAddExpression\n" +
                "3. testModifyExpression\n" +
                "4. testDeleteExpression\n" +
                "5. testGetListExp\n" +
                "6. testBooleanExpressionSearch\n" +
                "7. testAppearanceSearch\n" +
                "8. testSearchDocuments\n";
        System.out.println(functions);


    }

    private static String readInputString() {
        String inp = writer.next();
        return inp;
    }

    private static int readInputInteger() {
        Integer inp = writer.nextInt();
        return inp;
    }
}
