package javahighconcurrent.ch3;

import cn.hutool.core.date.LocalDateTimeUtil;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * 演示自定义线程池和拒绝策略的使用
 *
 * @author 51473
 */
public class RejectThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {
        Mytask mytask = new Mytask();
        /**
         *  自定义一个线程池，常驻线程和最大线程数都是5个。这和固定大小的线程池一样
         *  定义一个只有10个容量的等待队列,因为任务量极大,很有可能把内存撑死。
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                5,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r.toString() + "is discard!");
                    }
                });

        for (int i = 0; i < 100; i++) {
            executor.submit(mytask);
            Thread.sleep(10);
        }
    }

    public static class Mytask implements Runnable {

        @Override
        public void run() {
            System.out.println(LocalDateTimeUtil.of(LocalDateTime.now()) + ":Thread ID"
                    + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
        }

    }


}
