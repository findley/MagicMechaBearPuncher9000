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
	protected int[] gridSize = {32, 32};
	public Event[] events;
	private Event eventOne;
	private Event eventTwo;
	protected Camera[] cameras;	
	private final int imageChange = 75;
	protected TiledMap bgImage;
	
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
			
			
	}

	public void init(GameContainer container, StateBasedGame game,
			Player[] players) throws SlickException {
	}
	
	public void zoom(){
		//fuck if I know how this will work
	}

	public void update(GameContainer container, StateBasedGame game,
			Player[] players, int delta) throws SlickException {
	}

	public void enter(GameContainer container, StateBasedGame game,
			Player[] players) {
	}

	public void movePlayer(Input input, float moveValue, Player player, int delta){
		if (!player.isMoving) {
			if (input.isKeyDown(player.getButton("left"))) {
				float newPos = player.hubLoc[0]-gridSize[0];
				if (newPos  > 0) {
					player.destination = new float[] {newPos, player.hubLoc[1]};
					player.isMoving = true;
					player.direction = Direction.LEFT;
					
				}
			}
			if (input.isKeyDown(player.getButton("right"))) {
				float newPos = player.hubLoc[0]+gridSize[0];
				if (newPos + player.pWidth < bgImage.getWidth()*bgImage.getTileWidth()) {
					player.destination = new float[] {newPos, player.hubLoc[1]};
					player.isMoving = true;
					player.direction = Direction.RIGHT;
				}
			}
			if (input.isKeyDown(player.getButton("up"))) {
				float newPos = player.hubLoc[1]-gridSize[1];
				if (newPos  > 0) {
					player.destination = new float[] {player.hubLoc[0], newPos};
					player.isMoving = true;
					player.direction = Direction.UP;
				}
			}
			if (input.isKeyDown(player.getButton("down"))) {
				float newPos = player.hubLoc[1]+gridSize[1];
				if (newPos < bgImage.getHeight()*bgImage.getTileHeight()) {
					player.destination = new float[] {player.hubLoc[0], newPos};
					player.isMoving = true;
					player.direction = Direction.DOWN;
				}
			}
		} else {
			if ((int) player.hubLoc[0]/gridSize[0] == (int) player.destination[0]/gridSize[0] &&
				(int) player.hubLoc[1]/gridSize[1] == (int) player.destination[1]/gridSize[1] ) {
				player.destination = null;
				player.isMoving = false;
				player.inMotion = 0;
			} else {
				player.inMotion += delta;
				switch(player.direction) {
				case LEFT:
					player.hubLoc[0]-=(delta*gridSize[0]/player.moveDuration);
					if (player.inMotion - imageChange > 0) {
						player.inMotion -= imageChange;
						if (player.playerSprite == player.left1) {
							player.playerSprite = player.left2;
						} else {
							player.playerSprite = player.left1;
						}
					}
					break;
				case RIGHT:
					player.hubLoc[0]+=(delta*gridSize[0]/player.moveDuration);
					if (player.inMotion - imageChange > 0) {
						player.inMotion -= imageChange;
						if (player.playerSprite == player.right1) {
							player.playerSprite = player.right2;
						} else {
							player.playerSprite = player.right1;
						}
					}
					break;
				case UP:
					player.hubLoc[1]-=(float)(delta*gridSize[1]/player.moveDuration);
					if (player.inMotion - imageChange > 0) {
						player.inMotion -= imageChange;
						if (player.playerSprite == player.up1) {
							player.playerSprite = player.up2;
						} else {
							player.playerSprite = player.up1;
						}
					}
					break;
				case DOWN:
					player.hubLoc[1]+=(float)(delta*gridSize[1]/player.moveDuration);
					if (player.inMotion - imageChange > 0) {
						player.inMotion -= imageChange;
						if (player.playerSprite == player.down1) {
							player.playerSprite = player.down2;
						} else {
							player.playerSprite = player.down1;
						}
					}
					break;
				case NONE:
					//SHOULD NEVER HAPPEN IF IT DOES PRINT ERROR
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
