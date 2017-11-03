import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;

import java.util.ArrayList;
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
    boolean dflag;
    public GameManager(int a){
        currentMapID = 0;
        someone= new Player(0,0,0,50,50);
        mapList = new ArrayList<Map>();
        generateMaps();
    }

    public void render(GameContainer gc,StateBasedGame sbg, Graphics g) throws SlickException{

       if(dflag )
        g.drawImage(new Image (Assets.playerUp1),someone.getX(),someone.getY());
        else
           g.drawImage(new Image (Assets.playerUp2),someone.getX(),someone.getY());
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

    }



    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
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
            someone.move();

        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_S))
        {
            dflag = !dflag;
            someone.setDirectionX(0);
            someone.setDirectionY(1);
            someone.move();

        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_A))
        {
            someone.setDirectionX(-1);
            someone.setDirectionY(0);
            someone.move();

        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_D))
        {
            someone.setDirectionX(1);
            someone.setDirectionY(0);
            someone.move();

        }


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
