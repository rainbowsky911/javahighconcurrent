package javahighconcurrent.ch2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentLockCondition implements Runnable {
    private static ReentrantLock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();

    @Override
    public void run() {

        try {
            lock.lock();
            condition.await();
            System.out.println("thread is going on");
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ReentLockCondition r1 = new ReentLockCondition();
        Thread t = new Thread(r1);
        t.start();
        Thread.sleep(2000);

        //通知t1线程继续执行
        lock.lock();
        condition.signal();
        //虽然已经唤醒了t1线程，但是没有释放锁,但是它无法重新获得锁t1线程还是没法执行
        lock.unlock();
    }
}
