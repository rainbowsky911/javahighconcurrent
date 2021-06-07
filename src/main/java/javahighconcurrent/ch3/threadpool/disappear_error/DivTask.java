package javahighconcurrent.ch3.threadpool.disappear_error;

import lombok.AllArgsConstructor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 堆栈去哪里了,在线程池中寻找堆栈。
 *
 * @author 51473
 */
@AllArgsConstructor
public class DivTask implements Runnable {

    private int a;

    private int b;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0,
                Integer.MAX_VALUE,
                0L,
                TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        for (int i = 0; i < 5; i++) {

            /**
             * 我们可以将executor.submit替换为 Future<?> future = executor.submit(new DivTask(100, i));
             * 这样会打印部分堆栈信息
             *  sutmit不会返回错误堆栈信息。
             */
            // Future<?> future = executor.submit(new DivTask(100, i));
            // future.get();


            //会返回错误堆栈信息。
            //executor.execute(new DivTask(100, i));
        }
    }

    @Override
    public void run() {
        double r = a / b;
        System.out.println(r);
    }
}
