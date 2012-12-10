package weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import dudes.Dude;
import dudes.Player;

public class Fireman extends Weapon {	
		
	public Fireman(float x, float y) {
		super();
		this.x = x;
		this.y = y;
		damage = 15;
		attackWidth = 50;
		attackHeight = 60;
		delayTime = 500;
		spriteSizeX = 64;
		spriteSizeY = 64;
		playerSizeX = 64;
		playerSizeY = 64;
		offsetX = 0;
		offsetY = 0;
		attackOffsetY = 0;
		attackOffsetX = 0;
		pushback = 0;		
		name = "Fireman";
	}
	
	@Override
	public void createGroundSprite() throws SlickException {
		groundSprite = new Image("Assets/Weapons/Fireman/GroundFireBall.png");
	}
	
	@Override
	public void init() throws SlickException {
		weaponSheet = new SpriteSheet("Assets/Weapons/FireMan/player" + ((Player)owner).playerID + "Fire.png", spriteSizeX, spriteSizeY);
		playerSheet = ((Player)owner).sprites;
		defaultSprite[0] = weaponSheet.getSprite(0, 5);
		defaultSprite[1] = weaponSheet.getSprite(0, 4);
		initAnimations();
	}
}
