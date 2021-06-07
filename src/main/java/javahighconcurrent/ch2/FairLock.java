package javahighconcurrent.ch2;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: zdw
 * @Date: 2021/06/04/17:28
 * @Description: 测试公平锁和非公平锁
 */
public class FairLock implements Runnable {

    ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        FairLock f = new FairLock();
        Thread thread1 = new Thread(f, "thread1");
        Thread thread2 = new Thread(f, "thread2");
        thread1.start();
        thread2.start();
    }

    @Override
    public void run() {

        while (true) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }

    }


}
