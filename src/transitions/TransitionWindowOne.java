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

	private final int imageChange = 75;
	public TransitionWindowOne(Player[] players, DialogBox[] dialogBoxes, int[] locp1, int[] locp2) {
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
		this.bgImage = new TiledMap("Assets/TiledEditor/DanielHub.tmx");
	}
	
	public void zoom(){
		//fuck if I know how this will work
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
			} 
			if (input.isKeyDown(player.getButton("right"))) {
				int newPos = player.gridLoc[0] + 1;
				if (newPos + player.pWidth < bgImage.getWidth()
						* bgImage.getTileWidth()) {
					player.floatLoc[0] = -31;
					player.gridLoc[0] = newPos;
					player.isMoving = true;
					player.direction = Direction.RIGHT;
					
				}
			} 
			if (input.isKeyDown(player.getButton("up"))) {
				int newPos = player.gridLoc[1] - 1;
				if (newPos > 0) {
					player.floatLoc[1] = 31;
					player.gridLoc[1] = newPos;
					player.isMoving = true;
					player.direction = Direction.UP;
				}
			} 
			if (input.isKeyDown(player.getButton("down"))) {
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
					if (player.playerSprite == player.right1) {
						player.playerSprite = player.right2;
					} else {
						player.playerSprite = player.right1;
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
					if (player.playerSprite == player.right1) {
						player.playerSprite = player.right2;
					} else {
						player.playerSprite = player.right1;
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
	
	
	public void update(GameContainer container, StateBasedGame game,
			Player[] players, int delta) throws SlickException {
		super.update(container, game, players, delta);
		
	}


}
