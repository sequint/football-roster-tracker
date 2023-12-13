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
	
	public void handleMenuChoice() {
		switch (this.menuChoice) {
			case 1:
				System.out.println(this.menuChoice);
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
}
