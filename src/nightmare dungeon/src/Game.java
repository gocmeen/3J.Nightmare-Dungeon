

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame{
	
	public static final String gameName = "RPG game";
	public static final int startMenu = 0;
	public static final int arak= 1;
	public static final int settings = 2;
	public static final int highScores = 3;
	public static final int help = 4;
	public static final int credits = 5;
	public static final int help2 = 6;

	public Game(String gameName)throws SlickException {
		super(gameName);
		Settings x = new Settings(settings);

		this.addState(new Menu(startMenu));
		this.addState(new GameManager(arak,x.isSoundOn(),x.isMusicOn()));
		this.addState(x);
		this.addState(new HighScores(highScores));
		this.addState(new Help(help));
		this.addState(new Credits(credits));
		this.addState(new Help2(help2));

	}

	public void initStatesList(GameContainer gc) throws SlickException {
		
		this.getState(startMenu).init(gc, this);
		this.getState(arak).init(gc, this);
		this.getState(settings).init(gc, this);
		this.getState(highScores).init(gc, this);
		this.getState(help).init(gc, this);
		this.getState(credits).init(gc, this);
		this.getState(help2).init(gc, this);
		this.enterState(startMenu);
	}
	
	public static void main(String[] args) {
		AppGameContainer agc;
		try{
			agc = new AppGameContainer(new Game(gameName));
			agc.setTargetFrameRate(60);
			agc.setVSync(true);
			agc.setDisplayMode(1366, 850, false);
			agc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}

	}

	

}
