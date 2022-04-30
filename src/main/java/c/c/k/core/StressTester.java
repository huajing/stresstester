package c.c.k.core;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description TODO
 * @Author chenck
 * @Date 2022/4/30 17:03
 * @Version 1.0
 **/

public class StressTester {
    private static Log log = LogFactory.get();
    public StressResult test(int concurrencyLevel, int totalRequests, StressTask stressTask){
        StressContext stressContext = new StressContext();

        CountDownLatch countDownLatch = new CountDownLatch(concurrencyLevel);
        stressContext.setCountDownLatch(countDownLatch);
        stressContext.setCyclicBarrier(new CyclicBarrier(concurrencyLevel));
        stressContext.setStressTask(stressTask);
        stressContext.setAtomicInteger(new AtomicInteger());

        int everyThreadCount = totalRequests / concurrencyLevel;

        //提前构造worker
        List<StressThreadWorker> workerList = new ArrayList<>(concurrencyLevel);
        for (int i = 0; i < concurrencyLevel; i++) {
            workerList.add(new StressThreadWorker(stressContext, everyThreadCount));
        }

        //线程池执行任务
        ExecutorService executorService = Executors.newFixedThreadPool(concurrencyLevel);
        for (int i = 0; i < concurrencyLevel; i++) {
            executorService.submit(workerList.get(i));
        }

        try {
            //等待所有线程执行完成
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error("InterruptedException", e);
        }
        //关闭线程池
        executorService.shutdownNow();

        return new StressResult();
    }
}
