package u025;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 比如统计用户注册、登录这两个接口的响应时间的最大值和平均值、接口调用次数，并且将统计结果以 JSON 的格式输出到命令行中
 */
public class Metrics {
    /**
     * 要输出接口的响应时间的最大值、平均值和接口调用次数，
     * 我们首先要采集每次接口请求的响应时间，并且存储起来，然后按照某个时间间隔做聚合统计，最后才是将结果输出。
     *
     * 最小原型。
     */
    // Map 的 key 是接口名称，value 对应接口请求的响应时间或者时间戳
    private Map<String, List<Double>> responseTimes = new HashMap<>(); // 响应时间
    private Map<String, List<Double>> timestamps = new HashMap<>(); // 访问时间
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public void recordResponseTime(String apiName,double responseTime) {
        responseTimes.putIfAbsent(apiName,new ArrayList<>());
        responseTimes.get(apiName).add(responseTime);
    }

    public void recordTimestamp(String apiName,double timestamp) {
        timestamps.putIfAbsent(apiName,new ArrayList<>());
        timestamps.get(apiName).add(timestamp);
    }

    public void startRepeatReport(long period, TimeUnit unit) {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Map<String,Map<String,Double>> stats = new HashMap<>();
                responseTimes.forEach((k,v) -> {
                    stats.putIfAbsent(k,new HashMap<>());
                    stats.get(k).put("max",max(v));
                    stats.get(k).put("min",min(v));
                });
                timestamps.forEach((k,v) ->{
                    stats.putIfAbsent(k,new HashMap<>());
                    stats.get(k).put("count", (double) v.size());
                });

            }
        },0,period,unit);
    }

    private Double min(List<Double> dataset) {
        return null;
    }

    private Double max(List<Double> dataset) {
        return null;
    }


}
