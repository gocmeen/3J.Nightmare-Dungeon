package rpg.game.tutorial;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState{
	private boolean prevDown = false;
	private boolean prevUp = false;
	private boolean playActivated = true;
	private boolean settingsActivated = false;
	private boolean highScoresActivated = false;
	private boolean helpActivated = false;
	private boolean creditsActivated = false;
	private boolean exitActivated = false;

	public Menu(int startmenu) {
	}


	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(new Image("res/nightmareDungeon.png"), 250, 100);
		
		if (playActivated == false)
			g.drawImage(new Image("res/playUnactive.png"), 400, 200);
		else
			g.drawImage(new Image("res/playActivated.png"), 400, 200);
		
		if (settingsActivated == false)
			g.drawImage(new Image("res/settingsUnactivated.png"), 400, 300);
		else
			g.drawImage(new Image("res/settingsActivated.png"), 400, 300);
		
		if (highScoresActivated == false)
			g.drawImage(new Image("res/highScoreUnactive.png"), 400, 400);
		else
			g.drawImage(new Image("res/highScoreActivated.png"), 400, 400);
		
		if (helpActivated == false)
			g.drawImage(new Image("res/helpUnactive.png"), 400, 500);
		else
			g.drawImage(new Image("res/helpActivated.png"), 400, 500);
		
		if (creditsActivated == false)
			g.drawImage(new Image("res/creditsUnactive.png"), 400, 600);
		else
			g.drawImage(new Image("res/creditsActivated.png"), 400, 600);
		
		if (exitActivated == false)
			g.drawImage(new Image("res/exitUnactive.png"), 400, 700);
		else
			g.drawImage(new Image("res/exitActivated.png"), 400, 700);
	}
	
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (settingsActivated && Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			sbg.enterState(2);
		if (highScoresActivated && Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			sbg.enterState(3);
		if (helpActivated && Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			sbg.enterState(4);
		if (creditsActivated && Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			sbg.enterState(5);
		if (exitActivated && Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			gc.exit();
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

	
	public int getID() {
		return 0;
	}

}
