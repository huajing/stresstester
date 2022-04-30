package c.c.k.core;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @Description TODO
 * @Author chenck
 * @Date 2022/4/30 17:25
 * @Version 1.0
 **/
public class StressThreadWorker implements Runnable {
    private static Log log = LogFactory.get();
    private StressContext stressContext;
    private int everyThreadCount;
    private List<Long> everyTimes;

    public StressThreadWorker(StressContext stressContext, int everyThreadCount){
        this.stressContext = stressContext;
        this.everyThreadCount = everyThreadCount;
        this.everyTimes = new ArrayList<>();
    }

    public void run() {
        try {
            stressContext.getCyclicBarrier().await();
            this.doRun();
        } catch (Exception e) {
            log.error("InterruptedException", e);
            e.printStackTrace();
        }
        //执行完成
        stressContext.getCountDownLatch().countDown();
    }

    private void doRun()throws  Exception{
        for (int i = 0; i < everyThreadCount; i++) {
            long start = System.currentTimeMillis();
            try {
                //可以拿公共资源
                //int i1 = stressContext.getAtomicInteger().incrementAndGet();

                //模拟请求失败
                /*if(RandomUtil.randomBoolean()){
                    throw new Exception();
                }*/
                stressContext.getStressTask().doTask();
            } catch (Exception e) {
                stressContext.getFailedCounter().incrementAndGet();
                log.error("Exception", e);
                e.printStackTrace();
            }finally {
                long limit = System.currentTimeMillis() - start;
                everyTimes.add(limit);
            }
        }
    }

    public List<Long> getEveryTimes() {
        return everyTimes;
    }

    public Long allTime(){
        return everyTimes.stream().mapToLong(Long::longValue).sum();
    }


}
