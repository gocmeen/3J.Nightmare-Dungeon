import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;

import java.util.Arrays;
import java.util.Timer;
import java.util.ArrayList;
import java.util.concurrent.*;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by wifinaynay on 01/11/17.
 */
public class GameManager extends BasicGameState{
    private ArrayList<Map> mapList;
    private Player someone;
    private int currentMapID;
    boolean dFlag,rFlag,uFlag,lFlag;
    boolean attacked;
    protected SoundManager soundmanager;
    boolean sound_on,music_on;

    public GameManager(int a, boolean sound_on, boolean music_on)throws SlickException{
        currentMapID = 0;
        someone= new Player(700,700,0,50,50);
        mapList = new ArrayList<Map>();
        generateMaps();
        attacked = false;
        soundmanager = new SoundManager(sound_on,music_on);
        this.sound_on = sound_on;
        this.music_on = music_on;

    }

    public void render(GameContainer gc,StateBasedGame sbg, Graphics g) throws SlickException{
        Room curr = mapList.get(currentMapID).getCurrentRoom();
        for(int i = 0; i < curr.getMonsterList().size();i++){
            g.drawImage(new Image (Assets.monster), curr.getMonsterList().get(i).getX(),curr.getMonsterList().get(i).getY());
        }
        for(int i = 0; i < curr.getItemList().size();i++){

            if(curr.getItemList().get(i).itemID==0)
            g.drawImage(new Image (Assets.item1),curr.getItemList().get(i).getX(),curr.getItemList().get(i).getY());
            else if(curr.getItemList().get(i).itemID==1)
                g.drawImage(new Image (Assets.item2),curr.getItemList().get(i).getX(),curr.getItemList().get(i).getY());

        }
       if(dFlag )
        g.drawImage(new Image (Assets.playerDown),someone.getX(),someone.getY());
        else if(uFlag)
           g.drawImage(new Image (Assets.playerUp),someone.getX(),someone.getY());
        else if(rFlag)
           g.drawImage(new Image (Assets.playerRight),someone.getX(),someone.getY());
        else if(lFlag)
           g.drawImage(new Image (Assets.playerLeft),someone.getX(),someone.getY());




        for (int i = 0; i < someone.getProjectile().size();i++) {

            g.drawImage(new Image(Assets.playerAttack), someone.getProjectile().get(i).getX(),someone.getProjectile().get(i).getY());
            //System.out.println(someone.getProjectile().get(i).getdirX()+", "+someone.getProjectile().get(i).getdirY());

        }
//System.out.println("*******************");
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        soundmanager.playMusic();

    }



    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Room curr = mapList.get(currentMapID).getCurrentRoom();
        curr.moveMonsters(someone);
        if (Keyboard.isKeyDown(Keyboard.KEY_W)&&Keyboard.isKeyDown(Keyboard.KEY_D))
        {
            someone.setDirectionX(1);
            someone.setDirectionY(-1);

            someone.move();

        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)&&Keyboard.isKeyDown(Keyboard.KEY_D))
        {

            someone.setDirectionX(1);
            someone.setDirectionY(1);
            someone.move();

        }
        if (Keyboard.isKeyDown(Keyboard.KEY_W)&&Keyboard.isKeyDown(Keyboard.KEY_A))
        {
            someone.setDirectionX(-1);
            someone.setDirectionY(-1);
            someone.move();

        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)&&Keyboard.isKeyDown(Keyboard.KEY_A))
        {
            someone.setDirectionX(-1);
            someone.setDirectionY(1);
            someone.move();

        }
        if (Keyboard.isKeyDown(Keyboard.KEY_W))
        {
            someone.setDirectionX(0);
            someone.setDirectionY(-1);
            Entity colliededObject = curr.checkCollision(someone);
            if(curr.checkCollision(someone)==null)
            someone.move();
            else if(colliededObject.typeID==2){

                someone.addpassive((Item)colliededObject);
                curr.removeItem((Item)colliededObject);

            }
            uFlag=true;
            dFlag = false;
            rFlag = false;
            lFlag = false;

        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_S))
        {

            someone.setDirectionX(0);
            someone.setDirectionY(1);
            Entity colliededObject = curr.checkCollision(someone);

            if(colliededObject==null)
            someone.move();
            else if(colliededObject.typeID==2){

                someone.addpassive((Item)colliededObject);
                curr.removeItem((Item)colliededObject);

            }
            uFlag=false;
            dFlag = true;
            rFlag = false;
            lFlag = false;

        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_A))
        {
            someone.setDirectionX(-1);
            someone.setDirectionY(0);
            Entity colliededObject = curr.checkCollision(someone);
            if(curr.checkCollision(someone)==null)
            someone.move();
            else if(colliededObject.typeID==2){

                someone.addpassive((Item)colliededObject);
                curr.removeItem((Item)colliededObject);

            }
            uFlag=false;
            dFlag = false;
            rFlag = false;
            lFlag = true;

        }

        else if (Keyboard.isKeyDown(Keyboard.KEY_D))
        {
            someone.setDirectionX(1);
            someone.setDirectionY(0);
            Entity colliededObject = curr.checkCollision(someone);
            if(curr.checkCollision(someone)==null)
            someone.move();
            else if(colliededObject.typeID==2){

                someone.addpassive((Item)colliededObject);
                curr.removeItem((Item)colliededObject);

            }
            uFlag=false;
            dFlag = false;
            rFlag = true;
            lFlag = false;

        }
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)&&Keyboard.isKeyDown(Keyboard.KEY_UP)){
            long startTime = System.currentTimeMillis();
            soundmanager.playSound(1);
            someone.attack(startTime, someone.getX(), someone.getY(), -1, -1);
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)&&Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            long startTime = System.currentTimeMillis();
            soundmanager.playSound(1);
            someone.attack(startTime, someone.getX(), someone.getY(), -1, 1);
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)&&Keyboard.isKeyDown(Keyboard.KEY_UP)){
            long startTime = System.currentTimeMillis();
            soundmanager.playSound(1);
            someone.attack(startTime, someone.getX(), someone.getY(), 1, -1);
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)&&Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            long startTime = System.currentTimeMillis();
            soundmanager.playSound(1);
            someone.attack(startTime, someone.getX(), someone.getY(), 1, 1);
        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {

            soundmanager.playSound(1);
                long startTime = System.currentTimeMillis();

                someone.attack(startTime, someone.getX(), someone.getY(), -1, 0);





                //someone.addProjectile(new Projectile(1,1,someone.getX(),someone.getY(),1,1,-1,0));


        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
        {

            long startTime = System.currentTimeMillis();
                someone.attack(startTime,someone.getX(), someone.getY(), 1, 0);


            soundmanager.playSound(1);


                //someone.addProjectile(new Projectile(1,1,someone.getX(),someone.getY(),1,1,-1,0));


            //someone.addProjectile(new Projectile(1,1,someone.getX(),someone.getY(),1,1,1,0));
        }

        else if (Keyboard.isKeyDown(Keyboard.KEY_UP))
        {

            //someone.addProjectile(new Projectile(1,1,someone.getX(),someone.getY(),1,1,0,1));
                long startTime = System.currentTimeMillis();
                someone.attack(startTime,someone.getX(), someone.getY(), 0, -1);

            soundmanager.playSound(1);

        }


        else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
        {

                //someone.addProjectile(new Projectile(1,1,someone.getX(),someone.getY(),1,1,0,-1));

                long startTime = System.currentTimeMillis();
                someone.attack(startTime,someone.getX(), someone.getY(), 0, 1);


            soundmanager.playSound(1);


                //someone.addProjectile(new Projectile(1,1,someone.getX(),someone.getY(),1,1,-1,0));





        }
        // keyPressed(Keyboard.getEventKey(),'a');



      //  System.out.println(timer.aBoolean+", "+ someone.attacked);
        curr.moveProjectiles(someone);

//System.out.println(someone.getProjectile().size());
        //projectile move
        sound_on = SoundManager.sound_on;  //for pause menu
        music_on = SoundManager.music_on;  //for pause menu
        //sound on kısmı yok

        if(!music_on)
            soundmanager.getMusic().stop();

    }




    public Map getCurrentMap(){
        return mapList.get(currentMapID);
    }
    public void generateMaps(){
        Map m1 = new Map(0);
        Map m2 = new Map(1);
        Map m3 = new Map(2);
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
