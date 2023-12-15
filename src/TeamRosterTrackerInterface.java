import java.util.Scanner;
import java.lang.NumberFormatException;
import java.nio.file.Files;
import java.io.File;

public class TeamRosterTrackerInterface {

	public static void main(String[] args) {
		FootballRoster roster = new FootballRoster();
		Scanner scnr = new Scanner(System.in);
		int menuChoice = 0;
		
		// Display program title
		System.out.println("TEAM ROSTER TRACKER");
		System.out.println();
		
		// Display and handle menu choice while it is not 5 (exit)
		do {
			displayMenu();
			
			try {
				menuChoice = Integer.parseInt(scnr.nextLine());
				handleMenuChoice(roster, scnr, menuChoice);
			}
			catch (NumberFormatException numExcpt) {
				System.out.println("\nInvalid input. Please only enter an integer for your menu choice.\n");
			}
		} while (menuChoice != 5);
	}
	
	
	//**** MENU ****//
	
	private static void displayMenu() {
		System.out.println();
		System.out.println("Main Menu");
		System.out.println();
		System.out.println("1. Add a new player to the roster");
		System.out.println("2. Get a players info based on index position in the array");
		System.out.println("3. Save Roster to a File");
		System.out.println("4. Load Roster from a File");
		System.out.println("5. Exit");
		System.out.println();
		System.out.print("Please enter a number from the menu options above: ");
	}
	
	private static void handleMenuChoice(FootballRoster roster, Scanner scnr, int menuChoice) {
		switch (menuChoice) {
			case 1:
				addPlayerToRoster(roster, scnr);
				break;
				
			case 2:
				getPlayerInformation(roster, scnr);
				break;
				
			case 3:
				saveRosterToFile(roster, scnr);
				break;
				
			case 4:
				loadFileInformation(roster, scnr);
				break;
				
			case 5:
				exitProgram(scnr);
				break;
				
			default:
				printInputErrorMessage();
				break;
		}
	}
	
	
	//**** CASE 1 ****//

	private static void addPlayerToRoster(FootballRoster roster, Scanner scnr) {
		// Title for the create choice to section off the prompts
		System.out.println("\nCreate a player\n");
		
		// Get the player's information
		String name = getPlayerName(scnr);
		String position = getPlayerPosition(scnr);
		double totalYards = getTotalYards(scnr);
		boolean isDefensive = playerIsDefensive(scnr);
		Player newPlayer = new Player(name, position, totalYards, isDefensive);
		
		roster.addPlayer(newPlayer);
		
		System.out.println("\n" + newPlayer.getName() + " has been added to the roster!\n");
	}
	
	private static String getPlayerName(Scanner scnr) {
		System.out.print("Enter the player's name: ");
		return scnr.nextLine();
	}
	
	private static String getPlayerPosition(Scanner scnr) {
		System.out.print("Enter the player's position on the team: ");
		return scnr.nextLine();
	}
	
	private static double getTotalYards(Scanner scnr) {
		boolean inputIsNotDouble = true;  // Used to check user input for yards input
		double totalYards = 0.0;
		
		// Try getting total yards, and prompting again if there is not a valid input
		do {
			System.out.print("Enter the player's total passing, receiving, and rushing yards: ");
			try {
				totalYards = Double.parseDouble(scnr.nextLine());
				inputIsNotDouble = false;
			}
			catch (Exception e) {
				System.out.println("\nPlease enter a number for your input.");
			}
		} while (inputIsNotDouble);
		
		return totalYards;
	}
	
	private static boolean playerIsDefensive(Scanner scnr) {
		char yesOrNo = ' ';
		boolean inputIsNotValid = true;
		boolean isDefensive = false;
		
		// Try getting y or n input for whether or not a player is in defensive position
		// Prompt again if the input is not y or n (or capital version of those characters)
		do {
			System.out.print("Is the player in a defensive position? (y/n)");
			yesOrNo = scnr.next().charAt(0);
			
			if (Character.toLowerCase(yesOrNo) == 'y') {
				isDefensive = true;
				inputIsNotValid = false;
				clearScannerInput(scnr);
			}
			else if (Character.toLowerCase(yesOrNo) == 'n') {
				isDefensive = false;
				inputIsNotValid = false;
				clearScannerInput(scnr);
			}
			else {
				System.out.println("\nPlease enter with either a 'y' or an 'n'");
				clearScannerInput(scnr);
			}
		} while (inputIsNotValid);
		
		return isDefensive;
	}
	
	
	//**** CASE 2 ****//
	
	private static void getPlayerInformation(FootballRoster roster, Scanner scnr) {
		int numPlayers = roster.getTotalNumberOfPlayers();
		int playerIndex = 0;
		boolean inputIsNotValid = false;
		
		// If the roster has no players print an error message
		if (numPlayers < 1) {
			System.out.println("\nPlease enter a player into the roster before trying to access one.");
		}
		// Otherwise, get the information
		else {
			do {
				System.out.print("\nEnter the index position within the roster array to get that players info: ");
				
				try {
					playerIndex = Integer.parseInt(scnr.nextLine());
					
					inputIsNotValid = false;  // If an error was not thrown, the index was valid
					
					Player player = roster.getPlayer(playerIndex);
					
					String positionType = player.hasDefensivePosition() ? "Defense" : "Offense";
					
					System.out.println("\nPlayer Info:\n");
					System.out.println("Name: " + player.getName());
					System.out.println("Position: " + player.getPosition());
					System.out.println("Total Passing/Receiving/Rushing Yards: " + player.getpassingReceivingRushingYards());
					System.out.println("Position Type: " + positionType);
					System.out.println();
				}
				catch (NullPointerException nullExpt) {
					System.out.println("\nThat index does not exist in the roster array.");
					System.out.println("You may enter a number between 0 and " + (roster.getTotalNumberOfPlayers() - 1) + " to get a player's information.");
					
					inputIsNotValid = true;
				}
				catch (Exception e) {
					System.out.print("\nInvalid input, please enter an integer to access a player's information.\n");
					
					inputIsNotValid = true;
				}
			} while (inputIsNotValid);
		}
	}
	
	
	//**** CASE 3 ****//
	
	private static void saveRosterToFile(FootballRoster roster, Scanner scnr) {
		// Get the chose file name from the user and pre-pend text file format to end
		System.out.print("\nEnter file name to save roster to (will be saved as a .txt file, do not include '.txt' in name): ");
		String fileName = scnr.nextLine() + ".txt";
		
		// Check if the file already exists
		File file = new File(fileName);
		if (Files.exists(file.toPath())) {
			// If the file exists, prompt the user for override confirmation
			System.out.print("\nThe file name '" + fileName + "' already exists.");
			
			char response = ' ';
			boolean validResponse = false;
			do {
				System.out.print("Do you wish to override the current file? (y/n)");
				response = scnr.next().charAt(0);
				
				validResponse = isYesOrNo(response);
				
				clearScannerInput(scnr);
			} while (!validResponse);
			
			// Save the file if the user selects y, otherwise return to the menu
			if (Character.toLowerCase(response) == 'y') {
				saveFileAndPrintSuccess(roster, fileName);
			}
			else {
				System.out.println("\nReturning to the main menu.\n");
			}
		}
		// Otherwise, save the file
		else {
			saveFileAndPrintSuccess(roster, fileName);
		}	
	}
	
	private static boolean isYesOrNo(char input) {
		if (Character.toLowerCase(input) == 'y' || Character.toLowerCase(input) == 'n') {
			return true;
		}
		
		System.out.println("\nInvalid response, please enter 'y' or 'n'.");
		
		return false;
	}
	
	private static void saveFileAndPrintSuccess(FootballRoster roster, String fileName) {
		roster.saveRosterToFile(fileName);
		System.out.println("\nRoster saved to " + fileName + ".\n");
	}
	
	
	//**** CASE 4 ****//
	private static void loadFileInformation(FootballRoster roster, Scanner scnr) {
		System.out.print("\nEnter a file name to load roster data from: ");
		
		String fileName = scnr.nextLine();
		
		roster.loadRosterFromFile(fileName);
	}
	
	
	//**** CASE 5 ****//
	private static void exitProgram(Scanner scnr) {
		// Close the class scanner used in main
		scnr.close();
		
		System.out.println("\nHave a great day!");
	}
	
	
	//**** DEFAULT CASE ****//
	private static void printInputErrorMessage() {
		System.out.println("\nPlease enter a number 1-5 only.\n");
	}
	
	
	//**** SHARED FUNCTION ****//
	private static void clearScannerInput(Scanner scnr) {
		scnr.nextLine();
	}

}
