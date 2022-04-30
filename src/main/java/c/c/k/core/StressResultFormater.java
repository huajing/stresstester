package c.c.k.core;

import java.io.Writer;

/**
 * @Description TODO
 * @Author chenck
 * @Date 2022/4/30 17:04
 * @Version 1.0
 **/

public interface StressResultFormater {
    void format(StressResult stressResult, Writer writer);
}
