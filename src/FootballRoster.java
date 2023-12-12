
public class FootballRoster {
	private Player[] rosterArray;
	private int totalPlayers;
	
	// Default constructor
	public FootballRoster() {
		this.rosterArray = new Player[1000];
		this.totalPlayers = 0;
	}
	
	public int getTotalNumberOfPlayers() {
		return this.totalPlayers;
	}
}