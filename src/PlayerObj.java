

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import java.util.*;

public class PlayerObj {
	private float posX;
	private float posY;
	private float speed;
	private Control keys = null;
	public Image sprite;
	private Rectangle boundingBox;
	public final int sizeX = 32;
	public final int sizeY = 32;
	private int playerNum;
	private int health;
	private static final int healthNum = 5;
	private int numPoints = 0;
	public float[] windowPos;
    public float[] windowSize;
    private HashMap<String, Integer> buttons;
		
	//32x32 Pixel Sprite
	public PlayerObj(float x, float y, Control controls, Image image, int playerNum, HashMap<String, Integer> playerButtons) {
		posX = x;
		posY = y;
		speed = 3.5f;
		health = healthNum;
		this.keys = controls;
		this.playerNum = playerNum;
		sprite = image;
		buttons = playerButtons;
	}
	
	public void update(GameContainer container, int delta, UnitsList gameState) throws SlickException {
		Input input = container.getInput();
		    
			if (input.isKeyDown(keys.LEFT) && !gameState.collideWithUnitAt(posX-speed, posY, this)) {
				if(posX > 0)
					posX -= speed;
			}
			if (input.isKeyDown(keys.RIGHT) && !gameState.collideWithUnitAt(posX+speed, posY, this)) {
				if(posX < MainGame.GAME_WIDTH - sizeX)
					posX += speed;
			}
			if (input.isKeyDown(keys.UP) && !gameState.collideWithUnitAt(posX, posY-speed, this)) {
				if(posY > 0)
					posY -= speed;
			}
	
			if (input.isKeyDown(keys.DOWN) && !gameState.collideWithUnitAt(posX, posY+speed, this)) {
				if(posY < MainGame.GAME_HEIGHT - sizeY)
					posY += speed;
			}

	}
	
	public void draw(GameContainer container, Graphics g) {
		this.sprite.draw(posX, posY, 1f);
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g, float x, float y) {

        boundingBox.setX(x);
        boundingBox.setY(y);
        g.drawImage(sprite, x, y);
    }
	
	public Integer getButton(String command) {
        return this.buttons.get(command);
    }
	
	public Rectangle getBoundingBox() {
		return new Rectangle(posX, posY, sizeX, sizeY);
	}
	
	public void addPoints(int numPoints){
		this.numPoints += numPoints;
	}
	
	public int getPoints(){
		return this.numPoints;
	}
	
	@Override
    public String toString(){
		return "Player " + playerNum;
	}
}
