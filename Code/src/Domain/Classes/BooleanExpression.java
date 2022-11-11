package Code.src.Domain.Classes;

import java.util.*;
import java.util.regex.*;

public class BooleanExpression {
    /***
     * @brief Original boolean expression
     */
    private String originalBoolExpr;

    /***
     * @brief adjusted boolean expression
     */
    private String boolExpr;

    /***
     * @brief Boolean expression without quotes
     */
    private String noQuotes;
    /***
     * @brief Root of the expression tree
     */
    private Node root;


    /***
     * @brief Node for the expression tree.
     */
    static public class Node {
        String data;
        Node left, right;
    };

    /***
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

    /***
     * @brief Build Expression Tree from boolExpr.
     * @return Node, which will be the root of the Expression Tree.
     */
     private Node build(){
         String e = "( " + boolExpr.trim() + " )";

        String[] expr = e.split(" ");   //separamos expresión por elementos

        //Stack to hold nodes
        Stack<Node> stN = new Stack<>();

        //Stack to hold chars
        Stack<String> stC = new Stack<>();
        Node t, t1, t2;

        //Priority operators
        Map<String, Integer> priority = Map.of("&", 2, "|", 1);

        for (int i = 0; i < expr.length; ++i){

            if (expr[i].equals("(")){
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

    /***
     * @brief Prints the Expression Tree by inorder
     * @param root, top node of the tree
     */
    public static void inorder(Node root) {
        if (root != null){
            inorder(root.left);
            System.out.print(root.data);
            inorder(root.right);
        }
    }


    /***
     * @brief Constructs the representation for a boolean expression
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
        root = build();

    }

    //-------------------

    /***
     * @brief Checks correct format from boolean operands.
     */
    private void checkOperands() {
        //Delete parentheses + whitespaces
        noQuotes = noQuotes.replaceAll("[()]", "");
        noQuotes = noQuotes.replaceAll("\\s{2,}", " "); //avoid multiple whitespaces between words
        String noSpaces = noQuotes.replaceAll("\\s", "");   //String without any whitespace to compare operands
        System.out.println(noQuotes);

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

    /***
     * @brief Checks correct order parentheses
     */
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
     * @brief Deletes duplicate elements from the sets of words
     * @param s, content inside braces -> { s }
     * @return s without duplicates.
     */
    private String deleteDuplicates(String s) {
        String regex = "\\b(\\w+)\\b\\s*(?=.*\\b\\1\\b)";
        return s.replaceAll(regex, "");
    }

    /***
     * @brief Checks if the content inside braces is correct + removes content inside (to simplify analysis)
     */
    private void checkBraces() {
        Pattern pattern = Pattern.compile("(?<=\\{)(.*?)(?=\\})");
        //Pattern pattern = Pattern.compile("\\{.*?\\}");
        Matcher matcher = pattern.matcher(noQuotes);

        //for substring INSIDE {...}
        while(matcher.find()) {
            String content = matcher.group();
            if (content.isEmpty()) System.err.println("Braces { } must contain at least one word");
            else {
                String content2 = deleteDuplicates(content);
                content2 = content2.trim().replaceAll(" ", " & ");
                System.out.println(content2);
                boolExpr = boolExpr.replace(content, content2);
                boolExpr = boolExpr.replaceAll("[{}]", ""); //delete braces
                noQuotes = noQuotes.replace(content, "a");
            }
        }
    }

    /***
     * @brief Deletes content inside quotes (to simplify analysis)
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

    /***
     * @brief Returns the root of the tree
     * @return root of the Expression Tree
     */
    public Node getExpTree(){
        return root;
    }

    /***
     * @brief Returns the original boolean expression
     * @return
     */
    public String getExpression(){
        return originalBoolExpr;
    }


    /***
     * @brief Recursive function to check a sentence by the boolean expression
     * @param sentence, a sentence from a document.
     * @param n, node of the expression tree
     */
    private Boolean recursiveFind(String sentence, Node n) {
        if (n == null) return false;
        //if node is a leaf
        if (n.right == null & n.left == null) return sentence.contains(n.data);
        //node is an operand
        if (n.data.equals("&")) return recursiveFind(sentence, n.right) & recursiveFind(sentence, n.left);
        else if (n.data.equals("|")) return recursiveFind(sentence, n.right) | recursiveFind(sentence, n.left);
        return false;
    }

    /***
     * @brief Checks if a sentence is valid by the expression.
     * @param sentence, string to validate expression.
     * @return True if the sentence conforms the boolean expression, false otherwise.
     */
    public Boolean isDocumentValid(String sentence){
        return recursiveFind(sentence, root);
    }


};

/*
ELIMINAR ESPAIS EXTRES ENTRE { }, ( ), &, |, !
ENTRE COMILLES NO
 */

