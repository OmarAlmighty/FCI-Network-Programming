package Multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new PrintNum(100));
        executorService.execute(new PrintChar('a', 100));
        executorService.execute(new PrintChar('b', 100));

        executorService.shutdown();
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