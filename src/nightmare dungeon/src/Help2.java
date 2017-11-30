

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Help2 extends BasicGameState{
	
	//constructor
	public Help2(int help2){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//images are drawn
		g.drawImage(new Image("src/nightmare dungeon/res/help.png"), 400, 100);
		g.drawString("Press           to go back", 50, 800);
		g.drawImage(new Image("src/nightmare dungeon/res/leftButton.png"), 50, 755);
	}

	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//state changes when left button is pressed to return to the previous help menu
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			sbg.enterState(4);
	}

	//the state number of the class
	public int getID() {
		return 6;
	}

}
