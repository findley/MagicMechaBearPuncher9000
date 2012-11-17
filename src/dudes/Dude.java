package dudes;

import org.newdawn.slick.Color;
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
	public boolean isAttacking;
	public float moveSpeed;
	public int maxHealth;
	public Color healthFill;
	
	public abstract float[] weaponLoc();
	
	public Dude() {
		
	}
	
	public void attack(){
		this.weapon.attack();
	}
	
	public void render(Graphics g) throws SlickException {
		new Image("Assets/npcs/daughterFront.png").draw(pos[0], pos[1], 2);
		
		//Render a health bar for the Dude
				int offset = -10;
				float x = pos[0];
				float y = pos[1]+offset;
				int width = 100;
				int height = 10;
				int padding = 2;
				int healthRemaining = width*health/maxHealth;
				g.drawRect(x-padding-width/2, y-padding-height/2, width+padding*2, height+padding*2);
				g.setColor(healthFill);
				g.fillRect(x-width/2, y-height/2, healthRemaining, height);
	}
}
