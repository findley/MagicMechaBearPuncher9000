package weapons;


import java.util.ArrayList;

import org.newdawn.slick.Image;

import dudes.Dude;

/**
 * Class for a weapon. Keeps track of attack pattern, image, and damage.
 * @author msalvato
 *
 */
public abstract class Weapon {
	Image weaponSprite;
	Image attackSprite;
	int attackValue;
	int attackWidth;
	int attackHeight;
	protected float attackDelay;
	ArrayList<Attack> attacks;
	Dude owner;
	
	Weapon(){
		attacks = new ArrayList<Attack>();
	}

	// to start attack
	public abstract Attack attack();
		
	// method for the movement of an attack based on current info.
	// kinda state machine-y
	protected abstract boolean updateAttack(Attack attack);
	
	public void updateAttacks(){
		for(Attack attack: attacks){
			if(!updateAttack(attack)){
				attacks.remove(attack);
			}
		}
	}
	
	
}
