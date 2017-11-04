/**
 * Created by wifinaynay on 01/11/17.
 */
import java.awt.Graphics; //these will be changed
import java.awt.Rectangle;
abstract class Entity{
    //attributes
    protected int typeID;
    //sprite object
    protected int x,y;
    protected int width,height;
    protected Rectangle bounds;
    protected boolean active = true;
    //methods
    public Entity(int x, int y, int typeID,int width, int height){
        this.x = x;
        this.y = y;
        this.typeID = typeID;
        this.width = width;
        this.height = height;


        bounds = new Rectangle(0, 0, width, height);
    }
    public Entity(){

    }
    //this method returns the new positions rectangle.gideceği mesafe ile topluyor yeni rect return ediyor
    public Rectangle getCollisionRectangle(int xOffset,int  yOffset){
        return new Rectangle( (x + bounds.x + xOffset), (y + bounds.y + yOffset), bounds.width, bounds.height);
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTypeID() {
        return typeID;
    }


}