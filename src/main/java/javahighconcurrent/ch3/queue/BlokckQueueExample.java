package javahighconcurrent.ch3.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ArrayBlockingQueue实现生产者消费者
 * ArrayBlockingQueue有界队列
 * LinkedBlockQueue无界队列 默认Integer.MAX_VALUE
 */
public class BlokckQueueExample {
    public static void main(String[] args) throws InterruptedException {

        BlockingQueue queue =new ArrayBlockingQueue(1024);

        Produce produce=new Produce(queue);
        Consumer consumer =new Consumer(queue);

        new Thread(produce).start();
        new Thread(consumer).start();

        Thread.sleep(4000);
    }
}
