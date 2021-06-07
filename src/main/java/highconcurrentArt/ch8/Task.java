package highconcurrentArt.ch8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.*;
import java.util.stream.LongStream;

/**
 * ForkJoinTask
 *
 * @author 51473
 * <p>
 * /**
 * 基本使用
 * if(我的任务足够小){
 * 直接工作
 * }else{
 * 任务划分成两份，
 * 执行并等待结果。
 * }
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

/**
 * 通常做法是继承 RecursiveTask或 RecursiveAction
 * {@link RecursiveTask有返回值}
 * {{@link RecursiveAction 无返回值}}
 */
public class Task extends RecursiveTask<Long> {
    // 区分任务颗粒度
    private static final int THRESHOLD = 2;
    long data[];
    int start;
    int end;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long[] data = LongStream.rangeClosed(1, 100).toArray();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> future = pool.submit(new Task(data, 0, data.length - 1));
        System.out.println("统计结果:" + future.get());
    }

    @Override
    protected Long compute() {
        long sum = 0;
        if (end - start < THRESHOLD) {
            for (int i = start; i <= end; i++) {
                sum += data[i];
            }
        } else {
            int middle = (start + end) / 2;
            Task task1 = new Task(data, start, middle);
            Task task2 = new Task(data, middle + 1, end);
            //发起两个线程任务
            invokeAll(task1, task2);

            //等待线程返回结果
            Long result1 = task1.join();
            Long result2 = task2.join();
            sum = result1 + result2;
        }
        return sum;
    }
}

