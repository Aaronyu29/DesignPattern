package u026.repository;

import u026.RequestInfo;

import java.util.*;

// 一次拉取太多数据，容易造成 OOM。
public class RedisMetricsStrorage implements MetricsStorage {
    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {

    }

    @Override
    public List<RequestInfo> getRequestInfosByDuration(String apiName, long startTimestamp, long endTimestamp) {
        return null;
    }

    @Override
    public Map<String, List<RequestInfo>> getAllRequestInfosByDuration(long startTimestamp, long endTimestamp) {
        // 模拟返回数据。
        Map<String,List<RequestInfo>> map = new HashMap<>();
        map.put("register", Arrays.asList(new RequestInfo("register",123,10234),
                new RequestInfo("register",223,12223),
                new RequestInfo("register",323,12334)));
        map.put("login",Arrays.asList(new RequestInfo("login",23,12434),
                new RequestInfo("login",1223,14234)));

        return map;
    }


}
