public class ActiveItem extends Item{

    private boolean active = false;
    private int duration = 60;

    public ActiveItem(int x, int y, int typeID,int width, int height,int itemID, boolean active, int duration){
        super(x,y,typeID,width,height,itemID);
        this.active = active;
        this.duration = duration;
    }

    public boolean isExpired(){
        return active;
    }

    public void toggleActive(){
        active = !active;
    }

    public void activate(){}
}
