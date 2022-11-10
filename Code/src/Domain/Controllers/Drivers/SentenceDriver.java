package Code.src.Domain.Controllers.Drivers;

import Code.src.Domain.Classes.Sentence;

import java.util.Scanner;

public class SentenceDriver {
    private static Scanner writer = new Scanner(System.in);

    public static Sentence initialCreation() {
        System.out.println("Introduce the content of the sentence");
        String nWords = readInputString();
        Sentence sen = new Sentence(nWords);
        return sen;
    }

    public static void searchWord() {

    }

    public static void getSentence() {

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
