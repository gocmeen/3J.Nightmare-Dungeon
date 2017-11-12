

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState{
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

	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(new Image("src/nightmare dungeon/res/nightmareDungeon.png"), 250, 100);
		
		//if a boolean is true the button is highlighted
		if (playActivated == false)
			g.drawImage(new Image("src/nightmare dungeon/res/playUnactive.png"), 400, 200);
		else
			g.drawImage(new Image("src/nightmare dungeon/res/playActivated.png"), 400, 200);
		
		if (settingsActivated == false)
			g.drawImage(new Image("src/nightmare dungeon/res/settingsUnactivated.png"), 400, 300);
		else
			g.drawImage(new Image("src/nightmare dungeon/res/settingsActivated.png"), 400, 300);
		
		if (highScoresActivated == false)
			g.drawImage(new Image("src/nightmare dungeon/res/highScoreUnactive.png"), 400, 400);
		else
			g.drawImage(new Image("src/nightmare dungeon/res/highScoreActivated.png"), 400, 400);
		
		if (helpActivated == false)
			g.drawImage(new Image("src/nightmare dungeon/res/helpUnactive.png"), 400, 500);
		else
			g.drawImage(new Image("src/nightmare dungeon/res/helpActivated.png"), 400, 500);
		
		if (creditsActivated == false)
			g.drawImage(new Image("src/nightmare dungeon/res/creditsUnactive.png"), 400, 600);
		else
			g.drawImage(new Image("src/nightmare dungeon/res/creditsActivated.png"), 400, 600);
		
		if (exitActivated == false)
			g.drawImage(new Image("src/nightmare dungeon/res/exitUnactive.png"), 400, 700);
		else
			g.drawImage(new Image("src/nightmare dungeon/res/exitActivated.png"), 400, 700);
	}
	
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		//input from user is taken, when space is pressed it changes the states
		if (settingsActivated && Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			sbg.enterState(2);
		if (playActivated && Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			sbg.enterState(1);
		if (highScoresActivated && Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			sbg.enterState(3);
		if (helpActivated && Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			sbg.enterState(4);
		if (creditsActivated && Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			sbg.enterState(5);
		if (exitActivated && Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			gc.exit();
		
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
