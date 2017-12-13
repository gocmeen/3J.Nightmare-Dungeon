

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class HighScores extends BasicGameState{
	FileManager fm;
	//constructor
	public HighScores(int highScores){
		fm = new FileManager("src/nightmare dungeon/res/highScores.txt");
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//images are drawn
		g.drawImage(new Image("src/nightmare dungeon/res/highScore.png"), 250, 100);
		g.drawString(fm.highScores[0], 250, 200);
		g.drawString(fm.highScores[1], 250, 250);
		g.drawString(fm.highScores[2], 250, 300);
		g.drawString(fm.highScores[3], 250, 350);
		g.drawString(fm.highScores[4], 250, 400);
		g.drawString(fm.highScores[5], 250, 450);
		g.drawString(fm.highScores[6], 250, 500);
		g.drawString(fm.highScores[7], 250, 550);
		g.drawString(fm.highScores[8], 250, 600);
		g.drawString(fm.highScores[9], 250, 650);
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
