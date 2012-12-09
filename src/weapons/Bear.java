package weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import dudes.Dude;
import dudes.Player;

public class Bear extends Weapon {
	
	public Bear(Dude owner) {
		this(owner.pos[0], owner.pos[1]);
		assignOwner(owner);
	}
	public Bear(float x, float y) {
		super();
		this.x = x;
		this.y = y;
		damage = 15;
		attackWidth = 50;
		attackHeight = 60;
		delayTime = 500;
		spriteSizeX = 128;
		spriteSizeY = 128;
		playerSizeX = 40;
		playerSizeY = 64;
		offsetX = 44;
		offsetY = 44;
		attackOffsetY = -30;
		attackOffsetX = 20;
		
		name = "Bear";
	}
	
	@Override
	public void init() throws SlickException {
		weaponSheet = new SpriteSheet("Assets/Weapons/Bear/player" + ((Player)owner).playerID + "bigbear.png", spriteSizeX, spriteSizeY);
		defaultSprite[0] = weaponSheet.getSprite(0, 5);
		defaultSprite[1] = weaponSheet.getSprite(0, 4);	
		initAnimations();
	}
	
	@Override
	public void createGroundSprite() throws SlickException {
		groundSprite = new Image("Assets/Weapons/Bear/bearclaw.png");
	}
}
