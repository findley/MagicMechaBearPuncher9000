import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class TransitionWindow {
	
	protected boolean over = false;
	protected Player[] players;
	protected boolean[] inNode = { true, true };
	public Event[] events;

	
	public TransitionWindow(Player[] players) {
		this.players = players;
	}

	public void displayHubBackground(Graphics g, Player player) {
		
	}

	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {

	}

	public void init(GameContainer container, StateBasedGame game,
			Player[] players) throws SlickException {
	}
	
	public void zoom(){
		//fuck if I know how this will work
	}

	public void update(GameContainer container, StateBasedGame game,
			Player[] players) throws SlickException {
	}

	public void enter(GameContainer container, StateBasedGame game,
			Player[] players) {
	}

/*	public void movePlayer(Input input, float moveValue, Player player) {
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
*/
	public boolean over() {
		return over;
	}
}
