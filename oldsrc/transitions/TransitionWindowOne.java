package transitions;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import framework.*;

public class TransitionWindowOne extends TransitionWindow {
	
	
	public TransitionWindowOne(Player[] players, DialogBox dialogBox, int[] locp1, int[] locp2) {
		super(players, dialogBox, locp1, locp2);
	}

	public void displayHubBackground(Graphics g, Player player) {
		
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		super.render(container, game, g);
		
	}

	public void init(GameContainer container, StateBasedGame game,
			Player[] players) throws SlickException {
		super.init(container, game, players);
		this.bgImage = new TiledMap("Assets/TiledEditor/DanielHub.tmx");
	}
	
	public void zoom(){
		//fuck if I know how this will work
	}

	public void update(GameContainer container, StateBasedGame game,
			Player[] players, int delta) throws SlickException {
		super.update(container, game, players, delta);
		
	}


}