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
    private Image[] playerSprite;
    public Rectangle collisionRect;
    public String kindOfWindow;
    public int pWidth = 32;
    public int pHeight = 32;
    public float[] hubLoc;
    public float[] eventLoc;
    //playerNum is 0 or 1
    public int playerNum;
    
    public Player(float[] startWindowPos, float[] startWindowSize, HashMap<String, Integer> playerButtons, int playerNum) {
        windowPos = startWindowPos;
        windowSize = startWindowSize;
    	buttons = playerButtons;
        collisionRect = new Rectangle(10, 10, 30, 30);
        this.playerNum = playerNum;
        try {
        	//later incorporating array of sprites
            this.playerSprite[0] = new Image("Assets/Player"+playerNum+".png");
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
        g.drawImage(playerSprite[0], x, y);
    }

    public Integer getButton(String command) {
        return this.buttons.get(command);
    }
}
