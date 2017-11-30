import java.awt.Image;
import org.newdawn.slick.SlickException;

import java.awt.Rectangle;
import java.awt.Graphics2D;
abstract class Entity{
    //attributes
    protected int typeID; // determines the type of entity
    protected int x,y; //position x and position y of entity
    protected int width,height; // width and height properties of object
    protected Rectangle bounds; // bounds to determine boundaries of object, used in collission detection
    protected boolean active = true; 
    
    //constructor
    public Entity(int x, int y, int typeID,int width, int height){
        this.x = x;
        this.y = y;
        this.typeID = typeID;
        this.width = width;
        this.height = height;


        bounds = new Rectangle(0, 0, width, height); //create a rect object that determines bounds
    }

    //this method returns the new positions rectangle as entity moves
    public Rectangle getCollisionRectangle(int xOffset,int  yOffset){
        return new Rectangle( (x + bounds.x + xOffset), (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

  /*  public void draw( Graphics2D g, Image img) throws SlickException {
        g.drawImage(new Image(Assets.playerDown),this.getX(),this.getY());
    }*/
    
    // getters and setters for attributes
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

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}




}