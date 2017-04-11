package server;

import java.util.Arrays;
import java.util.HashMap;

public class CommandScanner {
	HashMap<String, ChatCommand> binds = new HashMap<String, ChatCommand>();
	public void bind(String command, ChatCommand lambda) {
		binds.put(command, lambda);
	}
	
	public boolean scan(String msg) {
		//find inner command calls
		
		
		String[] words = msg.split(" ");
		String command = words[0];
		String[] arguments = Arrays.copyOfRange(words, 1, words.length);
		
		
		if(binds.containsKey(command)) {
			binds.get(command).run(arguments);
			return true;
		} else {
			return false;
		}
	}
	

}
