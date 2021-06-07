package javahighconcurrent.ch3;

import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: zdw
 * @Date: 2021/06/05/16:11
 * @Description:
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {

        MyTask myTask = new MyTask();
        //固定个数线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            pool.submit(myTask);
        }
    }

    public static class MyTask implements Runnable {
        @Override
        @SneakyThrows
        public void run() {
            System.out.println(LocalDateTime.now() + ":Thread ID" + Thread.currentThread().getId());
            Thread.sleep(1000);
        }
    }
    /***
     *
     2021-06-05T16:19:57.842:Thread ID12
     2021-06-05T16:19:57.843:Thread ID14
     2021-06-05T16:19:57.843:Thread ID13
     2021-06-05T16:19:57.843:Thread ID15
     2021-06-05T16:19:57.842:Thread ID16

     2021-06-05T16:19:58.851:Thread ID14
     2021-06-05T16:19:58.851:Thread ID13
     2021-06-05T16:19:58.851:Thread ID12
     2021-06-05T16:19:58.851:Thread ID16
     2021-06-05T16:19:58.851:Thread ID15
     * 前5个和后5个输出结果正好相差1秒。前5个的线程ID和后5个的线程ID也是完全一样的。
     */

}
