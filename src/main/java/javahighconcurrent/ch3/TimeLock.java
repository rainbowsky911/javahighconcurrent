package javahighconcurrent.ch3;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁申请等待限时
 *
 * @author 51473
 */
public class TimeLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();

    private int number = 10;

    @Override
    public void run() {

        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                Thread.sleep(2000);
                System.out.println("get lock succ");
            } else {
                System.out.println("get lock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {

        TimeLock timeLock = new TimeLock();
        Thread t1 = new Thread(timeLock);
        Thread t2 = new Thread(timeLock);
        t1.start();
        t2.start();
    }
}
