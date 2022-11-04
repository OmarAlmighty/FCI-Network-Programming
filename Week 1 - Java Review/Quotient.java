package com.review;

import java.util.Scanner;

public class Quotient { // No Exception Handling
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter two numbers: ");
        int n1 = input.nextInt();
        int n2 = input.nextInt();
        System.out.println(n1 + "/" + n2 + " = " + (n1 / n2));
    }
}

class QuotientWithIf { // Exception Handling with If
    public static int quotient(int n1, int n2) {
        if (n2 == 0) {
            System.out.println("Divisor cannot be Zero!");
            System.exit(1);
        }
        return n1 / n2;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter two numbers: ");
        int n1 = input.nextInt();
        int n2 = input.nextInt();
        int result = quotient(n1, n2);
        System.out.println(n1 + " / " + n2 + " = " + result);
    }
}

class QuotientWithException {
    public static int quotient(int n1, int n2) {
        if (n2 == 0)
            throw new ArithmeticException("Divisor cannot be Zero!");
        return n1 / n2;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter two numbers: ");
        int n1 = input.nextInt();
        int n2 = input.nextInt();

        try {
            int result = quotient(n1, n2);
            System.out.println(n1 + " / " + n2 + " = " + result);
        } catch (ArithmeticException ex) {
            System.out.println(ex);
        }
        System.out.println("Execution continues ...");
    }

}