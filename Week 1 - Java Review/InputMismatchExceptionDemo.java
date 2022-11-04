package com.review;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputMismatchExceptionDemo {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean continueInput = true;

        do {
            try {
                System.out.print("Enter an integer: ");
                int n = input.nextInt();
                System.out.println("The number you entered is : " + n);
                continueInput = false;
            } catch (InputMismatchException ex) {
                System.out.println("Incorrect input. Try Again");
                input.nextLine();
            }
        } while (continueInput);
    }
}
