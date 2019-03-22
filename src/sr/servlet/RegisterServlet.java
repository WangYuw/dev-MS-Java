package sr.servlet;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import sr.socket.RegisterSocket;

@SuppressWarnings("serial")
public class RegisterServlet extends WebSocketServlet {

	@Override
	public void configure(WebSocketServletFactory factory) {
		// set a 10 second timeout
        //factory.getPolicy().setIdleTimeout(10000);
		factory.register(RegisterSocket.class);
		
	}

}
