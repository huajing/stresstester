package c.c.k.core;

import lombok.Data;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description TODO
 * @Author chenck
 * @Date 2022/4/30 17:29
 * @Version 1.0
 **/

@Data
public class StressContext {
    private CountDownLatch countDownLatch;
    private CyclicBarrier cyclicBarrier;
    private StressTask stressTask;
    private AtomicInteger atomicInteger;
}
