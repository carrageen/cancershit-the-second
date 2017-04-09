package SimpleClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import Server.Message;

public class IncomingHandler implements Runnable {
	ObjectInputStream ois;
	Socket socket;
	
	public IncomingHandler(Socket s) throws IOException {
		ois = new ObjectInputStream(s.getInputStream());
		socket = s;
	}

	public void run() {
		while (!socket.isClosed()) {
			try {
				String msg = ((Message) ois.readObject()).msg;
				System.out.println(msg);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
