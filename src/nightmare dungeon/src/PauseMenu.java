import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PauseMenu extends BasicGameState {

    private boolean prevDown = false; // used for taking single input from user
    private boolean prevUp = false; // used for taking single input from user

    //different states as booleans for forming the translation between them
    private boolean mainMenuActivated = true;
    private boolean retryActivated = false;
    private boolean settingsActivated = false;
    private boolean quitActivated = false;

    //constructor
    public PauseMenu(int pause){

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //g.drawImage(GameRender.lastState, 500, 400);
        g.drawImage(new Image("src/nightmare dungeon/res/pauseTitle.png"), 460, 100);
        if (mainMenuActivated == false)
            g.drawImage(new Image("src/nightmare dungeon/res/mainMenu.png"), 500, 200);
        else
            g.drawImage(new Image("src/nightmare dungeon/res/mainMenuPressed.png"), 500, 200);
        if (settingsActivated == false)
            g.drawImage(new Image("src/nightmare dungeon/res/settings.png"), 500, 300);
        else
            g.drawImage(new Image("src/nightmare dungeon/res/settingsPressed.png"), 500, 300);
        if (retryActivated == false)
            g.drawImage(new Image("src/nightmare dungeon/res/retry.png"), 500, 400);
        else
            g.drawImage(new Image("src/nightmare dungeon/res/retryPressed.png"), 500, 400);
        if (quitActivated == false)
            g.drawImage(new Image("src/nightmare dungeon/res/quit.png"), 500, 500);
        else
            g.drawImage(new Image("src/nightmare dungeon/res/quitPressed.png"), 500, 500);
    }


    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            GameUpdater.pausePressed = false;
            sbg.enterState(1);
        }

        //input from user is taken, when space is pressed it changes the states
        if (mainMenuActivated && Keyboard.isKeyDown(Keyboard.KEY_SPACE))
            sbg.enterState(0);
            if (retryActivated && Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            sbg.getState(7).init(gc,sbg);
            sbg.enterState(7);
        }
        if (settingsActivated && Keyboard.isKeyDown(Keyboard.KEY_SPACE))
            sbg.enterState(2);
        if (quitActivated && Keyboard.isKeyDown(Keyboard.KEY_SPACE))
            gc.exit();

        //boolean logic of changing buttons
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) && prevDown == false){
            prevDown = true;
            if (mainMenuActivated){
                mainMenuActivated = false;
                settingsActivated = true;
            }
            else if (retryActivated){
                retryActivated = false;
                quitActivated = true;
            }
            else if (settingsActivated){
                settingsActivated = false;
                retryActivated = true;
            }
            else if (quitActivated){
                quitActivated = false;
                mainMenuActivated = true;
            }
        }
        else if (!Keyboard.isKeyDown(Keyboard.KEY_DOWN))
            prevDown = false;
        if (Keyboard.isKeyDown(Keyboard.KEY_UP) && prevUp == false){
            prevUp = true;
            if (mainMenuActivated){
                mainMenuActivated = false;
                quitActivated = true;
            }
            else if (quitActivated){
                quitActivated = false;
                retryActivated = true;
            }
            else if (settingsActivated){
                settingsActivated = false;
                mainMenuActivated = true;
            }
            else if (retryActivated){
                retryActivated = false;
                settingsActivated = true;
            }
        }
        else if (!Keyboard.isKeyDown(Keyboard.KEY_UP))
            prevUp = false;
    }

    //the state number of the class
    public int getID() {
        return 8;
    }
}
