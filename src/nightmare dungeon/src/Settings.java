

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Settings extends BasicGameState{
	private boolean prevUp = false; // used for taking single input from user
	private boolean prevDown = false; // used for taking single input from user
	
	//different states as booleans for forming the translation between them
	private boolean prevSwitch = false;
	private boolean effectSoundOn = true;
	private boolean effectActive = true;
	private boolean musicSoundOn = true;
	private boolean musicActive = false;
	
	//constructor
	public Settings(int settings)
	{

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{

	}


	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(new Image("res/settingsTitle.png"), 300, 150);
		g.drawString("In-Game Effect Sound", 400, 300);
		g.drawString("In-Game Music Sound", 400, 500);
		g.drawString("Press Esc to go back", 50, 800);
		
		//if a boolean is true the switch is highlighted
		if (effectSoundOn && effectActive)       							
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
		
		//input from user is taken, when space is pressed it changes the states
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			sbg.enterState(0);
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && prevSwitch == false){
			prevSwitch = true;
			
			//boolean logic of changing switches
			if (effectSoundOn && effectActive)
			{
				effectSoundOn = false;
				SoundManager.sound_on = false;
			}
			else if (!effectSoundOn && effectActive)
			{
				effectSoundOn = true;
				SoundManager.sound_on = true;

			}
			if (musicSoundOn && musicActive)
			{
				musicSoundOn = false;
				SoundManager.music_on = false;
				SoundManager.playMusic();
			}
			else if (!musicSoundOn && musicActive)
			{
				musicSoundOn = true;
				SoundManager.music_on = true;
				SoundManager.playMusic();
			}
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

	//the state number of the class
	public int getID() {
		return 2;
	}

	//getters
	public boolean isSoundOn()
	{
		return effectSoundOn;
	}

	public boolean isMusicOn()
	{
		return musicSoundOn;
	}

}

