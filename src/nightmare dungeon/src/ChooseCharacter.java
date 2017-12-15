import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ChooseCharacter extends BasicGameState {

    private boolean prevLeft = false; // used for taking single input from user
    private boolean prevRight = false; // used for taking single input from user
    public static int chosenCharacter; //the chosen character can be accessed from all classes

    //different states as booleans for forming the translation between them
    private boolean aliceActivated = false;
    private boolean lazarusActivated = false;
    private boolean judasActivated = false;

    private long spacePressed = 0; //To not capture the KEY_SPACE press of the main menu.

    //constructor
    public ChooseCharacter(int chooseCharacter){

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
          aliceActivated = true;
          lazarusActivated = false;
          judasActivated = false;



    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(new Image("src/nightmare dungeon/res/chooseCharacter.png"), 250, 100);

        //if a boolean is true the button is highlighted
        if (aliceActivated == false)
            g.drawImage(new Image("src/nightmare dungeon/res/chooseAlice.png"), 200, 350);
        else
            g.drawImage(new Image("src/nightmare dungeon/res/chooseAliceSelected.png"), 200, 350);

        if (lazarusActivated == false)
            g.drawImage(new Image("src/nightmare dungeon/res/chooseLazarus.png"), 450, 350);
        else
            g.drawImage(new Image("src/nightmare dungeon/res/chooseLazarusSelected.png"), 450, 350);

        if (judasActivated == false)
            g.drawImage(new Image("src/nightmare dungeon/res/chooseJudas.png"), 700, 350);
        else
            g.drawImage(new Image("src/nightmare dungeon/res/chooseJudasSelected.png"), 700, 350);
    }


    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        spacePressed += delta; //To not capture the KEY_SPACE press of the main menu.

        //input from user is taken, when space is pressed it changes the states
        if ((aliceActivated || lazarusActivated || judasActivated) && Keyboard.isKeyDown(Keyboard.KEY_SPACE) && (spacePressed >= 1000)) {
            if (aliceActivated) {
                chosenCharacter = 1;
            }
            else if (lazarusActivated)
                chosenCharacter = 2;
            else if (judasActivated)
                chosenCharacter = 3;
            spacePressed = 0;
            sbg.getState(1).init(gc,sbg);
            sbg.enterState(1);

        }
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
            sbg.enterState(0);

        //boolean logic of changing buttons
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && prevRight == false){
            prevRight = true;
            if (aliceActivated){
                aliceActivated = false;
                lazarusActivated = true;
            }
            else if (lazarusActivated){
                lazarusActivated = false;
                judasActivated = true;
            }
            else if (judasActivated) {
                judasActivated = false;
                aliceActivated = true;
            }
        }
        else if (!Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
            prevRight = false;
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && prevLeft == false){
            prevLeft = true;
            if (aliceActivated){
                aliceActivated = false;
                judasActivated = true;
            }
            else if (judasActivated){
                judasActivated = false;
                lazarusActivated = true;
            }
            else if (lazarusActivated){
                lazarusActivated = false;
                aliceActivated = true;
            }
        }
        else if (!Keyboard.isKeyDown(Keyboard.KEY_LEFT))
            prevLeft = false;
    }

    //the state number of the class
    public int getID() {
        return 7;
    }

}

