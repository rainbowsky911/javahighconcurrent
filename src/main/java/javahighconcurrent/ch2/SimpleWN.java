package javahighconcurrent.ch2;

/**
 * wait和object方法简单声明
 *
 * @author 51473
 */
public class SimpleWN {

    private static Object object = new Object();

    /**
     * t2休眠了两秒 t1得到notify通知还是会重新获得object对象的锁
     * t1需要等待t2线程执行完才能继续执行
     *
     * @param args
     */
    public static void main(String[] args) {

        Thread t1 = new T1();
        Thread t2 = new T2();

        t1.start();
        t2.start();
    }

    public static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T1 start!");
                try {
                    System.out.println(System.currentTimeMillis() + ":T1 wait for object");
                    object.wait();
                } catch (Exception e) {

                }
                System.out.println(System.currentTimeMillis() + ":T1 end!");
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T2 start! notify one thread");
                try {
                    object.notify();
                    System.out.println(System.currentTimeMillis() + ":T2 end!");
                    Thread.sleep(2000);
                } catch (Exception e) {

                }
            }
        }
    }
}
