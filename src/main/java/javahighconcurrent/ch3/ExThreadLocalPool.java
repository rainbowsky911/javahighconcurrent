package javahighconcurrent.ch3;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: zdw
 * @Date: 2021/06/05/15:34
 * @Description: 扩展线程池
 */
public class ExThreadLocalPool {

    public static void main(String[] args) throws InterruptedException {

        /**
         * 扩展了原有的线程池
         * 重写了beforeExecute 、afterExecute、terminated方法
         *  记录一个任务的开始、结束和整个线程池的退出。
         */

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                5,
                0L,
                TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行:" + ((MyTask) r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成" + ((MyTask) r).name);
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };
        /*   ExecutorService executor = Executors.newFixedThreadPool(5);*/

        for (int i = 0; i < 5; i++) {
            MyTask myTask = new MyTask("Task" + i);

            //使用executor方式          executor和submit的区别
            executor.execute(myTask);
            Thread.sleep(10);
        }

        //shutdown并不会立即暴力关闭所有任务,等所有任务完成后,再关闭线程池
        executor.shutdown();
    }

    @Data
    @AllArgsConstructor
    public static class MyTask implements Runnable {
        private String name;

        @Override
        public void run() {
            System.out.println("正在执行" + "Thread ID:" + Thread.currentThread().getId()
                    + ",Task name" + name);
        }
    }
}
