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
	public int damage;
	int attackWidth;
	int attackHeight;
	public float attackTime;
	public ArrayList<Attack> attacks;
	Dude owner;
	
	Weapon(){
		attacks = new ArrayList<Attack>();
	}

	// to start attack
	public abstract void attack();
		
	// method for the movement of an attack based on current info.
	// kinda state machine-y
	protected abstract boolean updateAttack(Attack attack);
	
	public void updateAttacks(){
		ArrayList<Attack> removals = new ArrayList<Attack>();
		for(int i = 0; i < attacks.size(); i++){
			if(!updateAttack(attacks.get(i))){
				removals.add(attacks.get(i));
			}
		}
		for(Attack attack:removals){
			attacks.remove(attack);
		}
	}
	
	
}
