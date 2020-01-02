package u026;

import u026.repository.RedisMetricsStrorage;

public class Application {
    public static void main(String[] args) {
        RedisMetricsStrorage strorage = new RedisMetricsStrorage();
        MetricsCollector metricsCollector = new MetricsCollector(strorage);
        ConsoleReporter consoleReporter = new ConsoleReporter(strorage);
        consoleReporter.startRepeatedReport(60,60);

        metricsCollector.recordRequest(new RequestInfo("register",123,10234));
        metricsCollector.recordRequest(new RequestInfo("register",223,11234));
        metricsCollector.recordRequest(new RequestInfo("register",323,12334));
        metricsCollector.recordRequest(new RequestInfo("login",23,12434));
        metricsCollector.recordRequest(new RequestInfo("login",1223,14234));

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
