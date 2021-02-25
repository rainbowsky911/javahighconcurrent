package javahighconcurrent.ch3.threadpool.disappear_error;

import java.util.concurrent.*;

/**
 * 扩展我们的线程池,打印获得我们有价值的信息
 *
 * @author 51473
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {


    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void execute(Runnable task) {
        super.execute(wrap(task, clientTrace(), Thread.currentThread().getName()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(wrap(task, clientTrace(), Thread.currentThread().getName()));
    }

    private Exception clientTrace() {
        return new Exception("Client stack trace");
    }

    private Runnable wrap(final Runnable task, final Exception clientStack,
                          String clientThreadName) {

        return () -> {
            try {
                task.run();
            } catch (Exception e) {
                clientStack.printStackTrace();
                throw e;
            }
        };

    }

    public static void main(String[] args) {

        ThreadPoolExecutor pool = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE,
                0L, TimeUnit.MICROSECONDS, new SynchronousQueue<Runnable>());

        /**
         * 错误堆栈可以看到是在哪里提交的任务
         */
        for (int i = 0; i < 5; i++) {
            pool.execute(new DivTask(100, i));
        }
    }

}
