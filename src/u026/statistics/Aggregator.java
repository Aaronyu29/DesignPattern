package u026.statistics;

import u026.RequestInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// 根据原始数据，得到统计数据。
public class Aggregator {

    public static RequestStat aggreagtor(List<RequestInfo> requestInfos,long durationInSeconds) {
        double maxRespTime = Double.MIN_VALUE;
        double minRespTime = Double.MAX_VALUE;
        double avgRespTime = -1;
        double p99RespTime = -1;
        double p999RespTime = -1;
        double sumRespTime = 0;
        long count = 0;
        for(RequestInfo requestInfo: requestInfos) {
            ++count;
            double respTime = requestInfo.getResponseTime();
            if(maxRespTime < respTime) {
                maxRespTime = respTime;
            }
            if(minRespTime > respTime) {
                minRespTime = respTime;
            }
            sumRespTime += respTime;
        }

        avgRespTime = (count != 0) ? sumRespTime / count : 0;
        long tps = count / durationInSeconds;
        // RequestInfo 按照接口时间从小到大排列
        Collections.sort(requestInfos, new Comparator<RequestInfo>() {
            @Override
            public int compare(RequestInfo o1, RequestInfo o2) {
                double diff = o1.getResponseTime() - o2.getResponseTime();
                if(diff < 0.0) {
                    return -1;
                } else if(diff > 0.0) {
                    return 1;
                } else {
                    return 0;
                }
                // return Double.compare(o2.getResponseTime(),o1.getResponseTime());  升序排列
                // return Double.compare(o2.getResponseTime(),o1.getResponseTime()); 降序排列
            }
        });

        /*requestInfos.forEach(c->{
            System.out.println(c.getResponseTime());
        });*/

        if(count != 0) {
            int index99 = (int) (count * 0.99);
            int index999 = (int) (count * 0.999);
            p99RespTime = requestInfos.get(index99).getResponseTime();
            p999RespTime = requestInfos.get(index999).getResponseTime();
        }

        RequestStat requestStat = new RequestStat();
        requestStat.setAvgResponseTime(avgRespTime);
        requestStat.setCount(count);
        requestStat.setMaxResponseTime(maxRespTime);
        requestStat.setMinResponseTime(minRespTime);
        requestStat.setP99ResponseTime(p99RespTime);
        requestStat.setP999ResponseTime(p999RespTime);
        requestStat.setTps(tps);
        return requestStat;
    }

    public static void main(String[] args) {
        List<RequestInfo> list = new ArrayList<>();
        list.add(new RequestInfo("b",123,222));
        list.add(new RequestInfo("a",234,222));
        list.add(new RequestInfo("c",444,222));
        aggreagtor(list,20);
    }

}
