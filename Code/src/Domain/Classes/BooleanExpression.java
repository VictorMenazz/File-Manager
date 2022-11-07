package Code.src.Domain.Classes;

import java.util.*;
import java.util.regex.*;

public class BooleanExpression {
    private String boolExpr;
    private String noQuotes;

    static class nptr {
        char data;
        nptr left, right;
    };

    static nptr newNode(char c) {
        nptr n = new nptr();
        n.data = c;
        n.left = n.right = null;
        return n;
    }

    /***
     * Build Expression Tree from string s.
     * @param s
     * @return
     */
    static nptr build(String s){

        //Stack to hold nodes
        Stack<Character> stN = new Stack<>();

        //Stack to hold chars
        Stack<Character> stT = new Stack<>();
        nptr t, t1, t2;

        //Priority operators
        //Map<Character, Integer> priority = Map.of('&')

        for (int i = 0; i < s.length(); ++i){

            if (s.charAt(i) == '('){
                //stC.add
            }
        }
        return null;
    }



    /***
     * Constructor
     * @param s, represents the boolean expression.
     */
    public BooleanExpression(String s){
        boolExpr = s;
        noQuotes = s;
        removeQuotes();
        boolExpr = boolExpr.replaceAll("\\s{2,}", "");
        noQuotes = noQuotes.replaceAll("\\s{2,}", "");
        checkBraces();
        checkParentheses();
        //System.out.println(boolExpr);
        //System.out.println(noQuotes);


    }

    //-------------------

    private void checkOperands() {
        //Delete parentheses + spaces
        noQuotes = noQuotes.replaceAll("[()]", "");
        String noSpaces = noQuotes.replaceAll("\\s", "");

        String[] invalidOperators = {"&|", "|&", "!|", "!&", "!&|", "!|&",
                "&!|", "&|!", "|!&", "|&!"};

        for (String op : invalidOperators) {
            if (noSpaces.contains(op)) System.err.println("Invalid Logic Operator Configuration: " + op);
        }

        //Split expression by elements
        String[] boolExprSplit = noQuotes.split(" ");

        int n = boolExprSplit.length-1;
        if (n%2 == 1) {
            String word = boolExprSplit[n];
            if (word.equals("&") || word.equals("|") || word.equals("!")) System.err.println("Error: expression contains extra operand at the end");
        }

        for (int i = 1; i < boolExprSplit.length; i = i+2){
            String word = boolExprSplit[i];
            if (!(word.equals("&") || word.equals("|"))) {
                System.err.println("Missing logic operators between \"" + boolExprSplit[i - 1] + "\" and \"" + word + "\"");
            }
        }
    }

    private void checkParentheses() {
        Pair<Character, Character> brackets = new Pair<>('(', ')');
        Stack<Character> stack = new Stack<>();
        //Iterate char by char
        for (char c : noQuotes.toCharArray()) {
            //found ')'
            if (brackets.second == c){
                //if we haven't found any '(' before.
                if (stack.isEmpty() || stack.pop() != brackets.first){
                    break;
                }
                //CHECK CONTENT INSIDE PARENTHESIS IS CORRECT.
            }
            //found '('
            else if (brackets.first == c) {
                stack.push(c);
            }
        }
        if (!stack.isEmpty()) {
            System.err.println("Invalid expression: Parentheses mismatch");
        } else {
            checkOperands();
        }
    }

    /***
     * @param s, content inside braces -> { s }
     * @return s without duplicates.
     */
    private String deleteDuplicates(String s) {
        String regex = "\\b(\\w+)\\b\\s*(?=.*\\b\\1\\b)";
        return s.replaceAll(regex, "");
    }

    /***
     * Checks if the content inside braces is correct + removes content inside
     */
    private void checkBraces() {
        //Pattern pattern = Pattern.compile("(?<=\\{)(.*?)(?=\\})");
        Pattern pattern = Pattern.compile("\\{.*?\\}");
        //Pattern pattern2 = Pattern.compile("^\s" + "\s$")
        Matcher matcher = pattern.matcher(noQuotes);

        //for substring {...}
        while(matcher.find()) {
            String content = matcher.group();
            if (content.isEmpty()) System.err.println("Braces { } must contain at least one word");
            else {
                boolExpr = boolExpr.replace(content, deleteDuplicates(content));
                noQuotes = noQuotes.replace(content, "a");
            }
        }
    }

    /***
     * Deletes content inside quotes
     */
    private void removeQuotes(){
        Pattern pattern = Pattern.compile("\".*?\"");
        Matcher matcher = pattern.matcher(noQuotes);

        //Content WITH the quotes
        while(matcher.find()){
            String content = matcher.group();
            if (content.length() == 2) System.err.println("Sentence cannot be empty");
            else {
                noQuotes = noQuotes.replace(content, "a");
            }
        }
    }

    public String getExpression(){
        return boolExpr;
    }

}


/*


 */

