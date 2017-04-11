package server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import cancerApi.Message;

public class UserConnection implements Runnable {
	Socket client;
	ObjectInputStream input;
	ObjectOutputStream output;
	Room room;
	User user;
	int messageDelay = 17;
	CommandScanner cm = new CommandScanner();

	public UserConnection(Socket socket, Room room) throws IOException {
		this.client = socket;
		this.user = new User();
		this.room = room;
		
		cm.bind("!name", (s) -> {send(new Message("Your name is " + user.getName()));});
		cm.bind("!yell", (s) -> {for(String b:s)room.send(new Message(user.getName()+": "+b.toUpperCase()));});
	}

	@Override
	public void run() {
		try( 
			ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream()))
		{
			input = ois;
			output = oos;
			
		room.send(new Message(user + " connected"));
		
		try {
			while(!client.isInputShutdown()) {
				Message msg = (Message) input.readObject();
				handleMessage(msg);
			} 
		}catch (IOException ioe) {
			disconnect();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		
		} catch(IOException e) {}
		room.send(new Message(user + " disconnected"));
		room.remove(this);
	}
	
	public synchronized void send(Message msg) {
		messageDelay();
		try {
			output.writeObject(msg);
			output.flush();
		} catch (IOException e) {
			disconnect();
		}
	}
	
	void disconnect() {
		try {
		input.close();
		output.close();
		client.close();
		} catch(IOException e) {}
	}
	
	private void messageDelay() {
		try {
			Thread.sleep(messageDelay);
		} catch (InterruptedException e) {}
	}
	
	void handleMessage(Message msg) {
		if(!cm.scan(msg.text)) {
			room.send(new Message(user.getName() + ": " + msg.text));
		}
	}
}

