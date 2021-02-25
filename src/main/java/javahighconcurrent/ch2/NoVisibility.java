package javahighconcurrent.ch2;


public class NoVisibility {
    /***
     * 如果此处不加volatile,主方法修改了ready的值,
     * 线程检测不到ready的变化,所以程序会一直运行下去
     */
    private static   volatile boolean ready;
    private static int number;

    private static void setReady(boolean re){
        ready = re;
    }

    private static class ReaderThread extends Thread{
        @Override
        public void run() {
            while (!ready);
            System.out.println("hello world");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(1000);
        number = 42;
        setReady(true);
        Thread.sleep(1000);
    }
}
