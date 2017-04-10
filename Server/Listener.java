package server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.chattr.neonardo.chattr.Message;

public class Listener implements Runnable {
	Socket client;
	ObjectInputStream input;
	Room room;
	User user;

	public Listener(User user, Room room) throws IOException {
		this.client = user.getSocket();
		this.user = user;
		input = new ObjectInputStream(user.getSocket().getInputStream());
		this.room = room;
	}

	@Override
	public void run() {
		try {
			while(!client.isInputShutdown()) {
				Message msg = (Message) input.readObject();
				if (msg.text.startsWith("!name"))
					user.send(new Message("Your name is " + user.getName()));
				else {
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
