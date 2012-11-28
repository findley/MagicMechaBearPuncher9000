package projectiles;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import dudes.Dude;
import dudes.Player;

import weapons.Attack;

public abstract class Projectile {
	public Image sprite;
	public int damage;
    public float[] pos = new float[2];
    public Shape hitbox;
    public float moveSpeed;
	public boolean hasHit;
	public Player owner;
	public boolean isRight;
	
	public Projectile(float[] pos, Player owner, boolean isRight) {
		this.pos[0] = pos[0]+32;
		this.pos[1] = pos[1] + 32;
		this.hasHit = false;		
		this.owner = owner;
		this.isRight = isRight;
	}
	
	 public void render(Graphics g) throws SlickException {
		 	sprite.draw(pos[0], pos[1]);
	 }
	 
	 public void move() {
		 if (isRight) {
			 this.pos[0] += this.moveSpeed;		 
		 } else {
			 this.pos[0] -= this.moveSpeed;
		 }
	 }
	 
	 public Shape getHitbox() {
			return new Rectangle(pos[0], pos[1], sprite.getWidth(), sprite.getHeight());
	 }
}
