package cancerAPI;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public abstract class CancerClient {
	private ObjectOutputStream oos;
	public void connect(Socket s) {
		try {
			oos = new ObjectOutputStream(s.getOutputStream());
			new Thread(new Listener(s, this)).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void send(String s) {
		try {
			oos.writeObject(new Message(s));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected abstract void onMessageIncoming(String msg);
	
	public void disconnect() {
		
	}

}
