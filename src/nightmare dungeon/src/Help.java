

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Help extends BasicGameState{
	
	//constructor
	public Help(int help){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//images are drawn
		g.drawString("Movement:", 275, 250);
		g.drawImage(new Image("src/nightmare dungeon/res/arrowControls.png"), 150, 300);
		g.drawString("Shooting:", 625, 250);
		g.drawImage(new Image("src/nightmare dungeon/res/help.png"), 565, 100);
		g.drawImage(new Image("src/nightmare dungeon/res/shootControls.png"), 500, 300 );
		g.drawString("Activate Item:", 950, 250);
		g.drawImage(new Image("src/nightmare dungeon/res/spaceBar.png"), 890, 270);
		g.drawString("Pause Button:", 950, 390);
		g.drawImage(new Image("src/nightmare dungeon/res/pauseButton.png"), 890, 400);
		g.drawString("Press Esc to go back", 50, 700);
		g.drawString("Press           to continue help", 1050, 700);
		g.drawImage(new Image("src/nightmare dungeon/res/rightButton.png"), 1050, 645);

	}

	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//state changes according to which button is pressed
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			sbg.enterState(0);
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			sbg.enterState(6);
	}

	//the state number of the class
	public int getID() {
		return 4;
	}

}
