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
	public Event[] events;

	/*
	 * Constructor that allows for providing of a stateID
	 */
	public HubWindow(Player[] players, float[] locp1, float[] locp2) {
		this.players = players;
		locp1[0] = locp1[0]*this.players[0].pHeight;
		locp1[1] = locp1[1]*this.players[0].pHeight;
		locp2[0] = locp2[0]*this.players[1].pHeight;
		locp2[1] = locp2[1]*this.players[1].pHeight;
		
		this.players[0].hubLoc = locp1;
		this.players[1].hubLoc = locp2;
	}

	public void displayHubBackground(Graphics g, Player player) {
		
	}

	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {
			System.out.println("aaaa");
			for (Player x: players) {
				System.out.println(x.hubLoc[0]);
				x.render(container, game, g, x.hubLoc[0], x.hubLoc[1]);
			}
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
