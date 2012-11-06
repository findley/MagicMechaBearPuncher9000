import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;


public class Player {
/*-hubLoc - player location within the hub screen
-eventLoc - player location within event or transition screen
-image views (front, left, back, right sprites, etc)
-collisionRectangle
-kindOfWindow - which sort of window we're actively displaying*/
	public float[] windowPos;
	public float[] windowSize;
    private HashMap<String, Integer> buttons;
    public Image playerSprite;
    public Image left1;
    public Image right1;
    public Image up1;
    public Image down1;
    public Image left2;
    public Image right2;
    public Image up2;
    public Image down2;
    public Rectangle collisionRect;
    public String kindOfWindow;
    public int pWidth = 32;
    public int pHeight = 32;
    public float[] eventLoc;
    
    //Grid movement Code
    //gridLoc is now the grid location of the player.
    //floatLoc is the distance from a gridLoc.
    public int[] gridLoc;
    public float[] floatLoc;
    public boolean isMoving;
    public Direction direction;
    //In milliseconds
    public final int moveDuration = 250;
    public int inMotion = 0;
    
    //playerNum is 0 or 1
    public int playerNum;
    
    public Player(float[] startWindowPos, float[] startWindowSize, HashMap<String, Integer> playerButtons, int playerNum) {
        windowPos = startWindowPos;
        windowSize = startWindowSize;
    	buttons = playerButtons;
        collisionRect = new Rectangle(0,0,pWidth,pHeight);
        this.playerNum = playerNum;
        isMoving = false;
        floatLoc = new float[] {0, 0};
        
        try {
        	//later incorporating array of sprites
            this.down1 = new Image("Assets/players/player"+playerNum+"/forwardWalk1.png");
            this.up1 = new Image("Assets/players/player"+playerNum+"/backWalk1.png");
            this.right1 = new Image("Assets/players/player"+playerNum+"/walkRight1.png");
            this.left1 = new Image("Assets/players/player"+playerNum+"/walkLeft1.png");
            this.down2 = new Image("Assets/players/player"+playerNum+"/forwardWalk2.png");
            this.up2 = new Image("Assets/players/player"+playerNum+"/backWalk2.png");
            this.right2 = new Image("Assets/players/player"+playerNum+"/walkRight2.png");
            this.left2 = new Image("Assets/players/player"+playerNum+"/walkLeft2.png");

            playerSprite = this.down1;
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }

    public void render(GameContainer container, StateBasedGame game,
            Graphics g, float x, float y) {

        collisionRect.setX(x);
        collisionRect.setY(y);
        //later incorporating array of sprites
        g.drawImage(playerSprite, x, y);
    }

    public Integer getButton(String command) {
        return this.buttons.get(command);
    }
}
