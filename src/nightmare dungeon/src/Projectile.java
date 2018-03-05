public class Projectile extends Entity {

    protected int damage;
    protected int speed;
    long startTime;
    protected double directionX;
    protected double directionY;


    public Projectile(long startTime,int damage, int speed,int typeID,  int x, int y, int height, int width, double dirX, double dirY)
    {
        super(x,y,typeID,width,height);
        this.damage = damage;
        this.speed = speed;
        this.directionX = dirX;
        this.directionY = dirY;
        this.startTime=startTime;


    }



    public void updatePosition()
    {
        setX(x + (int)(directionX * speed)) ;
        setY(y + (int)(directionY * speed)) ;
        //System.out.println(this.getX());

    }

    public double getdirX()
    {
        return directionX;

    }
    public double getdirY()
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
