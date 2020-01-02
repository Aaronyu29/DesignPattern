package u026.repository;

import u026.RequestInfo;

import java.util.List;
import java.util.Map;

public interface MetricsStorage {

    void saveRequestInfo(RequestInfo requestInfo);
    List<RequestInfo> getRequestInfosByDuration(String apiName,long startTimestamp,long endTimestamp);
    Map<String,List<RequestInfo>> getAllRequestInfosByDuration(long startTimestamp,long endTimestamp);
    // 这个跟上面那个接口的方法不同的是：不需要指定 apiName。
}
