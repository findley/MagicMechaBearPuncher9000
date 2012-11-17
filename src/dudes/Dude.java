package dudes;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import weapons.Weapon;

public abstract class Dude {
	public int health;
	public Weapon weapon;
	public SpriteSheet sprite;
	public float[] pos = new float[2];
	public boolean isRight;
	public float moveSpeed;
	
	public abstract float[] weaponLoc();
	
	public Dude() {
		
	}
	
	public void attack(){
		this.weapon.attack(this);
	}
	
	public void render(Graphics g) throws SlickException {
		g.drawImage(new Image("Assets/npcs/daughterFront.png"), pos[0], pos[1]);
	}
}
