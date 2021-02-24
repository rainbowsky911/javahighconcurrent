package javahighconcurrent.ch2;

public class JoinMain {
    public volatile static int i = 0;

    public static class T1 extends Thread {

        @Override
        public void run() {
            for (int j = 0; j < 1000000; j++) {
                i++;
            }

        }
    }

    /***
     * join表示一个线程加入另一个线程。
     * join 会阻塞当前线程,直到目标线程执行完成。
     * join(long millis) 超过最大等待时间目标线程还在执行，当前线程也会因为"等不及了",继续执行
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new T1();
        t1.start();
        //如果不加人join,最后i的值可能是0,或者一个非常小数字
        t1.join();

        System.out.println(i);

    }
    /**
     * yield一个静态方法，会让当前线程让出一些资源。不是代表当前线程不执行了，还会参与CPU的竞争
     */

}
