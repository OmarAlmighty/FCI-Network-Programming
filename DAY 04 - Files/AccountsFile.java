package CH04;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class AccountsFile {
    private static final int REC_SIZE = 48;
    private static final int SURNAME_SIZE = 15;
    private static final int NUM_INITS = 3;
    private static long acctNum = 0;
    private static String surname, initials;
    private static float balance;

    public static void main(String[] args) throws IOException {
        RandomAccessFile ranAccts =
                new RandomAccessFile("accounts.dat", "rw");
        Scanner input = new Scanner(System.in);
        String reply = "y";
        do {
            acctNum++;
            System.out.println(
                    "\nAccount number " + acctNum + ".\n");
            System.out.print("Surname: ");
            surname = input.nextLine();
            System.out.print("Initial(s): ");
            initials = input.nextLine();
            System.out.print("Balance: ");
            balance = input.nextFloat();
            //Now get rid of carriage return(!)…
            input.nextLine();
            writeRecord(ranAccts); //Method defined below.
            System.out.print(
                    "\nDo you wish to do this again (y/n)? ");
            reply = input.nextLine();
        } while (reply.equals("y") || reply.equals("Y"));
        System.out.println();
        showRecords(ranAccts); //Method defined below.
    }

    public static void writeRecord(RandomAccessFile file) throws IOException {
        //First find starting byte for current record…
        long filePos = (acctNum - 1) * REC_SIZE;
        //Position fi le pointer…
        file.seek(filePos);
        //Now write the four (fixed-size) fields.
        //Note that a definition must be provided
        //for method writeString…
        file.writeLong(acctNum);
        writeString(file, surname, SURNAME_SIZE);
        writeString(file, initials, NUM_INITS);
        file.writeFloat(balance);
    }

    public static void writeString(RandomAccessFile file,
                                   String text, int fixedSize) throws IOException {
        int size = text.length();
        if (size <= fixedSize) {
            file.writeChars(text);
            //Now 'pad out' the field with spaces…
            for (int i = size; i < fixedSize; i++)
                file.writeChar(' ');
        } else //String is too long!
            file.writeChars(text.substring(0, fixedSize));
        //Write to fi le the first fixedSize characters of
        //string text, starting at byte zero.
    }

    public static void showRecords(RandomAccessFile file)
            throws IOException {
        long numRecords = file.length() / REC_SIZE;
        file.seek(0); //Go to start of file.
        for (int i = 0; i < numRecords; i++) {
            acctNum = file.readLong();
            surname = readString(file, SURNAME_SIZE);
            //readString defined below.
            initials = readString(file, NUM_INITS);
            balance = file.readFloat();
            System.out.printf("" + acctNum
                    + " " + surname
                    + " " + initials + " "
                    + "%.2f %n", balance);
        }
    }

    public static String readString(RandomAccessFile file, int fixedSize) throws IOException {
        String value = ""; //Set up empty string.
        for (int i = 0; i < fixedSize; i++)
            //Read character and concatenate it onto value…
            value += file.readChar();
        return value;
    }
}
