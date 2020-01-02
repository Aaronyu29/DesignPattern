package u025;

import java.util.concurrent.TimeUnit;

public class UserController {
    private Metrics metrics = new Metrics();
    public UserController() {
        metrics.startRepeatReport(60, TimeUnit.SECONDS);
    }

    public void register(UserVo user) {
        long startTimestamp = System.currentTimeMillis();
        metrics.recordTimestamp("register",startTimestamp);
        // ... 省略业务代码
        long respTimestamp = System.currentTimeMillis() - startTimestamp;
        metrics.recordResponseTime("register",respTimestamp); // long 转为 double
    }


    public UserVo login(String username,String password) {
        long startTimestamp = System.currentTimeMillis();
        metrics.recordTimestamp("login",startTimestamp);
        // ...
        long respTimestamp = System.currentTimeMillis() - startTimestamp;
        metrics.recordResponseTime("login",respTimestamp);

        return null;
    }
}
