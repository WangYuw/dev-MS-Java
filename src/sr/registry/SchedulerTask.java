package sr.registry;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.websocket.api.Session;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import sr.transferables.CommandTO;
import sr.transferables.ServiceQualityRequest;


/**
 * This class implements a singleton and runs in a separate thread. 
 * Every 10 seconds, it iterates over its collection of subscribed 
 * clients and sends a WebSocket message to each one.
 */
public class SchedulerTask implements Runnable {
	
	public static final int QUALITY = 100;
	
	private static SchedulerTask instance;
	private static Map<String, Session> sMap = new HashMap<String, Session>();
	
	private SchedulerTask() {}
	
	public static void add(Session s) {
        sMap.put(s.getRemoteAddress().getAddress().toString(), s);
    }

	public static void initialize() {
		if (instance == null) {
            instance = new SchedulerTask();
            new Thread(instance).start();
		}
	}

	@Override
	public void run() {
		 while (true) {
			 try {
				 Thread.sleep(3*1000);
				 for (String key : sMap.keySet()) {
					 Session s = sMap.get(key); 
					 if (s.isOpen()) {
						 CommandTO commandTO = new CommandTO();
						 commandTO.setMethod(QUALITY);
						 commandTO.setData(toJson(new ServiceQualityRequest()));
						 s.getRemote().sendString(toJson(commandTO));
					 }
					 else {
						 sMap.remove(key);
					 }
				 }
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		 }

	}

    private String toJson(Object object) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(object);
    } 
}
