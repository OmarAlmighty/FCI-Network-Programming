package com.review;

import java.util.Scanner;

public class Demo2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean cont = true;

        do{
            try {
                System.out.print("Enter an integer: ");
                int num = input.nextInt();
                System.out.println("You entered " + num);
                cont = false;
            }catch (Exception e){
                System.out.println("You enterend a worng number");
                input.nextLine();
            }
        }while(cont);
    }

}
