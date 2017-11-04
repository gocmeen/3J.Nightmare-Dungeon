public class Projectile extends Entity {

    protected int damage;
    protected int speed;
    long startTime;
    protected int directionX;
    protected int directionY;


    public Projectile(long startTime,int damage, int speed,int typeID,  int x, int y, int height, int width, int dirX, int dirY)
    {
        super(x,y,typeID,width,height);
        this.damage = damage;
        this.speed = speed;
        this.directionX = dirX;
        this.directionY = dirY;
        this.startTime=startTime;


    }

    public void ProjectileHeight(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public void updatePosition()
    {
        this.x += directionX * speed ;
        this.y += directionY * speed ;

    }

    public int getdirX()
    {
        return directionX;

    }
    public int getdirY()
    {
        return directionY;

    }

    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }
}
