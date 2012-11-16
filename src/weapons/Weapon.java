package weapons;


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
	

	// to start attack
	public abstract Attack attack(Dude dude);
		
	// method for the movement of an attack based on current info.
	// kinda state machine-y
	Attack updateAttack(Attack attack){
		return null;
	}
	
	
}
