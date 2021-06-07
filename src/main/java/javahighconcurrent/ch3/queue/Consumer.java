package javahighconcurrent.ch3.queue;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.BlockingQueue;

@Data
@AllArgsConstructor
public class Consumer implements Runnable {

    private BlockingQueue queue;

    @Override
    public void run() {
        try {
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
