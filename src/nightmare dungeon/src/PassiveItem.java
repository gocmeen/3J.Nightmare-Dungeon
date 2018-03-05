public class PassiveItem extends Item {

    protected int attackSpeedUp;
    protected int attackDamageUp;
    protected int speedUp;
    protected int healthUp;


    public PassiveItem(int x, int y, int typeID,int width, int height,int itemID, int attackSpeedUp, int attackDamageUp, int speedUp, int healthUp){
        super(x,y,typeID,width,height,itemID);
        this.healthUp=healthUp;
        this.speedUp=speedUp;
        this.attackDamageUp=attackDamageUp;
        this.attackSpeedUp=attackSpeedUp;
    }

    //getters
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
