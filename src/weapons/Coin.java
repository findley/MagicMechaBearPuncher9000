package weapons;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Coin {
	
	HashMap<String,Integer> points = new HashMap<String,Integer>();
	Image sprite;
	int value;
	public float[] pos;
	
	public Coin(String color, float[] position) throws SlickException{
		initPoints();
		value = points.get(color);
		pos = position;
		sprite = new Image("Assets/JewelsAndMisc/jewel"+color+".png");
	}
	
	private void initPoints(){
		points.put("yellow", 100);
		points.put("red", 200);
		points.put("blue", 300);
		points.put("green", 400);
		points.put("purple", 500);
	}

	public void Draw() {
		sprite.draw(pos[0], pos[1]);
	}
	
	public Shape getHitBox() {
		return new Rectangle(pos[0], pos[1], 16, 16);
	}
}
