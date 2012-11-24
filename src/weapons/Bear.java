package weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class Bear extends Weapon {

	public Bear(float x, float y) {
		super();
		this.x = x;
		this.y = y;
		damage = 5;
		attackWidth = 100;
		attackHeight = 6;
		attackTime = 1000;
		delayTime = 500;
	}
	
	@Override
	public void init() throws SlickException {
		super.init();
		weaponSheet = new SpriteSheet("Assets/Weapons/Bear/bearwalk.png", 64, 64);
		defaultSprite[0] = weaponSheet.getSprite(0, 5);
		defaultSprite[1] = weaponSheet.getSprite(0, 4);	
		initAnimations();
		groundSprite = new Image("Assets/Weapons/Bear/bearclaw.png");
	}
	
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		float[] corner = owner.weaponLoc();
		corner[0] -= attackWidth / 2;
		corner[1] -= attackHeight / 2;
		Rectangle hitbox = new Rectangle(corner[0], corner[1], attackWidth,
				attackHeight);
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