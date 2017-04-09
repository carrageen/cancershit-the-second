package Server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

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
				room.send(new Message(user.getName() + ": " + msg.text));
				System.out.println("Message from " + user.getName() + ": " + msg.text);
			}
		} catch (ClassNotFoundException | IOException e) {
			try {
				input.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		room.removeUser(user);
	}
}
