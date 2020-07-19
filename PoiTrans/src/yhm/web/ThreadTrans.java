package yhm.web;

import java.util.HashMap;
import java.util.Map;

public class ThreadTrans implements Runnable {
    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20180606000172840";
    private static final String SECURITY_KEY = "K4MbcrGTKwG1hbW2HOu1";
    private static ThreadTrans transMethod = null;
    private Map<Integer, String> map = new HashMap<>();
    private ThreadTrans() {
		// TODO Auto-generated constructor stub
	}
    public static ThreadTrans creatThreadTrans() {
    	if (transMethod == null) {
    		transMethod = new ThreadTrans();
    	}
		return transMethod;
    }
	public void setMap(Map<Integer, String> map) {
		this.map = map;
	}

	public void transValue() {
		for (Map.Entry<Integer, String> entry : this.map.entrySet()) {
			int key = entry.getKey();
			Thread thread = new Thread(transMethod,String.valueOf(key));
			thread.start();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		TransApi api = new TransApi(APP_ID, SECURITY_KEY);
		String name = Thread.currentThread().getName();
		String query = map.get(Integer.valueOf(name));
        System.out.println(api.getTransResult(query, "auto", "zh"));
	}
}
