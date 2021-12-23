package CH04;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandFile {
    public static void main(String[] args) throws IOException {
        // Create a random access file object.
        RandomAccessFile file = new RandomAccessFile("myfile.dat", "rw");

        // Writing to the file.
        file.writeChar('S');
        file.writeInt(2222);
        file.writeDouble(222.22);

        file.seek(0); // Moving file pointer to the beginning.

        // Reading data from the file.
        System.out.println(file.readChar());
        System.out.println(file.readInt());
        System.out.println(file.readDouble());

        file.seek(2); // Moving the file pointer to the second item.
        System.out.println(file.readInt());

        // Go to the end and append a boolean value to the file.
        file.seek(file.length());
        file.writeBoolean(true);

        // Since pointer is at the end, beyond the 4th item, brings the file pointer to the 4th item in the file.
        file.seek(4); // Moving the file pointer to the 4th item.
        System.out.println(file.readBoolean()); // Reading 4th item.
        file.close(); // Closing stream.

    }
}
