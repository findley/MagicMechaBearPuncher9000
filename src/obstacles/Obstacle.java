package obstacles;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import dudes.Player;

public abstract class Obstacle {
	public Image sprite;
    public float[] pos = new float[2];
    public Shape hitbox;
    public float speedEffect;
	
	public Obstacle(float[] pos) {
		this.pos[0] = pos[0]+32;
		this.pos[1] = pos[1] + 32;
	}
	
	 public void render(Graphics g) throws SlickException {
		 	sprite.draw(pos[0], pos[1]);
	 }
	 
	 public Shape getHitbox() {
			return new Rectangle(pos[0], pos[1], sprite.getWidth(), sprite.getHeight());
	 }
	 
	 public void effect(Player p){}
	 
	 public void endEffect(Player p){}
	 
}
