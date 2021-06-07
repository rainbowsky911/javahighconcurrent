package highconcurrentArt.ch8;

import java.util.concurrent.CountDownLatch;

public class JoinCountDownLactchTest2 {

    static CountDownLatch countDown = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            System.out.println(1);
            countDown.countDown();
            ;
            System.out.println(2);
            countDown.countDown();
        }).start();

        countDown.await();
        System.out.println("3");
    }
}
