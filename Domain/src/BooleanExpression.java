import java.util.ArrayList;

import javax.naming.NameClassPair;

public class BooleanExpression {
    private enum Operand {
        AND, OR, NOT
    }

    //Vector de lectura
    private ArrayList<String> expression;

    //s string que conté tots els parámetres
    public BooleanExpression(String s){
        int i = 0;

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
        }
    }

    private boolean isOperator(char c){
        return c == '&' || c == '|' || c == '!';
    }

    private void convertOperator(String c) {
        switch (c) {
            case "!":


        }
    }








}

/*
expressió -> vector de strings

establir operadors:
    al posar el caracter ! => indica que la paraula no ha d'estar




expressió: {p1 p2 p3} & (“hola adéu” | pep) & !joan:

llegir un a un caràcter string.

{} -> indica que venen paraules
"" -> indica que ve strings
() -> prioritat

p1, p2, p3 -> string
"hola adeu" -> frase





 */
