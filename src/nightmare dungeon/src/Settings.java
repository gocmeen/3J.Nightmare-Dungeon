

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Settings extends BasicGameState{
	private boolean prevUp = false;
	private boolean prevDown = false;
	private boolean prevSwitch = false;
	private boolean effectSoundOn = true;
	private boolean effectActive = true;
	private boolean musicSoundOn = true;
	private boolean musicActive = false;
	
	public Settings(int settings){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

	}

	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(new Image("res/settingsTitle.png"), 300, 150);
		g.drawString("In-Game Effect Sound", 400, 300);
		g.drawString("In-Game Music Sound", 400, 500);
		g.drawString("Press Esc to go back", 50, 800);
		if (effectSoundOn && effectActive)       							//Neden off olarak ba�l�yor?
			g.drawImage(new Image("res/switchOnActive.png"), 425, 350);
		else if (!effectSoundOn && effectActive)
			g.drawImage(new Image("res/switchOffActive.png"), 425, 350);
		if (musicSoundOn && musicActive)
			g.drawImage(new Image("res/switchOnActive.png"), 425, 550);
		else if (!musicSoundOn && musicActive)
			g.drawImage(new Image("res/switchOffActive.png"), 425, 550);
		if (effectSoundOn && !effectActive)
			g.drawImage(new Image("res/switchOn.png"), 425, 350);
		else if (!effectSoundOn && !effectActive)
			g.drawImage(new Image("res/switchOff.png"), 425, 350);
		if (musicSoundOn && !musicActive)
			g.drawImage(new Image("res/switchOn.png"), 425, 550);
		else if (!musicSoundOn && !musicActive)
			g.drawImage(new Image("res/switchOff.png"), 425, 550);

	}


	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			sbg.enterState(0);
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && prevSwitch == false){
			prevSwitch = true;
			if (effectSoundOn && effectActive)
				effectSoundOn = false;
			else if (!effectSoundOn && effectActive)
				effectSoundOn = true;
			if (musicSoundOn && musicActive)
				musicSoundOn = false;
			else if (!musicSoundOn && musicActive)
				musicSoundOn = true;
		}
		else if (!Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			prevSwitch = false;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_UP) && prevUp == false){
			prevUp = true;
			if (effectActive){
				effectActive = false;
				musicActive = true;
			}
			else if (musicActive){
				musicActive = false;
				effectActive = true;
			}
		}
		else if (!Keyboard.isKeyDown(Keyboard.KEY_UP))
			prevUp = false;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) && prevDown == false){
			prevDown = true;
			if (effectActive){
				effectActive = false;
				musicActive = true;
			}
			else if (musicActive){
				musicActive = false;
				effectActive = true;
			}
		}
		else if (!Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			prevDown = false;
	}

	
	public int getID() {
		return 2;
	}

}
