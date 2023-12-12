
public class FootballRoster {
	private Player[] rosterArray;
	private int totalPlayers;
	
	// Default constructor
	public FootballRoster() {
		this.rosterArray = new Player[1000]; // Allocate enough memory to cover changing size of array contents
		this.totalPlayers = 0;
	}
	
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
}
