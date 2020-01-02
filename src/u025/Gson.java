package u025;

import u026.statistics.RequestStat;

import java.util.Map;

public class Gson {
    public String toJson(Map<String, RequestStat> requestStatMap) {
        requestStatMap.forEach((k,v) ->{
            System.out.println("The apiName is: "+k +". The RequestStat is "+ v.toString());
        });
        return "";

    }
    // 应该从 maven 引入进来
}
