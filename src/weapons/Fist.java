package weapons;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import dudes.Dude;
import dudes.Player;

public class Fist extends Weapon {
	
	public Fist(Dude owner) {
		this(owner.pos[0], owner.pos[1]);
		assignOwner(owner);
	}
	
	public Fist(float x, float y) {
		super();
		damage = 5;
		attackWidth = 30;
		attackHeight = 6;
		attackTime = 200;
		delayTime = 500;
	}

	@Override
	public void init() throws SlickException {
		super.init();
		weaponSheet = new SpriteSheet("Assets/Weapons/Fist/player" + ((Player)owner).playerID + "Fist.png", 64, 64);
		defaultSprite[0] = weaponSheet.getSprite(0, 5);
		defaultSprite[1] = weaponSheet.getSprite(0, 4);
		initAnimations();
	}
	
	@Override
	public void attack() {
		float[] corner = owner.weaponLoc();
		//corner[0] -= attackWidth / 2;
		//corner[1] -= attackHeight / 2;
		Rectangle hitbox;
		if (owner.isRight){
			hitbox = new Rectangle(corner[0], corner[1], attackWidth,
				attackHeight);
		}
		else {
			hitbox = new Rectangle(corner[0]-attackWidth, corner[1], attackWidth,
					attackHeight);
		}
		attacks.add(new Attack(owner.isRight, hitbox, "player"));
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
