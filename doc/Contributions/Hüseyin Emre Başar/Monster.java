public class Monster extends Character{
	// constant
	private static final int   SPEED = 1; // default speed value of player
	private static final int   HEALTH = 5; // default health value of player
	private static final int   ATTACK_DAMAGE = 50; // default attack damage value of player
	private static final int   ATTACK_SPEED = 50; // default attack speed value of player
	//attributes
	private int monsterType;// attribute to determine type of monster, not functional at this stage
	
	// constructor
	public Monster(int x, int y, int typeID,int width, int height){
		super(x,  y,  typeID, width,  height,  HEALTH ,  SPEED, ATTACK_DAMAGE,ATTACK_SPEED);
	}

	// getters and setters
	public int getMonsterType() {
		return monsterType;
	}

	public void setMonsterType(int monsterType) {
		this.monsterType = monsterType;
	}
}
