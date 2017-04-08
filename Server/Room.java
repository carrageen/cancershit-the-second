package Server;
import java.io.IOException;
import java.util.ArrayList;

public class Room {
	private ArrayList<User> users;
	
	public Room() {
		users = new ArrayList<User>();
	}
	
	public void addUser(User u) {
		users.add(u);
		try {
			new Thread(new ConnectionHandler(u, this)).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void send(Message msg) throws IOException{
		for (User u : users) {
			u.send(msg);
		}
	}

}
