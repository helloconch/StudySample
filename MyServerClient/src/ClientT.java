import java.io.BufferedReader;
import java.io.IOException;

public class ClientT implements Runnable {
	private BufferedReader brServer;

	public ClientT(BufferedReader brServer) {
		this.brServer = brServer;
	}

	@Override
	public void run() {
		try {
			String line = null;
			while ((line = brServer.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (brServer != null)
					brServer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
