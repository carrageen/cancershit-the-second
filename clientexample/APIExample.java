package clientexample;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class APIExample {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 4269);
		//Ein CancerClient Object verbindet sich mit dem Server und erstellt automatisch
		//Den Thread um einkommende Nachrichten zu behandeln.
		//CancerClient ist abstract und muss mit einer onMessageIncoming() Methode
		//ausgestattet werden die immer dann aufgerufen wird, wenn eine Nachricht eintrifft.
		//Der Parameter ist der Text der Nachricht. In der Form "Name: Inhalt"
		//Der Client wird nur mit Strings benutzt, Message Objecte kommen nicht vor.
		
		Client client = new Client();
		client.connect(socket);

		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		while(running) {
			//Schicke Nachricht als einfachen String an den Server
			
			client.send(scanner.nextLine());
		}
		
		//Aus Höflichkeit die Verbindung schließen.
		client.disconnect();
		scanner.close();
	}

}
