

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import javax.swing.*;

public class Menu extends BasicGameState{

	private long spacePressed = 0; //To not capture the KEY_SPACE press of the main menu.

	private boolean prevDown = false; // used for taking single input from user
	private boolean prevUp = false; // used for taking single input from user
	
	//different states as booleans for forming the translation between them
	private boolean playActivated = true;         
	private boolean settingsActivated = false;
	private boolean highScoresActivated = false;
	private boolean helpActivated = false;
	private boolean creditsActivated = false;
	private boolean exitActivated = false;
	
	//constructor
	public Menu(int startmenu) {
	}

	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{


	}

	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(new Image("src/nightmare dungeon/res/nightmareDungeon.png"), 400, 100);

		//if a boolean is true the button is highlighted
		if (playActivated == false)
			g.drawImage(new Image("src/nightmare dungeon/res/playUnactive.png"), 550, 200);
		else
			g.drawImage(new Image("src/nightmare dungeon/res/playActivated.png"), 550, 200);
		
		if (settingsActivated == false)
			g.drawImage(new Image("src/nightmare dungeon/res/settingsUnactivated.png"), 550, 300);
		else
			g.drawImage(new Image("src/nightmare dungeon/res/settingsActivated.png"), 550, 300);
		
		if (highScoresActivated == false)
			g.drawImage(new Image("src/nightmare dungeon/res/highScoreUnactive.png"), 550, 400);
		else
			g.drawImage(new Image("src/nightmare dungeon/res/highScoreActivated.png"), 550, 400);
		
		if (helpActivated == false)
			g.drawImage(new Image("src/nightmare dungeon/res/helpUnactive.png"), 550, 500);
		else
			g.drawImage(new Image("src/nightmare dungeon/res/helpActivated.png"), 550, 500);
		
		if (creditsActivated == false)
			g.drawImage(new Image("src/nightmare dungeon/res/creditsUnactive.png"), 550, 600);
		else
			g.drawImage(new Image("src/nightmare dungeon/res/creditsActivated.png"), 550, 600);
		
		if (exitActivated == false)
			g.drawImage(new Image("src/nightmare dungeon/res/exitUnactive.png"), 550, 700);
		else
			g.drawImage(new Image("src/nightmare dungeon/res/exitActivated.png"), 550, 700);
	}
	
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		spacePressed += delta; //To not capture the KEY_SPACE press of the main menu.

		//input from user is taken, when space is pressed it changes the states
		if (settingsActivated && Keyboard.isKeyDown(Keyboard.KEY_SPACE) && (spacePressed >= 1000)) {
			FileManager.readFromFile("src/nightmare dungeon/res/highScores.txt");
			sbg.enterState(2);
		}
		if (playActivated && Keyboard.isKeyDown(Keyboard.KEY_SPACE)&& (spacePressed >= 1000)) {
			FileManager.readFromFile("src/nightmare dungeon/res/highScores.txt");
	        sbg.getState(7).init(gc,sbg);
			sbg.enterState(7);
		}
		if (highScoresActivated && Keyboard.isKeyDown(Keyboard.KEY_SPACE)&& (spacePressed >= 1000)) {
			FileManager.readFromFile("src/nightmare dungeon/res/highScores.txt");
			sbg.enterState(3);
		}
		if (helpActivated && Keyboard.isKeyDown(Keyboard.KEY_SPACE)&& (spacePressed >= 1000)) {
			FileManager.readFromFile("src/nightmare dungeon/res/highScores.txt");
			sbg.enterState(4);
		}
		if (creditsActivated && Keyboard.isKeyDown(Keyboard.KEY_SPACE)&& (spacePressed >= 1000)) {
			FileManager.readFromFile("src/nightmare dungeon/res/highScores.txt");
			sbg.enterState(5);
		}
		if (exitActivated && Keyboard.isKeyDown(Keyboard.KEY_SPACE)&& (spacePressed >= 1000)) {
			FileManager.readFromFile("src/nightmare dungeon/res/highScores.txt");
			gc.exit();
		}
		
		//boolean logic of changing buttons
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) && prevDown == false){
			prevDown = true;
			if (playActivated){
				playActivated = false;
				settingsActivated = true;
			}
			else if (settingsActivated){
				settingsActivated = false;
				highScoresActivated = true;
			}
			else if (highScoresActivated){
				highScoresActivated = false;
				helpActivated = true;
			}
			else if (helpActivated){
				helpActivated = false;
				creditsActivated = true;
			}
			else if (creditsActivated){
				creditsActivated = false;
				exitActivated = true;
			}
			else if (exitActivated){
				exitActivated = false;
				playActivated = true;
			}
		}
		else if (!Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			prevDown = false;
		if (Keyboard.isKeyDown(Keyboard.KEY_UP) && prevUp == false){
			prevUp = true;
			if (playActivated){
				playActivated = false;
				exitActivated = true;
			}
			else if (settingsActivated){
				settingsActivated = false;
				playActivated = true;
			}
			else if (highScoresActivated){
				highScoresActivated = false;
				settingsActivated = true;
			}
			else if (helpActivated){
				helpActivated = false;
				highScoresActivated = true;
			}
			else if (creditsActivated){
				creditsActivated = false;
				helpActivated = true;
			}
			else if (exitActivated){
				exitActivated = false;
				creditsActivated = true;
			}
		}
		else if (!Keyboard.isKeyDown(Keyboard.KEY_UP))
			prevUp = false;
	}

	//the state number of the class
	public int getID() {
		return 0;
	}

}
