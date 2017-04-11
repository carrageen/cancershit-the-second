package server;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import cancerApi.Message;

public class Room {
	private ArrayList<UserConnection> userConnections;
	
	public Room() {
		userConnections = new ArrayList<UserConnection>();
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
	}
}
