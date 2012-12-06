package weapons;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import dudes.Dude;
import dudes.Player;

public class Diglet extends Weapon {
	
	public Diglet(Dude owner) {
		this(owner.pos[0], owner.pos[1]);
		assignOwner(owner);
	}

	public Diglet(float x, float y) {
		super();
		this.x = x;
		this.y = y;
		damage = 15;
		attackWidth = 64;
		attackHeight = 92;
		delayTime = 0;
		spriteSizeX = 64;
		spriteSizeY = 92;
		playerSizeX = 64;
		playerSizeY = 92;
		attackOffsetY = -92/2;
		attackOffsetX = -64/2;
	}
	@Override
	public void init() throws SlickException {
		weaponSheet = new SpriteSheet("Assets/Weapons/Diglet/player0Diglet.png", spriteSizeX, spriteSizeY);
		//weaponSheet = new SpriteSheet("Assets/Weapons/Diglet/player" + ((Player)owner).playerID + "bigbear.png", spriteSizeX, spriteSizeY);
		defaultSprite[0] = weaponSheet.getSprite(21, 0);
		defaultSprite[1] = weaponSheet.getSprite(22, 0);	
		initAnimations();
	}
	
	@Override
	public void createGroundSprite() throws SlickException {
		groundSprite = new Image("Assets/Weapons/Crossbow/crossbow.png");
	}
	
	@Override
	public void initAnimations() {
		// flinch left
		anims[0] = new Animation(weaponSheet, 0, 0, 0, 0, true, 500, true);
		anims[0].setLooping(false);
		// flinch right
		anims[1] = new Animation(weaponSheet, 0, 0, 0, 0, true, 500, true);
		anims[1].setLooping(false);

		// punch left
		anims[2] = new Animation(weaponSheet, 0, 0, 20, 0, true, 35, true);
		anims[2].setLooping(false);
		// punch right
		anims[3] = new Animation(weaponSheet, 0, 0, 20, 0, true, 35, true);
		anims[3].setLooping(false);

		// walk left
		anims[4] = new Animation(weaponSheet, 21, 0, 22, 0, true, 150, true);
		// walk right
		anims[5] = new Animation(weaponSheet, 21, 0, 22, 0, true, 150, true);
	}
}
