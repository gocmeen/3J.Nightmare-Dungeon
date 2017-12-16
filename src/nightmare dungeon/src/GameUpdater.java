import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;
import java.util.ArrayList;

public class GameUpdater{

    private ArrayList<Map> mapList; //holds the list of maps
    private Player someone; //Player object(Alice)
    private int currentMapID; //id of the current Map
    private int width = 1380;
    private int height = 780;
    private boolean dFlag,rFlag,uFlag,lFlag; //up,downiright and left directions
    private int playerID = 0;
    private int minion = 0;
    private int passiveItem = 2;
    private int player = 0;
    private int door = 4;

    public static boolean pausePressed = false;

    protected SoundManager soundmanager; // Sound Manager Object

    boolean sound_on,music_on; //flags for sound and music checks
    private int width1;

    public GameUpdater()throws SlickException, IOException{//ArrayList<Map> mapList, int currentMapID,
                       //Player someone, int width, int height, SoundManager soundmanager,
                       //boolean sound_on, boolean music_on)throws SlickException{
        /*this.currentMapID = currentMapID; //initial map is with id = 0
        this.someone= someone; //initializing Alice
        this.mapList = mapList;
        this.width = width;
        this.height = height;
        generateMaps(); //Basically generates the maps and rooms and entities inside the Rooms
        this.soundmanager = soundmanager;
        this.sound_on = sound_on;
        this.music_on = music_on;*/
        //renderer = new GameRender();
        soundmanager = new SoundManager(sound_on,music_on);


    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException, IOException {

        currentMapID = 0; //initial map is with id = 0
        someone= new Player(300,300,0,50, 50); //initializing Alice
        mapList = new ArrayList<Map>();
        generateMaps(); //Basically generates the maps and rooms and entities inside the Rooms

        this.sound_on = sound_on;
        this.music_on = music_on;
        soundmanager.playMusic();

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException,IOException{
        /*if (gc.getInput().isKeyPressed(Keyboard.KEY_P))
            gc.setPaused(!gc.isPaused());*/
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
            //curr.attackMonsterProjectiles(someone);
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
                        someone.setX(1290 - 1);
                        System.out.println("ask");
                    } else if (someone.getX() == 1330- 1) {
                        someone.setX(40 + 1);
                        System.out.println("ask1");
                    }
                    if (someone.getY() == 0 + 17) {
                        someone.setY(700 - 17);
                        System.out.println("ask2");
                    } else {
                        if (someone.getY() == 740 - 17)
                            someone.setY(40 + 17);
                        System.out.println("ask3");
                    }


                }
                else if(curr.checkCollision(someone).typeID == 5&&curr.checkCleared()){

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


                } else if (curr.checkCollision(someone).typeID == door && curr.checkCleared()) {
                    Door collidedDoor = (Door) curr.checkCollision(someone);
                    soundmanager.playSound(4);

                    mapList.get(currentMapID).setCurrentRoomID(collidedDoor.getRoomID2());


                    someone.setX(collidedDoor.getX());
                    someone.setY(collidedDoor.getY());
                    if (someone.getX() == 0 + 1) {
                        someone.setX(1290 - 1);
                        System.out.println("ask");
                    } else if (someone.getX() == 1330- 1) {
                        someone.setX(40 + 1);
                        System.out.println("ask1");
                    }
                    if (someone.getY() == 0 + 17) {
                        someone.setY(700 - 17);
                        System.out.println("ask2");
                    } else {
                        if (someone.getY() == 740 - 17)
                            someone.setY(40 + 17);
                        System.out.println("ask3");
                    }
                }
                else if(curr.checkCollision(someone).typeID == 5&&curr.checkCleared()){

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


                } else if (curr.checkCollision(someone).typeID == door && curr.checkCleared()) {
                    Door collidedDoor = (Door) curr.checkCollision(someone);
                    soundmanager.playSound(4);

                    mapList.get(currentMapID).setCurrentRoomID(collidedDoor.getRoomID2());


                    someone.setX(collidedDoor.getX());
                    someone.setY(collidedDoor.getY());
                    if (someone.getX() == 0 + 1) {
                        someone.setX(1290 - 1);
                        System.out.println("ask");
                    } else if (someone.getX() == 1330- 1) {
                        someone.setX(40 + 1);
                        System.out.println("ask1");
                    }
                    if (someone.getY() == 0 + 17) {
                        someone.setY(700 - 17);
                        System.out.println("ask2");
                    } else {
                        if (someone.getY() == 740 - 17)
                            someone.setY(40 + 17);
                        System.out.println("ask3");
                    }
                }
                else if(curr.checkCollision(someone).typeID == 5&&curr.checkCleared()){

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


                } else if (curr.checkCollision(someone).typeID == door && curr.checkCleared()) {
                    Door collidedDoor = (Door) curr.checkCollision(someone);
                    soundmanager.playSound(4);

                    mapList.get(currentMapID).setCurrentRoomID(collidedDoor.getRoomID2());


                    someone.setX(collidedDoor.getX());
                    someone.setY(collidedDoor.getY());
                    if (someone.getX() == 0 + 1) {
                        someone.setX(1290 - 1);
                        System.out.println("ask");
                    } else if (someone.getX() == 1330- 1) {
                        someone.setX(40 + 1);
                        System.out.println("ask1");
                    }
                    if (someone.getY() == 0 + 17) {
                        someone.setY(700 - 17);
                        System.out.println("ask2");
                    } else {
                        if (someone.getY() == 740 - 17)
                            someone.setY(40 + 17);
                        System.out.println("ask3");
                    }
                }
                else if(curr.checkCollision(someone).typeID == 5&&curr.checkCleared()){

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


                } else if (curr.checkCollision(someone).typeID == door && curr.checkCleared()) {
                    Door collidedDoor = (Door) curr.checkCollision(someone);
                    soundmanager.playSound(4);

                    mapList.get(currentMapID).setCurrentRoomID(collidedDoor.getRoomID2());


                    someone.setX(collidedDoor.getX());
                    someone.setY(collidedDoor.getY());
                    if (someone.getX() == 0 + 1) {
                        someone.setX(1290 - 1);
                        System.out.println("ask");
                    } else if (someone.getX() == 1330- 1) {
                        someone.setX(40 + 1);
                        System.out.println("ask1");
                    }
                    if (someone.getY() == 0 + 17) {
                        someone.setY(700 - 17);
                        System.out.println("ask2");
                    } else {
                        if (someone.getY() == 740 - 17)
                            someone.setY(40 + 17);
                        System.out.println("ask3");
                    }
                }
                else if(curr.checkCollision(someone).typeID == 5&&curr.checkCleared()){

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


                } else if (curr.checkCollision(someone).typeID == door && curr.checkCleared()) {
                    Door collidedDoor = (Door) curr.checkCollision(someone);
                    soundmanager.playSound(4);

                    mapList.get(currentMapID).setCurrentRoomID(collidedDoor.getRoomID2());


                    someone.setX(collidedDoor.getX());
                    someone.setY(collidedDoor.getY());
                    if (someone.getX() == 0 + 1) {
                        someone.setX(1290 - 1);
                        System.out.println("ask");
                    } else if (someone.getX() == 1330- 1) {
                        someone.setX(40 + 1);
                        System.out.println("ask1");
                    }
                    if (someone.getY() == 0 + 17) {
                        someone.setY(700 - 17);
                        System.out.println("ask2");
                    } else {
                        if (someone.getY() == 740 - 17)
                            someone.setY(40 + 17);
                        System.out.println("ask3");
                    }
                }
                else if(curr.checkCollision(someone).typeID == 5&&curr.checkCleared()){

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


                } else if (curr.checkCollision(someone).typeID == door && curr.checkCleared()) {
                    Door collidedDoor = (Door) curr.checkCollision(someone);
                    soundmanager.playSound(4);

                    mapList.get(currentMapID).setCurrentRoomID(collidedDoor.getRoomID2());


                    someone.setX(collidedDoor.getX());
                    someone.setY(collidedDoor.getY());
                    if (someone.getX() == 0 + 1) {
                        someone.setX(1290 - 1);
                        System.out.println("ask");
                    } else if (someone.getX() == 1330- 1) {
                        someone.setX(40 + 1);
                        System.out.println("ask1");
                    }
                    if (someone.getY() == 0 + 17) {
                        someone.setY(700 - 17);
                        System.out.println("ask2");
                    } else {
                        if (someone.getY() == 740 - 17)
                            someone.setY(40 + 17);
                        System.out.println("ask3");
                    }
                }
                else if(curr.checkCollision(someone).typeID == 5&&curr.checkCleared()){

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


                } else if (curr.checkCollision(someone).typeID == door && curr.checkCleared()) {
                    Door collidedDoor = (Door) curr.checkCollision(someone);


                    mapList.get(currentMapID).setCurrentRoomID(collidedDoor.getRoomID2());


                    someone.setX(collidedDoor.getX());
                    someone.setY(collidedDoor.getY());
                    if (someone.getX() == 0 + 1) {
                        someone.setX(1290 - 1);
                        System.out.println("ask");
                    } else if (someone.getX() == 1330- 1) {
                        someone.setX(40 + 1);
                        System.out.println("ask1");
                    }
                    if (someone.getY() == 0 + 17) {
                        someone.setY(700 - 17);
                        System.out.println("ask2");
                    } else {
                        if (someone.getY() == 740 - 17)
                            someone.setY(40 + 17);
                        System.out.println("ask3");
                    }
                }
                else if(curr.checkCollision(someone).typeID == 5&&curr.checkCleared()){

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
                //setting directions
                uFlag = false;
                dFlag = false;
                rFlag = true;
                lFlag = false;

            }
            //Detescts user inputs for attack and shoots projectiles
            if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                //gets current time to set time intervals between projectiles
                long startTime = System.currentTimeMillis();

                if (someone.attack(startTime, someone.getX(), someone.getY(), -1, -1))
                    soundmanager.playSound(1);
            } else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                long startTime = System.currentTimeMillis();

                if (someone.attack(startTime, someone.getX(), someone.getY(), -1, 1))
                    soundmanager.playSound(1);
            } else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                long startTime = System.currentTimeMillis();

                if (someone.attack(startTime, someone.getX(), someone.getY(), 1, -1))
                    soundmanager.playSound(1);
            } else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                long startTime = System.currentTimeMillis();

                if (someone.attack(startTime, someone.getX(), someone.getY(), 1, 1))
                    soundmanager.playSound(1);
            } else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {


                long startTime = System.currentTimeMillis();

                if (someone.attack(startTime, someone.getX(), someone.getY(), -1, 0))

                    soundmanager.playSound(1);


            } else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {

                long startTime = System.currentTimeMillis();
                if (someone.attack(startTime, someone.getX(), someone.getY(), 1, 0))


                    soundmanager.playSound(1);


            } else if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {


                long startTime = System.currentTimeMillis();
                if (someone.attack(startTime, someone.getX(), someone.getY(), 0, -1))

                    soundmanager.playSound(1);

            } else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {


                long startTime = System.currentTimeMillis();
                if (someone.attack(startTime, someone.getX(), someone.getY(), 0, 1))


                    soundmanager.playSound(1);

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
        }
        }

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

    public int getCurrentMapID(){
        return currentMapID;
    }

    public Player getPlayer(){
        return someone;
    }

    public ArrayList<Map> getMapList(){
        return mapList;
    }

    public boolean getUpFlag(){
        return uFlag;
    }

    public boolean getDownFlag(){
        return dFlag;
    }

    public boolean getRightFlag(){
        return rFlag;
    }

    public boolean getLeftFlag(){
        return lFlag;
    }
}
