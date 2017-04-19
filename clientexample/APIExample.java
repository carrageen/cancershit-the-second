package clientexample;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;

class APIExample {
	public static void main(String[] args) throws IOException {
		InetAddress host = InetAddress.getByName(new URL("http://niggafaggot.ddnsking.com/").getHost());
		Socket socket = new Socket(host.getHostAddress(), 4269);

		//Ein CancerClient Object verbindet sich mit dem server und erstellt automatisch
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
			//Schicke Nachricht als einfachen String an den server
			client.send(scanner.nextLine());
		}
		
		//Aus H�flichkeit die Verbindung schlie�en.
		client.disconnect();
		scanner.close();
	}
}
