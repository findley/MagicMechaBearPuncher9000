package weapons;

import org.newdawn.slick.Image;
import players.Player;

/**
 * Class for a weapon. Keeps track of attack pattern, image, and damage.
 * @author msalvato
 *
 */
public class Weapon {
	Image sprite;
	int attackValue;

	// to start attack
	public void attack(Player p){
		
	}
	
	// method for the movement of an attack based on current info.
	// kinda state machine-y
	Attack updateAttack(Attack attack){
		return null;
	}
	
	
}
