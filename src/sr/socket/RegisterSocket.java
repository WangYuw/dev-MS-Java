package sr.socket;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import sr.registry.SchedulerTask;
import sr.registry.ServiceRegistry;
import sr.transferables.CommandTO;
import sr.transferables.RegisterInfoTO;
import sr.transferables.ServiceInfoResp;
import sr.transferables.ServiceQualityInfoResp;
import sr.transferables.ServiceRequest;

@WebSocket(maxTextMessageSize = 64 * 1024)
public class RegisterSocket{
	
	public static final int TTL = 5;
	public static final int REGISTER = 1;
	public static final int UNREGISTER = 2;
	public static final int UPDATEREGISTERINFO = 3;
	public static final int SERVICEREQUEST = 4;
	public static final int QUALITY = 100;
	
	private String dateFormat = "yyyy-MM-dd HH:mm:ss";
	
	private ServiceRegistry registry;
	
	@OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        System.out.println("WebSocket Closed. Code:" + statusCode + ", Reason: " + reason);
    }

    @OnWebSocketError
    public void onError(Throwable t) {
        System.out.println("Error: " + t.getMessage());
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        System.out.println("Connect: " + session.getRemoteAddress().getAddress());
        this.registry = new ServiceRegistry();
        
        //If a client subscribes, add Session to SchedulerTask. 
        SchedulerTask.initialize();
        SchedulerTask.add(session);
    }
    
    @OnWebSocketMessage
    public void onMessage(Session session, String message) {
    	if (session.isOpen()) {
    		//System.out.println("Message: " + message);
    		CommandTO commandTO = fromJson(message, CommandTO.class);
    		switch (commandTO.getMethod()) {
	    		case REGISTER: {
	    			RegisterInfoTO rito = fromJson(commandTO.getData(), RegisterInfoTO.class);
	    			RegisterInfoTO newto = registry.register(rito);
					sendMessage(session ,"Result: Register Success : " + toJson(newto));
	    		}
	    		break;
	    		case UNREGISTER: {
	    			long riId = commandTO.getLong("id");
	    			registry.unregister(riId);
	    			sendMessage(session ,"Unregistering Success: " + riId);
	    		}
	    		break;
	    		case UPDATEREGISTERINFO: {
	    			RegisterInfoTO rito = fromJson(commandTO.getData(), RegisterInfoTO.class);
	    			RegisterInfoTO newto = registry.updateRegisterInfo(rito);
	    			sendMessage(session ,"Update RegisterInfo Success : " + toJson(newto));
	    		}
	    		break;
	    		case SERVICEREQUEST: {
	    			ServiceRequest req = fromJson(commandTO.getData(), ServiceRequest.class);
	    			List<RegisterInfoTO> results = registry.findServicesByNameVersion(req);
	    			RegisterInfoTO min = getMinLoadService(results);
	    			
	    			ServiceInfoResp resp = new ServiceInfoResp();
					resp.setName(min.getName());
					resp.setIp(min.getIp());
					resp.setServiceId(min.getId());
					resp.setVersion(min.getVersion());
					resp.setTtl(TTL*1000);

	    			sendMessage(session ,"Service Request Results : " + toJson(resp));
	    		}
	    		break;
	    		case QUALITY: {
	    			ServiceQualityInfoResp resp = fromJson(commandTO.getData(), ServiceQualityInfoResp.class);
	    			RegisterInfoTO srv = registry.findServiceById(resp.getServiceId());
	    			System.out.println(srv);
	    			System.out.println(resp);
	    			if (srv.getQualityId() == 0) {
	    				srv.setQualityId(resp.getQuality().getId());
	    				registry.createServiceQuality(resp.getQuality());
	    				registry.updateRegisterInfo(srv);
	    			}
	    			else {
	    				registry.updateServiceQuality(resp.getQuality());
	    				registry.updateRegisterInfo(srv);
	    			}
	    			sendMessage(session ,"Update Quality Success : " + toJson(resp));
	    		}
	    		break;
    		}
    	}
    }
    
    //random
    private RegisterInfoTO getMinLoadService(List<RegisterInfoTO> list) {
    	RegisterInfoTO min = list.get(new Random().nextInt(list.size()));
    	return min;
    }
    
    private void sendMessage(Session session, String str) {
        try {
            session.getRemote().sendString(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    
    private <T> T fromJson(String json, Class<T> classOf) {
        try {
            Gson gson = new GsonBuilder().setDateFormat(dateFormat).create();
            return gson.fromJson(json, classOf);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private String toJson(Object object) {
        Gson gson = new GsonBuilder().setDateFormat(dateFormat).create();
        return gson.toJson(object);
    } 
	
}