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
			User user = new User(client);
			user.generateName();
			room.addUser(user);
			System.out.println(user.getName() + " connected from " + user.getSocket());
		}
		serverSocket.close();
	}
}
