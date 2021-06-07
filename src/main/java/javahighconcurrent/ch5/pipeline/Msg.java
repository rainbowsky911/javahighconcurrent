package javahighconcurrent.ch5.pipeline;

/**
 * 计算(B+C)*B/2的结果
 * 线程间携带结果进行信息交换订单载体。
 */
public class Msg {
    public double i;
    public double j;
    public String orgStr = null;
}
