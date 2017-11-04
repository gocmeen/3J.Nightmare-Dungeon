public class Projectile extends Entity {

	//attributes
    protected int damage; // holds damage of projectile
    protected int speed; // holds speed of projectile
    protected long startTime; // attribute for determinig the time interval in which a projectile is thrown
    protected int directionX; // holds x component of movement direction
    protected int directionY; // holds y component of movement direction


    public Projectile(long startTime,int damage, int speed,int typeID,  int x, int y, int height, int width, int dirX, int dirY)
    {
        super(x,y,typeID,width,height); // inherits super class Entity
        this.damage = damage;
        this.speed = speed;
        this.directionX = dirX;
        this.directionY = dirY;
        this.startTime=startTime;


    }
    
    // updates position of projectile according to speed and direction
    public void updatePosition()
    {
        this.x += directionX * speed ;
        this.y += directionY * speed ;
    }

    //getters
    public int getdirX(){
        return directionX;
    }
    
    public int getdirY(){
        return directionY;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }
}
