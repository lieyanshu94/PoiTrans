package yhm.web;

public class testMain {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20180606000172840";
    private static final String SECURITY_KEY = "K4MbcrGTKwG1hbW2HOu1";

    public static void main(String[] args) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
        String query = "height 600\nswitch";
        System.out.println(api.getTransResult(query, "auto", "zh"));
    }

}
