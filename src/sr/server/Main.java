package sr.server;

public class Main {

	public static void main(String[] args) throws Exception {
		RegisterServer rserver = new RegisterJettyServer();
		rserver.setup();
		rserver.start();
	}

}
