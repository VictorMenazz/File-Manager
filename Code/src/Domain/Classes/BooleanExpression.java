package Code.src.Domain.Classes;

import java.util.*;
import java.util.regex.*;

public class BooleanExpression {
    private String [][] expression;
    private String boolExpr;
    private String noQuotes;

    /***
     * Constructor
     * @param s, represents the boolean expression.
     */
    public BooleanExpression(String s){
        boolExpr = s;
        noQuotes = s;
        removeQuotes();
        checkBraces();
        checkParentheses();
        //checkBrackets(s);
        System.out.println(boolExpr);
        System.out.println(noQuotes);




        /*if (isExpressionValid(s)){
            //We will insert character to the expression matrix


        }

        /*int i = 0;

        while (i < s.length()){
            char c = s.charAt(i);
            if (c != ' ') {
                if (isOperator(c)) {
                    //es operador boolea

                } else if (c == '{' || c == '}') {
                    //vindran paraules
                } else if (c == '"') {
                    //ve paraula sencera
                    c = s.charAt(++i);
                    String sentence = "" + c;
                    while (c != '"') {
                        sentence = sentence + c;
                        c = s.charAt(++i);
                    }
                    ++i;
                }
            }
            ++i;
        }*/
    }


    /***
     * @param s
     * @return true if expression is valid, false otherwise.
     */
    private void isExpressionValid(String s){
        ArrayList<String> expressionList = new ArrayList<>();

        //If word is null or empty ("")
        if (s == null || s.isEmpty()) {} //fer saltar excepció;

        //return checkParentheses(s) && checkOperators(s);
    }

    /***
     * Checks proper order parentheses + verifies they are not empty
     * @param s
     * @return
     */
    /*private Boolean checkParentheses(String s){
        //Check proper order brackets
        Pair<Character, Character> brackets = new Pair<>('(', ')');
        Pair<Character, Character> braces = new Pair<>('{', '}');
        Stack<Character> stack = new Stack<>();

        String content = "";
        boolean inside = false;
        boolean evenQuotes = true;

        //Iterate char by char
        for (char c : s.toCharArray()) {
            //found '"'
            if (c == '"') {
                evenQuotes = !evenQuotes;
            }
            else if (inside) content += c;

            //found ')' or '}'
            else if (brackets.second == c || braces.second == c){
                //if we haven't found any '(' or '{' before.
                if (stack.isEmpty() || stack.pop() != brackets.first){
                    break;
                }

                //CHECK CONTENT INSIDE PARENTHESIS IS CORRECT.
                inside = false;
                //checkContent(content);
                content = "";        //reinitialize
            }
            //found '(' or '{'
            else if (brackets.first == c) {
                inside = true;
                stack.push(c);
            }
        }
        if (!stack.isEmpty()) {
            //MILLOR FER-HO COM EXCEPCIÓ
            System.err.println("Invalid expression: Parentheses mismatch");
            return false;
        }
        return true;
    }*/




    /***
     * checks content inside brackets ( )
     * @param s
     */
    private void checkBrackets(String s) {
        Pattern pattern = Pattern.compile("(?<=\\()(.*?)(?=\\))");
        Matcher matcher = pattern.matcher(s);

        //for substring inside { }
        while(matcher.find()) {
            String content = matcher.group();
            //checkOperators(content);
        }
    }



    //-------------------

    private void checkOperands() {
        //let result = s.matches();

        //Delete parentheses
        //String s2 = s.replaceAll("[\\(\\)]", "");
        //If there are consecutive same operators, then convert them to a single operator.
        //String s = noQuotes.replaceAll("[&]{2,}", "&").replaceAll("[|]{2,}", "|").replaceAll("[!]{2,}", "!");

        String[] invalidOperators = {"&|", "|&", "!|", "!&", "!&|", "!|&",
                "&!|", "&|!", "|!&", "|&!"};

        for (String op : invalidOperators) {
            if (noQuotes.contains(op)) System.err.println("Invalid Logic Operator Configuration: " + op);
        }

        //Split string by operators &, |
        String regex = "((?<=\\Q & \\E)|(?=\\Q & \\E))|" + "((?<=\\Q | \\E)|(?=\\Q | \\E))";
        String[] boolExprSplit = noQuotes.split(regex);

        for (String s : boolExprSplit) {
            //Remove spaces between words
            String word = s.trim();
            if (!word.matches("[|&!]")){
                
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
                //if we haven't found any '(' or '{' before.
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
            if (content.length() == 2) System.err.println("Sentence can not be empty");
            else {
                noQuotes = noQuotes.replace(content, "a");
            }
        }
    }


    private String getBoolExpr(){
        return boolExpr;
    }

}

