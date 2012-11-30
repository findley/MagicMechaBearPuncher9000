package weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import projectiles.Arrow;
import projectiles.SpearProjectile;

import dudes.Dude;
import dudes.Player;

public class Spear extends RangeWeapon {

	public Spear(Dude owner) {
		this(owner.pos[0], owner.pos[1]);
		assignOwner(owner);
	}
	
	public Spear(float x, float y) {
		super(x, y);
		spriteSizeX = 134;
		spriteSizeY = 134;
		cooldown = 80.0f;
		offsetX = 32;
	}
	
	@Override
	public void init() throws SlickException {
		weaponSheet = new SpriteSheet("Assets/Weapons/Spear/player" + ((Player)owner).playerID + "spear.png", spriteSizeX, spriteSizeY);
		defaultSprite[0] = weaponSheet.getSprite(0, 5);
		defaultSprite[1] = weaponSheet.getSprite(0, 4);
		initAnimations();
	}
	
	@Override
	public void attack() throws SlickException {
		if (this.ranged){
			projectiles.add(new SpearProjectile(owner.pos, (Player) owner, owner.isRight));
		}
	}
	
	@Override
	public void createGroundSprite() throws SlickException {
		groundSprite = new Image("Assets/Weapons/Spear/spear.png");
	}
}
