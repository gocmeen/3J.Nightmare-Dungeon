package rpg.game.tutorial;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Credits extends BasicGameState{
	public Credits(int credits){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(new Image("res/credits.png"), 340, 100);
		g.drawString("Mehmet Oguz Gocmen", 440, 300);
		g.drawString("Berk Mandiracioglu", 440, 400);
		g.drawString("Huseyin Emre Basar", 440, 500);
		g.drawString("Hakan Sarp Aydemir", 440, 600);
		g.drawString("Press Esc to go back", 50, 800);
	}

	
	public void update(GameContainer arg0, StateBasedGame sbg, int delta) throws SlickException {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			sbg.enterState(0);
	}

	
	public int getID() {
		return 5;
	}

}
