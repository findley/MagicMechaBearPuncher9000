package weapons;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import projectiles.Bolt;
import projectiles.Projectile;

import dudes.Player;

public class Wizard extends RangeWeapon {

	public Animation lightning;
	
	public Wizard(float x, float y) {
		super(x, y);
		this.x = x;
		this.y = y;
		damage = 15;
		attackWidth = 50;
		attackHeight = 180;
		delayTime = 500;
		spriteSizeX = 64;
		spriteSizeY = 64;
		playerSizeX = 64;
		playerSizeY = 64;
		offsetX = 0;
		attackOffsetY = -90;
		attackOffsetX = 45;
		
		name = "Wizard";
	}
	
	@Override
	public void init() throws SlickException {
		SpriteSheet ln = new SpriteSheet("Assets/Weapons/Wand/lightning.png", 100, 500);
		lightning = new Animation(ln, 0, 0, 9, 0, true, 100, true);
		weaponSheet = new SpriteSheet("Assets/Weapons/Wand/player" + ((Player)owner).playerID + "Wizard.png", spriteSizeX, spriteSizeY);
		defaultSprite[0] = weaponSheet.getSprite(0, 5);
		defaultSprite[1] = weaponSheet.getSprite(0, 4);
		initAnimations();
	}
	
	@Override
	public void attack() throws SlickException {
		lightning.draw();
		lightning.start();
	}
	
	@Override
	public void createGroundSprite() throws SlickException {
		groundSprite = new Image("Assets/Weapons/Wand/groundWand.png");
	}
}