

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Help2 extends BasicGameState{
	
	//constructor
	public Help2(int help2){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//images are drawn
		g.drawImage(new Image("src/nightmare dungeon/res/help.png"), 565, 100);
		g.drawString("Press           to go back", 50, 700);
		g.drawImage(new Image("src/nightmare dungeon/res/leftButton.png"), 50, 655);
		g.drawString("This is you:", 400, 250);
		g.drawImage(new Image("src/nightmare dungeon/res/judasDown.png"), 410, 300);
		g.drawImage(new Image("src/nightmare dungeon/res/lazarusDown.png"), 440, 300);
		g.drawImage(new Image("src/nightmare dungeon/res/Alice.png"), 470, 300);
		g.drawString("Stat Upgrades:", 800, 250);
		g.drawImage(new Image("src/nightmare dungeon/res/item1.png"), 830, 300);
		g.drawImage(new Image("src/nightmare dungeon/res/item2.png"), 860, 300);
		g.drawString("Minions:", 400, 450);
		g.drawImage(new Image("src/nightmare dungeon/res/monster1.png"), 390, 525);
		g.drawImage(new Image("src/nightmare dungeon/res/Judas.png"), 440, 485);
		g.drawString("Active Items:", 800, 450);
		g.drawImage(new Image("src/nightmare dungeon/res/activeItem.png"), 850,485);
	}

	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//state changes when left button is pressed to return to the previous help menu
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			sbg.enterState(4);
	}

	//the state number of the class
	public int getID() {
		return 6;
	}

}
