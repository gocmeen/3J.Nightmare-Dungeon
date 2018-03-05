

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Credits extends BasicGameState{
	//constructor
	public Credits(int credits){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//images are drawn
		g.drawImage(new Image("src/nightmare dungeon/res/credits.png"), 500, 100);
		g.drawString("Mehmet Oguz Gocmen", 580, 300);
		g.drawString("Berk Mandiracioglu", 580, 400);
		g.drawString("Huseyin Emre Basar", 580, 500);
		g.drawString("Hakan Sarp Aydemir", 580, 600);
		g.drawString("Press Esc to go back", 50, 700);
	}

	
	public void update(GameContainer arg0, StateBasedGame sbg, int delta) throws SlickException {
		//user presses escape to return to the main menu
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			sbg.enterState(0);
	}

	//the state number of the class
	public int getID() {
		return 5;
	}

}
