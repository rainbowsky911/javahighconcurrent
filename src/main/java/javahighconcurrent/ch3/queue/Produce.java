package javahighconcurrent.ch3.queue;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.BlockingQueue;

@Data
@AllArgsConstructor
public class Produce implements Runnable {
    private BlockingQueue queue = null;


    @Override
    public void run() {
        try {
            queue.put("1");
            Thread.sleep(1000);
            queue.put("2");
            Thread.sleep(1000);
            queue.put("3");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
