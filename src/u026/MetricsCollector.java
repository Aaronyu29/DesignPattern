package u026;

import u026.repository.MetricsStorage;

public class MetricsCollector {
    private MetricsStorage metricsStorage; // 使用接口而非实现编程

    // 依赖注入
    public MetricsCollector(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    // 用一个函数来实现最小原型中的两个函数。u025.Metrics
    public void recordRequest(RequestInfo requestInfo) {
        if(requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())) {
            return;
        }
        metricsStorage.saveRequestInfo(requestInfo);
    }

}
