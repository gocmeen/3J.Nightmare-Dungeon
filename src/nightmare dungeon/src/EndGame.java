import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;
import java.lang.reflect.Field;

public class EndGame extends BasicGameState{

    TextField textField;

    //constructor
    public EndGame(int endGame){

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        textField = new TextField(gc, gc.getDefaultFont(), 550, 250, 200, 50, new ComponentListener() {
            @Override
            public void componentActivated(AbstractComponent abstractComponent) {
                textField.setFocus(true);
            }
        });
    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString("Enter your username(Max. 10 characters): ", 500, 200);
        textField.render(gc, g);
        g.drawString("Press esc to save your name and go back to main menu", 50, 700);
        g.drawString("Your name will be in the high scores section if you \n        have beaten any of the top 10 scores.", 465, 400);
    }


    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        textField.setFocus(true);
        textField.setMaxLength(10);
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            String temp2 = textField.getText();
            String temp = FileManager.scores[10];
            for (int i = 9; i >= 0; i--){
                if (Integer.parseInt(temp) >= Integer.parseInt(FileManager.scores[i])){
                    FileManager.scores[i + 1] = FileManager.scores[i];
                    FileManager.scores[i] = temp;
                    FileManager.names[i + 1] = FileManager.names[i];
                    FileManager.names[i] = temp2;
                }
            }
            FileManager.writeToFile("src/nightmare dungeon/res/highScores.txt");
            sbg.enterState(0);
        }
    }

    //the state number of the class
    public int getID() {
        return 9;
    }
}
