import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import java.awt.Rectangle;
import java.io.IOException;
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
public class GamePresenter extends BasicGameState {
    //Attributes
    /*private ArrayList<Map> mapList; //holds the list of maps
    private Player someone; //Player object(Alice)
    private int currentMapID; //id of the current Map
    private int width = 1200;
    private int height = 720;
    boolean dFlag,rFlag,uFlag,lFlag; //up,downiright and left directions
    protected SoundManager soundmanager; // Sound Manager Object
    boolean sound_on,music_on; //flags for sound and music checks
    */
    private ArrayList<Map> mapList; //holds the list of maps
    private Player someone; //Player object(Alice)
    private int currentMapID; //id of the current Map
    private int width = 1380;
    private int height = 780;
    private boolean dFlag,rFlag,uFlag,lFlag; //up,downiright and left directions
    private int playerID = 0;
    private int minion = 0;
    private int passiveItem = 2;
    private int activeItem = 3;
    private int door = 4;
    private int portal = 5;
    private int obstacle = 6;


    public static boolean pausePressed = false;
    public static Image lastState;
    protected SoundManager soundmanager; // Sound Manager Object

    boolean sound_on,music_on; //flags for sound and music checks
    //Constructor
    public GamePresenter(int a, boolean sound_on, boolean music_on)throws SlickException,IOException{
        /*currentMapID = 0; //initial map is with id = 0
        someone= new Player(300,300,0,50, 50); //initializing Alice
        mapList = new ArrayList<Map>();
        //generateMaps(); //Basically generates the maps and rooms and entities inside the Rooms
        soundmanager = new SoundManager(sound_on,music_on);
        this.sound_on = sound_on;
        this.music_on = music_on;
    */
        soundmanager = new SoundManager(sound_on,music_on);


    }

    /*
     * This method gets the X and Y coordinates of the Entities in Room class
     * Then, it draws them using their corresponding coordinates and images
     *This method is called repetetively as the game continues
     * */
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g) throws SlickException{


        if (GameUpdater.pausePressed) {
            lastState = new Image("src/nightmare dungeon/res/backGround1.png");
            g.copyArea(lastState, 0, 0);
        }
        if(currentMapID == 0)
            g.drawImage(new org.newdawn.slick.Image(Assets.floor1), 0,0);
        else if (currentMapID == 1)
            g.drawImage(new org.newdawn.slick.Image(Assets.floor2), 0,0);
        else if (currentMapID == 2)
            g.drawImage(new org.newdawn.slick.Image(Assets.floor3), 0,0);


        //  Rectangle healthbar = new Rectangle();
        g.setColor(org.newdawn.slick.Color.black);

        g.fillRect(10,20,someone.getMaxHealth(),30);

        g.setColor(org.newdawn.slick.Color.white);
        g.drawString("Point: " + someone.getPoint(), 650, 15);

        g.setColor(org.newdawn.slick.Color.red);
        g.fillRect(10,20,someone.getHealth(),30);

        g.setColor(org.newdawn.slick.Color.white);
        g.drawString( someone.getHealth() + "/" + someone.getMaxHealth() ,10,25);
        //getting the current room from the Map
        try{
            Room curr = mapList.get(currentMapID).getCurrentRoom();


        //Looping through the monsterList to get the coordinates the coordinates of the monsters inside the room
        for(int i = 0; i < curr.getMonsterList().size();i++){
            if(curr.getMonsterList().get(i).getMonsterType()==0){
                g.drawImage(new org.newdawn.slick.Image(Assets.monster1), curr.getMonsterList().get(i).getX(),curr.getMonsterList().get(i).getY());}
            else if(curr.getMonsterList().get(i).getMonsterType()==1){
                g.drawImage(new org.newdawn.slick.Image(Assets.monster2), curr.getMonsterList().get(i).getX(),curr.getMonsterList().get(i).getY());
            }
            else if(curr.getMonsterList().get(i).getMonsterType()==99){
                g.drawImage(new org.newdawn.slick.Image(Assets.boss), curr.getMonsterList().get(i).getX(),curr.getMonsterList().get(i).getY());
            }
            else if(curr.getMonsterList().get(i).getMonsterType()==98){
                g.drawImage(new org.newdawn.slick.Image(Assets.boss1), curr.getMonsterList().get(i).getX(),curr.getMonsterList().get(i).getY());
            }
            else if(curr.getMonsterList().get(i).getMonsterType()==97){
                g.drawImage(new org.newdawn.slick.Image(Assets.boss2), curr.getMonsterList().get(i).getX(),curr.getMonsterList().get(i).getY());
            }
        }
        //Looping through the itemList to get the coordinates the coordinates of the items inside the room
        for(int i = 0; i < curr.getItemList().size();i++){

            if(curr.getItemList().get(i).typeID==activeItem)
                g.drawImage(new org.newdawn.slick.Image(Assets.activeItem),curr.getItemList().get(i).getX(),curr.getItemList().get(i).getY());
            else if(curr.getItemList().get(i).typeID==passiveItem) {
                
                if (curr.getItemList().get(i).itemID == 0)
                    g.drawImage(new org.newdawn.slick.Image(Assets.item1), curr.getItemList().get(i).getX(), curr.getItemList().get(i).getY());
                else if (curr.getItemList().get(i).itemID == 1)
                    g.drawImage(new org.newdawn.slick.Image(Assets.item2), curr.getItemList().get(i).getX(), curr.getItemList().get(i).getY());
            }

        }

        for(int i = 0; i < curr.getObstacleList().size();i++){

            g.drawImage(new org.newdawn.slick.Image(Assets.obstacle), curr.getObstacleList().get(i).getX(),curr.getObstacleList().get(i).getY());

        }
        for(int i = 0; i < curr.getDoorList().size();i++){
            if(curr.checkCleared())
                g.drawImage(new org.newdawn.slick.Image(Assets.opendoor),curr.getDoorList().get(i).getX(),curr.getDoorList().get(i).getY());
            else
                g.drawImage(new org.newdawn.slick.Image(Assets.door),curr.getDoorList().get(i).getX(),curr.getDoorList().get(i).getY());
        }
        if(curr.getIsBoss()){

            if(curr.getPort()!=null)
            {
                if(curr.checkCleared())
                    g.drawImage(new org.newdawn.slick.Image(Assets.portalopen),curr.getPort().getX(),curr.getPort().getY());
                else
                    g.drawImage(new org.newdawn.slick.Image(Assets.portal),curr.getPort().getX(),curr.getPort().getY());
            }
        }
        //changing the image of player according to the direction it goes and the chosen character
        if (ChooseCharacter.chosenCharacter == 1) {
            if (dFlag)
                g.drawImage(new org.newdawn.slick.Image(Assets.playerDown), someone.getX(), someone.getY());
            else if (uFlag)
                g.drawImage(new org.newdawn.slick.Image(Assets.playerUp), someone.getX(), someone.getY());
            else if (rFlag)
                g.drawImage(new org.newdawn.slick.Image(Assets.playerRight), someone.getX(), someone.getY());
            else if (lFlag)
                g.drawImage(new org.newdawn.slick.Image(Assets.playerLeft), someone.getX(), someone.getY());
            else
                g.drawImage(new org.newdawn.slick.Image(Assets.playerDown), someone.getX(), someone.getY());
        }
        else if (ChooseCharacter.chosenCharacter == 2){
            if (dFlag)
                g.drawImage(new org.newdawn.slick.Image(Assets.player2Down), someone.getX(), someone.getY());
            else if (uFlag)
                g.drawImage(new org.newdawn.slick.Image(Assets.player2Up), someone.getX(), someone.getY());
            else if (rFlag)
                g.drawImage(new org.newdawn.slick.Image(Assets.player2Right), someone.getX(), someone.getY());
            else if (lFlag)
                g.drawImage(new org.newdawn.slick.Image(Assets.player2Left), someone.getX(), someone.getY());
            else
                g.drawImage(new org.newdawn.slick.Image(Assets.player2Down), someone.getX(), someone.getY());
        }
        else if (ChooseCharacter.chosenCharacter == 3){
            if (dFlag)
                g.drawImage(new org.newdawn.slick.Image(Assets.player3Down), someone.getX(), someone.getY());
            else if (uFlag)
                g.drawImage(new org.newdawn.slick.Image(Assets.player3Up), someone.getX(), someone.getY());
            else if (rFlag)
                g.drawImage(new org.newdawn.slick.Image(Assets.player3Right), someone.getX(), someone.getY());
            else if (lFlag)
                g.drawImage(new org.newdawn.slick.Image(Assets.player3Left), someone.getX(), someone.getY());
            else
                g.drawImage(new org.newdawn.slick.Image(Assets.player3Down), someone.getX(), someone.getY());
        }



        //looping through the projectileList to draw projectiles inside the room.
        for (int i = 0; i < someone.getProjectile().size();i++) {
            //DUZELTILECEK SINIRLARI AŞINCA SİLECEK
            if(someone.getX()<1366-76&& someone.getX()>31&&someone.getY()<780-67&&someone.getY()>67)
            g.drawImage(new org.newdawn.slick.Image(Assets.playerAttack), someone.getProjectile().get(i).getX(),someone.getProjectile().get(i).getY());
            //aSystem.out.println("drawn");

        }

        for(int i = 0; i < curr.getMonsterList().size();i++) {
            for (int j = 0; j < curr.getMonsterList().get(i).getProjectile().size(); j++) {
                if(curr.getMonsterList().get(i).getProjectile().get(j).getX()<1366-37&&curr.getMonsterList().get(i).getProjectile().get(j).getX()>0+31
                        &&curr.getMonsterList().get(i).getProjectile().get(j).getY()<780 -67&&curr.getMonsterList().get(i).getProjectile().get(j).getY()>+67) {
                    if(curr.getMonsterList().get(i).getMonsterType()==99)
                        g.drawImage(new Image(Assets.spiderkid), curr.getMonsterList().get(i).getProjectile().get(j).getX(), curr.getMonsterList().get(i).getProjectile().get(j).getY());
                    else if(curr.getMonsterList().get(i).getMonsterType()==98)
                        g.drawImage(new Image(Assets.knife), curr.getMonsterList().get(i).getProjectile().get(j).getX(), curr.getMonsterList().get(i).getProjectile().get(j).getY());
else
                    g.drawImage(new Image(Assets.monsterAttack), curr.getMonsterList().get(i).getProjectile().get(j).getX(), curr.getMonsterList().get(i).getProjectile().get(j).getY());

                }
            }
            //System.out.println("aaa");
        }
        } catch(ArrayIndexOutOfBoundsException exc){
            FileManager.scores[10] = String.valueOf(someone.getPoint());
            sbg.enterState(9);
        }

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        try {
            currentMapID = 0; //initial map is with id = 0
            someone= new Player(300,300,0,50, 50); //initializing Alice
            mapList = new ArrayList<Map>();
            generateMaps(); //Basically generates the maps and rooms and entities inside the Rooms

            this.sound_on = sound_on;
            this.music_on = music_on;
            soundmanager.playMusic();
        } catch (IOException e) {
            System.out.println("asd");
        }


    }
    /*
     * This method is called repetetively as the game continues
     * Inside this method Controller manipulates Model according to the user Input
     * This method manages the movement and attack of the player
     *
     * */
    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
try{
    if(someone.isAlive()) {

        if (gc.getInput().isKeyPressed(Keyboard.KEY_P)) {
            pausePressed = true;
            sbg.enterState(8);
        }

        Room curr = mapList.get(currentMapID).getCurrentRoom(); // current room that is from the Map class
        curr.moveMonsters(someone);
        if(curr.checkBossDied()){
            curr.createPortal();
        }
        //movement according to key presses W, A , S and D
        curr.attackMonsterProjectiles(someone);
        if (Keyboard.isKeyDown(Keyboard.KEY_W) && Keyboard.isKeyDown(Keyboard.KEY_D))//&& curr.checkRoomCollision(someone))
        {
            //Setting the direction according to Input
            someone.setDirectionX(1);
            someone.setDirectionY(-1);
            //Move method is called
            Entity colliededObject = curr.checkCollision(someone);
            if (curr.checkCollision(someone) == null) {// && curr.checkRoomCollision(someone))
                someone.move(width, height);
                System.out.println("AAAAAAAAAA");
            }
            else if (colliededObject.typeID == passiveItem) {// && curr.checkRoomCollision(someone)){
                curr.removeItem((PassiveItem) colliededObject);
                someone.addPassive((PassiveItem) colliededObject);


            } else if (curr.checkCollision(someone).typeID == door && curr.checkCleared()) {
                Door collidedDoor = (Door) curr.checkCollision(someone);
                soundmanager.playSound(4);

                mapList.get(currentMapID).setCurrentRoomID(collidedDoor.getRoomID2());


                someone.setX(collidedDoor.getX());
                someone.setY(collidedDoor.getY());
                if (someone.getX() == 0 + 1) {
                    someone.setX(1260 - 1);
                    System.out.println("ask");
                } else if (someone.getX() == 1330- 1) {
                    someone.setX(55 + 1);
                    System.out.println("ask1");
                }
                if (someone.getY() == 0 + 17) {
                    someone.setY(685 - 17);
                    System.out.println("ask2");
                } else {
                    if (someone.getY() == 740 - 17)
                        someone.setY(55 + 17);
                    System.out.println("ask3");
                }


            }
            else if(curr.checkCollision(someone).typeID == portal&&curr.checkCleared()){

                Portal port = (Portal) curr.checkCollision(someone);

                if(currentMapID==port.getMapID1()){
                    System.out.println("yakışıklı: "+currentMapID);
                    currentMapID=port.getMapID2();
                    System.out.println(currentMapID);
                }
                 else{
                    System.out.println("yakışıklı2: "+currentMapID);
                    currentMapID=port.getMapID1();
                    System.out.println(currentMapID);

                }
            }
            else if (colliededObject.typeID == activeItem)// && curr.checkRoomCollision(someone))
            {
                curr.removeItem((ActiveItem) colliededObject);
                someone.addActive((ActiveItem) colliededObject);
                someone.setPoint(someone.getPoint() + 200);

            }


        } else if (Keyboard.isKeyDown(Keyboard.KEY_S) && Keyboard.isKeyDown(Keyboard.KEY_D))//&& curr.checkRoomCollision(someone))
        {
            //Setting the direction according to Input
            someone.setDirectionX(1);
            someone.setDirectionY(1);
            Entity colliededObject = curr.checkCollision(someone);
            if (curr.checkCollision(someone) == null){// && curr.checkRoomCollision(someone))
                someone.move(width, height);
                System.out.println("AAAAAAAAAA");
            }
            else if (colliededObject.typeID == passiveItem) {// && curr.checkRoomCollision(someone)){
                curr.removeItem((PassiveItem) colliededObject);
                someone.addPassive((PassiveItem) colliededObject);
                someone.setPoint(someone.getPoint() + 200);

            }
            else if (curr.checkCollision(someone).typeID == door && curr.checkCleared()) {
                Door collidedDoor = (Door) curr.checkCollision(someone);
                soundmanager.playSound(4);

                mapList.get(currentMapID).setCurrentRoomID(collidedDoor.getRoomID2());


                someone.setX(collidedDoor.getX());
                someone.setY(collidedDoor.getY());
                if (someone.getX() == 0 + 1) {
                    someone.setX(1260 - 1);
                    System.out.println("ask");
                } else if (someone.getX() == 1330- 1) {
                    someone.setX(55 + 1);
                    System.out.println("ask1");
                }
                if (someone.getY() == 0 + 17) {
                    someone.setY(685 - 17);
                    System.out.println("ask2");
                } else {
                    if (someone.getY() == 740 - 17)
                        someone.setY(55 + 17);
                    System.out.println("ask3");
                }
            }
            else if(curr.checkCollision(someone).typeID == portal&&curr.checkCleared()){

                Portal port = (Portal) curr.checkCollision(someone);

                if(currentMapID==port.getMapID1()){
                    System.out.println("yakışıklı: "+currentMapID);
                    currentMapID=port.getMapID2();
                    System.out.println(currentMapID);
                }
                 else{
                    System.out.println("yakışıklı2: "+currentMapID);
                    currentMapID=port.getMapID1();
                    System.out.println(currentMapID);

                }
            }
            else if (colliededObject.typeID == activeItem)// && curr.checkRoomCollision(someone))
            {
                curr.removeItem((ActiveItem) colliededObject);
                someone.addActive((ActiveItem) colliededObject);
                someone.setPoint(someone.getPoint() + 200);

            }

        } else if (Keyboard.isKeyDown(Keyboard.KEY_W) && Keyboard.isKeyDown(Keyboard.KEY_A))//&& curr.checkRoomCollision(someone))
        {
            //Setting the direction according to Input
            someone.setDirectionX(-1);
            someone.setDirectionY(-1);
            Entity colliededObject = curr.checkCollision(someone);
            if (curr.checkCollision(someone) == null){// && curr.checkRoomCollision(someone))
                someone.move(width, height);System.out.println("AAAAAAAAAA");
            }
            else if (colliededObject.typeID == passiveItem) {// && curr.checkRoomCollision(someone)){
                curr.removeItem((PassiveItem) colliededObject);
                someone.addPassive((PassiveItem) colliededObject);
                someone.setPoint(someone.getPoint() + 200);


            } else if (curr.checkCollision(someone).typeID == door && curr.checkCleared()) {
                Door collidedDoor = (Door) curr.checkCollision(someone);
                soundmanager.playSound(4);

                mapList.get(currentMapID).setCurrentRoomID(collidedDoor.getRoomID2());


                someone.setX(collidedDoor.getX());
                someone.setY(collidedDoor.getY());
                if (someone.getX() == 0 + 1) {
                    someone.setX(1260 - 1);
                    System.out.println("ask");
                } else if (someone.getX() == 1330- 1) {
                    someone.setX(55 + 1);
                    System.out.println("ask1");
                }
                if (someone.getY() == 0 + 17) {
                    someone.setY(685 - 17);
                    System.out.println("ask2");
                } else {
                    if (someone.getY() == 740 - 17)
                        someone.setY(55 + 17);
                    System.out.println("ask3");
                }
            }
            else if(curr.checkCollision(someone).typeID == portal&&curr.checkCleared()){

                Portal port = (Portal) curr.checkCollision(someone);

                if(currentMapID==port.getMapID1()){
                    System.out.println("yakışıklı: "+currentMapID);
                    currentMapID=port.getMapID2();
                    System.out.println(currentMapID);
                }
                 else{
                    System.out.println("yakışıklı2: "+currentMapID);
                    currentMapID=port.getMapID1();
                    System.out.println(currentMapID);

                }
            }
            else if (colliededObject.typeID == activeItem)// && curr.checkRoomCollision(someone))
            {
                curr.removeItem((ActiveItem) colliededObject);
                someone.addActive((ActiveItem) colliededObject);
                someone.setPoint(someone.getPoint() + 200);

            }

        } else if (Keyboard.isKeyDown(Keyboard.KEY_S) && Keyboard.isKeyDown(Keyboard.KEY_A))//&& curr.checkRoomCollision(someone))
        {
            //Setting the direction according to Input
            someone.setDirectionX(-1);
            someone.setDirectionY(1);
            Entity colliededObject = curr.checkCollision(someone);
            if (curr.checkCollision(someone) == null) {// && curr.checkRoomCollision(someone))
                someone.move(width, height);
                System.out.println("AAAAAAAAAA");
            }
            else if (colliededObject.typeID == passiveItem) {// && curr.checkRoomCollision(someone)){
                curr.removeItem((PassiveItem) colliededObject);
                someone.addPassive((PassiveItem) colliededObject);
                someone.setPoint(someone.getPoint() + 200);

            } else if (curr.checkCollision(someone).typeID == door && curr.checkCleared()) {
                Door collidedDoor = (Door) curr.checkCollision(someone);
                soundmanager.playSound(4);

                mapList.get(currentMapID).setCurrentRoomID(collidedDoor.getRoomID2());


                someone.setX(collidedDoor.getX());
                someone.setY(collidedDoor.getY());
                if (someone.getX() == 0 + 1) {
                    someone.setX(1260 - 1);
                    System.out.println("ask");
                } else if (someone.getX() == 1330- 1) {
                    someone.setX(55 + 1);
                    System.out.println("ask1");
                }
                if (someone.getY() == 0 + 17) {
                    someone.setY(685 - 17);
                    System.out.println("ask2");
                } else {
                    if (someone.getY() == 740 - 17)
                        someone.setY(55 + 17);
                    System.out.println("ask3");
                }
            }
            else if(curr.checkCollision(someone).typeID == portal&&curr.checkCleared()){

                Portal port = (Portal) curr.checkCollision(someone);

                if(currentMapID==port.getMapID1()){
                    System.out.println("yakışıklı: "+currentMapID);
                    currentMapID=port.getMapID2();
                    System.out.println(currentMapID);
                }
                 else{
                    System.out.println("yakışıklı2: "+currentMapID);
                    currentMapID=port.getMapID1();
                    System.out.println(currentMapID);

                }
            }
            else if (colliededObject.typeID == activeItem)// && curr.checkRoomCollision(someone))
            {
                curr.removeItem((ActiveItem) colliededObject);
                someone.addActive((ActiveItem) colliededObject);
                someone.setPoint(someone.getPoint() + 200);

            }

        } else if (Keyboard.isKeyDown(Keyboard.KEY_W))//&& curr.checkRoomCollision(someone))
        {
            //Setting the direction according to Input
            someone.setDirectionX(0);
            someone.setDirectionY(-1);

            //Collision method of Room class is called in order to check collision
            Entity colliededObject = curr.checkCollision(someone);
            //move according to collision
            if (curr.checkCollision(someone) == null) {// && curr.checkRoomCollision(someone))
                someone.move(width, height);
                System.out.println("AAAAAAAAAA");
            }
            else if (colliededObject.typeID == passiveItem) {// && curr.checkRoomCollision(someone)){
                curr.removeItem((PassiveItem) colliededObject);
                someone.addPassive((PassiveItem) colliededObject);
                someone.setPoint(someone.getPoint() + 200);

            } else if (curr.checkCollision(someone).typeID == door && curr.checkCleared()) {
                Door collidedDoor = (Door) curr.checkCollision(someone);
                soundmanager.playSound(4);

                mapList.get(currentMapID).setCurrentRoomID(collidedDoor.getRoomID2());


                someone.setX(collidedDoor.getX());
                someone.setY(collidedDoor.getY());
                if (someone.getX() == 0 + 1) {
                    someone.setX(1260 - 1);
                    System.out.println("ask");
                } else if (someone.getX() == 1330- 1) {
                    someone.setX(55 + 1);
                    System.out.println("ask1");
                }
                if (someone.getY() == 0 + 17) {
                    someone.setY(685 - 17);
                    System.out.println("ask2");
                } else {
                    if (someone.getY() == 740 - 17)
                        someone.setY(55 + 17);
                    System.out.println("ask3");
                }
            }
            else if(curr.checkCollision(someone).typeID == portal&&curr.checkCleared()){

                Portal port = (Portal) curr.checkCollision(someone);

                if(currentMapID==port.getMapID1()){
                    System.out.println("yakışıklı: "+currentMapID);
                    currentMapID=port.getMapID2();
                    System.out.println(currentMapID);
                }
                 else{
                    System.out.println("yakışıklı2: "+currentMapID);
                    currentMapID=port.getMapID1();
                    System.out.println(currentMapID);

                }
            }
            else if (colliededObject.typeID == activeItem)// && curr.checkRoomCollision(someone))
            {
                curr.removeItem((ActiveItem) colliededObject);
                someone.addActive((ActiveItem) colliededObject);
                someone.setPoint(someone.getPoint() + 200);

            }
            //setting directions
            uFlag = true;
            dFlag = false;
            rFlag = false;
            lFlag = false;

        } else if (Keyboard.isKeyDown(Keyboard.KEY_S))//&& curr.checkRoomCollision(someone))
        {
            //Setting the direction according to Input
            someone.setDirectionX(0);
            someone.setDirectionY(1);
            //Collision method of Room class is called in order to check collision
            Entity colliededObject = curr.checkCollision(someone);
            //move according to collision
            if (colliededObject == null) {//&& curr.checkRoomCollision(someone))
                someone.move(width, height);
                System.out.println("AAAAAAAAAA");
            }
            else if (colliededObject.typeID == passiveItem)// && curr.checkRoomCollision(someone))
            {
                curr.removeItem((PassiveItem) colliededObject);
                someone.addPassive((PassiveItem) colliededObject);
                someone.setPoint(someone.getPoint() + 200);

            } else if (curr.checkCollision(someone).typeID == door && curr.checkCleared()) {
                Door collidedDoor = (Door) curr.checkCollision(someone);
                soundmanager.playSound(4);

                mapList.get(currentMapID).setCurrentRoomID(collidedDoor.getRoomID2());


                someone.setX(collidedDoor.getX());
                someone.setY(collidedDoor.getY());
                if (someone.getX() == 0 + 1) {
                    someone.setX(1260 - 1);
                    System.out.println("ask");
                } else if (someone.getX() == 1330- 1) {
                    someone.setX(55 + 1);
                    System.out.println("ask1");
                }
                if (someone.getY() == 0 + 17) {
                    someone.setY(685 - 17);
                    System.out.println("ask2");
                } else {
                    if (someone.getY() == 740 - 17)
                        someone.setY(55 + 17);
                    System.out.println("ask3");
                }
            }
            else if(curr.checkCollision(someone).typeID == portal&&curr.checkCleared()){

                Portal port = (Portal) curr.checkCollision(someone);

                if(currentMapID==port.getMapID1()){
                    System.out.println("yakışıklı: "+currentMapID);
                    currentMapID=port.getMapID2();
                    System.out.println(currentMapID);
                }
                 else{
                    System.out.println("yakışıklı2: "+currentMapID);
                    currentMapID=port.getMapID1();
                    System.out.println(currentMapID);

                }
            }
            else if (colliededObject.typeID == activeItem)// && curr.checkRoomCollision(someone))
            {
                curr.removeItem((ActiveItem) colliededObject);
                someone.addActive((ActiveItem) colliededObject);
                someone.setPoint(someone.getPoint() + 200);

            }

            //setting directions
            uFlag = false;
            dFlag = true;
            rFlag = false;
            lFlag = false;

        } else if (Keyboard.isKeyDown(Keyboard.KEY_A))//&& curr.checkRoomCollision(someone))
        {
            //Setting the direction according to Input
            someone.setDirectionX(-1);
            someone.setDirectionY(0);
            //Collision method of Room class is called in order to check collision
            Entity colliededObject = curr.checkCollision(someone);
            //move according to collision
            if (curr.checkCollision(someone) == null) {
                someone.move(width, height);System.out.println("AAAAAAAAAA");
                System.out.println("AAAAAAAAAAAA");
            }
            else if (colliededObject.typeID == passiveItem) {
                curr.removeItem((PassiveItem) colliededObject);
                someone.addPassive((PassiveItem) colliededObject);
                someone.setPoint(someone.getPoint() + 200);

            } else if (curr.checkCollision(someone).typeID == door && curr.checkCleared()) {
                Door collidedDoor = (Door) curr.checkCollision(someone);
                soundmanager.playSound(4);

                mapList.get(currentMapID).setCurrentRoomID(collidedDoor.getRoomID2());


                someone.setX(collidedDoor.getX());
                someone.setY(collidedDoor.getY());
                if (someone.getX() == 0 + 1) {
                    someone.setX(1260 - 1);
                    System.out.println("ask");
                } else if (someone.getX() == 1330- 1) {
                    someone.setX(55 + 1);
                    System.out.println("ask1");
                }
                if (someone.getY() == 0 + 17) {
                    someone.setY(685 - 17);
                    System.out.println("ask2");
                } else {
                    if (someone.getY() == 740 - 17)
                        someone.setY(55 + 17);
                    System.out.println("ask3");
                }
            }
            else if(curr.checkCollision(someone).typeID == portal&&curr.checkCleared()){

                Portal port = (Portal) curr.checkCollision(someone);

                if(currentMapID==port.getMapID1()){
                    System.out.println("yakışıklı: "+currentMapID);
                    currentMapID=port.getMapID2();
                    System.out.println(currentMapID);
                }
                 else{
                    System.out.println("yakışıklı2: "+currentMapID);
                    currentMapID=port.getMapID1();
                    System.out.println(currentMapID);

                }
            }
            else if (colliededObject.typeID == activeItem)// && curr.checkRoomCollision(someone))
            {
                curr.removeItem((ActiveItem) colliededObject);
                someone.addActive((ActiveItem) colliededObject);
                someone.setPoint(someone.getPoint() + 200);

            }
            //setting directions
            uFlag = false;
            dFlag = false;
            rFlag = false;
            lFlag = true;

        } else if (Keyboard.isKeyDown(Keyboard.KEY_D))//&& curr.checkRoomCollision(someone))
        {
            //Setting the direction according to Input
            someone.setDirectionX(1);
            someone.setDirectionY(0);
            //Collision method of Room class is called in order to check collision
            Entity colliededObject = curr.checkCollision(someone);
            //move according to collision
            if (curr.checkCollision(someone) == null) {
                someone.move(width, height);
                System.out.println("AAAAAAAAAA");
            }
            else if (colliededObject.typeID == passiveItem) {
                curr.removeItem((PassiveItem) colliededObject);
                someone.addPassive((PassiveItem) colliededObject);
                someone.setPoint(someone.getPoint() + 200);

            } else if (curr.checkCollision(someone).typeID == door && curr.checkCleared()) {
                Door collidedDoor = (Door) curr.checkCollision(someone);


                mapList.get(currentMapID).setCurrentRoomID(collidedDoor.getRoomID2());


                someone.setX(collidedDoor.getX());
                someone.setY(collidedDoor.getY());
                if (someone.getX() == 0 + 1) {
                    someone.setX(1260 - 1);
                    System.out.println("ask");
                } else if (someone.getX() == 1330- 1) {
                    someone.setX(55 + 1);
                    System.out.println("ask1");
                }
                if (someone.getY() == 0 + 17) {
                    someone.setY(685 - 17);
                    System.out.println("ask2");
                } else {
                    if (someone.getY() == 740 - 17)
                        someone.setY(55 + 17);
                    System.out.println("ask3");
                }
            }
            else if(curr.checkCollision(someone).typeID == portal&&curr.checkCleared()){

                Portal port = (Portal) curr.checkCollision(someone);

                if(currentMapID==port.getMapID1()){
                    System.out.println("yakışıklı: "+currentMapID);
                    currentMapID=port.getMapID2();
                    System.out.println(currentMapID);
                }
                 else{
                    System.out.println("yakışıklı2: "+currentMapID);
                    currentMapID=port.getMapID1();
                    System.out.println(currentMapID);

                }
            }
            else if (colliededObject.typeID == activeItem)// && curr.checkRoomCollision(someone))
            {
                curr.removeItem((ActiveItem) colliededObject);
                someone.addActive((ActiveItem) colliededObject);
                someone.setPoint(someone.getPoint() + 200);

            }
            //setting directions
            uFlag = false;
            dFlag = false;
            rFlag = true;
            lFlag = false;

        }
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
            someone.useActive();

        //Detescts user inputs for attack and shoots projectiles
        else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            //gets current time to set time intervals between projectiles
            long startTime = System.currentTimeMillis();

            if(someone.isActiveValid() ){

            if (someone.getActiveItem().activate(someone,startTime))

                soundmanager.playSound(1);

            }else{
                if (someone.attack(startTime, someone.getX(), someone.getY(), -1, -1))

                    soundmanager.playSound(1);
            }

        } else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            long startTime = System.currentTimeMillis();

            if(someone.isActiveValid() ){

                if (someone.getActiveItem().activate(someone,startTime))

                    soundmanager.playSound(1);

            }else{
                if (someone.attack(startTime, someone.getX(), someone.getY(), -1, 1))

                    soundmanager.playSound(1);
            }

        } else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            long startTime = System.currentTimeMillis();

            if(someone.isActiveValid() ){

                if (someone.getActiveItem().activate(someone,startTime))

                    soundmanager.playSound(1);

            }else{
                if (someone.attack(startTime, someone.getX(), someone.getY(), 1, -1))

                    soundmanager.playSound(1);
            }
        } else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            long startTime = System.currentTimeMillis();

            if(someone.isActiveValid() ){

                if (someone.getActiveItem().activate(someone,startTime))

                    soundmanager.playSound(1);

            }else{
                if (someone.attack(startTime, someone.getX(), someone.getY(), 1, 1))

                    soundmanager.playSound(1);
            }
        } else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {


            long startTime = System.currentTimeMillis();

            if(someone.isActiveValid() ){
                System.out.println("lefttt");
                if (someone.getActiveItem().activate(someone,startTime))

                    soundmanager.playSound(1);

            }else{
                if (someone.attack(startTime, someone.getX(), someone.getY(), -1, 0))

                    soundmanager.playSound(1);
            }

        } else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {

            long startTime = System.currentTimeMillis();
            if(someone.isActiveValid() ){

                if (someone.getActiveItem().activate(someone,startTime))

                    soundmanager.playSound(1);

            }else{
                if (someone.attack(startTime, someone.getX(), someone.getY(), 1, 0))

                    soundmanager.playSound(1);
            }

        } else if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {


            long startTime = System.currentTimeMillis();

            if(someone.isActiveValid() ){

                if (someone.getActiveItem().activate(someone,startTime))

                    soundmanager.playSound(1);

            }else{
                if (someone.attack(startTime, someone.getX(), someone.getY(), 0, -1))

                    soundmanager.playSound(1);
            }

        } else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {


            long startTime = System.currentTimeMillis();
            if(someone.isActiveValid() ){

                if (someone.getActiveItem().activate(someone,startTime))

                    soundmanager.playSound(1);

            }else{
                if (someone.attack(startTime, someone.getX(), someone.getY(), 0, 1))

                    soundmanager.playSound(1);
            }

        }

        curr.moveProjectiles(someone);


        //projectile move
        sound_on = SoundManager.sound_on;  //for pause menu
        music_on = SoundManager.music_on;  //for pause menu
        //sound on kısmı yok

        //if(!music_on)
        //soundmanager.getMusic().stop();
    }
    else {
        SoundManager.playSound(3);
        sbg.getState(0).init(gc, sbg);
        sbg.enterState(0);
    }}catch (IOException e){
    System.out.print("asd");
}

        /*Room curr = mapList.get(currentMapID).getCurrentRoom(); // current room that is from the Map class
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

            */

    }



    //returns current map
    public Map getCurrentMap(){
        return mapList.get(currentMapID);
    }
    //generates maps(layers) inside the game
    public void generateMaps() throws IOException{
        //this neighbours are one directioned
        Map m1 = new Map(0,width,height,1);
        Map m2 = new Map(1,width,height,2);
        Map m3 = new Map(2,width,height,-1);
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
