package c.c.k;

import c.c.k.core.*;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import java.io.StringWriter;

/**
 * @Description TODO
 * @Author chenck
 * @Date 2022/4/30 17:01
 * @Version 1.0
 **/

public class StressTestUtils {
    private static Log log = LogFactory.get();
    private static StressTester stressTester = new StressTester();
    private static SimpleResultFormater simpleResultFormater = new SimpleResultFormater();

    public static StressResult test(int concurrencyLevel, int totalRequests, StressTask stressTask){
        return stressTester.test(concurrencyLevel, totalRequests, stressTask);
    }

    public static void testAndPrint(int concurrencyLevel, int totalRequests, StressTask stressTask){
        StressResult test = stressTester.test(concurrencyLevel, totalRequests, stressTask);
        System.out.println(format(test, simpleResultFormater));
    }

    public static String format(StressResult stressResult, StressResultFormater formater){
        StringWriter writer = new StringWriter();
        formater.format(stressResult, writer);
        return writer.toString();
    }
}
