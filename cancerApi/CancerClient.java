package cancerApi;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public abstract class CancerClient {
	private ObjectOutputStream oos;
	private Listener listener;
	Socket socket;
	public void connect(Socket socket) {
		try {
			this.socket = socket;
			oos = new ObjectOutputStream(socket.getOutputStream());
			listener = new Listener(socket, this);
			new Thread(listener).start();
		} catch (IOException e) {
		}
	}
	
	public void send(String s) {
		try {
			oos.writeObject(new Message(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected abstract void onMessageIncoming(Message msg);
	
	public void disconnect() {
		try {
			oos.close();
			listener.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
