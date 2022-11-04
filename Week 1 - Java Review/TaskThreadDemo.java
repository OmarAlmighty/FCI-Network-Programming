package com.review;

public class TaskThreadDemo {
    public static void main(String[] args) {
        Runnable printA = new PrintChar('a', 15);
        Runnable printB = new PrintChar('b', 15);
        Runnable print100 = new PrintNum(15);

        Thread th1 = new Thread(printA);
        Thread th2 = new Thread(printB);
        Thread th3 = new Thread(print100);

        th1.start();
        th2.start();
        th3.start();

    }
}

class PrintChar implements Runnable {
    private char ch;
    private int times;

    public PrintChar(char ch, int times) {
        this.ch = ch;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.times; i++) {
            System.out.println(ch);
        }
    }
}

class PrintNum implements Runnable {
    private int lastNum;

    public PrintNum(int lastNum) {
        this.lastNum = lastNum;
    }

    @Override
    public void run() {
        for (int i = 0; i < lastNum; i++) {
            System.out.println(" " + i);
        }
    }
}