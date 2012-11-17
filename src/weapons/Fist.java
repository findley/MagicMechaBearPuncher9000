package weapons;

import org.newdawn.slick.geom.Rectangle;

import dudes.Dude;

public class Fist extends Weapon {
	
	Fist(Dude owner) {
		this.owner = owner;
		weaponSprite = null;
		attackSprite = null;
		attackValue = 5;
		attackWidth = 6;
		attackHeight = 6;
	}

	@Override
	public Attack attack() {
		float[] center = owner.weaponLoc();
		center[0] -= attackWidth / 2;
		center[1] -= attackHeight / 2;
		Rectangle hitbox = new Rectangle(center[0], center[1], attackWidth,
				attackHeight);
		return new Attack(attackSprite, owner.isRight, hitbox, "player");
	}

	@Override
	protected boolean updateAttack(Attack attack) {
		if(owner.isAttacking) {
			return true;
		}
		else{
			return false;
		}
	}
}
