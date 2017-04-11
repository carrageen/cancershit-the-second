package server;
import java.io.IOException;
import java.util.ArrayList;

import cancerApi.Message;

public class Room {
	private ArrayList<User> users;
	
	public Room() {
		users = new ArrayList<User>();
	}
	
	public void addUser(User u) {
		users.add(u);
		try {
			new Thread(new Listener(u, this)).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		send(new Message(u + " connected"));
	}
	
	public void send(Message msg){
		for (User u : users) {
			u.send(msg);
		}
	}
	
	public void removeUser(User u) {
		users.remove(u);
		send(new Message(u + " disconnected"));
	}

}
