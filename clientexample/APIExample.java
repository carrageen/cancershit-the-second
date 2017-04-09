package clientexample;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class APIExample {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 4269);
		//Ein CancerClient Object Verbindet sich mit dem Server und erstellt automatisch
		//Den Thread um einkommende Nachrichten zu behandeln.
		//CancerClient ist abstract und muss mit einer onMessageIncoming() Methode
		//ausgestattet werden.
		
		Client client = new Client();
		client.connect(socket);

		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		while(running) {
			//Schicke Nachricht als einfachen String an den Server
			
			client.send(scanner.nextLine());
		}
		scanner.close();
	}

}
