package weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import dudes.Dude;
import dudes.Player;

public class Mini extends Weapon {

	public Mini(Dude owner) {
		this(owner.pos[0], owner.pos[1]);
		assignOwner(owner);
	}
	
	public Mini(float x, float y) {
		super();
		this.x = x;
		this.y = y;
		damage = 5;
		attackWidth = 30;
		attackHeight = 6;
		attackTime = 200;
		delayTime = 500;
		playerSizeX = 32;
		playerSizeY = 32;
	}

	@Override
	public void init() throws SlickException {
		weaponSheet = new SpriteSheet("Assets/Weapons/Mini/player" + ((Player)owner).playerID + "Mini.png", playerSizeX, playerSizeY);
		playerSheet = ((Player)owner).sprites;
		defaultSprite[0] = weaponSheet.getSprite(0, 5);
		defaultSprite[1] = weaponSheet.getSprite(0, 4);
		initAnimations();
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
	
	@Override
	public void createGroundSprite() throws SlickException {
		groundSprite = new Image("Assets/Weapons/Mini/potionBottle.png");
	}
}
