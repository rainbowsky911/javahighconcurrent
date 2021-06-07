package javahighconcurrent.ch5.pipeline;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * P1取得了封装两个操作数的Msg,并进行求和。将结果传递给乘法线程P2。当没有数据需要处理
 * P1进行等待
 */
public class Plus implements Runnable {

    public static BlockingQueue<Msg> bq = new LinkedBlockingDeque<>();

    @Override
    public void run() {
        while (true) {
            try {
                Msg msg = bq.take();
                msg.j = msg.i + msg.j;
                Multiply.bq.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
