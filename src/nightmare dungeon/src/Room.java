/**
 * Created by wifinaynay on 01/11/17.
 */

import org.lwjgl.Sys;

import java.util.ArrayList;
public class Room{
    private ArrayList<Monster> monsterList;
    private ArrayList<Item> itemList;
    private int id;
    private int width, height;
    private ArrayList<Integer> collided;
    //itemlist
    //obstacleList
    //projectileList





    public Room(int width, int height,int id){
        this.width=width;
        this.height=height;
        this.id = id;
        monsterList = new ArrayList<Monster>();
        itemList= new ArrayList<Item>();
        generateMonsters();
        generateItems();
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

            Monster m1 = new Monster(600,600,1,30,44);
            Monster m2 = new Monster(800,70,1,40,44);
            Monster m3 = new Monster(900,300,1,40,44);
            monsterList.add(m1);
            monsterList.add(m2);
            monsterList.add(m3);
        collided = new ArrayList<Integer>();
        for(int i = 0; i < monsterList.size(); i++ ){
            collided.add(0);
        }

    }
    public void generateItems(){

        Item i1 = new Item(600,90,2,30,44,0,30,40,50,60);
        Item i2 = new Item(400,400,2,30,44,1,30,40,40,60);
        itemList.add(i1);
        itemList.add(i2);
    }
    public void removeItem(Item item){
        for (int i = 0; i < itemList.size(); i++) {
           if(item==itemList.get(i))
               itemList.remove(i);

        }
    }
    public Entity checkCollision(Character someone) {
        for (int i = 0; i < monsterList.size(); i++) {
            if (someone.getCollisionRectangle((int)someone.getDirectionX() * someone.getSpeed(), (int)someone.getDirectionY() * someone.getSpeed()).intersects(monsterList.get(i).getCollisionRectangle(0, 0))
                    &&someone!=monsterList.get(i))
                return monsterList.get(i);
        }
        for (int i = 0; i < itemList.size(); i++) {
            if (someone.getCollisionRectangle((int)someone.getDirectionX() * someone.getSpeed(), (int)someone.getDirectionY() * someone.getSpeed()).intersects(itemList.get(i).getCollisionRectangle(0, 0))
                    )
                return itemList.get(i);
        }
        return null;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public ArrayList<Monster> getMonsterList() {
        return monsterList;
    }
    //monster attack ve collision eklencek
    public Entity checkProjectileCollision(Projectile project){

        for (int i = 0; i < monsterList.size(); i++) {

                if (project.getCollisionRectangle((project.getdirX() * project.getSpeed()),(int)project.getdirY() * project.getSpeed()).intersects(monsterList.get(i).getCollisionRectangle(0, 0))
                        )
                    return monsterList.get(i);


        }
        return null;
    }
    //monstter movement logic
    public void moveProjectiles(Player someone){

        for(int i =0; i < someone.getProjectile().size();i++){
            Entity creature = checkProjectileCollision(someone.getProjectile().get(i));
            if(checkProjectileCollision(someone.getProjectile().get(i))==null)
                someone.updateProjectile(i);
            else{
                if(creature.typeID==1){
                    Character abc = (Character)creature;
                    abc.setHealth(-someone.getProjectile().get(i).getDamage());
                    if(abc.getHealth()<=0) {
                        monsterList.remove(creature);

                    }
                }
                someone.removeProjectile(i);
            }
        }
    }

    public void moveMonsters(Player someone){
        int x = someone.getX();
        int y=someone.getY();
        double angle;

        for (int i = 0; i < monsterList.size(); i++) {

             angle=(float)Math.atan2((double)(y-monsterList.get(i).getY()),(x-monsterList.get(i).getX()));

            if(collided.get(i)==0) {
                monsterList.get(i).setDirectionX(Math.cos(angle));
                monsterList.get(i).setDirectionY(Math.sin(angle));
            }

             if((checkCollision(monsterList.get(i))==null)&&
                    !monsterList.get(i).getCollisionRectangle((int)monsterList.get(i).getDirectionX() * someone.getSpeed(), (int)monsterList.get(i).getDirectionY() * someone.getSpeed()).intersects(someone.getCollisionRectangle(0, 0))
                     ){
                 if(collided.get(i)>0) {
                     collided.set(i, collided.get(i) + 1);
                     //random olabilir
                     if(collided.get(i)==25){
                         monsterList.get(i).setDirectionY(monsterList.get(i).getDirectionY());
                         monsterList.get(i).setDirectionX(-monsterList.get(i).getDirectionX());
                     }
                     if(collided.get(i)==40){
                         monsterList.get(i).setDirectionY(0*monsterList.get(i).getDirectionY());
                         monsterList.get(i).setDirectionX(-monsterList.get(i).getDirectionX());
                     }

                 }
                 monsterList.get(i).move();
                 if( collided.get(i)==80)
                     collided.set(i,0);}
             else
             {
                 if(collided.get(i)<100){
                 monsterList.get(i).setDirectionY(-monsterList.get(i).getDirectionY());
                 monsterList.get(i).setDirectionX(-monsterList.get(i).getDirectionX());

                 }
                if(collided.get(i)==0)
                 collided.set(i,collided.get(i)+1);

                 monsterList.get(i).move();

             }


        }

    }
    //generateItems(){}
    //checkoccupied()
    //removeItem()
    //removeObstacle()
    //removeMonster()
    //addProjectile()
    //removeProjectile()



}
