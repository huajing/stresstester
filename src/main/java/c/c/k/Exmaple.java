package c.c.k;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description TODO
 * @Author chenck
 * @Date 2022/4/30 17:05
 * @Version 1.0
 **/

public class Exmaple {
    private static Log log = LogFactory.get();

    public static void main(String[] args) {
        //待压测的url列表
        List<String> urls = new ArrayList<>();
        AtomicInteger atomicInteger = new AtomicInteger();

        StressTestUtils.testAndPrint(200, 1000, ()->{
            //模拟请求时间
            Thread.sleep(RandomUtil.randomLong(100, 200));

//            int i = atomicInteger.incrementAndGet();
//            String url = urls.get(i);
//            String s = HttpUtil.get("https://www.baidu.com");
        });
    }
}
