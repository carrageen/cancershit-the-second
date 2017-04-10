package server;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

import com.chattr.neonardo.chattr.Message;

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
		try {
			File nameList = new File("C:/Users/Manull/Desktop/census-dist-male-first.txt");
			Scanner sc;
			int nameListLines = 1000;
			Random rand = new Random();
			int lineNumber = rand.nextInt(nameListLines);
			String selectedLine = "Robert Paulson";

			sc = new Scanner(nameList);
			for (int i = 0; i<lineNumber;i++) {
				selectedLine=sc.nextLine();
			}
			sc.close();

			String selectedName = selectedLine.substring(0, selectedLine.indexOf(" "));
			selectedName = selectedName.toLowerCase();
			String firstLetter = selectedName.substring(0, 1);
			String otherLetters = selectedName.substring(1, selectedName.length());
			firstLetter = firstLetter.toUpperCase();
			selectedName = firstLetter + otherLetters;

			this.name = selectedName;

		} catch(Exception e) {
			
		}
	}

	public String getName() {
		return name;

	}

	public String toString() {
		return getName();
	}
}
