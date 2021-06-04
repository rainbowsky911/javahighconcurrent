package javahighconcurrent.ch2;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: zdw
 * @Date: 2021/06/04/16:35
 * @Description:    错误的加锁方式
 */
public class AccountingSyncBad  implements Runnable {

    static int i = 0;

    public  synchronized void increment() {
        i++;
    }

    /***
     * 在14行代码前面加上了static。即使两个线程指向不同的Runnable对象，但是方法块请求的的是当前类的锁，
     * 而非当前实例。所以线程见还是可以正确同步。
     */
   /* public static synchronized void increment() {
        i++;
    }*/


    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            increment();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        /**
         * 虽然在14行加了锁，但是两个线程的runnable对象并不是一个实例，
         * 换言之，这两个对象使用的是两把不同的锁，线程安全无法保证，
         */
        Thread thread = new Thread(new AccountingSyncBad());
        Thread thread2 = new Thread(new AccountingSyncBad());
        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
        System.out.println(i);
    }

}


