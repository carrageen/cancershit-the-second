package SimpleClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import Server.Message;

public class Listener implements Runnable {
	ObjectInputStream ois;
	Socket socket;
	
	public Listener(Socket s) throws IOException {
		ois = new ObjectInputStream(s.getInputStream());
		socket = s;
	}

	public void run() {
		while (!socket.isInputShutdown()) {
			try {
				String msg = ((Message) ois.readObject()).text;
				System.out.println(msg);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
