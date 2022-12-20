package FONTS.src.Domain.Controllers.Drivers;

import FONTS.src.Domain.Classes.Sentence;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @file SentenceDriver.java
 * @brief Test <em>Sentence</em>
 */

/**
 * Driver of class Sentence
 *
 * @author Victor Mena Doz
 */

public class SentenceDriver {
    private static Scanner writer = new Scanner(System.in).useDelimiter(Pattern.compile("[\\r\\n;]+"));

    public static Sentence initialCreation() {
        System.out.println("Introduce text of the Sentence:");
        String nWords = readInputString();
        Sentence sen = new Sentence(nWords);
        return sen;
    }

    public static void testSentenceConstruct() throws IOException {
        Sentence s = initialCreation();
        System.out.println("Sentence created -> Text: " + s.getSentence() + "\n");
    }

    public static void testSearchWord() {
        Sentence s = initialCreation();
        System.out.println("Introduce a string to search in the sentence:");
        String word = readInputString();
        if(word.isEmpty() | word.isBlank()) System.err.println("No es correcto introducir una expresion vacia");
        if(s.searchWord(word)) System.out.println("FOUND IT");
        else System.out.printf("NOT FOUND IT");
        System.out.println();
    }

    public static void testGetSentence() {
        Sentence s = initialCreation();
        System.out.println("Text: " + s.getSentence() + "\n");
    }

    public static void main(String[] args) throws IOException {
        String functions = "0. All\n" +
                "1. testSentenceConstruct\n" +
                "2. testSearchWord\n" +
                "3. testGetSentence\n";

        System.out.println("Sentence Driver:");
        System.out.println("Introduce the number allocated to the function you want to test.");
        System.out.println("Functions:");
        System.out.println(functions);
        int code = readInputInteger();
        while(code < 4 & code >= 0) {
            switch (code) {
                case 1:
                    System.out.println("testSentenceConstruct() choose:");
                    testSentenceConstruct();
                    break;
                case 2:
                    System.out.println("testSearchWord() choose:");
                    testSearchWord();
                    break;
                case 3:
                    System.out.println("testGetSentence() choose:");
                    testGetSentence();
                    break;

                default:
                    System.out.println("testSentenceConstruct() choose:");
                    testSentenceConstruct();
                    System.out.println("testSearchWord() choose:");
                    testSearchWord();
                    System.out.println("testGetSentence() choose:");
                    testGetSentence();
            }
            System.out.println();
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
