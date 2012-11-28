package weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import projectiles.FireballProjectile;
import projectiles.Projectile;

import dudes.Player;

public class RangeWeapon extends Weapon {

	public RangeWeapon(float x, float y) {
		super();
		this.x = x;
		this.y = y;
		damage = 0;
		attackWidth = 100;
		attackHeight = 6;
		attackTime = 200;
		delayTime = 500;
		playerSize = 64;
		this.ranged = true;
	}
	
	@Override
	public void init() throws SlickException {
		super.init();
		weaponSheet = new SpriteSheet("Assets/Weapons/Fireball/player" + ((Player)owner).playerID + "Fireball.png", playerSize, playerSize);
		defaultSprite[0] = weaponSheet.getSprite(0, 5);
		defaultSprite[1] = weaponSheet.getSprite(0, 4);
		initAnimations();
	}
	
	@Override
	public void attack() throws SlickException {
		if (this.ranged){
			projectiles.add(new FireballProjectile(owner.pos, (Player) owner, owner.isRight));
		}
		
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
	
	@Override
	public void createGroundSprite() throws SlickException {
		groundSprite = new Image("Assets/Weapons/Fireball/GroundFireBall.png");
	}
}
