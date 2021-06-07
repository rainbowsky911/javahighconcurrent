package javahighconcurrent.ch4;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: zdw
 * @Date: 2021/06/07/9:21
 * @Description:
 */
public class AtomicStanpedRefeerenceDemo {


    /**
     * public boolean compareAndSet(V   expectedReference,V   newReference,int expectedStamp,int newStamp) {}
     * 期望值，写入新值，期望时间戳，新时间戳。
     */

    static AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19, 0);

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            final int stamp = money.getStamp();
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        while (true) {
                            Integer m = money.getReference();
                            if (m < 20) {
                                if (money.compareAndSet(m, m + 20, stamp, stamp + 1)) {
                                    System.out.println("余额小于20元,充值成功。余额:" + money.getReference() + "元");
                                    break;
                                }
                            } else {
                                // System.out.println("余额大于20元，无需充值。");
                                break;
                            }
                        }
                    }
                }
            }.start();
        }

        //用户消费线程，模拟消费行为
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    while (true) {
                        int stamp = money.getStamp();
                        Integer m = money.getReference();
                        if (m > 10) {
                            System.out.println("大于10元");
                            if (money.compareAndSet(m, m - 10, stamp, stamp + 1)) {
                                System.out.println("成功消费10元,余额:" + money.getReference() + "元");
                                break;
                            }
                        } else {
                            System.out.println("没有足够的金额");
                            break;
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                }
            }
        }.start();


    }
}
