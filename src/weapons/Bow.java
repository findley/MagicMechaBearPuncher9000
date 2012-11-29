package weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import projectiles.Arrow;
import projectiles.FireballProjectile;
import projectiles.Projectile;

import dudes.Player;

public class Bow extends RangeWeapon {

	public Bow(float x, float y) {
		super(x, y);
	}
	
	@Override
	public void init() throws SlickException {
		weaponSheet = new SpriteSheet("Assets/Weapons/Bow/player" + ((Player)owner).playerID + "Bow.png", playerSize, playerSize);
		defaultSprite[0] = weaponSheet.getSprite(0, 5);
		defaultSprite[1] = weaponSheet.getSprite(0, 4);
		initAnimations();
	}
	
	@Override
	public void attack() throws SlickException {
		if (this.ranged){
			projectiles.add(new Arrow(owner.pos, (Player) owner, owner.isRight));
		}
	}
	
	@Override
	public void createGroundSprite() throws SlickException {
		groundSprite = new Image("Assets/Weapons/Bow/groundbow.png");
	}
}
