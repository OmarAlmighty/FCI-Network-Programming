package CH04;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Copy {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 2) {
            System.out.println(
                    "You must supply TWO file names.");
            System.out.println("Syntax:");
            System.out.println(
                    " java Copy <source> <destination>");
            return;
        }
        Scanner source = new Scanner(new File(args[0]));
        PrintWriter destination =
                new PrintWriter(new File(args[1]));
        String input;
        while (source.hasNext()) {
            input = source.nextLine();
            destination.println(input);
        }
        source.close();
        destination.close();
    }
}
