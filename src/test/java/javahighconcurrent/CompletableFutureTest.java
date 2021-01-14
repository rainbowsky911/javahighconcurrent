package javahighconcurrent;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class CompletableFutureTest {

    public static Integer calc(Integer number) {
        try {
            //模拟一个长时间的执行
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // return number * number;

        /**
         * 组合CompseComplatetableFuture
         */
        return number / 2;
    }

    /**
     * 异步执行任务
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testComplateableTest() throws ExecutionException, InterruptedException {
        final CompletableFuture<Integer>
                future = new CompletableFuture<>().supplyAsync(() -> calc(20));

        System.out.println(future.get());
    }

    /**
     * 流式调用
     * 使用supplueAsync执行一个异步任务，接着连续流式调用对任务的处理结果再进行加工
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testComplateableTest2() throws ExecutionException, InterruptedException {
        final CompletableFuture<Void>
                future = new CompletableFuture<>().supplyAsync(() -> calc(1))
                /*.thenApply((i) -> Integer.toString(i))
                .thenApply((str) -> "\"" + str + "\"")*/
                .thenAccept(System.out::println);

        future.get();
    }

    /**
     * 异常处理
     */
    @Test
    public void testCompletableException() throws ExecutionException, InterruptedException {

        final CompletableFuture<Void>
                future = new CompletableFuture<>().supplyAsync(() -> calcWithZeroException(20))
                .exceptionally(e -> {
                    System.out.println(e.toString());
                    return 0;
                })
                .thenApply((i) -> Integer.toString(i))
                .thenApply((str) -> "\"" + str + "\"")
                .thenAccept(System.out::println);

        future.get();
    }


    /**
     * 组合CompletableCompose
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testComposeCompletable() throws ExecutionException, InterruptedException {
        final CompletableFuture<Void>
                future = new CompletableFuture<>().supplyAsync(() -> calc(20))
                .thenCompose((i) -> CompletableFuture.supplyAsync(() -> calc(i)))
                .thenApply((str) -> "\"" + str + "\"")
                .thenAccept(System.out::println);

        future.get();
    }

    /**
     * 组合CompletableCompose
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testComposeCompletable2() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer>
                future1 = new CompletableFuture<>().supplyAsync(() -> calc(20));

        CompletableFuture<Integer>
                future2 = new CompletableFuture<>().supplyAsync(() -> calc(2));

        CompletableFuture<Void> fu = future1.thenCombine(future2, (i, j) -> i + j)
                .thenApply((str) -> "\"" + str + "\"")
                .thenAccept(System.out::println);
        fu.get();
    }


    public static Integer calcWithZeroException(Integer number) {
        try {
            //模拟一个长时间的执行
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return number / 0;
    }


}
