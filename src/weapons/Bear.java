package weapons;

import org.newdawn.slick.Animation;
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
		pushback = 64;
		
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
	
	public void initAnimations() {
		// flinch left
		anims[0] = new Animation(weaponSheet, 0, 0, 3, 0, true, 500, true);
		anims[0].setLooping(false);
		// flinch right
		anims[1] = new Animation(weaponSheet, 0, 1, 3, 1, true, 500, true);
		anims[1].setLooping(false);

		// punch left
		anims[2] = new Animation(weaponSheet, 0, 2, 8, 2, true, 50, true);
		anims[2].setLooping(false);
		// punch right
		anims[3] = new Animation(weaponSheet, 0, 3, 8, 3, true, 50, true);
		anims[3].setLooping(false);

		// walk left
		anims[4] = new Animation(weaponSheet, 0, 4, 3, 4, true, 150, true);
		// walk right
		anims[5] = new Animation(weaponSheet, 0, 5, 3, 5, true, 150, true);
	}
}
