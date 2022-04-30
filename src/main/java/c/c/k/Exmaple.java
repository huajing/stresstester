package c.c.k;

import cn.hutool.http.HttpUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description TODO
 * @Author chenck
 * @Date 2022/4/30 17:05
 * @Version 1.0
 **/

public class Exmaple {
    private static Log log = LogFactory.get();

    public static void main(String[] args) {
        StressTestUtils.test(10, 100, ()->{
            //String s = HttpUtil.get("url here");
            return null;
        });
    }
}
