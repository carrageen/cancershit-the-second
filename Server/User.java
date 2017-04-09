package server;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import cancerAPI.Message;

public class User {
	private ObjectOutputStream toClient;
	private Socket socket;
	private String name;

	public User(Socket s) throws IOException {
		toClient = new ObjectOutputStream(s.getOutputStream());
		socket = s;
	}

	public void send(Message msg) throws IOException {
		toClient.writeObject(msg);
		toClient.flush();
	}
	
	public Socket getSocket() {
		return socket;
	}

	public void generateName() {
		name = "Robert Paulson";
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return getName();
	}
}
