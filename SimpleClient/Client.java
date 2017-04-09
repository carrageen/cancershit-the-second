package SimpleClient;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import Server.Message;

public class Client {
	public static void main (String[] args) throws UnknownHostException, IOException {
		Socket server = new Socket("localhost", 4269);
		Scanner scanner = new Scanner(System.in);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(server.getOutputStream());
		new Thread(new Listener(server)).start();
		while (!server.isClosed()) {
			Message message = new Message();
			message.text = scanner.nextLine();
			objectOutputStream.writeObject(message);
			objectOutputStream.flush();
		}
		server.close();
		scanner.close();
	}
}
