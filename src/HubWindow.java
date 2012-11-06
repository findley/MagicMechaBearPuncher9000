import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

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
	protected int[] gridSize = { 32, 32 };
	public Event[] events;
	public Sprite[] sprites;
	private Event eventOne;
	private Event eventTwo;
	protected Camera[] cameras;
	private final int imageChange = 75;
	protected TiledMap bgImage;
	protected boolean[][] blocked;

	/*
	 * Constructor that allows for providing of a stateID
	 */
	public HubWindow(Player[] players, int[] locp1, int[] locp2) {
		this.players = players;
		this.players[0].gridLoc = locp1;
		this.players[1].gridLoc = locp2;
	}

	public void displayHubBackground(Graphics g, Player player) {

	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {

	}

	public void init(GameContainer container, StateBasedGame game,
			Player[] players) throws SlickException {
	}

	public void zoom() {
		// fuck if I know how this will work
	}

	public void update(GameContainer container, StateBasedGame game,
			Player[] players, int delta) throws SlickException {
	}

	public void enter(GameContainer container, StateBasedGame game,
			Player[] players) {
	}

	public void movePlayer(Input input, float moveValue, Player player,
			int delta) {
		if (!player.isMoving) {
			if (input.isKeyDown(player.getButton("left"))) {
				int newPos = player.gridLoc[0] - 1;
				if (newPos > 0) {
					if (!blocked[newPos][player.gridLoc[1]]) {
						player.floatLoc[0] = 31;
						player.gridLoc[0] = newPos;
						player.isMoving = true;
						player.direction = Direction.LEFT;
					} else {
						player.playerSprite = player.left1;
					}
				}
			} else if (input.isKeyDown(player.getButton("right"))) {
				int newPos = player.gridLoc[0] + 1;
				if (newPos + player.pWidth < bgImage.getWidth()
						* bgImage.getTileWidth()) {
					if (!blocked[newPos][player.gridLoc[1]]) {
						player.floatLoc[0] = -31;
						player.gridLoc[0] = newPos;
						player.isMoving = true;
						player.direction = Direction.RIGHT;
					} else {
						player.playerSprite = player.right1;
					}
				}
			} else if (input.isKeyDown(player.getButton("up"))) {
				int newPos = player.gridLoc[1] - 1;
				if (newPos > 0) {
					if (!blocked[player.gridLoc[0]][newPos]) {
						player.floatLoc[1] = 31;
						player.gridLoc[1] = newPos;
						player.isMoving = true;
						player.direction = Direction.UP;
					} else {
						player.playerSprite = player.up1;
					}
				}
			} else if (input.isKeyDown(player.getButton("down"))) {
				int newPos = player.gridLoc[1] + 1;
				if (newPos < bgImage.getHeight() * bgImage.getTileHeight()) {
					if (!blocked[player.gridLoc[0]][newPos]) {
						player.floatLoc[1] = -31;
						player.gridLoc[1] = newPos;
						player.isMoving = true;
						player.direction = Direction.DOWN;
					}else {
						player.playerSprite = player.down1;
					}
				}
			}
		} else {
			if (!player.isMoving) {
				player.floatLoc = new float[] { 0, 0 };
				player.isMoving = false;
				System.out.println("stop");
			} else {
				player.inMotion += delta;
				switch (player.direction) {
				case LEFT:
					player.floatLoc[0] -= (delta * gridSize[0] / player.moveDuration);
					if (player.inMotion - imageChange > 0) {
						player.inMotion -= imageChange;
						if (player.playerSprite == player.left1) {
							player.playerSprite = player.left2;
						} else {
							player.playerSprite = player.left1;
						}
					}
					if (player.floatLoc[0] < 0) {
						player.isMoving = false;
					}
					break;
				case RIGHT:
					player.floatLoc[0] += (delta * gridSize[0] / player.moveDuration);
					if (player.inMotion - imageChange > 0) {
						player.inMotion -= imageChange;
						if (player.playerSprite == player.right1) {
							player.playerSprite = player.right2;
						} else {
							player.playerSprite = player.right1;
						}
					}
					if (player.floatLoc[0] > 0) {
						player.isMoving = false;
					}
					break;
				case UP:
					player.floatLoc[1] -= (float) (delta * gridSize[1] / player.moveDuration);
					if (player.inMotion - imageChange > 0) {
						player.inMotion -= imageChange;
						if (player.playerSprite == player.up1) {
							player.playerSprite = player.up2;
						} else {
							player.playerSprite = player.up1;
						}
					}
					if (player.floatLoc[1] < 0) {
						player.isMoving = false;
					}
					break;
				case DOWN:
					player.floatLoc[1] += (float) (delta * gridSize[1] / player.moveDuration);
					if (player.inMotion - imageChange > 0) {
						player.inMotion -= imageChange;
						if (player.playerSprite == player.down1) {
							player.playerSprite = player.down2;
						} else {
							player.playerSprite = player.down1;
						}
					}
					if (player.floatLoc[1] > 0) {
						player.isMoving = false;
					}
					break;
				case NONE:
					// SHOULD NEVER HAPPEN IF IT DOES PRINT ERROR
					System.out.println("ERRORRRRORRRORRR!");
					break;
				default:
					break;
				}
			}
		}
	}

	public boolean over() {
		return over;
	}

}
