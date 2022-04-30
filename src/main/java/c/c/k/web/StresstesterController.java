package c.c.k.web;

import c.c.k.core.SimpleResultFormater;
import c.c.k.core.StressResult;
import c.c.k.core.StressTask;
import c.c.k.core.StressTestUtils;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description TODO
 * @Author chenck
 * @Date 2022/4/30 23:02
 * @Version 1.0
 **/

@RestController
@Slf4j
public class StresstesterController {
    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    @GetMapping("/start")
    public String start(int c, int t){
        if(atomicBoolean.compareAndSet(false, true)) {
            StressResult test = StressTestUtils.test(c, t, stressTask);
            String str = StressTestUtils.format(test, new SimpleResultFormater()).replace("\r\n", "<br/>");
            atomicBoolean.set(false);
            return str;
        }else {
            return "stresstester is running";
        }
    }

    private StressTask stressTask = new StressTask() {
        @Override
        public void doTask() throws Exception {
            HttpUtil.get("http://localhost:8080/");
        }
    };
}
