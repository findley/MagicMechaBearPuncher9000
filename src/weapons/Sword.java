package weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class Sword extends Weapon {

	public Sword(float x, float y) {
		super();
		this.x = x;
		this.y = y;
		damage = 5;
		attackWidth = 100;
		attackHeight = 6;
		attackTime = 200;
		delayTime = 500;
	}
	
	@Override
	public void init() throws SlickException {
		super.init();
		//weaponSheet = new SpriteSheet("Assets/Weapons/Sword/sheet.png", 64, 64);
		//defaultSprite = weaponSheet.getSprite(0, 0);		
		//initAnimations();
		weaponSheet = new SpriteSheet("Assets/Weapons/Fist/player0Fist.png", 64, 64);
		defaultSprite[0] = weaponSheet.getSprite(0, 5);
		defaultSprite[1] = weaponSheet.getSprite(0, 4);
		initAnimations();
		groundSprite = new Image("Assets/Weapons/Sword/sword.png");
	}
	
	@Override
	public void attack() {
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
