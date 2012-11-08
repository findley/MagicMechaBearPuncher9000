package framework;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;


public class TransitionWindow {
	
	protected boolean over = false;
	protected Player[] players;
	protected DialogBox[] dialogBoxes;
	protected boolean[] inNode = { true, true };
	protected int[] gridSize = { 32, 32 };
	
	protected String[] miniNames;
	public EventWindow[] events;
	public EventWindow[] currentEvents = {null, null};
	
	protected Camera camera;
	private final int imageChange = 75;
	protected TiledMap bgImage;
	protected boolean[][] blocked;
	protected String[][] miniArray;
	
	protected int[] mylocp1;
	protected int[] mylocp2;

	
	public TransitionWindow(Player[] players, DialogBox[] dialogBoxes, int[] locp1, int[] locp2) {
		this.players = players;
		this.dialogBoxes = dialogBoxes;
		this.mylocp1 = locp1;
		this.mylocp2 = locp2;
	}

	public void displayHubBackground(Graphics g, Player player) {
		
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		for (int i = 0; i < players.length; i++) {
			//camera.drawMap();
			//camera.translateGraphics();
			players[i].render(container, game, g, players[i].gridLoc[0]
			* 32 + players[i].floatLoc[0], players[i].gridLoc[1]
			* 32 + players[i].floatLoc[1]);
			
		}
		
		container.getGraphics().resetTransform();
		//float leftLoc = players[0].windowPos[0] + players[0].windowSize[0];
		//float rightLoc = players[1].windowPos[0] - players[0].windowSize[0];
		//g.setColor(Color.black);
		//g.fillRect(leftLoc, (float) 0, rightLoc, (float) container.getHeight());
	}

	public void init(GameContainer container, StateBasedGame game,
			Player[] players) throws SlickException {
		this.players[0].gridLoc = this.mylocp1;
		this.players[1].gridLoc = this.mylocp2;
	}
	
	public void zoom(){
		//fuck if I know how this will work
	}

	public void update(GameContainer container, StateBasedGame game,
			Player[] players, int delta) throws SlickException {
		
		Input input = container.getInput();	
		
		for (int i = 0; i < players.length; i++) {
			movePlayer(input, 5, players[i], delta);
		}
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
					player.floatLoc[0] = 31;
					player.gridLoc[0] = newPos;
					player.isMoving = true;
					player.direction = Direction.LEFT;
					
				}
			} else if (input.isKeyDown(player.getButton("right"))) {
				int newPos = player.gridLoc[0] + 1;
				if (newPos + player.pWidth < bgImage.getWidth()
						* bgImage.getTileWidth()) {
					player.floatLoc[0] = -31;
					player.gridLoc[0] = newPos;
					player.isMoving = true;
					player.direction = Direction.RIGHT;
					
				}
			} else if (input.isKeyDown(player.getButton("up"))) {
				int newPos = player.gridLoc[1] - 1;
				if (newPos > 0) {
					player.floatLoc[1] = 31;
					player.gridLoc[1] = newPos;
					player.isMoving = true;
					player.direction = Direction.UP;
				}
			} else if (input.isKeyDown(player.getButton("down"))) {
				int newPos = player.gridLoc[1] + 1;
				if (newPos < bgImage.getHeight() * bgImage.getTileHeight()) {
					player.floatLoc[1] = -31;
					player.gridLoc[1] = newPos;
					player.isMoving = true;
					player.direction = Direction.DOWN;					
				}
			}
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
					player.floatLoc = new float[] { 0f, 0f };
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
					player.floatLoc = new float[] { 0f, 0f };
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
					player.floatLoc = new float[] { 0f, 0f };
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
					player.floatLoc = new float[] { 0f, 0f };
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
	
	
	public boolean over() {
		return over;
	}
}
