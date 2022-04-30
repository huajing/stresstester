package c.c.k.core;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
        long start = System.currentTimeMillis();
        StressContext stressContext = new StressContext();

        CountDownLatch countDownLatch = new CountDownLatch(concurrencyLevel);
        stressContext.setCountDownLatch(countDownLatch);
        stressContext.setCyclicBarrier(new CyclicBarrier(concurrencyLevel));
        stressContext.setStressTask(stressTask);
        stressContext.setFailedCounter(new AtomicInteger());

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
        int realTotalRequests = everyThreadCount * concurrencyLevel;

        StressResult stressResult = new StressResult();
        StressTester.SortResult sortResult = this.getSortedTimes(workerList);
        stressResult.setAllTime(System.currentTimeMillis() - start);
        stressResult.setTotalRequests(totalRequests);
        stressResult.setFailedRequests(stressContext.getFailedCounter().get());
        stressResult.setConcurrencyLevel(concurrencyLevel);
        stressResult.setAllTimes(sortResult.allTimes);

        return stressResult;
    }

    protected StressTester.SortResult getSortedTimes(List<StressThreadWorker> workers) {
        List<Long> allTimes = new ArrayList();
        List<Long> trheadTimes = new ArrayList();
        Iterator var5 = workers.iterator();

        while(var5.hasNext()) {
            StressThreadWorker worker = (StressThreadWorker)var5.next();
            List<Long> everyWorkerTimes = worker.getEveryTimes();
            long workerTotalTime = StatisticsUtils.getTotal(everyWorkerTimes);
            trheadTimes.add(workerTotalTime);
            Iterator var10 = everyWorkerTimes.iterator();

            while(var10.hasNext()) {
                Long time = (Long)var10.next();
                allTimes.add(time);
            }
        }

        Collections.sort(allTimes);
        Collections.sort(trheadTimes);
        StressTester.SortResult result = new StressTester.SortResult();
        result.allTimes = allTimes;
        result.trheadTimes = trheadTimes;
        return result;
    }
    class SortResult {
        List<Long> allTimes;
        List<Long> trheadTimes;

        SortResult() {
        }
    }
}

