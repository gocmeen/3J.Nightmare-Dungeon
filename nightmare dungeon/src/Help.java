

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Help extends BasicGameState{
	public Help(int help){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("Movement:", 225, 250);
		g.drawImage(new Image("res/arrowControls.png"), 100, 300);
		g.drawImage(new Image("res/help.png"), 400, 100);
		g.drawString("Press Esc to go back", 50, 800);
		g.drawString("Press           to continue help", 650, 800);
		g.drawImage(new Image("res/rightButton.png"), 650, 745);
	}

	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			sbg.enterState(0);
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			sbg.enterState(6);
	}

	
	public int getID() {
		return 4;
	}

}
