package Multithreading;

public class ThreadShowName extends Thread {
    public static void main(String[] args) {
        ThreadShowName th1 = new ThreadShowName();
        ThreadShowName th2 = new ThreadShowName();
        th1.start();
        th2.start();
    }

    @Override
    public void run() {
        int pause;
        for (int i = 0; i < 10 ; i++) {
            try{
                System.out.println(getName()+ " being executed");
                pause = (int) (Math.random()*3000); //0-3 seconds
                sleep(pause);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }
}
