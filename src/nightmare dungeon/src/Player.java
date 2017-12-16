import org.newdawn.slick.SlickException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by wifinaynay on 01/11/17.
 */
public class Player extends Character {
    private int lifecount;
    private ActiveItem activeItem = null;
    private ArrayList<PassiveItem> items;
    //private Projectile projectileList;
    private long lastChangedRoom;
    private static final int   SPEED = 3;
    final protected int MAX_HEALTH = 300;
    private static final int   HEALTH = 200;
    private static final int   ATTACK_DAMAGE = 4;
    private static final int   ATTACK_SPEED = 70;
    private int point;

     public Player(int x, int y, int typeID,int width, int height)
     {
          super(x,  y,  typeID, width,  height,  HEALTH ,  SPEED, ATTACK_DAMAGE,ATTACK_SPEED);
          items = new ArrayList<PassiveItem>();
          lastChangedRoom = -1;
          point = 30000;
     }


     public void useActive(){
         if(this.activeItem != null){
             long copyLastAttacked = getLastAttacked();
             long startTime = System.currentTimeMillis();
            try {
                attack(startTime, x, y, 1, 0);
                attack(startTime+10000, x, y, 1, 0);
                attack(startTime+20000, x, y, -1, 0);
                attack(startTime+30000, x, y, 0, -1);
                attack(startTime+40000, x, y, 0, 1);
                setLastAttacked(copyLastAttacked);
                activeItem=null;
            }
            catch(IOException e){}

         }
     }

     public boolean isActiveValid(){

         return false;
     }
    //public void updatelifecount()[]
    //removePassive()
    public void addPassive(PassiveItem item)throws SlickException{
         items.add(item);
         if(speed<12)
         this.speed += item.getSpeedUp();
         if(attackDamage<15)
         this.attackDamage+= item.getAttackDamageUp();
         this.attackSpeed+= item.getAttackSpeedUp();
         this.health+= item.getHealthUp();
        SoundManager.playSound(5);

    }

    public void addActive(ActiveItem item){
         this.activeItem = item;
    }
    public void updateActive(){

    }

    public int getPoint(){
        if (point > 0)
            return point;
        else
            return 0;
    }

    public void setPoint(int point){
        this.point = point;
    }

    public ActiveItem getActiveItem(){
        return activeItem;
    }

}
