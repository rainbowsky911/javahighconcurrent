package javahighconcurrent.ch2;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock读写锁
 *
 * @author 51473
 */
public class ReadWriteLockDemo {

    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();

    private int value;

    public static void main(String[] args) {

        final ReadWriteLockDemo demo = new ReadWriteLockDemo();
        Runnable readRunnable = () -> {

            try {
                demo.handleRead(readLock);
                // demo.handleRead(lock);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Runnable writeRunnable = () -> {

            try {
                demo.handleWrite(writeLock, new Random().nextInt());
                //  demo.handleWrite(lock,new Random().nextInt(100));
            } catch (Exception e) {

            }
        };


        for (int i = 0; i < 18; i++) {
            new Thread(readRunnable).start();
        }

        for (int i = 0; i < 20; i++) {
            new Thread(writeRunnable).start();
        }
    }

    public void handleRead(Lock lock) throws Exception {

        lock.lock();    //模拟读操作
        try {
            Thread.sleep(1000); //读操作耗时多，写操作的优势越明显。
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock, int index) {
        lock.lock();

        try {
            Thread.sleep(1000); //模拟写操作
            value = index;
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }
    /***
     * 书上的读写锁执行时间为2秒。重入锁20秒
     */
}
