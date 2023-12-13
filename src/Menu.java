import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu {
	private Scanner scnr;
	private int menuChoice;
	
	// Default constructor
	public Menu() {
		this.scnr = new Scanner(System.in);
		this.menuChoice = 0;
	}
	
	public int getMenuChoice() throws InputMismatchException {
		this.displayMenu();
		
		this.menuChoice = Integer.parseInt(this.scnr.nextLine());
		
		return this.menuChoice;
	}
	
	public void handleMenuChoice(FootballRoster roster) {
		switch (this.menuChoice) {
			case 1:
				Player newPlayer = this.createNewPlayer();
				roster.addPlayer(newPlayer);
				
				System.out.println(newPlayer.getName() + " has been added to the roster!");
				
				break;
			case 2:
				System.out.println(this.menuChoice);
				break;
			case 3:
				System.out.println(this.menuChoice);
				break;
			case 4:
				System.out.println(this.menuChoice);
				break;
			case 5:
				System.out.println("Have a great day!");
				break;
			default:
				System.out.println();
				System.out.println("Please enter a number 1-5 only.");
				System.out.println();
				break;
		}
	}
	
	public void closeMenuScanner() {
		this.scnr.close();
	}
	
	private void displayMenu() {
		System.out.println("Main Menu");
		System.out.println();
		System.out.println("1. Add a new player to the roster");
		System.out.println("2. Get a players info based on index position in the array");
		System.out.println("3. Save Roster to a File");
		System.out.println("4. Load Roster from a File");
		System.out.println("5. Exit");
		System.out.println();
		System.out.println("Please enter a number from the menu options above: ");
	}
	
	private Player createNewPlayer() {
		System.out.println("Create a player");
		System.out.println();
		
		System.out.println("Enter the player's name: ");
		String name = this.scnr.nextLine();
		
		System.out.println("Enter the player's position on the team: ");
		String position = this.scnr.nextLine();
		
		boolean inputIsNotDouble = true;  // Used to check user input for yards input
		double totalYards = 0.0;
		
		// Try getting total yards, and prompting again if there is not a valid input
		do {
			System.out.println("Enter the player's total passing, receiving, and rushing yards: ");
			try {
				totalYards = Double.parseDouble(this.scnr.nextLine());
				inputIsNotDouble = false;
			}
			catch (Exception e) {
				System.out.println("Please enter a number for your input.");
			}
		} while (inputIsNotDouble);
		
		char yesOrNo = ' ';
		boolean inputIsNotValid = true;
		boolean isDefensive = false;
		
		// Try getting y or n input for whether or not a player is in defensive position
		// Prompt again if the input is not y or n (or capital version of those characters)
		do {
			System.out.println("Is the player in a defensive position? (y/n)");
			yesOrNo = this.scnr.next().charAt(0);
			
			System.out.println(Character.toLowerCase(yesOrNo));
			
			if (Character.toLowerCase(yesOrNo) == 'y' || Character.toLowerCase(yesOrNo) == 'n') {
				isDefensive = yesOrNo == 'y' ? true : false;
				inputIsNotValid = false;
				this.scnr.nextLine();  // Clear the input
			}
			else {
				System.out.println("Please enter with either a 'y' or an 'n'");
				this.scnr.nextLine();  // Clear the input
			}
		} while (inputIsNotValid);
		
		// Return a new player created with inputs
		return new Player(name, position, totalYards, isDefensive);
	}
}