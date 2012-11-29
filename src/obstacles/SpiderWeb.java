package obstacles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class SpiderWeb extends Obstacle{
	
	public SpiderWeb(float[] pos) throws SlickException {
		super(pos);
		this.sprite = new Image("Assets/JewelsAndMisc/spiderweb.png");
		this.hitbox = new Rectangle(pos[0], pos[1], sprite.getWidth(), sprite.getHeight());
		this.speedEffect = 0.5f;
	}
	
}
