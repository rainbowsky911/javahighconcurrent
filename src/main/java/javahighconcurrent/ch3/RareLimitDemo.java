package javahighconcurrent.ch3;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: zdw
 * @Date: 2021/06/05/15:34
 * @Description: RareLimit限流
 */
public class RareLimitDemo {

    //每秒至多输出两条记录
    static RateLimiter limiter = RateLimiter.create(2);

    public static void main(String[] args) {
        test2();

    }

    public static void test() {
        for (int i = 0; i < 10; i++) {
            limiter.acquire();
            new Thread(new Task()).start();
        }
    }

    /**
     * 当请求成功时，tryAcquire方法返回true。否则返回false，如果访问数据量超出限制。那么超出的部分直接丢弃，不在进行处理。
     */
    public static void test2() {
        for (int i = 0; i < 100; i++) {
            if (!limiter.tryAcquire()) {
                continue;
            }
            new Thread(new Task()).start();
        }
    }

    public static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(LocalDateTime.now());
        }
    }


}
