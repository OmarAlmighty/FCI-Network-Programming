package CH04;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Demo_1 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("F:\\fci\\Network programming\\Java Workspace\\src\\CH04\\data.txt");
        PrintWriter writer = new PrintWriter(file);
        writer.println("This is a new Text");
        writer.close();
    }
}
