
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class FootballRoster {
	private Player[] rosterArray;
	private int totalPlayers;
	
	// Default constructor
	public FootballRoster() {
		this.rosterArray = new Player[1000]; // Allocate enough memory to cover changing size of array contents
		this.totalPlayers = 0;
	}
	
	
	
	// Public Class Functions //
	
	public int getTotalNumberOfPlayers() {
		return this.totalPlayers;
	}
	
	// Gets a player with an index passed
	public Player getPlayer(int index) {
		// If the index passed is outside the bounds of non-null elements return null
		if (index < 0 || index >= this.totalPlayers) {
			return null;
		}
		
		return this.rosterArray[index];
	}
	
	public void addPlayer(Player newItem) {
		if (newItem == null) {
			System.out.println("Player not added");
		}
		// Add the new player to the next null element in array
		// and increment the total amount of players in the array
		else {
			this.rosterArray[this.totalPlayers] = newItem;
			this.totalPlayers++;
		}
	}
	
	public void saveRosterToFile(String fileName) {
		// Try initializing a file output stream
		FileOutputStream outputStream = null;
		try {
			outputStream = this.openOutputStream(fileName);
		}
		// If the file fails to open, prompt user to try again
		catch (Exception expt) {
			System.out.println("There was an issue saving to " + fileName);
			System.out.println("Please try again or try another file name.");
		}
		
		PrintWriter writer = new PrintWriter(outputStream);
		
		// If the roster array is empty print error
		if (this.rosterArray[0] == null) {
			System.out.println("There are no players in the roster. Please add a player(s) before saving.");
		}
		// Otherwise print information of each player to the file
		else {
			this.printPlayerInfo(writer);
			writer.close();
		}
	}
	
	public void loadRosterFromFile(String fileName) {
		// Try opening an input stream with the file name passed
		FileInputStream inputStream = null;
		try {
			inputStream = this.openInputStream(fileName);
		}
		catch (Exception expt) {
			System.out.println("There was an issue opening " + fileName);
			System.out.println("Please try again or try another file name.");
		}
		
		// Create a scanner to read the file
		Scanner fileScanner = new Scanner(inputStream);
		
		this.readFileToRoster(fileScanner);
		fileScanner.close();
	}
	
	
	
	// Private Class Functions //
	
	private FileOutputStream openOutputStream(String fileName) throws IOException {
		FileOutputStream outputStream = new FileOutputStream(fileName);
		
		return outputStream;
	}
	
	private void printPlayerInfo(PrintWriter writer) {
		for (int i = 0; i < this.totalPlayers; i++) {
			System.out.println(this.rosterArray[i].getName());
			writer.println(this.rosterArray[i].getName());
			writer.println(this.rosterArray[i].getPosition());
			writer.println(this.rosterArray[i].getpassingReceivingRushingYards());
			writer.println(this.getPositionType(this.rosterArray[i]));
		}
	}
	
	private String getPositionType(Player player) {
		if (player.hasDefensivePosition()) {
			return "defense";
		}
		
		return "offense";
	}
	
	private FileInputStream openInputStream(String fileName) throws IOException {
		FileInputStream inputStream = new FileInputStream(fileName);
		
		return inputStream;
	}
	
	private void readFileToRoster(Scanner fileScanner) {
		// Clear the former array contents and reset the size
		this.clearRosterArray();
		this.totalPlayers = 0;
		
		// While there is another line to scan in the file
		// create an instance of a player and read file contents into the player object
		while (fileScanner.hasNextLine()) {
			String name = fileScanner.nextLine();
			String position = fileScanner.nextLine();
			double yards = Double.parseDouble(fileScanner.nextLine());
			boolean isDefensive = fileScanner.nextLine() == "defense" ? true : false; // Sets the isDefensive boolean based on position type written to file
			
			// Create an instance of a player with the information read from the file
			Player nextPlayer = new Player(name, position, yards, isDefensive);
			
			// Add the player to the next null value in the roster array
			this.rosterArray[this.totalPlayers] = nextPlayer;
			
			// Increment the total players to the next null index and value of total players in the array
			this.totalPlayers++;
		}
	}
	
	private void clearRosterArray() {
		for (int i = 0; i < this.totalPlayers; i++) {
			this.rosterArray[i] = null;
		}
	}
}
