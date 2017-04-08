package Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CancerServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(4269);
		Room room = new Room();
		boolean running = true;
		while(running) {
			Socket client = serverSocket.accept();
			System.out.println("New connection from " + client.getRemoteSocketAddress());
			room.addUser(new User(client));
			
		}
		serverSocket.close();
	}
}
