package javahighconcurrent.ch6.compeletablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 流式调用&&组合多个compeletablefuture
 * thenCompose方法用于接力两个CompletableFuture
 * themCombine方法用于组合两个CompletableFuture的输出
 */
public class ComposeTest {

    public static int calc(int para) {
        try {
            //模拟一个长时间执行
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        return para / 2;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> fu =
                CompletableFuture.supplyAsync(() -> calc(50))
                        .thenCompose((i) -> CompletableFuture.supplyAsync(() -> calc(i)))
                        .thenApply((str) -> "\"" + str + "\"")
                        .thenAccept(System.out::println);
        fu.get();

        CompletableFuture<Integer> intFuture = CompletableFuture.supplyAsync(() -> calc(50));
        CompletableFuture<Integer> intFuture2 = CompletableFuture.supplyAsync(() -> calc(25));

        CompletableFuture<Void> f = intFuture.thenCombine(intFuture2, (i, j) -> (i + j))
                .thenApply((str) -> "\"" + str + "\"")
                .thenAccept(System.out::println);
        f.get();
    }

}
