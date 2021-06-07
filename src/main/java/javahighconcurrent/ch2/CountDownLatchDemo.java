package javahighconcurrent.ch2;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * countDownLacth是一种多线程控制工具,简单成为倒计计数器。它可以让某一个线程等待直到倒计计数结束，再开始执行。
 * 构造函数接收一个整数作为参数，即当前这个计数器的计数个数。
 */
public class CountDownLatchDemo implements Runnable {

    static final CountDownLatch end = new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executorService.submit(demo);
        }
        //等待检查
        end.await();
        //发射
        System.out.println("fire");
        executorService.shutdown();

    }

    @Override
    public void run() {
        try {
            //检查模拟任务
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("check complate");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
