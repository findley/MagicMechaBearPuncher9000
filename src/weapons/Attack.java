package weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
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
	Image image;
	boolean isRight;
	public Shape hitbox;
	private Shape realHitbox;
	// monster, player
	String source;
	
	public int attackTime;
	public float duration;
	

	public Attack(Boolean isRight, Shape hitbox,
			String source) {
		super();
		this.isRight = isRight;
		this.realHitbox = hitbox;
		this.source = source;
		this.attackTime = 0;
		this.duration = 0;
		
		this.hitbox = new Rectangle(0,0,0,0);
	}
	
	public Attack(Boolean isRight, Shape hitbox,
			String source, int attackTiming) {
		super();
		this.isRight = isRight;
		this.realHitbox = hitbox;
		this.source = source;
		this.attackTime = attackTiming;
		this.duration = 0;
		
		this.hitbox = new Rectangle(0,0,0,0);
	}
	
	public void update(float delta) {
		float nextFrame = duration + delta;
		
		if (duration <= attackTime && nextFrame > attackTime) {
			this.hitbox = this.realHitbox;
		} else {
			System.out.println("nothing");
			this.hitbox = new Rectangle(0,0,0,0);
		}
		duration += delta;
	}
}
