/**
 * Created by wifinaynay on 01/11/17.
 */

import java.awt.Graphics; //these will be changed
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.concurrent.*;

public class Character extends Entity {
    //Attributes
    protected boolean attacked;
    protected int health;
    protected int speed;
    protected double directionX, directionY;
    protected int attackDamage,attackSpeed;
    protected ArrayList<Projectile> projectile;
    protected boolean alive;
    protected int projectileCount;
    protected String name;

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

    }
    //changes the x and y coordinates of the player
    public void move(){
        x += directionX * speed ;
        y += directionY * speed ;
    }

    //GETTERS
    public int getHealth(){
        return health;
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
    public boolean attack(long startTime,int x, int y, double dirX, double dirY) //x and y are directions
    {
        //if there are no projectiles in the room
        if(projectile.size()==0){
            addProjectile(new Projectile(startTime,1,4,3,x,y,1,1,dirX,dirY));
        return true;
        }
        else //if there are projectiles
            //if the time differences between last projectile and this one is greater then half a second
            if(startTime-projectile.get(projectile.size()-1).startTime>1*1*1000) {
                addProjectile(new Projectile(startTime, 1, 4, 3, x, y, 1, 1, dirX, dirY)); //add the projectile
                return true;
            }
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
        this.health+= health;
    }
}
