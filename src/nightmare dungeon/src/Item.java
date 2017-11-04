/**
 * Created by wifinaynay on 03/11/17.
 */
public class Item extends Entity{
    protected int attackSpeedUp;
    protected int attackDamageUp;
    protected int speedUp;
    protected int healthUp;
    protected int itemID;
    public Item(int x, int y, int typeID,int width, int height,int itemID, int attackSpeedUp, int attackDamageUp, int speedUp, int healthUp){
        super(x,y,typeID,width,height);
        this.itemID=itemID;
        this.healthUp=healthUp;
        this.speedUp=speedUp;
        this.attackDamageUp=attackDamageUp;
        this.attackSpeedUp=attackSpeedUp;
    }

    public int getAttackDamageUp() {
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
    }
}
