import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class EndGame extends BasicGameState{

    TextField textField;

    //constructor
    public EndGame(int endGame){

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        textField = new TextField(gc, gc.getDefaultFont(), 50, 50, 200, 50, new ComponentListener() {
            @Override
            public void componentActivated(AbstractComponent abstractComponent) {
                textField.setFocus(true);
            }
        });
    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.red);
        textField.render(gc, g);
    }


    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        textField.setFocus(true);
    }

    //the state number of the class
    public int getID() {
        return 9;
    }
}
