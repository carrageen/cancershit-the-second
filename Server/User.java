package Server;
import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class User {
	private String name;
	
	public User() {
		generateName();
	}

	void generateName() {
		name = "Robert Paulson";
		try {
			File nameList = new File("https://www2.census.gov/topics/genealogy/1990surnames/dist.male.first");
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

		} catch(Exception e) {}
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return getName();
	}
}
