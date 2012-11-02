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
	public boolean started;
	
    public GameState(int stateID){
        super();
    }
    
    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
    	
    	float[] p1WinSize = { container.getWidth() / 2, container.getHeight() };
        float[] p2WinSize = { container.getWidth() / 2, container.getHeight() };
        float[] p1WinPos = { 0, 0 };
        float[] p2WinPos = { container.getWidth() / 2, 0 };

        HashMap<String, Integer> p1Buttons = new HashMap<String, Integer>();
        p1Buttons.put("up", Input.KEY_W);
        p1Buttons.put("left", Input.KEY_A);
        p1Buttons.put("down", Input.KEY_S);
        p1Buttons.put("right", Input.KEY_D);
        p1Buttons.put("action", Input.KEY_T);
        HashMap<String, Integer> p2Buttons = new HashMap<String, Integer>();
        p2Buttons.put("up", Input.KEY_UP);
        p2Buttons.put("left", Input.KEY_LEFT);
        p2Buttons.put("down", Input.KEY_DOWN);
        p2Buttons.put("right", Input.KEY_RIGHT);
        p2Buttons.put("action", Input.KEY_PERIOD);

        players = new Player[2];
        players[0] = new Player(p1WinPos, p1WinSize, p1Buttons, 1);
        players[1] = new Player(p2WinPos, p2WinSize, p2Buttons, 2);
        started = false;

        // levelUp = new Sound("resources/music/levelup.wav");
        initHubs(container, game);
        initTransitions();
        
    }
    
    ///TODO: ADD MORE HUBS HERE
    public void initHubs(GameContainer container, StateBasedGame game) {
    	hubWindows = new ArrayList<HubWindow>();
    	try{
    		hubWindows.add(new TownWindow(players, new float[] {1,2}, new float[] {5, 10}));
    		currentHubWindow = hubWindows.get(0);
    		currentHubWindow.init(container, game, players);
    	} catch (SlickException e) {
    		e.printStackTrace();
    	}
    }
    
    //TODO: ADD MORE TRANSITION SCREENS
    public void initTransitions() {
    	transitionWindows = new ArrayList<TransitionWindow>();
    	transitionWindows.add(new TransitionWindow(players));
    	currentTransition = transitionWindows.get(0);
    }
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
    		throws SlickException {
    	
    	if (hubWindows.size() == transitionWindows.size()) {
    		currentHubWindow.render(container, game, g);
    	} else {
    		currentTransition.render(container, game, g);
    	}
    	
    }

    

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
    	
    	started = true;
    	Input input = container.getInput();

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            container.exit();
        }
        
        
        if (currentHubWindow.over()) {
    		hubWindows.remove(0);
    	}
    	
    	if (currentTransition.over()) {
    		transitionWindows.remove(0);
    	}
    	
    	if (hubWindows.size() == transitionWindows.size()) {
    		currentHubWindow.update(container, game, players, delta);
    	} else {
    		currentTransition.update(container, game, players);
    	}
        
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
    	
    	float[] p1WinSize = { container.getWidth() / 2-16, container.getHeight() };
        float[] p2WinSize = { container.getWidth() / 2-16, container.getHeight() };
        float[] p1WinPos = { 0, 0 };
        float[] p2WinPos = { container.getWidth() / 2+32, 0 };

        HashMap<String, Integer> p1Buttons = new HashMap<String, Integer>();
        p1Buttons.put("up", Input.KEY_W);
        p1Buttons.put("left", Input.KEY_A);
        p1Buttons.put("down", Input.KEY_S);
        p1Buttons.put("right", Input.KEY_D);
        p1Buttons.put("action", Input.KEY_T);
        HashMap<String, Integer> p2Buttons = new HashMap<String, Integer>();
        p2Buttons.put("up", Input.KEY_UP);
        p2Buttons.put("left", Input.KEY_LEFT);
        p2Buttons.put("down", Input.KEY_DOWN);
        p2Buttons.put("right", Input.KEY_RIGHT);
        p2Buttons.put("action", Input.KEY_PERIOD);

        players = new Player[2];
        players[0] = new Player(p1WinPos, p1WinSize, p1Buttons, 1);
        players[1] = new Player(p2WinPos, p2WinSize, p2Buttons, 2);
        started = false;

        // levelUp = new Sound("resources/music/levelup.wav");
        initHubs(container, game);
        initTransitions();
        
    }
    
    @Override
    public void leave(GameContainer container, StateBasedGame game)
            throws SlickException {
    }
	
}
