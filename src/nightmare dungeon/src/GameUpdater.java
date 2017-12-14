import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class GameUpdater{

    private ArrayList<Map> mapList; //holds the list of maps
    private Player someone; //Player object(Alice)
    private int currentMapID; //id of the current Map
    private int width = 1366;
    private int height = 780;
    private boolean dFlag,rFlag,uFlag,lFlag; //up,downiright and left directions

    public static boolean pausePressed = false;

    protected SoundManager soundmanager; // Sound Manager Object

    boolean sound_on,music_on; //flags for sound and music checks

    public GameUpdater()throws SlickException{//ArrayList<Map> mapList, int currentMapID,
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

        currentMapID = 0; //initial map is with id = 0
        someone= new Player(300,300,0,50, 50); //initializing Alice
        mapList = new ArrayList<Map>();
        generateMaps(); //Basically generates the maps and rooms and entities inside the Rooms
        soundmanager = new SoundManager(sound_on,music_on);
        this.sound_on = sound_on;
        this.music_on = music_on;

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        soundmanager.playMusic();

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
        /*if (gc.getInput().isKeyPressed(Keyboard.KEY_P))
            gc.setPaused(!gc.isPaused());*/
        if (gc.getInput().isKeyPressed(Keyboard.KEY_P)) {
            pausePressed = true;
            sbg.enterState(8);
        }

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
            else if(curr.checkCollision(someone).typeID==4){
                Door collidedDoor = (Door) curr.checkCollision(someone);
                if(mapList.get(currentMapID).getCurrentRoomID()==collidedDoor.getRoomID1())
                        mapList.get(currentMapID).setCurrentRoomID(collidedDoor.getRoomID2());
                else
                    mapList.get(currentMapID).setCurrentRoomID(collidedDoor.getRoomID2());
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

        //if(!music_on)
            //soundmanager.getMusic().stop();

    }

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
