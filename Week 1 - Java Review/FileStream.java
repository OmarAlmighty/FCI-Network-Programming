package com.review;

import java.io.*;

public class FileStream {
    public static void main(String args[]) throws IOException {

        try (
                FileOutputStream out = new FileOutputStream("temp.dat");
        ) {
            for (int i = 0; i < 10; i++) {
                out.write(i);
            }
        }
        try (
                FileInputStream in = new FileInputStream("temp.dat");
        ) {
            int val;
            while ((val = in.read()) != -1)
                System.out.print(val + "  ");

        }
    }
}

class DataFileStream {
    public static void main(String[] args) throws IOException {
        System.out.println();
        try (
                DataOutputStream out = new DataOutputStream(new FileOutputStream("data.xyz"));
        ) {
            out.writeUTF("Ahmed");
            out.writeInt(20);
            out.writeUTF("Asmaa");
            out.writeInt(15);
            out.writeUTF("Ali");
            out.writeInt(18);
        }

        try (
                DataInputStream in = new DataInputStream(new FileInputStream("data.xyz"));
        ) {
            System.out.println(in.readUTF() + "  " + in.readInt());
            System.out.println(in.readUTF() + "  " + in.readInt());
            System.out.println(in.readUTF() + "  " + in.readInt());
        }
    }
}
