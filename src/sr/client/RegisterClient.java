package sr.client;

import java.io.IOException;
import java.net.URI;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import sr.transferables.CommandTO;
import sr.transferables.RegisterInfoTO;
import sr.transferables.ServiceQualityInfoResp;
import sr.transferables.ServiceQualityTO;
import sr.transferables.ServiceRequest;

public class RegisterClient {
	
	public static final int REGISTER = 1;
	public static final int UNREGISTER = 2;
	public static final int UPDATEREGISTERINFO = 3;
	public static final int SERVICEREQUEST = 4;
	public static final int QUALITY = 100;
	
	private String dateFormat = "yyyy-MM-dd HH:mm:ss";
	
	 public static void main(String[] args) throws Exception {
		 RegisterClient client = new RegisterClient();
	     client.start();
	 }
	 
	 public void start() throws Exception {
		 WebSocketClient client = new WebSocketClient();
		 MyWebSocket socket = new MyWebSocket();
	        
		 client.start();
	        
		 URI destUri = new URI("ws://localhost:8080/registry");
	        
		 ClientUpgradeRequest request = new ClientUpgradeRequest();
		 System.out.println("Connecting to: " + destUri);
		 client.connect(socket, destUri, request);
		 socket.getLatch().await();
		 
		 //test register
		 CommandTO registerCommandTO = new CommandTO();
		 registerCommandTO.setMethod(REGISTER);
		 RegisterInfoTO ri = new RegisterInfoTO();
		 ri.setId(1000);
		 ri.setName("Auth");
		 ri.setIp("auth");
		 ri.setVersion("v1.0");
		 registerCommandTO.setData(toJson(ri));
		 socket.sendMessage(toJson(registerCommandTO));
		 
		 //test unregister
		 CommandTO unregisterCommandTO = new CommandTO();
		 unregisterCommandTO.setMethod(UNREGISTER);
		 unregisterCommandTO.setLong("id", 1000);
		 socket.sendMessage(toJson(unregisterCommandTO));
		 
		 //test updateRegisterInfo
		 CommandTO updateCommandTO = new CommandTO();
		 updateCommandTO.setMethod(UPDATEREGISTERINFO);
		 ri.setIp("new auth");
		 ri.setVersion("v1.1");
		 updateCommandTO.setData(toJson(ri));
		 socket.sendMessage(toJson(updateCommandTO));
		 
		 //test service request (find service by SserviceRequest(name, version))
		 CommandTO requestCommandTO = new CommandTO();
		 requestCommandTO.setMethod(SERVICEREQUEST);
		 ServiceRequest req = new ServiceRequest("Auth", "v1.1");
		 requestCommandTO.setData(toJson(req));
		 socket.sendMessage(toJson(requestCommandTO));
		 
		 Thread.sleep(10000l);
		 
		 client.stop();
	}
	 
	@WebSocket
	public class MyWebSocket {
		
		private Session session;
		CountDownLatch latch= new CountDownLatch(1);
		
		@OnWebSocketConnect
	    public void onConnect(Session session) throws IOException {
			this.session=session;
			System.out.println("Connecting");
			latch.countDown();
	    }

		@OnWebSocketMessage
	    public void onMessage(Session session, String message) {
			if (session.isOpen()) {
				System.out.println("Message from Server: " + message);
				if(message.startsWith("{")) {
					CommandTO commandTO = fromJson(message, CommandTO.class);
					switch (commandTO.getMethod()) {
						case QUALITY: {
							float load = new Random().nextFloat();
							ServiceQualityTO sq = new ServiceQualityTO(10000, load);
							CommandTO qualityCommandTO = new CommandTO();
							qualityCommandTO.setMethod(QUALITY);
							ServiceQualityInfoResp resp = new ServiceQualityInfoResp(1000, sq);
							qualityCommandTO.setData(toJson(resp));
							sendMessage(toJson(qualityCommandTO));
			    		}
						break;
					}
				}
			}
			
		}

		@OnWebSocketClose
		public void onClose(int statusCode, String reason) {
			System.out.println("WebSocket Closed. Code:" + statusCode + ", Reason: " + reason);
		}
		
	    public void sendMessage(String str) {
	        try {
	            session.getRemote().sendString(str);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public CountDownLatch getLatch() {
	        return latch;
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
