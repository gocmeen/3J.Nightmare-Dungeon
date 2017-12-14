import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PauseMenu extends BasicGameState {
    //constructor
    public PauseMenu(int pause){

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(GameRender.lastState, 500, 400);
    }


    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            GameUpdater.pausePressed = false;
            sbg.enterState(1);
        }
    }

    //the state number of the class
    public int getID() {
        return 8;
    }
}
