package weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import projectiles.FireballProjectile;
import projectiles.Projectile;

import dudes.Dude;
import dudes.Player;

public class RangeWeapon extends Weapon {

	public RangeWeapon(Dude owner) {
		this(owner.pos[0], owner.pos[1]);
		assignOwner(owner);
	}
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
	}
	
	@Override
	public void attack() throws SlickException {
		
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
		groundSprite = null;
	}
}
