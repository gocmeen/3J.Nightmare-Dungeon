

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;

public class Game extends StateBasedGame{
	
	public static final String gameName = "RPG game";
	public static final int startMenu = 0;
	public static final int game= 1;
	public static final int settings = 2;
	public static final int highScores = 3;
	public static final int help = 4;
	public static final int credits = 5;
	public static final int help2 = 6;
	public static final int chooseCharacter = 7;
	public static final int pauseMenu = 8;
	public static final int endGame = 9;


	public Game(String gameName)throws SlickException ,IOException{
		super(gameName);
		Settings x = new Settings(settings);

		this.addState(new Menu(startMenu));
		this.addState(new GamePresenter(game,x.isSoundOn(),x.isMusicOn()));
		this.addState(x);
		this.addState(new HighScores(highScores));
		this.addState(new Help(help));
		this.addState(new Credits(credits));
		this.addState(new Help2(help2));
		this.addState(new ChooseCharacter(chooseCharacter));
		this.addState(new PauseMenu(pauseMenu));
		this.addState(new EndGame(endGame));

	}

	public void initStatesList(GameContainer gc) throws SlickException {
		
		this.getState(startMenu).init(gc, this);
		//this.getState(arak).init(gc, this);
		this.getState(settings).init(gc, this);
		this.getState(highScores).init(gc, this);
		this.getState(help).init(gc, this);
		this.getState(credits).init(gc, this);
		this.getState(help2).init(gc, this);
		this.getState(chooseCharacter).init(gc, this);
		this.getState(pauseMenu).init(gc, this);
		this.getState(endGame).init(gc, this);
		this.enterState(startMenu);
	}
	
	public static void main(String[] args)throws IOException {
		AppGameContainer agc;
		try{
			agc = new AppGameContainer(new Game(gameName));
			agc.setTargetFrameRate(60);
			agc.setVSync(true);
			agc.setShowFPS(false);
			agc.setDisplayMode(1380, 780, false);
			agc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}

	}

	

}
