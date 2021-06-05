package javahighconcurrent.ch6.compeletablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: zdw
 * @Date: 2021/06/04/0:13
 * @Description:
 */
public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
      /*  CompletableFuture f = new CompletableFuture();

        f.complete("hello");
        System.out.println(f.get());

        System.out.println("---------------------------");

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("I'll run in a separate thread than the main thread.");


        });
        System.out.println(future1.get());*/

        CompletableFuture<String> future = CompletableFuture.supplyAsync(()-> {
                return "Result of the asynchronous computation";
        });

        String result = future.get();
      //  System.out.println(result);

    }
}
