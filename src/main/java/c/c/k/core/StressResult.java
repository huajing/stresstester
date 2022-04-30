package c.c.k.core;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author chenck
 * @Date 2022/4/30 17:04
 * @Version 1.0
 **/

@Data
public class StressResult {
    private long allTime;
    private int totalRequests;
    private int concurrencyLevel;
    private List<Long> allTimes = new ArrayList<>();
    private int failedRequests;

}
