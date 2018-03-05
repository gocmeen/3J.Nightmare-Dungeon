

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
	private boolean prevSwitch;
	private boolean effectSoundOn;
	private boolean effectActive;
	private boolean musicSoundOn;
	private boolean musicActive;

	private long spacePressed = 0; //To not capture the KEY_SPACE press of the main menu.

	//constructor
	public Settings(int settings)
	{

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		prevSwitch = false;
		effectSoundOn = false;
		effectActive = true;
		musicSoundOn = false;
		musicActive = false;
	}


	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(new Image("src/nightmare dungeon/res/settingsTitle.png"), 500, 150);
		g.drawString("In-Game Effect Sound", 600, 300);
		g.drawString("In-Game Music Sound", 600, 500);
		g.drawString("Press Esc to go back", 50, 700);

		//if a boolean is true the switch is highlighted
		if (effectSoundOn && effectActive)
			g.drawImage(new Image("src/nightmare dungeon/res/switchOnActive.png"), 600, 350);
		else if (!effectSoundOn && effectActive)
			g.drawImage(new Image("src/nightmare dungeon/res/switchOffActive.png"), 600, 350);
		if (musicSoundOn && musicActive)
			g.drawImage(new Image("src/nightmare dungeon/res/switchOnActive.png"), 600, 550);
		else if (!musicSoundOn && musicActive)
			g.drawImage(new Image("src/nightmare dungeon/res/switchOffActive.png"), 600, 550);
		if (effectSoundOn && !effectActive)
			g.drawImage(new Image("src/nightmare dungeon/res/switchOn.png"), 600, 350);
		else if (!effectSoundOn && !effectActive)
			g.drawImage(new Image("src/nightmare dungeon/res/switchOff.png"), 600, 350);
		if (musicSoundOn && !musicActive)
			g.drawImage(new Image("src/nightmare dungeon/res/switchOn.png"), 600, 550);
		else if (!musicSoundOn && !musicActive)
			g.drawImage(new Image("src/nightmare dungeon/res/switchOff.png"), 600, 550);

	}


	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		spacePressed += delta; //To not capture the KEY_SPACE press of the main menu.

		//input from user is taken, when space is pressed it changes the states
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			sbg.enterState(0);
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && prevSwitch == false && (spacePressed >= 1000)){
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

