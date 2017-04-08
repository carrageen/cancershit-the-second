package Server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ConnectionHandler implements Runnable {
	Socket client;
	ObjectInputStream input;
	Room room;
	User user;

	public ConnectionHandler(User user, Room room) throws IOException {
		this.client = user.getSocket();
		this.user = user;
		input = new ObjectInputStream(user.getSocket().getInputStream());
		this.room = room;
	}

	@Override
	public void run() {
		try {
			user.info = (UserInfo) input.readObject();
			while(!client.isClosed()) {
				Message msg = (Message) input.readObject();
				msg.msg = user.info.getName() + ": " + msg.msg;
				room.send(msg);
				System.out.println(msg);
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

}
