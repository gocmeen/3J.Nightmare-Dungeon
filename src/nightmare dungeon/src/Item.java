/**
 * Created by wifinaynay on 03/11/17.
 */


public class Item extends Entity{
	
	//type of upgrades that are caused by items
    protected int attackSpeedUp;
    protected int attackDamageUp;
    protected int speedUp;
    protected int healthUp;
    protected int itemID; //the ID of the item
    
    //constructor
    public Item(int x, int y, int typeID,int width, int height,int itemID){
        super(x,y,typeID,width,height);
        this.itemID=itemID;
        //this.healthUp=healthUp;
        //this.speedUp=speedUp;
        //this.attackDamageUp=attackDamageUp;
        //this.attackSpeedUp=attackSpeedUp;
    }
    
    //getters
    /*public int getAttackDamageUp() {
        return attackDamageUp;
    }

    public int getAttackSpeedUp() {
        return attackSpeedUp;
    }

    public int getHealthUp() {
        return healthUp;
    }

    public int getItemID() {
        return itemID;
    }

    public int getSpeedUp() {
        return speedUp;
    }*/
}
