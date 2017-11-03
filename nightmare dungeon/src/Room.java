/**
 * Created by wifinaynay on 01/11/17.
 */
import java.util.ArrayList;
public class Room{
    private ArrayList<Monster> monsterList;
    private int id;
    private int width, height;
    //itemlist
    //obstacleList
    //projectileList





    public Room(int width, int height,int id){
        this.width=width;
        this.height=height;
        this.id = id;
        monsterList = new ArrayList<Monster>();
        generateMonsters();
    }

    public int getHeight() {
        return height;
    }

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }
    //generating 3 monsters for now
    public void generateMonsters(){

            Monster m1 = new Monster(60,60,1,50,50);
            Monster m2 = new Monster(80,70,1,50,50);
            Monster m3 = new Monster(90,100,1,50,50);
            monsterList.add(m1);
            monsterList.add(m2);
            monsterList.add(m3);

    }
    public Entity checkCollision(Player someone) {
        for (int i = 0; i < monsterList.size(); i++) {
            if (someone.getCollisionRectangle(someone.getDirectionX() * someone.getSpeed(), 0).intersects(monsterList.get(i).getCollisionRectangle(0, 0)))
                return monsterList.get(i);
        }
        return null;
    }

    //generateItems(){}
    //checkoccupied()
    //removeItem()
    //removeObstacle()
    //removeMonster()
    //addProjectile()
    //removeProjectile()



}
