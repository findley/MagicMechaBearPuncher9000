package dudes;

import org.newdawn.slick.SpriteSheet;

import weapons.Weapon;

public abstract class Dude {
	int health;
	Weapon weapon;
	SpriteSheet sprite;
	float[] pos;
	public boolean isRight;
	
	public abstract float[] weaponLoc();
	
	public void Dude(float xPos, float yPos) {
		
	}
	
	void attack(){
		this.weapon.attack(this);
	}
	
}
