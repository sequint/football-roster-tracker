
public class TeamRosterTrackerInterface {

	public static void main(String[] args) {
		FootballRoster roster = new FootballRoster();
		Menu menu = new Menu();
		int menuChoice = 0;
		
		// Display program title
		System.out.println("TEAM ROSTER TRACKER");
		System.out.println();
		
		// Display and handle menu choice while it is not 5 (exit)
		do {
			try {
				menuChoice = menu.getMenuChoice();
				menu.handleMenuChoice(roster);
			}
			catch (Exception e) {
				System.out.println();
				System.out.println("Invalid input. Please only enter an integer for your menu choice.");
				System.out.println();
			}
		} while (menuChoice != 5);
	}

}
