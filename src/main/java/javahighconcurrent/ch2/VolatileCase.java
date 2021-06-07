package javahighconcurrent.ch2;

import java.util.concurrent.TimeUnit;

public class VolatileCase {

    private static boolean ready;
    private static int number;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new PrintThread());
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        number = 20;
        ready = true;
        TimeUnit.SECONDS.sleep(5);

    }

    private static class PrintThread implements Runnable {

        @Override
        public void run() {
            while (!ready) {
                System.out.println("hey jude");
            }
            System.out.println("number is:" + number);
        }

    }

}
