import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class HubWindow {
	/*-TileMap (hopefully.  if not, Image) masterMap
	 -function to move players, update player location
	 -function to zoom in for a particular player's half of the screen
	 -function to render everything
	 -boolean over - determines when we're done in a particular hub, to
	 switch over to the transition*/

	protected boolean over = false;
	protected Player[] players;
	protected boolean[] inNode = { true, true };
	protected Image bgImage;
	public Event[] events;

	/*
	 * Constructor that allows for providing of a stateID
	 */
	public HubWindow(Player[] players) {
		this.players = players;
	}

	public void displayMinigameBackground(Graphics g, Player player) {
		// do we want the +21? Probably, but easy to fix
		// hacky image instead of tileset deal
		g.drawImage(bgImage.getSubImage(1000, 1000, 24 * 32, 16 * 32)
				.getScaledCopy(590, 720), (int) (players[0].windowPos[0]),
				(int) (players[0].windowPos[1]));
		g.drawImage(bgImage.getSubImage(1000, 1000, 24 * 32, 16 * 32)
				.getScaledCopy(590, 720), (int) (players[1].windowPos[0] + 10),
				(int) (players[1].windowPos[1]));
	}

	public void render(GameContainer container, StateBasedGame game,
			Graphics g, Player[] player) throws SlickException {

	}

	public void init(GameContainer container, StateBasedGame game,
			Player[] players) throws SlickException {
	}
	
	public void zoom(){
		//fuck if I know how this will work
	}

	public void update(GameContainer container, StateBasedGame game, int delta,
			Player[] players) throws SlickException {
	}

	public void enter(GameContainer container, StateBasedGame game,
			Player[] players) {
	}

	public void movePlayer(Input input, float moveValue, Player player) {
		if (input.isKeyDown(player.getButton("left"))) {
			float newPos = player.hubLoc[0] - moveValue;
			if (newPos > players[0].windowPos[0]) {
				player.hubLoc[0] -= moveValue;
			}
		}
		if (input.isKeyDown(player.getButton("right"))) {
			if (player.hubLoc[0] + player.pWidth
					+ moveValue < players[0].windowPos[0]
					+ player.windowSize[0]) {
				player.hubLoc[0] += moveValue;
			}
		}
		if (input.isKeyDown(player.getButton("up"))) {
			if (player.hubLoc[1] - moveValue > player.windowPos[1]) {
				player.hubLoc[1] -= moveValue;
			}
		}
		if (input.isKeyDown(player.getButton("down"))) {
			if (player.hubLoc[1] + player.pHeight
					+ moveValue < player.windowPos[1]
					+ player.windowSize[1]) {
				player.hubLoc[1] += moveValue;
			}
		}
	}

	public boolean over() {
		return over;
	}

}
