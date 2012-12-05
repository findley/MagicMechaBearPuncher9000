package weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import dudes.Dude;
import dudes.Player;

public class Mecha extends Weapon {

	public Mecha(Dude owner) {
		this(owner.pos[0], owner.pos[1]);
		assignOwner(owner);
	}

	public Mecha(float x, float y) {
		super();
		this.x = x;
		this.y = y;
		damage = 15;
		attackWidth = 50;
		attackHeight = 180;
		attackTime = 200;
		delayTime = 500;
		spriteSizeX = 192;
		spriteSizeY = 192;
		playerSizeX = 192;
		playerSizeY = 192;
		cooldown = 50;
		attackOffsetY = -90;
		attackOffsetX = 45;
	}
	
	@Override
	public void init() throws SlickException {
		weaponSheet = new SpriteSheet("Assets/Weapons/Mecha/player" + ((Player)owner).playerID + "Mecha.png", spriteSizeX, spriteSizeY);
		defaultSprite[0] = weaponSheet.getSprite(0, 5);
		defaultSprite[1] = weaponSheet.getSprite(0, 4);	
		initAnimations();
	}

	@Override
	public void attack() throws SlickException {
		float[] center = this.getPlayerHitBox(owner.pos[0], owner.pos[1]).getCenter();
		Rectangle hitbox;
		if (owner.isRight){
			hitbox = new Rectangle(center[0] + attackOffsetX, center[1] + attackOffsetY, attackWidth,
				attackHeight);
		}
		else {
			hitbox = new Rectangle(center[0]-attackWidth - attackOffsetX, center[1] + attackOffsetY, attackWidth,
					attackHeight);
		}
		attacks.add(new Attack(owner.isRight, hitbox, "player"));
	}

	@Override
	public void createGroundSprite() throws SlickException {
		groundSprite = new Image("Assets/Weapons/Mecha/groundWand.png");
	}
}
