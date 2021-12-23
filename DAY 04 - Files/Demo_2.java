package CH04;

import java.io.FileWriter;
import java.io.IOException;

public class Demo_2 {
    public static void main(String[] args) throws IOException {
        FileWriter file = new FileWriter("F:\\fci\\Network programming" +
                "\\Java Workspace\\src\\CH04\\data.txt", true);
        file.write("This is another text!");
        file.close();
    }
}
