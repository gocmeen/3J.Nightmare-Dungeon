package rpg.game.tutorial;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class HighScores extends BasicGameState{
	public HighScores(int highScores){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(new Image("res/highScore.png"), 250, 100);
		g.drawString("Press Esc to go back", 50, 800);
	}

	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			sbg.enterState(0);
	}

	
	public int getID() {
		return 3;
	}

}
