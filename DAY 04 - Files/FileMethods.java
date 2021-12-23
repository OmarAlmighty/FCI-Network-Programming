package CH04;

import java.io.File;
import java.util.Scanner;

public class FileMethods {
    public static void main(String[] args) {
        String filename;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter name of file/directory ");
        System.out.print("or press <Enter> to quit: ");
        filename = input.nextLine(); // Read file name from user
        while (!filename.equals("")){ // Not <Enter> key.
            File fileDir = new File(filename); // Create a File object
            if (!fileDir.exists()) { // Is the path valid?
                System.out.println(filename + " does not exist!");
                break; // Get out of loop.
            }
            if (fileDir.isFile()) // Is the input points to a file?
                System.out.println("It is a file.");
            else // Is the input points to a folder?
                System.out.println("It is a directory.");
            if (fileDir.canRead()) // Is the file/folder readable?
                System.out.print("It is readable.");
            else
                System.out.println("It is not readable.");
            if (fileDir.canWrite()) // Is the file/folder writable?
                System.out.println("It is writable");
            else
                System.out.println("It is not writable");

            if (fileDir.isDirectory()) { // List the content of the folder
                System.out.println("Contents:");
                String[] fileList = fileDir.list(); // Get list of the files in the folder
                for (int i = 0; i < fileList.length; i++)
                    System.out.println(" " + fileList[i]);
            } else { // If it is a file, print the size
                System.out.print("Size of file: ");
                System.out.println(fileDir.length() + " bytes.");
            }
            System.out.print("\n\nEnter name of next file/directory ");
            System.out.print("or press <Enter> to quit: ");
            filename = input.nextLine();
        }
        input.close();
    }
}
