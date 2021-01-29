package highconcurrentArt.ch8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * ForkJoinTask
 * @author 51473
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task extends  RecursiveTask<Long> {

    long data [];
    int start;
    int end;



    @Override
    protected Long  compute() {
        long sum=0;
        if(end-start<10){
            for (int i = start; i <= end; i++) {
                sum += data[i];
            }
        }else {
            int midddle=(start+end)/2;
            int middle = (start + end) / 2;
            Task left = new Task(data,start,middle);
            Task right = new Task(data,middle + 1,end);
            left.fork();//fork 操作
            right.fork();//fork 操作
            sum = left.join() + right.join();//join操作
        }
        return sum;
    }

    public static void main(String[] args) {
      long [] data= LongStream.rangeClosed(1,100).toArray();
        long sum = new ForkJoinPool().invoke(new Task(data, 0, data.length - 1));
        System.out.println(sum);
    }
}
