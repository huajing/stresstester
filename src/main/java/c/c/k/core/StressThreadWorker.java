package c.c.k.core;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

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

    public StressThreadWorker(StressContext stressContext, int everyThreadCount){
        this.stressContext = stressContext;
        this.everyThreadCount = everyThreadCount;
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
            try {
                int i1 = stressContext.getAtomicInteger().incrementAndGet();
                log.info("index {} thread {}", i1, Thread.currentThread());
                stressContext.getStressTask().doTask();
            } catch (Exception e) {
                log.error("Exception", e);
                e.printStackTrace();
            }finally {

            }
        }
    }
}
