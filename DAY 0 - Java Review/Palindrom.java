package com.review;

import java.util.Scanner;

public class Palindrom {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a String: ");
        String s = scanner.nextLine();

        int low=0, high=s.length()-1;

        boolean isPalindrom = true;

        while (low < high){
            if(s.charAt(low) != s.charAt(high)){
                isPalindrom = false;
                break;
            }
            low++; high--;
        }

        if(isPalindrom)
            System.out.println("The word is palindrome");
        else
            System.out.println("The word is not palindrome");
    }
}
