package c.c.k;

import c.c.k.core.SimpleResultFormater;
import c.c.k.core.StressResult;
import c.c.k.core.StressTask;
import c.c.k.core.StressTester;

/**
 * @Description TODO
 * @Author chenck
 * @Date 2022/4/30 17:01
 * @Version 1.0
 **/

public class StressTestUtils {
    private static StressTester stressTester = new StressTester();
    private static SimpleResultFormater simpleResultFormater = new SimpleResultFormater();

    public static StressResult test(int concurrencyLevel, int totalRequests, StressTask stressTask){
        return stressTester.test(concurrencyLevel, totalRequests, stressTask);
    }
}
