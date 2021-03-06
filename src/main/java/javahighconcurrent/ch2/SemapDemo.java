package javahighconcurrent.ch2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 允许多个多线程同时访问,无论是内部锁synchronized还是重入锁ReentrantLock一次都只允许
 * 一个线程访问一个资源，而信号量可以指定多个线程，同时访问某一个资源。
 */
public class SemapDemo implements Runnable {

    /**
     * 允许5个线程同时访问
     */
    final Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {

        /**
         * 申请使用 acquire操作,离开时使用release方法释放信号量。
         * 系统以5个线程为单位,依次输出带有线程ID的提示文本。
         */
        ExecutorService pool = Executors.newFixedThreadPool(20);
        final SemapDemo demo = new SemapDemo();
        for (int i = 0; i < 20; i++) {
            pool.submit(demo);
        }
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + ":" + "done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
