package SimpleClient;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import Server.Message;
import Server.UserInfo;

public class Client {
	public static void main (String[] args) throws UnknownHostException, IOException {
		Socket server = new Socket(/*"80.139.156.67"*/"localhost", 4269);
		Scanner scanner = new Scanner(System.in);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(server.getOutputStream());
		objectOutputStream.writeObject(new UserInfo());
		new Thread(new IncomingHandler(server)).start();
		while (!server.isClosed()) {
			Message message = new Message();
			message.msg = scanner.nextLine();
			objectOutputStream.writeObject(message);
			objectOutputStream.flush();
		}
		server.close();
		scanner.close();
	}
}
