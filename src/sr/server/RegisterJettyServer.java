package sr.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;

import sr.servlet.RegisterServlet;

public class RegisterJettyServer implements RegisterServer{
	
	private Server server;
    
    public void setup() {
        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.addConnector(connector);
        
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        server.setHandler(handler);
        handler.addServlet(RegisterServlet.class, "/registry");
    }
    
    public void start() throws Exception {
        server.start();
        server.dump(System.err);
        server.join();
    }

	public static void main(String[] args) throws Exception {
		RegisterServer rserver = new RegisterJettyServer();
		rserver.setup();
		rserver.start();
	}

}
