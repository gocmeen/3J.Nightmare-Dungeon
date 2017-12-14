import java.util.ArrayList;

/**
 * Created by wifinaynay on 01/11/17.
 */
public class Player extends Character {
    private int lifecount;
    //private ActiveItem activeItem;
    private ArrayList<PassiveItem> items;
    //private Projectile projectileList;

    private static final int   SPEED = 5;
    final protected int MAX_HEALTH = 300;
    private static final int   HEALTH = 200;
    private static final int   ATTACK_DAMAGE = 50;
    private static final int   ATTACK_SPEED = 50;

     public Player(int x, int y, int typeID,int width, int height)
     {
          super(x,  y,  typeID, width,  height,  HEALTH ,  SPEED, ATTACK_DAMAGE,ATTACK_SPEED);
          items = new ArrayList<PassiveItem>();
     }








     //public void useActive(Item it){}
    //public void updatelifecount()[]
    //removePassive()
    public void addPassive(PassiveItem item){
         items.add(item);
         this.speed += item.getSpeedUp();
         this.attackDamage+= item.getAttackDamageUp();
         this.attackSpeed+= item.getAttackSpeedUp();
         this.health+= item.getHealthUp();


    }
    //updateActive()






}
