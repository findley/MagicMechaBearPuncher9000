package weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 * Simply a holder for the attack box of a weapons attack
 * Mostly used to keep the location.
 * Passed into a weapons updateAttack function to return new location.
 * Tracked by higher state for collision detection.
 * @author msalvato
 *
 */
public class Attack {
	Weapon type;
	Image image;
	boolean isRight;
	Shape hitbox;
	
	// monster, player
	String source;

	public Attack(Weapon type, Image image, Boolean isRight, Shape hitbox,
			String source) {
		super();
		this.type = type;
		this.image = image;
		this.isRight = isRight;
		this.hitbox = hitbox;
		this.source = source;
	}
	
}
