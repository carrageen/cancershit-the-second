package server;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import cancerApi.Message;

public class Room {
	ArrayList<UserConnection> userConnections;
	String name;
	
	public Room() {
		userConnections = new ArrayList<UserConnection>();
		setName("room");
	}
	
	public void addConnection(Socket socket) {
		try {
			UserConnection uc = new UserConnection(socket, this);
			userConnections.add(uc);
			new Thread(uc).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(Message msg){
		for (UserConnection u : userConnections) {
			u.send(msg);
		}
		System.out.println(name + ": " + msg.text);
	}
	
	public void remove(UserConnection uc) {
		userConnections.remove(uc);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
