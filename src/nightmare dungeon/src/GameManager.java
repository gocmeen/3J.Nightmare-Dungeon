import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import java.awt.Rectangle;

import java.util.Arrays;
import java.util.Timer;
import java.util.ArrayList;
import java.util.concurrent.*;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by wifinaynay on 01/11/17.
 */
public class GameManager extends BasicGameState{
    //Attributes
    private ArrayList<Map> mapList; //holds the list of maps
    private Player someone; //Player object(Alice)
    private int currentMapID; //id of the current Map
    private int width = 1200;
    private int height = 720;
    boolean dFlag,rFlag,uFlag,lFlag; //up,downiright and left directions

    protected SoundManager soundmanager; // Sound Manager Object
    boolean sound_on,music_on; //flags for sound and music checks

    GameRender renderer;


    //Constructor
    public GameManager(int a, boolean sound_on, boolean music_on)throws SlickException{
        currentMapID = 0; //initial map is with id = 0
        someone= new Player(300,300,0,50, 50); //initializing Alice
        mapList = new ArrayList<Map>();
        generateMaps(); //Basically generates the maps and rooms and entities inside the Rooms
        soundmanager = new SoundManager(sound_on,music_on);
        this.sound_on = sound_on;
        this.music_on = music_on;
        renderer = new GameRender();

    }

    /*
    * This method gets the X and Y coordinates of the Entities in Room class
    * Then, it draws them using their corresponding coordinates and images
    *This method is called repetetively as the game continues
    * */
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g) throws SlickException{

        //renderer.render(gc, sbg, g,
          //      someone, mapList, currentMapID,
        //    dFlag, uFlag, lFlag, rFlag);
        Rectangle healthbar = new Rectangle();
        g.setColor(Color.red);

        g.fillRect(10,50,someone.getMaxHealth()*(someone.getHealth()/someone.getMaxHealth()),50);
        g.setColor(Color.white);
        g.drawString( someone.getHealth() + "/" + someone.getMaxHealth() ,10,55);
        //getting the current room from the Map
        Room curr = mapList.get(currentMapID).getCurrentRoom();

        g.drawImage(new Image (Assets.background), 150,0);
        //Looping through the monsterList to get the coordinates the coordinates of the monsters inside the room
        for(int i = 0; i < curr.getMonsterList().size();i++){
            if(curr.getMonsterList().get(i).getMonsterType()==0){
            g.drawImage(new Image (Assets.monster1), curr.getMonsterList().get(i).getX(),curr.getMonsterList().get(i).getY());}
            else if(curr.getMonsterList().get(i).getMonsterType()==1){
                g.drawImage(new Image (Assets.monster2), curr.getMonsterList().get(i).getX(),curr.getMonsterList().get(i).getY());
            }
        }
        //Looping through the itemList to get the coordinates the coordinates of the items inside the room
        for(int i = 0; i < curr.getItemList().size();i++){

            if(curr.getItemList().get(i).itemID==0)
            g.drawImage(new Image (Assets.item1),curr.getItemList().get(i).getX(),curr.getItemList().get(i).getY());
            else if(curr.getItemList().get(i).itemID==1)
                g.drawImage(new Image (Assets.item2),curr.getItemList().get(i).getX(),curr.getItemList().get(i).getY());

        }

        for(int i = 0; i < curr.getObstacleList().size();i++){

                g.drawImage(new Image (Assets.obstacle), curr.getObstacleList().get(i).getX(),curr.getObstacleList().get(i).getY());

        }
        //changing the image of player according to the direction it goes
       if(dFlag )
        g.drawImage(new Image (Assets.playerDown),someone.getX(),someone.getY());
        else if(uFlag)
           g.drawImage(new Image (Assets.playerUp),someone.getX(),someone.getY());
        else if(rFlag)
           g.drawImage(new Image (Assets.playerRight),someone.getX(),someone.getY());
        else if(lFlag)
           g.drawImage(new Image (Assets.playerLeft),someone.getX(),someone.getY());
        else
           g.drawImage(new Image (Assets.playerDown),someone.getX(),someone.getY());



        //looping through the projectileList to draw projectiles inside the room.
        for (int i = 0; i < someone.getProjectile().size();i++) {

            g.drawImage(new Image(Assets.playerAttack), someone.getProjectile().get(i).getX(),someone.getProjectile().get(i).getY());


        }

        for(int i = 0; i < curr.getMonsterList().size();i++) {
            for (int j = 0; j < curr.getMonsterList().get(i).getProjectile().size(); j++) {
                if(curr.getMonsterList().get(i).getProjectile().get(j).getX()<1000&&curr.getMonsterList().get(i).getProjectile().get(j).getX()>0
                        &&curr.getMonsterList().get(i).getProjectile().get(j).getY()<850&&curr.getMonsterList().get(i).getProjectile().get(j).getY()>0)
                g.drawImage(new Image(Assets.monsterAttack), curr.getMonsterList().get(i).getProjectile().get(j).getX(), curr.getMonsterList().get(i).getProjectile().get(j).getY());


    }
    //System.out.println("aaa");
}

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        soundmanager.playMusic();

    }


    /*
    * This method is called repetetively as the game continues
    * Inside this method Controller manipulates Model according to the user Input
    * This method manages the movement and attack of the player
    *
    * */
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        Room curr = mapList.get(currentMapID).getCurrentRoom(); // current room that is from the Map class
        curr.moveMonsters(someone);
        //movement according to key presses W, A , S and D
        curr.attackMonsterProjectiles(someone);
        if (Keyboard.isKeyDown(Keyboard.KEY_W)&&Keyboard.isKeyDown(Keyboard.KEY_D))//&& curr.checkRoomCollision(someone))
        {
            //Setting the direction according to Input
            someone.setDirectionX(1);
            someone.setDirectionY(-1);
            //Move method is called
            someone.move(width,height);

        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_S)&&Keyboard.isKeyDown(Keyboard.KEY_D))//&& curr.checkRoomCollision(someone))
        {
            //Setting the direction according to Input
            someone.setDirectionX(1);
            someone.setDirectionY(1);
            someone.move(width,height);

        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_W)&&Keyboard.isKeyDown(Keyboard.KEY_A))//&& curr.checkRoomCollision(someone))
        {
            //Setting the direction according to Input
            someone.setDirectionX(-1);
            someone.setDirectionY(-1);
            someone.move(width,height);

        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_S)&&Keyboard.isKeyDown(Keyboard.KEY_A))//&& curr.checkRoomCollision(someone))
        {
            //Setting the direction according to Input
            someone.setDirectionX(-1);
            someone.setDirectionY(1);
            someone.move(width,height);

        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_W))//&& curr.checkRoomCollision(someone))
        {
            //Setting the direction according to Input
            someone.setDirectionX(0);
            someone.setDirectionY(-1);

            //Collision method of Room class is called in order to check collision
            Entity colliededObject = curr.checkCollision(someone);
            //move according to collision
            if(curr.checkCollision(someone)==null)// && curr.checkRoomCollision(someone))
            someone.move(width,height);
            else if(colliededObject.typeID==2){// && curr.checkRoomCollision(someone)){

                someone.addPassive((PassiveItem)colliededObject);
                curr.removeItem((PassiveItem)colliededObject);

            }
            //setting directions
            uFlag=true;
            dFlag = false;
            rFlag = false;
            lFlag = false;

        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_S))//&& curr.checkRoomCollision(someone))
        {
            //Setting the direction according to Input
            someone.setDirectionX(0);
            someone.setDirectionY(1);
            //Collision method of Room class is called in order to check collision
            Entity colliededObject = curr.checkCollision(someone);
            //move according to collision
            if(colliededObject==null )//&& curr.checkRoomCollision(someone))
            someone.move(width,height);
            else if(colliededObject.typeID==2)// && curr.checkRoomCollision(someone))
            {

                someone.addPassive((PassiveItem)colliededObject);
                curr.removeItem((PassiveItem)colliededObject);

            }
            //setting directions
            uFlag=false;
            dFlag = true;
            rFlag = false;
            lFlag = false;

        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_A))//&& curr.checkRoomCollision(someone))
        {
            //Setting the direction according to Input
            someone.setDirectionX(-1);
            someone.setDirectionY(0);
            //Collision method of Room class is called in order to check collision
            Entity colliededObject = curr.checkCollision(someone);
            //move according to collision
            if(curr.checkCollision(someone)==null )
            someone.move(width,height);
            else if(colliededObject.typeID==2 ){

                someone.addPassive((PassiveItem)colliededObject);
                curr.removeItem((PassiveItem)colliededObject);

            }
            //setting directions
            uFlag=false;
            dFlag = false;
            rFlag = false;
            lFlag = true;

        }

        else if (Keyboard.isKeyDown(Keyboard.KEY_D))//&& curr.checkRoomCollision(someone))
        {
            //Setting the direction according to Input
            someone.setDirectionX(1);
            someone.setDirectionY(0);
            //Collision method of Room class is called in order to check collision
            Entity colliededObject = curr.checkCollision(someone);
            //move according to collision
            if(curr.checkCollision(someone)==null )
            someone.move(width,height);
            else if(colliededObject.typeID==2 ){

                someone.addPassive((PassiveItem)colliededObject);
                curr.removeItem((PassiveItem)colliededObject);

            }
            //setting directions
            uFlag=false;
            dFlag = false;
            rFlag = true;
            lFlag = false;

        }
        //Detescts user inputs for attack and shoots projectiles
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)&&Keyboard.isKeyDown(Keyboard.KEY_UP)){
            //gets current time to set time intervals between projectiles
            long startTime = System.currentTimeMillis();

            if(someone.attack(startTime, someone.getX(), someone.getY(), -1, -1))
            soundmanager.playSound(1);
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)&&Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            long startTime = System.currentTimeMillis();

            if(someone.attack(startTime, someone.getX(), someone.getY(), -1, 1))
            soundmanager.playSound(1);
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)&&Keyboard.isKeyDown(Keyboard.KEY_UP)){
            long startTime = System.currentTimeMillis();

            if(someone.attack(startTime, someone.getX(), someone.getY(), 1, -1))
            soundmanager.playSound(1);
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)&&Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            long startTime = System.currentTimeMillis();

            if(someone.attack(startTime, someone.getX(), someone.getY(), 1, 1))
            soundmanager.playSound(1);
        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {


                long startTime = System.currentTimeMillis();

                if(someone.attack(startTime, someone.getX(), someone.getY(), -1, 0))

                    soundmanager.playSound(1);






        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
        {

            long startTime = System.currentTimeMillis();
                if(someone.attack(startTime,someone.getX(), someone.getY(), 1, 0))


            soundmanager.playSound(1);



        }

        else if (Keyboard.isKeyDown(Keyboard.KEY_UP))
        {


                long startTime = System.currentTimeMillis();
               if( someone.attack(startTime,someone.getX(), someone.getY(), 0, -1))

            soundmanager.playSound(1);

        }


        else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
        {



                long startTime = System.currentTimeMillis();
                if(someone.attack(startTime,someone.getX(), someone.getY(), 0, 1))


            soundmanager.playSound(1);

        }

        curr.moveProjectiles(someone);


        //projectile move
        sound_on = SoundManager.sound_on;  //for pause menu
        music_on = SoundManager.music_on;  //for pause menu
        //sound on kısmı yok

        if(!music_on)
            soundmanager.getMusic().stop();

    }



    //returns current map
    public Map getCurrentMap(){
        return mapList.get(currentMapID);
    }
    //generates maps(layers) inside the game
    public void generateMaps(){
        Map m1 = new Map(0,width,height);
        Map m2 = new Map(1,width,height);
        Map m3 = new Map(2,width,height);
        mapList.add(m1);
        mapList.add(m2);
        mapList.add(m3);
    }
    public void setCurrentMapID(int currentMapID) {
        this.currentMapID = currentMapID;
    }

    public int getID(){
        return 1;
    }

}
