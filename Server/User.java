package Server;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class User {
	private ObjectOutputStream toClient;
	private Socket socket;
	public UserInfo info;
	public User(Socket s) throws IOException {
		toClient = new ObjectOutputStream(s.getOutputStream());
		socket = s;
	}

	public void send(Message msg) throws IOException {
		toClient.writeObject(msg);
		toClient.flush();
	}
	
	public Socket getSocket() {
		return socket;
	}
}
