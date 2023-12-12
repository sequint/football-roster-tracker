
public class TeamRosterTrackerInterface {

	public static void main(String[] args) {
		
		// To be updated, for test only
		Player player1 = new Player("Steven", "QB", 230.45, false);
		
		System.out.println(player1.getName());
		System.out.println(player1.getPosition());
		System.out.println(player1.getpassingReceivingRushingYards());
		System.out.println(player1.hasOffensivePosition());
		System.out.println(player1.hasDefensivePosition());
		
		FootballRoster roster1 = new FootballRoster();
		
		System.out.println();
		System.out.println(roster1.getTotalNumberOfPlayers());
		
		roster1.addPlayer(player1);
		roster1.addPlayer(player1);
		
		System.out.println(roster1.getTotalNumberOfPlayers());
		
		Player testPlayer = roster1.getPlayer(1);
		System.out.println(testPlayer.getName());

	}

}
