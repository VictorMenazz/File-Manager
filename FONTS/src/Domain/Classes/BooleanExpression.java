package FONTS.src.Domain.Classes;

import java.util.*;
import java.util.regex.*;

 /**
  * @file BooleanExpression.java
  *   Class <em>Boolean Expression</em>
  */

/**
 * Class Boolean Expression to search documents
 *
 * @author Júlia Amenós Dien
 */
public class BooleanExpression {
    /**
     * Original boolean expression
     */
    private String originalBoolExpr;

    /**
     * Adjusted boolean expression
     */
    private String boolExpr;

    /**
     * Boolean expression without quotes
     */
    private String noQuotes;

    /**
     * Root of the expression tree
     */
    private Node root;

    /**
     * Node for the expression tree.
     */
    static private class Node {
        String data;
        Node left, right;
    };

    /**
     * Creates an empty node with data.
     * @param c, string with the value for the data of the node.
     * @return the created node.
     */
    static private Node newNode(String c) {
        Node n = new Node();
        n.data = c;
        n.left = n.right = null;
        return n;
    }

    /**
     * Build Expression Tree from boolExpr.
     * @return Node, which will be the root of the Expression Tree.
     */
     private Node build(){
         String e = "( " + boolExpr.trim() + " )";

        String[] expr = e.split(" ");   //separamos expresión por elementos
         /*for (String s : expr){
             System.out.println(s);
         }*/

        //Stack to hold nodes
        Stack<Node> stN = new Stack<>();

        //Stack to hold chars
        Stack<String> stC = new Stack<>();
        Node t, t1, t2;

        //Priority operators
        Map<String, Integer> priority = Map.of("&", 2, "|", 1);

        Boolean isSentence = false;
        String sentence = "";

        for (int i = 0; i < expr.length; ++i){
            if (expr[i].charAt(0) == '"') {
                int n = expr[i].length();
                if (expr[i].charAt(n-1) == '"') {
                    t = newNode(expr[i].substring(1, n-1));
                    stN.add(t);
                } else {
                    isSentence = true;
                    sentence += expr[i].substring(1);   //saltamos comilla
                }
            }
            else if (isSentence) {
                int n = expr[i].length();
                if (expr[i].charAt(n-1) == '"') {
                    sentence = sentence + " " + expr[i].substring(0, n-1);  //saltamos comilla
                    t = newNode(sentence);
                    stN.add(t);
                    sentence = "";  //reiniciar string auxiliar
                    isSentence = false;
                } else {
                    sentence = sentence + " " + expr[i];
                }
            }

            else if (expr[i].equals("(")){
                stC.add(expr[i]);
            }
            else if (expr[i].equals(")")){
                while (!stC.isEmpty() && !stC.peek().equals("(")) {
                    t = newNode(stC.peek());
                    stC.pop();
                    t1 = stN.peek();
                    stN.pop();
                    t2 = stN.peek();
                    stN.pop();
                    t.left = t2;
                    t.right = t1;
                    stN.add(t);
                }
                stC.pop();
            }
            else if (expr[i].equals("&") || expr[i].equals("|")){

                //If an operator with lower or same associativity appears
                while (!stC.isEmpty() && !stC.peek().equals("(") &&
                        ((!expr[i].equals("&") && priority.get(stC.peek()) >= priority.get(expr[i])) ||
                        (expr[i].equals("&") && priority.get(stC.peek()) > priority.get(expr[i])))) {
                    t = newNode(stC.peek());
                    stC.pop();

                    t1 = stN.peek();
                    stN.pop();

                    t2 = stN.peek();
                    stN.pop();

                    t.left = t2;
                    t.right = t1;

                    stN.add(t);
                }
                stC.push(expr[i]);
            }
            else {
                t = newNode(expr[i]);
                stN.add(t);
            }
        }
        t = stN.peek();
        return t;
    }

    /**
     * Recursive function to check a sentence by the boolean expression
     * @param sentence, a sentence from a document.
     * @param n, node of the expression tree
     */
    private Boolean recursiveFind(String sentence, Node n) {
        sentence = sentence.toLowerCase();

        if (n == null) return false;
        //if node is a leaf
        if (n.right == null && n.left == null) {
            if (n.data.charAt(0) == '!') {
                //int idx = n.data.indexOf(n.data.substring(1));
                String s = n.data.substring(1);
                //String regex = ".*\\b" + Pattern.quote(s) + "\\b.*";
                //Pattern p = Pattern.compile("\b" + s + "\b");
                //String pattern = "\\b(?<!" + s + ")\\b" + s + "\\b";
                //Matcher m = p.matcher(sentence);
                //return !sentence.matches(pattern);
                return !sentence.contains(s);
            }
            //String regex = ".*\\b" + Pattern.quote(n.data) + "\\b.*";
            //return sentence.matches("")

            

            return sentence.contains(n.data);

            //return sentence.contains(n.data);

            //System.out.println(sentence);
            //System.out.println(m.matches());

            //return m.matches();
        }
        //node is an operand
        if (n.data.equals("&")) return recursiveFind(sentence, n.right) && recursiveFind(sentence, n.left);
        else if (n.data.equals("|")) return recursiveFind(sentence, n.right) || recursiveFind(sentence, n.left);
        return false;
    }

    /**
     * Checks correct format from boolean operands.
     * @throws Exception
     */
    private void checkOperands() throws Exception {
        //Delete parentheses + whitespaces
        noQuotes = noQuotes.replaceAll("[()]", "");
        noQuotes = noQuotes.replaceAll("\\s{2,}", " "); //avoid multiple whitespaces between words
        String noSpaces = noQuotes.replaceAll("\\s", "");   //String without any whitespace to compare operands

        String[] invalidOperators = {"&|", "|&", "!|", "!&", "!&|", "!|&",
                "&!|", "&|!", "|!&", "|&!"};

        for (String op : invalidOperators) {
            if (noSpaces.contains(op)) {
                throw new Exception("Invalid Logic Operator Configuration: "+ op);
                //System.err.println("Invalid Logic Operator Configuration: " + op);
            }
        }

        //Split expression by elements
        String[] boolExprSplit = noQuotes.split(" ");

        int n = boolExprSplit.length-1;
        if (n%2 == 1) {
            String word = boolExprSplit[n];
            if (word.equals("&") || word.equals("|") || word.equals("!")) throw new Exception("expression contains extra operand at the end");
            //System.err.println("Error: expression contains extra operand at the end");
        }

        for (int i = 1; i < boolExprSplit.length; i = i+2){
            String word = boolExprSplit[i];
            if (!(word.equals("&") || word.equals("|"))) {
                throw new Exception("Missing logic operators between \"" + boolExprSplit[i - 1] + "\" and \"" + word + "\"");
                //System.err.println("Missing logic operators between \"" + boolExprSplit[i - 1] + "\" and \"" + word + "\"");
            }
        }
    }

    /**
     * Checks correct order parentheses
     * @throws Exception
     */
    private void checkParentheses() throws Exception {
        Pair<Character, Character> brackets = new Pair<>('(', ')');
        Stack<Character> stack = new Stack<>();
        //Iterate char by char
        for (char c : noQuotes.toCharArray()) {
            //found ')'
            if (brackets.second == c){
                //if we haven't found any '(' before.
                if (stack.isEmpty() || stack.pop() != brackets.first){
                    stack.push(c);
                    break;
                }

            }
            //found '('
            else if (brackets.first == c) {
                stack.push(c);
            }
        }

        if (!stack.isEmpty()) {
            throw new Exception("Invalid expression: Parentheses mismatch");
            //System.err.println("Invalid expression: Parentheses mismatch");
        } else {
            checkOperands();
        }
    }

    /**
     * Deletes duplicate elements from the sets of words
     * @param s, content inside braces -> { s }
     * @return s without duplicates.
     */
    private String deleteDuplicates(String s) {
        String regex = "\\b(\\w+)\\b\\s*(?=.*\\b\\1\\b)";
        return s.replaceAll(regex, "");
    }

    /**
     * Checks if the content inside braces is correct + removes content inside (to simplify analysis)
     * @throws Exception
     */
    private void checkBraces() throws Exception {
        Pattern pattern = Pattern.compile("(?<=\\{)(.*?)(?=\\})");
        //Pattern pattern = Pattern.compile("\\{.*?\\}");
        Matcher matcher = pattern.matcher(noQuotes);

        //for substring INSIDE {...}
        while(matcher.find()) {
            String content = matcher.group();
            if (content.isEmpty()) throw new Exception("Braces { } must contain at least one word");
                //System.err.println("Braces { } must contain at least one word");
            else {
                String content2 = deleteDuplicates(content);
                content2 = content2.trim().replaceAll(" ", " & ");
                boolExpr = boolExpr.replace(content, content2);
                boolExpr = boolExpr.replaceAll("[{}]", ""); //delete braces
                noQuotes = noQuotes.replace(content, "a");
            }
        }
    }

    /**
     * Put space between parentheses and word to simplify tree building
     * @throws Exception
     */
    private void separateParentheses() throws Exception {
        Pattern pattern = Pattern.compile("^[^(]+(?!\\S)");
        Matcher matcher = pattern.matcher(noQuotes);

        while(matcher.find()) {
            String content = matcher.group();
            if (content.length() == 2) throw new Exception("Parentheses ( ) are useless");
                //System.err.println("Parentheses ( ) are useless");
            else {
                boolExpr = boolExpr.replaceAll("\\(", "( "); //separate parentheses and word
                boolExpr = boolExpr.replaceAll("\\)", " )"); //separate parentheses and word
            }
        }
    }

    /**
     * Deletes content inside quotes (to simplify analysis)
     * @throws Exception
     */
    private void removeQuotes() throws Exception {
        Pattern pattern = Pattern.compile("\".*?\"");
        Matcher matcher = pattern.matcher(noQuotes);

        //Content WITH the quotes
        while(matcher.find()){
            String content = matcher.group();
            if (content.length() == 2) throw new Exception("Sentence can not be empty");
                //System.err.println("Sentence cannot be empty");
            else {
                noQuotes = noQuotes.replace(content, "a");
            }
        }
    }

    //------------------- PUBLIC METHODS -------------------//

    /**
     * Default creator of boolean expression
     * @param s, represents the boolean expression.
     * @throws Exception
     */
    public BooleanExpression(String s) throws Exception {
        originalBoolExpr = s;
        boolExpr = s.toLowerCase();
        noQuotes = s.toLowerCase();
        removeQuotes();
        boolExpr = boolExpr.replaceAll("\\s{2,}", " ");
        noQuotes = noQuotes.replaceAll("\\s{2,}", " ");
        checkBraces();
        checkParentheses();
        separateParentheses();
        root = build();
    }

    /**
     * Returns the original boolean expression
     * @return original boolean expression
     */
    public String getExpression(){
        return originalBoolExpr;
    }


    /**
     * Checks if a sentence is valid by the expression.
     * @param sentence, string to validate expression.
     * @return True if the sentence conforms the boolean expression, false otherwise.
     */
    public Boolean isDocumentValid(String sentence){
        return recursiveFind(sentence, root);
    }

}


