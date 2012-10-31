import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
//import java.util.Random;
import java.util.HashMap;
import java.util.Stack;
import java.util.TreeSet;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState{
/*-has list of hubWindows
-variable currentHubWindow -which hub we're currently in
-variable eventWindow - which event we're currently in
-list of transitionWindows (explained later)
-queue of strings to be displayed - related to what dialogue/text is
shown during a particular 'event' (also explained later)*/
    public Player[] players;
	public ArrayList<HubWindow> hubWindows;
	public ArrayList<TransitionWindow> transitionWindows;
	public HubWindow currentHubWindow;
	public EventWindow eventWindow;
	public TransitionWindow currentTransition;
	public ArrayList<String> textBoxes;
	
    public GameState(int stateID){
        super();
    }
    
    
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
    		throws SlickException {
    	
    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
    }

    protected void triggerMinigame(GameContainer container,
            StateBasedGame game, Player player, EventWindow minigame)
            throws SlickException {
    }

    @Override
    public int getID() {
    	return 1;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game)
            throws SlickException {
 
    }
    
    @Override
    public void leave(GameContainer container, StateBasedGame game)
            throws SlickException {
    }
	
}
