package c.c.k.core;

import java.util.Iterator;
import java.util.List;

/**
 * @Description TODO
 * @Author chenck
 * @Date 2022/4/30 20:20
 * @Version 1.0
 **/

public class StatisticsUtils {
    public static long getTotal(List<Long> times) {
        long total = 0L;

        Long time;
        for(Iterator var4 = times.iterator(); var4.hasNext(); total += time) {
            time = (Long)var4.next();
        }

        return total;
    }

    public static float getAverage(List<Long> allTimes) {
        long total = getTotal(allTimes);
        return getAverage(total, allTimes.size());
    }

    public static float getAverage(long total, int size) {
        return (float)total / (float)size;
    }

    public static float getTps(long totalRequests, long totalTimes, int concurrencyLevel) {
        return (float)totalRequests / totalTimes * concurrencyLevel * 1000.0F;
    }

    public static float toMs(long nm) {
        return (float)nm / 1.0F;
    }

    public static float toMs(float nm) {
        return nm / 1.0F;
    }
}
