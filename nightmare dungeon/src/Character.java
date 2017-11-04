/**
 * Created by wifinaynay on 01/11/17.
 */

import java.awt.Graphics; //these will be changed
import java.awt.Rectangle;
import java.util.ArrayList;
public class Character extends Entity{

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
        projectile = new ArrayList<Projectile>();

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
    public void attack(int x, int y, int dirX, int dirY) //x and y are directions
    {
        if(projectile.size()<20)
        projectile.add(new Projectile(1,1,x,y,1,1,dirX,dirY));

    }
    public void addProjectile(Projectile p)
    {
        projectile.add(p);
    }


}
