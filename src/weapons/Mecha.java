package weapons;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

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
		delayTime = 500;
		spriteSizeX = 192;
		spriteSizeY = 192;
		playerSizeX = 80;
		playerSizeY = 192;
		offsetX = 50;
		attackOffsetY = -90;
		attackOffsetX = 45;
		
		name = "Mecha";
	}
	
	@Override
	public void init() throws SlickException {
		weaponSheet = new SpriteSheet("Assets/Weapons/Mecha/player" + ((Player)owner).playerID + "Mecha.png", spriteSizeX, spriteSizeY);
		defaultSprite[0] = weaponSheet.getSprite(0, 5);
		defaultSprite[1] = weaponSheet.getSprite(0, 4);	
		initAnimations();
	}

	@Override
	public void createGroundSprite() throws SlickException {
		groundSprite = new Image("Assets/Weapons/Mecha/cog.png");
	}
	
	@Override
	public Shape getPlayerHitBox(float ownerX, float ownerY) {
		if (this.owner.isRight) {
			offsetX = 50;
		} else {
			offsetX = 60;
		}
		return new Rectangle(ownerX + offsetX, ownerY + offsetY, playerSizeX, playerSizeY);						
	}
	
	public void initAnimations() {
		// flinch left
		anims[0] = new Animation(weaponSheet, 0, 0, 3, 0, true, 500, true);
		anims[0].setLooping(false);
		// flinch right
		anims[1] = new Animation(weaponSheet, 0, 1, 3, 1, true, 500, true);
		anims[1].setLooping(false);

		// punch left
		anims[2] = new Animation(weaponSheet, 0, 2, 3, 2, true, 100, true);
		anims[2].setLooping(false);
		// punch right
		anims[3] = new Animation(weaponSheet, 0, 3, 3, 3, true, 100, true);
		anims[3].setLooping(false);

		// walk left
		anims[4] = new Animation(weaponSheet, 0, 4, 3, 4, true, 150, true);
		// walk right
		anims[5] = new Animation(weaponSheet, 0, 5, 3, 5, true, 150, true);
	}
}
