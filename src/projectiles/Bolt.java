package projectiles;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import dudes.Dude;
import dudes.Player;

import weapons.Attack;

public class Bolt extends Projectile{

	public Bolt(float[] pos, Player owner, boolean isRight) throws SlickException {
		super(pos, owner, isRight);
		this.pos[1]-= 200;
		this.moveSpeed = 10.0f;
		this.damage = 10;
		this.sprite = new Image("Assets/Weapons/Wand/newBolt.png");
		if (isRight) {
			this.pos[0] += 64;
		} else {
			this.pos[0]-=64;
		}
		this.hitbox = new Rectangle(pos[0], pos[1], sprite.getWidth(), sprite.getHeight());
	}
	
	public void move() {
		 this.pos[1] += this.moveSpeed;
	 }
}
