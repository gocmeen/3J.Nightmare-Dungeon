/**
 * Created by wifinaynay on 01/11/17.
 */

import org.lwjgl.Sys;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
public class Room{
    //attributes
    private ArrayList<Monster> monsterList;
    private ArrayList<Item> itemList;
    private ArrayList<Obstacle> obstacleList;
    private ArrayList<Door> doorList;
    private int id;
    private int width, height1;
    private ArrayList<Integer> collided;
    private long currTime;
    private int bossX;
    private int bossY;
    private boolean isBoss;
    private Portal port;
    private Monster boss;
private Portal port2;

    //constructor
    public Room(int width, int height,int id,ArrayList<Door> neighbours,boolean isBoss,Portal porta)throws IOException{
        this.width=width;
        this.height1=height;
        this.id = id;
        doorList = new ArrayList<Door>();
        obstacleList = new ArrayList<Obstacle>();
        monsterList = new ArrayList<Monster>();
        itemList= new ArrayList<Item>();
        generateMonsters();
        generateItems();
        generateObstacles();
        port = null;

        currTime = System.currentTimeMillis(); //Game start time
       doorList= new ArrayList<Door>();

        for(int i = 0;i < neighbours.size();i++){
            if(neighbours.get(i).getRoomID1()==id){
                doorList.add(new Door(neighbours.get(i).getX(),neighbours.get(i).getY(),4,30,30,neighbours.get(i).getRoomID1(),neighbours.get(i).getRoomID2()));
            }

        }
        //generateDoors();
        this.isBoss=isBoss;
        if(isBoss&&porta.getMapID1()==0){
            boss = new Monster(500,500,1,200,130,99);
            monsterList.add(boss);
            collided.add(0);
            port2=new Portal(500,500,5,porta.getWidth(),porta.getHeight(),porta.getMapID1(),porta.getMapID2());
            //System.out.println("Hayattan soğudum: "+this.port.getMapID1()+", "+ this.port.getMapID2());
            boss.setAttackDamage(7);
            boss.setHealth(50);
        }
        if(isBoss && porta.getMapID1() == 1){
            boss = new Monster(500,500,1,200,130,98);
            monsterList.add(boss);
            collided.add(0);
            port2=new Portal(500,500,5,porta.getWidth(),porta.getHeight(),porta.getMapID1(),porta.getMapID2());
            //System.out.println("Hayattan soğudum: "+this.port.getMapID1()+", "+ this.port.getMapID2());
            boss.setAttackSpeed(300);
            boss.setAttackDamage(12);
            boss.setHealth(60);
        }
        if(isBoss && porta.getMapID1() == 2){
            boss = new Monster(500,500,1,200,130,97);
            monsterList.add(boss);
            collided.add(0);
            port2=new Portal(500,500,5,porta.getWidth(),porta.getHeight(),porta.getMapID1(),porta.getMapID2());
            //System.out.println("Hayattan soğudum: "+this.port.getMapID1()+", "+ this.port.getMapID2());
            boss.setSpeed(20);
            boss.setAttackDamage(20);
            boss.setHealth(70);
        }

        else
            this.port=null;

    }

public boolean checkBossDied(){
        if(isBoss){
            for(int i = 0;  i < monsterList.size();i++){
                if(monsterList.get(i).getMonsterType()==99||monsterList.get(i).getMonsterType()==98||monsterList.get(i).getMonsterType()==97)
                    return false;
            }
            return true;
        }
        return false;
}
public void createPortal(){
    port=new Portal(bossX,bossY,5,port2.getWidth(),port2.getHeight(),port2.getMapID1(),port2.getMapID2());

}

    public int getHeight() {
        return height1;
    }

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public ArrayList<Door> getDoorList() {
        return doorList;
    }

    //generating 3 monsters for now
    public void generateMonsters() throws IOException{

        for(int i = 0 ; i < 3; i++){
           int randomX= ThreadLocalRandom.current().nextInt(0,1000);
            int randomY = ThreadLocalRandom.current().nextInt(0,600);
            int typee = ThreadLocalRandom.current().nextInt(0,2);
            BufferedImage image = null;
            if(typee==0)
                image = ImageIO.read(new File(Assets.monster1));
            else if(typee==1)
                image = ImageIO.read(new File(Assets.monster2));
            int w = image.getWidth();
            int h = image.getHeight();
            //System.out.println(id+" w: "+w+",h: "+h);
            Monster m1 = new Monster(randomX,randomY,1,w,h,typee);
            monsterList.add(m1);
        }






        collided = new ArrayList<Integer>();
        for(int i = 0; i < monsterList.size(); i++ ){
            collided.add(0);
        }
        //
    }
    public void generateDoors(){

/*

        for(int i = 0; i < neighbours.size();i++){
            int randomX= ThreadLocalRandom.current().nextInt(0,1330);
            int randomY = ThreadLocalRandom.current().nextInt(0,740);
            int selectFrom4 = ThreadLocalRandom.current().nextInt(0,4);
            Door d1=null;
            if(selectFrom4==0)
                 d1= new Door(randomX,0,4,30,30,id,neighbours.get(i));
            else if(selectFrom4==1)
                d1= new Door(0,randomY,4,30,30,id,neighbours.get(i));
            else if(selectFrom4==2)
                d1= new Door(1340,randomY,4,30,30,id,neighbours.get(i));
            else
                d1= new Door(randomX,750,4,30,30,id,neighbours.get(i));
            doorList.add(d1);

        }*/



    }
    public boolean checkCleared(){
        return monsterList.size()==0;
    }
    //generates items inside the room
    public void generateItems()throws IOException{
        for(int i = 0; i < 2; i++){
            int randomX= ThreadLocalRandom.current().nextInt(0,1000);
            int randomY = ThreadLocalRandom.current().nextInt(0,600);
            int typee = ThreadLocalRandom.current().nextInt(0,2);
            BufferedImage image = null;
            if(typee==0)
                image = ImageIO.read(new File(Assets.item1));
            else if(typee==1)
                image = ImageIO.read(new File(Assets.item2));
            int w = image.getWidth();
            int h = image.getHeight();
            Item i1 = new PassiveItem(randomX,randomY,2,w,h,typee,40,2,1,10);
            itemList.add(i1);
        }

        int randomX= ThreadLocalRandom.current().nextInt(51 + 27,1000);
        int randomY = ThreadLocalRandom.current().nextInt(67 + 39,600);
        //int typee = ThreadLocalRandom.current().nextInt(0,2);
        BufferedImage image = null;
        image = ImageIO.read(new File(Assets.activeItem));
        int w = image.getWidth();
        int h = image.getHeight();
        Item i1 = new ActiveItem(randomX,randomY,3,w,h,0,false, 1000);
        itemList.add(i1);
        System.out.println( itemList.get(itemList.size()-1).typeID);
    }

    public void generateObstacles()throws IOException{
        int offset = 50;

        for(int i = 0; i < 3 ; i++){
            int randomX= ThreadLocalRandom.current().nextInt(51 + 27,1100);
            int randomY = ThreadLocalRandom.current().nextInt(67 + 39,600);

            BufferedImage image = ImageIO.read(new File(Assets.obstacle));
            int w = image.getWidth();
            int h = image.getHeight();
            Obstacle o1 = new Obstacle(randomX, randomY,6,39,27);
            //obstacleList.add(o1);
/*
            System.out.println("random x: " + randomX + ", random y: " + randomY);

            for(int n = 0; n < doorList.size(); n++) {
                Rectangle r1 = new Rectangle(doorList.get(n).getX()- 50,doorList.get(n).getY(), 50,50);
                Rectangle r2 = new Rectangle(doorList.get(n).getX() + 39 ,doorList.get(n).getY(), 50,50);
                Rectangle r3 = new Rectangle(doorList.get(n).getX(),doorList.get(n).getY()- 50, 50,50);
                Rectangle r4 = new Rectangle(doorList.get(n).getX(),doorList.get(n).getY()- 27, 50,50);
                while (o1.getCollisionRectangle(0,0).intersects(r1) || o1.getCollisionRectangle(0,0).intersects(r2)
                        ||o1.getCollisionRectangle(0,0).intersects(r3)||o1.getCollisionRectangle(0,0).intersects(r4)){
                    randomX= ThreadLocalRandom.current().nextInt(0,1000);
                    randomY = ThreadLocalRandom.current().nextInt(0,600);
                    System.out.println("random x: " + randomX + ", random y: " + randomY);
                    System.out.println("in");
                }
                System.out.println("out");
                o1 = new Obstacle(randomX, randomY,3,39,27);
            }
*/
            obstacleList.add(o1);
        }


    }
    //removes items from the room
    public void removeItem(Item item){
        for (int i = 0; i < itemList.size(); i++) {
           if(item==itemList.get(i))
               itemList.remove(i);

        }
    }

    //checks collision between characters and entities and returns collided object
    public Entity checkCollision(Character someone)throws SlickException {
        Monster check=null;
        if(someone instanceof Monster)
            check = (Monster)someone;

if(check==null||(check.getMonsterType()!=97&&check.getMonsterType()!=98)) {
    for (int i = 0; i < monsterList.size(); i++) {
        //monstr projectile hit

        //iterating over monster projectiles to check collision

        if (someone.getCollisionRectangle((int) someone.getDirectionX() * someone.getSpeed(), (int) someone.getDirectionY() * someone.getSpeed()).intersects(monsterList.get(i).getCollisionRectangle(0, 0))
                && someone != monsterList.get(i)) {


            if (someone.getHealth() > 0 && System.currentTimeMillis() - currTime > 300) {  //.Checks time delay between attacks. Cant get hit for like 0.3 second after first hit.
                someone.setHealth(-(monsterList.get(i).getAttackDamage()));
                currTime = System.currentTimeMillis();
                SoundManager.playSound(2);
            }

            return monsterList.get(i);
        }

    }

    for (int i = 0; i < itemList.size(); i++) {
        if (someone.getCollisionRectangle((int) someone.getDirectionX() * someone.getSpeed(), (int) someone.getDirectionY() * someone.getSpeed()).intersects(itemList.get(i).getCollisionRectangle(0, 0))
                ) {
            return itemList.get(i);

        }

    }

    for (int i = 0; i < obstacleList.size(); i++) {
        if (someone.getCollisionRectangle((int) someone.getDirectionX() * someone.getSpeed(), (int) someone.getDirectionY() * someone.getSpeed()).intersects(obstacleList.get(i).getCollisionRectangle(0, 0))
                )
            return obstacleList.get(i);
    }
    for (int i = 0; i < doorList.size(); i++) {
        if (someone.getCollisionRectangle((int) someone.getDirectionX() * someone.getSpeed(), (int) someone.getDirectionY() * someone.getSpeed()).intersects(doorList.get(i).getCollisionRectangle(0, 0))) {

            //SoundManager.playSound(4);
            return doorList.get(i);
        }
    }
    if (port != null)
        if (someone.getCollisionRectangle((int) someone.getDirectionX() * someone.getSpeed(), (int) someone.getDirectionY() * someone.getSpeed()).intersects(port.getCollisionRectangle(0, 0)))
            return port;
}
        return null;
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
    public void attackMonsterProjectiles(Player someone)throws IOException, SlickException{

        int x = someone.getX();
        int y = someone.getY();
        long startTime = System.currentTimeMillis();
        double angle;
        for (int i = 0; i < monsterList.size(); i++) {
            if(monsterList.get(i).getMonsterType()== 0 || monsterList.get(i).getMonsterType() == 99 || monsterList.get(i).getMonsterType() == 98) {

                angle = (float) Math.atan2(((double) y - monsterList.get(i).getY()), ((double) x - monsterList.get(i).getX()));

                if(monsterList.get(i).getMonsterType() == 99)
                {

                    long yeniTime = startTime;
                    boolean flag = false;
                    for(int j = 0; j< 30; j++)
                    {
                        if(monsterList.get(i).attack(startTime, monsterList.get(i).getX(), monsterList.get(i).getY(), randomWithRange(-1,1), randomWithRange(-1,1)) == false)
                        {
                            // monsterList.get(i).setLastAttacked(yeniTime);
                            break;
                        }
                        else
                            flag = true;

                        startTime+=10000;
                    }
                    if(flag) {
                        monsterList.get(i).setLastAttacked(yeniTime);
                        SoundManager.playSound(6);
                    }
                }
                else if(monsterList.get(i).getMonsterType() == 98)
                {
                    monsterList.get(i).attack(startTime, monsterList.get(i).getX(), monsterList.get(i).getY(), Math.cos(angle), Math.sin(angle));
                }

                else
                    monsterList.get(i).attack(startTime, monsterList.get(i).getX(), monsterList.get(i).getY(), Math.cos(angle), Math.sin(angle));

            }
        }
        moveProjectiles(someone);
    }
    /*this method is used for moving the projectiles inside the room
   *
   * */
    public void moveProjectiles(Player someone)throws SlickException{

        for (int i = 0; i < monsterList.size(); i++) {
            for(int j =0; j < monsterList.get(i).getProjectile().size();j++){
                //collided creature
               if( someone.getCollisionRectangle(0, 0).intersects(monsterList.get(i).getProjectile().get(j).getCollisionRectangle(0,0))) {
                   someone.setHealth(-monsterList.get(i).getAttackDamage());
                   monsterList.get(i).removeProjectile(j);
                   SoundManager.playSound(2);
                   someone.setPoint(someone.getPoint() - 20 * monsterList.get(i).getAttackDamage());
               }
                //checking collision
               // if(checkProjectileCollision(monsterList.get(i).getProjectile().get(j))==null)
                else
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
                     Monster abc = (Monster) creature;
                    //decreasing monsters health by the damage of projectile
                    abc.setHealth(-someone.getAttackDamage());
                    //if monster dies
                    if(abc.getHealth()<=0) {
                        if (abc.getMonsterType() == 99)
                            someone.setPoint(someone.getPoint() + 100);
                        if (abc.getMonsterType() == 0)
                            someone.setPoint(someone.getPoint() + 10);
                        if (abc.getMonsterType() == 1)
                            someone.setPoint(someone.getPoint() + 40);

                        //erasing the monster
                        if(abc.getMonsterType()==99||abc.getMonsterType()==98||abc.getMonsterType()==97){
                            bossX=abc.getX();
                            bossY = abc.getY();
                        }

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
    public void moveMonsters(Player someone)throws SlickException{
        //getting the coordinates of the user
        int x = someone.getX();
        int y = someone.getY();

        double angle;
        //looping through the monster
        for (int i = 0; i < monsterList.size(); i++) {
            if (monsterList.get(i).getMonsterType() == 1||monsterList.get(i).getMonsterType() == 99 || monsterList.get(i).getMonsterType() == 98 || monsterList.get(i).getMonsterType()==97){
                //calculating the angle between the monster and the player
                angle = (float) Math.atan2((double) (y - monsterList.get(i).getY()), (x - monsterList.get(i).getX()));
                //System.out.println(angle);
                //set directions of the monster so that monster follows player
                if (collided.get(i) == 0) {
                    //x direction gets the cosine of the angle
                    monsterList.get(i).setDirectionX(Math.cos(angle));
                    //y direction gets the sine of the angle
                    monsterList.get(i).setDirectionY(Math.sin(angle));
                    //System.out.println("debuggg");
                }
                //checking collision

                if ((checkCollision(monsterList.get(i)) == null  &&
                        !monsterList.get(i).getCollisionRectangle((int) monsterList.get(i).getDirectionX() * someone.getSpeed(), (int) monsterList.get(i).getDirectionY() * someone.getSpeed()).intersects(someone.getCollisionRectangle(0, 0))
                )) {
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
                    monsterList.get(i).move(width,height1);
                    if (collided.get(i) == 80)
                        collided.set(i, 0);
                } else {
                    //checking if the collision is with player
                    if(monsterList.get(i).getCollisionRectangle((int) monsterList.get(i).getDirectionX() * someone.getSpeed(), (int) monsterList.get(i).getDirectionY() * someone.getSpeed()).intersects(someone.getCollisionRectangle(0, 0))){
                        if(someone.getHealth()>0&&  System.currentTimeMillis()-currTime >  300){
                            someone.setHealth(-monsterList.get(i).getAttackDamage());
                            currTime = System.currentTimeMillis();
                            someone.setPoint(someone.getPoint() - 20 * monsterList.get(i).getAttackDamage());
                        }}
                    //if collides after colliding bounce back again
                    if (collided.get(i) < 100) {
                        monsterList.get(i).setDirectionY(-monsterList.get(i).getDirectionY());
                        monsterList.get(i).setDirectionX(-monsterList.get(i).getDirectionX());

                    }
                    //if collided for the first time
                    if (collided.get(i) == 0)
                        collided.set(i, collided.get(i) + 1);

                    monsterList.get(i).move(width,height1);

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


    //getters


    public Portal getPort() {
        return port;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public ArrayList<Monster> getMonsterList() {
        return monsterList;
    }

    public ArrayList<Obstacle> getObstacleList() {
        return obstacleList;
    }
    public boolean getIsBoss(){
        return isBoss;
    }

    public double randomWithRange(int min, int max)
    {
        double range = (max - min) + 1;
        return (double)(Math.random() * range) + min;
    }
}
