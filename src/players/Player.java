package players;

import java.util.HashMap;

import org.newdawn.slick.SpriteSheet;

import weapons.Weapon;

public class Player {
	int health;
	Weapon weapon;
	SpriteSheet sprite;
	float[] pos;
	HashMap<String, Integer> buttons;
	
	void move(String button){
		//something to do with modifying loc based on button
	}
	
	void attack(){
		this.weapon.attack(this);
	}
	
	void pickup(){
		// tries to pick up what might be nearby.
	}
}
