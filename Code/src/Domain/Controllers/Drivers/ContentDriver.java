package Code.src.Domain.Controllers.Drivers;

import Code.src.Domain.Classes.Content;
import Code.src.Domain.Classes.Sentence;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContentDriver {
    private static Scanner writer = new Scanner(System.in).useDelimiter("\n");

    public static Content initialCreation1() throws IOException {
        System.out.println("Introduce text for the Content: ");
        String text = readInputString();
        if(text.isEmpty() | text.isBlank()) System.err.println("No es correcto introducir una expresion vacia");
        System.out.println("Introduce language for the Content (ENG, CAT or ESP): ");
        String language = readInputString();
        if(language.isEmpty() | language.isBlank()) System.err.println("No es correcto introducir una expresion vacia");
        Content cont = new Content(text, language);
        return cont;
    }

    public static void testContentConstruct() throws IOException {
        Content c = initialCreation1();
        System.out.println("Content created -> Text: " + c.getText() + "\n" + "Language: " + c.getLanguage());
        System.out.println();
    }

    public static void testGetLanguage() throws IOException {
        Content c = initialCreation1();
        System.out.println("Language: " + c.getLanguage());
        System.out.println();
    }

    public static void testGetText() throws IOException {
        Content c = initialCreation1();
        System.out.println("Text: " + c.getText());
        System.out.println();
    }

    public static void testGetVector() throws IOException {
        Content c = initialCreation1();
        System.out.println("Vector for appearance: ");
        Map<String, Integer> aux = c.getVector();
        for(Map.Entry mp: aux.entrySet()) {
            System.out.println(mp.getKey()+ " -> " + mp.getValue() + "\n");
        }
        System.out.println();
    }

    public static void testWordContain() throws IOException {
        Content c = initialCreation1();
        System.out.println("Introduce a string to search in the text: ");
        String word = readInputString();
        if(word.isEmpty() | word.isBlank()) System.err.println("No es correcto introducir una expresion vacia");
        if(c.wordContain(word)) System.out.println("FOUND IT");
        else System.out.printf("NOT FOUND IT");
        System.out.printf("");
    }

    public static void main(String[] args) throws IOException {
        String functions = "0. All\n" +
                "1. testContentConstruct\n" +
                "2. testGetLanguage\n" +
                "3. testGetText\n" +
                "4. testGetVector\n" +
                "5. testWordContain\n" +
                "6. testAll\n";

        System.out.println("Content Driver:");
        System.out.println("Introduce the number allocated to the function you want to test.");
        System.out.println("Functions:");
        System.out.println(functions);
        int code = readInputInteger();
        while(code != 7) {
            switch (code) {
                case 1:
                    System.out.println("testContentConstruct() choose:");
                    testContentConstruct();
                    break;
                case 2:
                    System.out.println("testGetLanguage() choose:");
                    testGetLanguage();
                    break;
                case 3:
                    System.out.println("testGetText() choose:");
                    testGetText();
                    break;
                case 4:
                    System.out.println("testGetVector() choose:");
                    testGetVector();
                    break;
                case 5:
                    System.out.println("testWordContain() choose:");
                    testWordContain();
                    break;

                default:
                    System.out.println("testContentConstruct() test:");
                    testContentConstruct();
                    System.out.println("testGetLanguage() test:");
                    testGetLanguage();
                    System.out.println("testGetText() test:");
                    testGetText();
                    System.out.println("testGetVector() test:");
                    testGetVector();
                    System.out.println("testWordContain() test:");
                    testWordContain();
            }
            code = readInputInteger();
        }
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
