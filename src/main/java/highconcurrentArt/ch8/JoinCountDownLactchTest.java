package highconcurrentArt.ch8;

public class JoinCountDownLactchTest {


    public static void main(String[] args) throws InterruptedException {

        Thread parse1 = new Thread(() -> {
        });

        Thread parse2 = new Thread(() -> System.out.println("parse2 finsh"));
        parse1.start();
        parse2.start();
        parse1.join();
        parse2.join();
        System.out.println("all parse finsh");
    }
}
