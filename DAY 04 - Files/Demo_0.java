package CH04;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Demo_0 {
    public static void main(String[] args) throws FileNotFoundException {
        String file_name = "F:\\fci\\Network programming\\" +
                "Java Workspace\\src\\CH04\\data.txt";
        File myfile = new File(file_name);
        PrintWriter writer = new PrintWriter(myfile);
        writer.println("Welcome To Network Programming");
        writer.println(5);
        writer.println(14/7);
        writer.println(true);
        writer.close();
        Scanner reader = new Scanner(myfile);
        String x1 = reader.next();
        int x2 = reader.nextInt();
        float x3 = reader.nextFloat();
        boolean x4 = reader.nextBoolean();
        System.out.println(x1);
        System.out.println(x2);
        System.out.println(x3);
        System.out.println(x4);

    }
}
