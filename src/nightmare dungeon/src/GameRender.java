import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.util.ArrayList;

public class GameRender{

    public static Image lastState;
    public GameRender(){
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g,
                       Player someone, ArrayList<Map> mapList, int currentMapID,
                       boolean dFlag, boolean uFlag, boolean lFlag, boolean rFlag) throws SlickException {
        /*if (gc.isPaused())
        {
            g.drawString("asdfasdfasdfasdfasdfasdfasdfasdfdddddddddddddd", 500, 500);s
            Rectangle rect = new Rectangle (0, 0, 1300, 780);
            g.setColor(new Color(0xAABBCCDD));
            g.fillRect(300,300,300,300);
        }*/

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

        g.setColor(org.newdawn.slick.Color.red);
        g.fillRect(10,20,someone.getHealth(),30);

        g.setColor(org.newdawn.slick.Color.white);
        g.drawString( someone.getHealth() + "/" + someone.getMaxHealth() ,10,25);
        //getting the current room from the Map
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
        }
        //Looping through the itemList to get the coordinates the coordinates of the items inside the room
        for(int i = 0; i < curr.getItemList().size();i++){

            if(curr.getItemList().get(i).itemID==0)
                g.drawImage(new org.newdawn.slick.Image(Assets.item1),curr.getItemList().get(i).getX(),curr.getItemList().get(i).getY());
            else if(curr.getItemList().get(i).itemID==1)
                g.drawImage(new org.newdawn.slick.Image(Assets.item2),curr.getItemList().get(i).getX(),curr.getItemList().get(i).getY());

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

            g.drawImage(new org.newdawn.slick.Image(Assets.playerAttack), someone.getProjectile().get(i).getX(),someone.getProjectile().get(i).getY());


        }

        for(int i = 0; i < curr.getMonsterList().size();i++) {
            for (int j = 0; j < curr.getMonsterList().get(i).getProjectile().size(); j++) {
                if(curr.getMonsterList().get(i).getProjectile().get(j).getX()<1366&&curr.getMonsterList().get(i).getProjectile().get(j).getX()>0
                        &&curr.getMonsterList().get(i).getProjectile().get(j).getY()<780&&curr.getMonsterList().get(i).getProjectile().get(j).getY()>0)
                    g.drawImage(new Image(Assets.monsterAttack), curr.getMonsterList().get(i).getProjectile().get(j).getX(), curr.getMonsterList().get(i).getProjectile().get(j).getY());


            }
            //System.out.println("aaa");
        }
    }
}
