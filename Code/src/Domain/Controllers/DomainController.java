package Domain.Controllers;
//import Domain.Classes.*;

import Code.src.Domain.Classes.BooleanExpression;

import java.util.Scanner;

public class DomainController {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter boolean expression:");

        String s = scanner.nextLine();
        BooleanExpression bw = new BooleanExpression(s);


    }
}