package transitions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import framework.DialogBox;
import framework.Player;
import framework.TransitionWindow;

public class TransitionWindowZero extends TransitionWindow{
	Image bgIm;

	public TransitionWindowZero(Player[] players, DialogBox[] dialogBoxes, int[] locp1, int[] locp2) {
		super(players, dialogBoxes, locp1, locp2);
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
		this.bgIm = new Image("Assets/Black.jpg");
	}

	public void update(GameContainer container, StateBasedGame game,
			Player[] players, int delta) throws SlickException {
		
	}
	
}
