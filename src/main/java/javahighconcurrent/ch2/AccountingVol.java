package javahighconcurrent.ch2;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 原子类计算对比普通计算
 *
 * @author 51473
 */
public class AccountingVol extends Thread {

    static AccountingVol instance = new AccountingVol();
    static volatile int i = 0;
    static AtomicLong atomicLong = new AtomicLong(0);

    /**
     * i++不是一个原子操作，计算结果会小于正确的值
     */
    public static void increment() {
        // i++;
        atomicLong.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new AccountingVol();
        Thread t2 = new AccountingVol();

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(atomicLong);

    }

    @Override
    public void run() {

        for (int j = 0; j < 200000; j++) {
            increment();
        }
    }
}
