/**
 * Created by wifinaynay on 01/11/17.
 */

import org.lwjgl.Sys;

import java.util.ArrayList;
public class Room{
    //attributes
    private ArrayList<Monster> monsterList;
    private ArrayList<Item> itemList;
    private int id;
    private int width, height;
    private ArrayList<Integer> collided;





    //constructor
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

            Monster m1 = new Monster(200,600,1,30,44,0);
            Monster m2 = new Monster(400,70,1,40,44,0);
            Monster m3 = new Monster(500,300,1,131,155,1);
            monsterList.add(m1);
            monsterList.add(m2);
            monsterList.add(m3);
        collided = new ArrayList<Integer>();
        for(int i = 0; i < monsterList.size(); i++ ){
            collided.add(0);
        }

    }
    //generates items inside the room
    public void generateItems(){

        Item i1 = new Item(600,90,2,30,44,0,30,40,5,60);
        Item i2 = new Item(400,400,2,30,44,1,30,40,4,60);
        itemList.add(i1);
        itemList.add(i2);
    }
    //removes items from the room
    public void removeItem(Item item){
        for (int i = 0; i < itemList.size(); i++) {
           if(item==itemList.get(i))
               itemList.remove(i);

        }
    }
    //checks collision between characters and entities and returns collided object
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

    //checks the collision of the projectile
    public Entity checkProjectileCollision(Projectile project){

        for (int i = 0; i < monsterList.size(); i++) {

                if (project.getCollisionRectangle((int)(project.getdirX() * project.getSpeed()),(int)project.getdirY() * project.getSpeed()).intersects(monsterList.get(i).getCollisionRectangle(0, 0))
                        )
                    return monsterList.get(i);


        }
        return null;
    }

    //this method makes monsters attack
    public void attackMonsterProjectiles(Player someone){

        int x = someone.getX();
        int y=someone.getY();
        long startTime = System.currentTimeMillis();
        double angle;
        for (int i = 0; i < monsterList.size(); i++) {
            if(monsterList.get(i).getMonsterType()==0) {

                angle = (float) Math.atan2(((double) y - monsterList.get(i).getY()), ((double) x - monsterList.get(i).getX()));

                monsterList.get(i).attack(startTime, monsterList.get(i).getX(), monsterList.get(i).getY(), Math.cos(angle), Math.sin(angle));
            }
        }
        moveProjectiles(someone);
    }

    /*this method is used for moving the projectiles inside the room
   *
   * */
    public void moveProjectiles(Player someone){

        for (int i = 0; i < monsterList.size(); i++) {
            for(int j =0; j < monsterList.get(i).getProjectile().size();j++){
                //collided creature
                Entity creature = checkProjectileCollision(monsterList.get(i).getProjectile().get(j));
                //checking collision
               // if(checkProjectileCollision(monsterList.get(i).getProjectile().get(j))==null)
                    monsterList.get(i).updateProjectile(j);
                /*else{
                    //if collided creature is monster
                    if(creature.typeID==0){
                        //casting Entity to Creature
                        Character abc = (Character)creature;
                        //decreasing monsters health by the damage of projectile
                        abc.setHealth(-monsterList.get(i).getProjectile().get(j).getDamage());
                        //if monster dies
                        if(abc.getHealth()<=0) {
                            //erasing the monster
                            //monsterList.remove(creature);
                            System.out.println("here");

                        }
                    }
                    //when collided projectile is removed from room
                   // monsterList.get(i).removeProjectile(j);
                }*/
            }
        }

        for(int i =0; i < someone.getProjectile().size();i++){
            //collided creature
            Entity creature = checkProjectileCollision(someone.getProjectile().get(i));
            //checking collision
            if(checkProjectileCollision(someone.getProjectile().get(i))==null)
                someone.updateProjectile(i);
            else{
                //if collided creature is monster
                if(creature.typeID==1){
                    //casting Entity to Creature
                    Character abc = (Character)creature;
                    //decreasing monsters health by the damage of projectile
                    abc.setHealth(-someone.getProjectile().get(i).getDamage());
                    //if monster dies
                    if(abc.getHealth()<=0) {
                        //erasing the monster
                        monsterList.remove(creature);

                    }
                }
                //when collided projectile is removed from room
                someone.removeProjectile(i);
            }
        }
    }
    /*
    * This method is used for movements of themosnters inside room
    * Monsters follow the player
    * When they collide to an Entity they bounce back for some distance
    *
    * */
    public void moveMonsters(Player someone){
        //getting the coordinates of the user
        int x = someone.getX();
        int y=someone.getY();

        double angle;
        //looping through the monster
        for (int i = 0; i < monsterList.size(); i++) {
            if (monsterList.get(i).getMonsterType() == 1){
                //calculating the angle between the monster and the player
                angle = (float) Math.atan2((double) (y - monsterList.get(i).getY()), (x - monsterList.get(i).getX()));
            //set directions of the monster so that monster follows player
            if (collided.get(i) == 0) {
                //x direction gets the cosine of the angle
                monsterList.get(i).setDirectionX(Math.cos(angle));
                //y direction gets the sine of the angle
                monsterList.get(i).setDirectionY(Math.sin(angle));
            }
            //checking collision
            if ((checkCollision(monsterList.get(i)) == null) &&
                    !monsterList.get(i).getCollisionRectangle((int) monsterList.get(i).getDirectionX() * someone.getSpeed(), (int) monsterList.get(i).getDirectionY() * someone.getSpeed()).intersects(someone.getCollisionRectangle(0, 0))
                    ) {
                if (collided.get(i) > 0) {

                    collided.set(i, collided.get(i) + 1);
                    //bouncing back when collided
                    if (collided.get(i) == 25) {
                        monsterList.get(i).setDirectionY(monsterList.get(i).getDirectionY());
                        monsterList.get(i).setDirectionX(-monsterList.get(i).getDirectionX());
                    }
                    //still bouncing but this time towards another direction
                    if (collided.get(i) == 40) {
                        monsterList.get(i).setDirectionY(0 * monsterList.get(i).getDirectionY());
                        monsterList.get(i).setDirectionX(-monsterList.get(i).getDirectionX());
                    }

                }
                //moving the monster
                monsterList.get(i).move();
                if (collided.get(i) == 80)
                    collided.set(i, 0);
            } else {
                //if collides after colliding bounce back again
                if (collided.get(i) < 100) {
                    monsterList.get(i).setDirectionY(-monsterList.get(i).getDirectionY());
                    monsterList.get(i).setDirectionX(-monsterList.get(i).getDirectionX());

                }
                //if collided for the first time
                if (collided.get(i) == 0)
                    collided.set(i, collided.get(i) + 1);

                monsterList.get(i).move();

            }
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
