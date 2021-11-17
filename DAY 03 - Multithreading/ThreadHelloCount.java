package Multithreading;

public class ThreadHelloCount {
    public static void main(String[] args) {
        HelloThread hello = new HelloThread();
        CountThread count = new CountThread();
        hello.start();
        count.start();
    }
}

class HelloThread extends Thread{
    @Override
    public void run() {
        int pause;
        for (int i = 0; i < 5; i++) {
            try{
                System.out.println("Hello!");
                pause = (int)(Math.random()*3000);
                sleep(pause);
            }catch (InterruptedException ex){
                ex.getMessage();
            }
        }
    }
}
class CountThread extends Thread{
    @Override
    public void run() {
        int pause;
        for (int i = 0; i < 5; i++) {
            try{
                System.out.println(i);
                pause = (int)(Math.random()*3000);
                sleep(pause);
            }catch (InterruptedException ex){
                ex.getMessage();
            }
        }
    }
}
