package projectiles;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import weapons.Attack;

public abstract class Projectile {
	public Image sprite;
	public int damage;
    public float[] pos = new float[2];
    public Shape hitbox;
    public float moveSpeed;
	public boolean hasHit;
	public Projectile(float[] pos) {
		this.pos = pos;
		hitbox = new Rectangle(pos[0], pos[1], sprite.getWidth(), sprite.getHeight());
		this.hasHit = false;		
	}
	
	 public void render(Graphics g) throws SlickException {
		 	sprite.draw(pos[0], pos[1]);
	 }
	 
	 public void move() {
		 this.pos[0] += this.moveSpeed;		 
	 }
}
