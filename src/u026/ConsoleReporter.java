package u026;

import u025.Gson;
import u026.repository.MetricsStorage;
import u026.statistics.Aggregator;
import u026.statistics.RequestStat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 相当于上帝类，定时从数据库中捞数据，然后输出结果到命令行，最后定时执行。
 */
public class ConsoleReporter {

    private MetricsStorage metricsStorage;
    private ScheduledExecutorService executors;

    public ConsoleReporter(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
        executors = Executors.newSingleThreadScheduledExecutor();
    }
    // 第 4 个代码逻辑：定时触发第 1、2、3 代码逻辑的执行。
    public void startRepeatedReport(long period, long durationInSeconds) {
        executors.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // 1. 按照给定的时间区间，从数据库中拉取数据
                long endTimestamp = System.currentTimeMillis();
                long startTimestamp = endTimestamp - durationInSeconds * 1000;

                Map<String, List<RequestInfo>> allRequestInfos
                        = metricsStorage.getAllRequestInfosByDuration(startTimestamp, endTimestamp);
                System.out.println("Time Span: ["+startTimestamp +", "+ endTimestamp +"]");
                Map<String, RequestStat> requestStatMap = new HashMap<>();
                // 2. 根据原始数据，得到统计数据。
                allRequestInfos.forEach((apiName,requestInfos) -> {
                    RequestStat requestStat = Aggregator.aggreagtor(requestInfos, durationInSeconds);
                    requestStatMap.put(apiName,requestStat);
                });

                // 3. 将数据显示到终端
                Gson gson = new Gson();
                System.out.println(gson.toJson(requestStatMap));
            }
        },0,period,TimeUnit.SECONDS);
    }
}
