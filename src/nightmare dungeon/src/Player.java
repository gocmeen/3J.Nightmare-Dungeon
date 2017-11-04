import java.util.ArrayList;


public class Player extends Character {
	//constants
	private static final int   SPEED = 1; // default speed value of player
	private static final int   HEALTH = 50; // default health value of player
	private static final int   ATTACK_DAMAGE = 50; // default attack damage value of player
	private static final int   ATTACK_SPEED = 50; // default attack speed value of player

	//attributes
	private int lifecount; // holds number of lives of player
	//private ActiveItem activeItem; // holds the active item of player, not functional right now
	private ArrayList<Item> items; // holds passive items of player, holds item at this stage of implementation
	
	// constructor
	public Player(int x, int y, int typeID,int width, int height)
	{
		super(x,  y,  typeID, width,  height,  HEALTH ,  SPEED, ATTACK_DAMAGE,ATTACK_SPEED);
		items = new ArrayList<Item>();// creating
	}
	
	// adds passiveItem to list of player, includes all attributes at this stage for testing
	public void addPassive(Item item){
		items.add(item);
		this.speed += item.getSpeedUp();
		this.attackDamage+= item.getAttackDamageUp();
		this.attackSpeed+= item.getAttackSpeedUp();
		this.health+= item.getHealthUp();
	}
	
	//public void updateActive(int ){}
	//public void useActive(Item it){}
	//public void updatelifeCount(){}
	//public void removePassive(PassiveItem item){}
}
