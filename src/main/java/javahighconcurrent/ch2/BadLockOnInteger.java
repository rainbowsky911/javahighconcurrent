package javahighconcurrent.ch2;

/**
 * 错误的加锁, 。对象一旦创建不可被修改
 * 如果一个Integer对象代表为1,那么它就永远表示1
 */
public class BadLockOnInteger implements Runnable {

    public static Integer i = 0;
    static BadLockOnInteger instance = new BadLockOnInteger();

    @Override
    public void run() {
        for (int k = 0; k < 100000; k++) {
            synchronized (instance) {//加锁成功
                i++;
           // synchronized (i) {
                /**
                 * 加锁失败。把锁加载变量i上有问题，因为i++的在真是执行时变成了Integer.valueOf(i.intValue()+1)
                 * valueOf会倾向返回一个代表指定数值的Integer对象实例，因此i++的本质就是创建一个新的Integer对象，并将他的引用赋值给i
                 *  在多线程见，并不一定能够看到同一个i对象，因为i对象一直在变，因此两个线程每次加锁可能都加在了不同的对象实例上。
                 */
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(i);
    }
}
