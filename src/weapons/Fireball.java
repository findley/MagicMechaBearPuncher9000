package weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import projectiles.FireballProjectile;
import projectiles.Projectile;

import dudes.Player;

public class Fireball extends RangeWeapon {

	public Fireball(float x, float y) {
		super(x, y);
		//cooldown = 0.0f;
	}
	
	@Override
	public void init() throws SlickException {
		weaponSheet = new SpriteSheet("Assets/Weapons/Fireball/player" + ((Player)owner).playerID + "Fireball.png", playerSizeX, playerSizeY);
		defaultSprite[0] = weaponSheet.getSprite(0, 5);
		defaultSprite[1] = weaponSheet.getSprite(0, 4);
		initAnimations();
	}
	
	@Override
	public void attack() throws SlickException {
		if (this.ranged){
			projectiles.add(new FireballProjectile(owner.pos, (Player) owner, owner.isRight));
		}
	}
	
	@Override
	public void createGroundSprite() throws SlickException {
		groundSprite = new Image("Assets/Weapons/Fireball/GroundFireBall.png");
	}
}
