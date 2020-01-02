package u026.statistics;

public class RequestStat {
    private double maxResponseTime;
    private double minResponseTime;
    private double p99ResponseTime;
    private double p999ResponseTime;
    private double avgResponseTime;
    private long count;
    private long tps;

    public double getMaxResponseTime() {
        return maxResponseTime;
    }

    public void setMaxResponseTime(double maxResponseTime) {
        this.maxResponseTime = maxResponseTime;
    }

    public double getMinResponseTime() {
        return minResponseTime;
    }

    public void setMinResponseTime(double minResponseTime) {
        this.minResponseTime = minResponseTime;
    }

    public double getP99ResponseTime() {
        return p99ResponseTime;
    }

    public void setP99ResponseTime(double p99ResponseTime) {
        this.p99ResponseTime = p99ResponseTime;
    }

    public double getP999ResponseTime() {
        return p999ResponseTime;
    }

    public void setP999ResponseTime(double p999ResponseTime) {
        this.p999ResponseTime = p999ResponseTime;
    }

    public double getAvgResponseTime() {
        return avgResponseTime;
    }

    public void setAvgResponseTime(double avgResponseTime) {
        this.avgResponseTime = avgResponseTime;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getTps() {
        return tps;
    }

    public void setTps(long tps) {
        this.tps = tps;
    }

    @Override
    public String toString() {
        return "RequestStat{" +
                "maxResponseTime=" + maxResponseTime +
                ", minResponseTime=" + minResponseTime +
                ", p99ResponseTime=" + p99ResponseTime +
                ", p999ResponseTime=" + p999ResponseTime +
                ", avgResponseTime=" + avgResponseTime +
                ", count=" + count +
                ", tps=" + tps +
                '}';
    }
}
