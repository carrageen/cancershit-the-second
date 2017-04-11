package server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import cancerApi.Message;

public class Listener implements Runnable {
	Socket client;
	ObjectInputStream input;
	Room room;
	User user;

	CommandScanner cm = new CommandScanner();

	public Listener(User user, Room room) throws IOException {
		this.client = user.getSocket();
		this.user = user;
		input = new ObjectInputStream(user.getSocket().getInputStream());
		this.room = room;
		
		cm.bind("!name", (s) -> {user.send(new Message(user.getName()));});
		cm.bind("!yell", (s) -> {for(String b:s)room.send(new Message(user.getName()+": "+b.toUpperCase()));});
	}

	@Override
	public void run() {
		try {
			while(!client.isInputShutdown()) {
				Message msg = (Message) input.readObject();
				if(!cm.scan(msg.text)) {
					room.send(new Message(user.getName() + ": " + msg.text));
					System.out.println("Message from " + user.getName() + ": " + msg.text);
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			try {
				input.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		room.removeUser(user);
		System.out.println("Connection closed: "+  user + " " + client);
	}
	

}

