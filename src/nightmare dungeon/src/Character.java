/**
 * Created by wifinaynay on 01/11/17.
 */

import java.awt.Graphics; //these will be changed
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.concurrent.*;

public class Character extends Entity {
    protected boolean attacked;
    protected int health;
    protected int speed;
    protected double directionX, directionY;
    protected int attackDamage,attackSpeed;
    protected ArrayList<Projectile> projectile;
    //image olacak
    protected boolean alive;
    protected int projectileCount;
    protected String name;
    protected static final String playerLeft1="asdsd.png";
    protected static final String playerLeft2="asdsd.png";
    protected static final String playerLeft3="asdsd.png";
    public Character(int x, int y, int typeID,int width, int height, int health , int speed, int attackDamage, int attackSpeed){
        super(x,y,typeID,width,height);
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
    public void move(){
        x += directionX * speed ;
        y += directionY * speed ;
    }
    public void attack(){
        //attack yapılacak
    }

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

    public int getSpeed() {
        return speed;
    }
    public void attack(long startTime,int x, int y, int dirX, int dirY) //x and y are directions
    {

        if(projectile.size()==0)
            addProjectile(new Projectile(startTime,1,4,3,x,y,1,1,dirX,dirY));
        else
            if(startTime-projectile.get(projectile.size()-1).startTime>1*0.5*1000)
                addProjectile(new Projectile(startTime,1,4,3,x,y,1,1,dirX,dirY));
    }
    public void addProjectile(Projectile p)
    {
        projectile.add(p);
    }
    public ArrayList<Projectile> getProjectile()
    {

        return projectile;
    }
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
