package Server;

import java.io.Serializable;
/**
 * wird einmal bei verbindungsaufbau vom client empfangen
 * enthält name und farbe und avatar und so ein shit
*/
public class UserInfo implements Serializable{
	private static final long serialVersionUID = -3947594960486424457L;
	private String name;

	public void generateName() {
		name = "Robert Paulson";
	}
	
	public UserInfo() {
		generateName();
	}
	
	public String getName() {
		return name;
	}

}
