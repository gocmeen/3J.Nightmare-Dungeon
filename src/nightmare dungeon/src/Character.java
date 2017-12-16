

import javax.imageio.ImageIO;
import java.awt.Graphics; //these will be changed
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.*;

public class Character extends Entity {
    //Attributes
    protected boolean attacked;
    protected int health;
    final protected int MAX_HEALTH = 300;
    protected int speed;
    protected double directionX, directionY;
    protected int attackDamage,attackSpeed;
    protected ArrayList<Projectile> projectile;
    protected boolean alive;
    protected int projectileCount;
    protected String name;
    protected boolean vulnerable;
    protected long lastAttacked;

    //Constructor
    public Character(int x, int y, int typeID,int width, int height, int health , int speed, int attackDamage, int attackSpeed){
        //calling the constructor of Entity class
        super(x,y,typeID,width,height);
        //initializing the attributes
        this.health = health ;
        this.speed = speed ;
        this.directionX = 0;
        this.directionY = 0;
        this.attackDamage=attackDamage;
        this.attackSpeed=attackSpeed;
        attacked= false;
        projectile = new ArrayList<Projectile>();
        alive = true;
        vulnerable = true;
        lastAttacked=-1;
    }
    //changes the x and y coordinates of the player
    public void move(int roomWidth, int roomHeight){

        roomHeight -= 44; // WIDTH OF PLAYER
        roomWidth -= 33;

        int xOffsetStart = 0;
        int xOffsetEnd = roomWidth - 0;
        int yOffsetStart = 0;
        int yOffsetEnd = roomHeight - 0;

        //System.out.println("x: " + this.getX() + " y: " + this.getY());

        if(this instanceof Player) {
            boolean inRangeWidthMin = (this.getX() > xOffsetStart);
            boolean inRangeWidthMax = (this.getX() < xOffsetEnd);
            boolean inRangeHeightMin = (this.getY() > yOffsetStart);
            boolean inRangeHeightMax = (this.getY() < yOffsetEnd);

            //System.out.println("x: " + this.getX() + " y: " + this.getY());

            if (inRangeWidthMin && inRangeWidthMax && inRangeHeightMin && inRangeHeightMax) {
                this.setX(x + (int) (directionX * speed));
                this.setY(y + (int) (directionY * speed));
                //System.out.println("x: " + this.getX() + " y: " + this.getY());

            }else if (inRangeWidthMax && inRangeWidthMin) {
                if (!inRangeHeightMax) {
                    System.out.println("debug1");
                    this.setY(yOffsetEnd - 1);
                } else if (!inRangeHeightMin) {
                    System.out.println("debug2");
                    this.setY(yOffsetStart + 1);
                }
            } else if (inRangeHeightMax && inRangeHeightMin) {
                if (!inRangeWidthMin) {
                    System.out.println("debug2");
                    this.setX(xOffsetStart + 1);
                } else if (!inRangeWidthMax) {
                    System.out.println("debug3");
                    this.setX(xOffsetEnd - 1);
                }
            }
        else if(!inRangeHeightMax && !inRangeWidthMin){
            //System.out.println("debug4");
            this.setX(xOffsetStart+1);
            this.setY(yOffsetEnd-1);
        }
        else if(!inRangeHeightMax && !inRangeWidthMax){
            //System.out.println("debug5");
            this.setX(xOffsetEnd-1);
            this.setY(yOffsetEnd-1);
        }
        else if(!inRangeHeightMin && !inRangeWidthMin){
            //System.out.println("debug6");
            this.setX(xOffsetStart+1);
            this.setY(yOffsetStart+1);
        }
        else if(!inRangeHeightMin && !inRangeWidthMax){
            //System.out.println("debug7");
            this.setX(xOffsetEnd-1);
            this.setY(yOffsetStart+1);
        }
        }else if(this instanceof Monster){
            x +=  (directionX * speed);
            y +=  (directionY * speed);
            //this.setX(x + (int) (directionX * speed));
            //this.setY(y + (int) (directionY * speed));
        }

        //System.out.println("x: " + this.getX() + " y: " + this.getY());

    }

    //GETTERS
    public int getHealth(){
        return health;
    }

    public int getMaxHealth(){
        return MAX_HEALTH;
    }

    public int getAttackDamage(){
        return attackDamage;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public String getName() {
        return name;
    }

    public double getDirectionX() {
        return directionX;
    }

    public double getDirectionY() {
        return directionY;
    }

    public int getProjectileCount() {
        return projectileCount;
    }

    public int getSpeed()

    {
        return speed;
    }
    //SETTERS

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void setDirectionX(double directionX) {
        this.directionX = directionX;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public void setDirectionY(double directionY) {
        this.directionY = directionY;
    }

    public void setProjectileCount(int projectileCount) {
        this.projectileCount = projectileCount;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /*
    * this method is used for attacking (shooting projectile)
    *
    * */
    public boolean attack(long startTime,int x, int y, double dirX, double dirY) throws IOException //x and y are directions
    {
        BufferedImage image = ImageIO.read(new File(Assets.playerAttack));
        int w = image.getWidth();
        int h = image.getHeight();
        //if there are no projectiles in the room
        if(lastAttacked==-1){
            addProjectile(new Projectile(startTime,1,4,7,x,y,w,h,dirX,dirY));
            lastAttacked=startTime;
        return true;
        }
        else //if there are projectiles
            //if the time differences between last projectile and this one is greater then half a second
            if((double)startTime-lastAttacked>(50/(double)attackSpeed)*1*1000) {
                //wdSystem.out.println("AAAAA: "+ (double)(50/attackSpeed)*1*1000);
                addProjectile(new Projectile(startTime, 1, 5, 7, x, y, w, h, dirX, dirY)); //add the projectile
                lastAttacked=startTime;
                return true;
            }
        System.out.println("fired");
    return false;
    }
    public void addProjectile(Projectile p)
    {
        projectile.add(p);
    }

    public ArrayList<Projectile> getProjectile()
    {

        return projectile;
    }
    //update the position of projectile
    public void updateProjectile(int index) {



            projectile.get(index).updatePosition( );

            /*
               probably will add remove etc.
            */



    }

    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }

    public void removeProjectile(int index){
        projectile.remove(index);
    }

    public void setHealth(int health) {
        if(vulnerable)
        this.health+= health;
    }

     public boolean getAlive()
     {return alive ;}

     public boolean isAlive()
     {
         if( getHealth() > 0)
             return true;
         else
             return false;

     }

    public void setLastAttacked(long lastAttacked) {
        this.lastAttacked = lastAttacked;
    }

    public long getLastAttacked() {
        return lastAttacked;
    }
}
