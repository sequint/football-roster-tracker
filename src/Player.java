
public class Player {
	
	private String name;
	private String position;
	private double passingReceivingRushingYards;
	private boolean isDefensive;
	
	@SuppressWarnings("unused")
	private Player() {}
	
	Player(String name, String position, double yards, boolean defensive) {
		this.name = name;
		this.position = position;
		this.passingReceivingRushingYards = yards;
		this.isDefensive = defensive;
	}
	
	String getName() {
		return this.name;
	}
	
	String getPosition() {
		return this.position;
	}
	
	double getpassingReceivingRushingYards() {
		return this.passingReceivingRushingYards;
	}
	
	boolean hasDefensivePosition() {
		return this.isDefensive ? true : false;
	}
	
	boolean hasOffensivePosition() {
		return !this.isDefensive ? true : false;
	}

}
