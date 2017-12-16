import java.io.IOException;

public class ActiveItem extends Item{

    private boolean active = false;
    private int duration = 0;

    public ActiveItem(int x, int y, int typeID,int width, int height,int itemID, boolean active, int duration){
        super(x,y,typeID,width,height,itemID);
        this.active = active;
        this.duration = duration;
    }

    public boolean isItemActive(){
        if(duration == 0)
            active = false;
        else
            active = true;
        return active;
    }


    public void toggleActive(){
        active = !active;
        if(active)
            duration = 60;
        else
            duration = 0;
    }

    public boolean activate(Player someone, long startTime) throws IOException{

        //if(this.itemID == 1) {

            boolean upperLeft= someone.attack(startTime, someone.getX(), someone.getY(), -1, -1);
            boolean upperRight= someone.attack(startTime, someone.getX(), someone.getY(), 1, -1);
            boolean bottomLeft= someone.attack(startTime, someone.getX(), someone.getY(), -1, 1);
            boolean bottomRight= someone.attack(startTime, someone.getX(), someone.getY(), 1, 1);
            return  upperLeft && upperRight && bottomLeft && bottomRight;
        //}

        //return false;
    }
}
