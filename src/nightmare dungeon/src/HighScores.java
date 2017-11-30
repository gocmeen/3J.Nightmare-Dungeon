

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class HighScores extends BasicGameState{
	
	//constructor
	public HighScores(int highScores){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//images are drawn
		g.drawImage(new Image("src/nightmare dungeon/res/highScore.png"), 250, 100);
		g.drawString("Press Esc to go back", 50, 800);
	}

	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//user presses escape to return to the main menu
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			sbg.enterState(0);
	}

	//the state number of the class
	public int getID() {
		return 3;
	}

}
