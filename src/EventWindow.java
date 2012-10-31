import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;

public class EventWindow {
	/*
	 * Object: Window Displays a single game state, but does so by displaying it
	 * to only a section of the screen specified by parameters to the render
	 * call. This allows for the rendering of this view multiple times in
	 * different locations on the screen, or for the movement of this view
	 * around the screen.
	 */
	/**
	 * This is for minigame windows, not for the overall node structure.
	 */

	protected boolean over = false;
	protected Player player;
	/*
	 * Constructor that allows for providing of a stateID
	 */
	public EventWindow(Player player) {
		this.player = player;
	}
	
	public void displayMinigameBackground(Graphics g, Player player) {

	}

	public void render(GameContainer container, StateBasedGame game,
			Graphics g, Player player) throws SlickException {

	}

	public void init(GameContainer container, StateBasedGame game, Player player)
			throws SlickException {
	}

	public void update(GameContainer container, StateBasedGame game, int delta,
			Player player) throws SlickException {
	}

	public void enter(GameContainer container, StateBasedGame game,
			Player player) {
	}

	public void movePlayer(Input input, float moveValue, int schema) {
		// different control schema should be passed in
		if (schema == 0) {
			if (input.isKeyDown(player.getButton("left"))) {
				if (player.eventLoc[0] - moveValue > player.windowPos[0]) {
					player.eventLoc[0] -= moveValue;
				}
			}
			if (input.isKeyDown(player.getButton("right"))) {
				if (player.eventLoc[0] + player.pWidth + moveValue < player.windowPos[0]
						+ player.windowSize[0]) {
					player.eventLoc[0] += moveValue;
				}
			}
			if (input.isKeyDown(player.getButton("up"))) {
				if (player.eventLoc[1] - moveValue > player.windowPos[1]) {
					player.eventLoc[1] -= moveValue;
				}
			}
			if (input.isKeyDown(player.getButton("down"))) {
				if (player.eventLoc[1] + player.pHeight + moveValue < player.windowPos[1]
						+ player.windowSize[1]) {
					player.eventLoc[1] += moveValue;
				}
			}
		}
		if (schema == 1) {
			// grid based movement here
		}
	}

	public boolean over() {
		return over;
	}

}