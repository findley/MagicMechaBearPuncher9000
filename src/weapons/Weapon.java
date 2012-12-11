package weapons;


import org.newdawn.slick.geom.Shape;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import projectiles.Projectile;

import dudes.Dude;

/**
 * Class for a weapon. Keeps track of attack pattern, image, and damage.
 * @author msalvato
 *
 */
public abstract class Weapon {
	public Image groundSprite;
	public SpriteSheet weaponSheet;
	public SpriteSheet playerSheet;
	public Animation[] anims = new Animation[8];
	public Animation[] dieanims = new Animation[2];
	// animations are flinch L, flinch R, punch L, punch R, walk L, walk R
	public Image[] defaultSprite = new Image[2];
	// for left and right
	
	public int spriteSizeX;
	public int spriteSizeY;
	public int playerSizeX;
	public int playerSizeY;
	public int offsetX;
	public int offsetY;
	public int attackOffsetX;
	public int attackOffsetY;
	
	public int attackTiming;
	
    protected static HashMap<String, Sound> attackNoises = new HashMap<String, Sound>();
	
	public int kind; //0 for town, 1 for forest, 2 for castle
	
	public boolean isFist = false;
	public int damage;
	public int attackWidth;
	
	public int itemTimer;
	public int attackHeight;
	public int delayTime;
	public Attack attack;
	public Dude owner;
	public float x;
	public float y;
	public boolean ranged;
	public float pushback = 0;
	public ArrayList<Projectile> projectiles;
	
	public String name;
	
	public Weapon() {
		itemTimer = 15000;
		projectiles = new ArrayList<Projectile>();
		spriteSizeX = 64;
		spriteSizeY = 64;
		playerSizeX = 64;
		playerSizeY = 64;
		offsetX = 0;
		offsetY = 0;
		
		//Default placeholder for weapons' names. Should be overridden in subclasses.
		name = "weapon";
		
		attackTiming = 0;
	}
	
	public abstract void init() throws SlickException;
	
	public void initAnimations() {
		// flinch left
		anims[0] = new Animation(weaponSheet, 0, 0, 3, 0, true, 500, true);
		anims[0].setLooping(false);
		// flinch right
		anims[1] = new Animation(weaponSheet, 0, 1, 3, 1, true, 500, true);
		anims[1].setLooping(false);

		// punch left
		anims[2] = new Animation(weaponSheet, 0, 2, 3, 2, true, 60, true);
		anims[2].setLooping(false);
		// punch right
		anims[3] = new Animation(weaponSheet, 0, 3, 3, 3, true, 60, true);
		anims[3].setLooping(false);

		// walk left
		anims[4] = new Animation(weaponSheet, 0, 4, 3, 4, true, 80, true);
		// walk right
		anims[5] = new Animation(weaponSheet, 0, 5, 3, 5, true, 80, true);
	}

	// to start attack
	
	public void attack() throws SlickException {
		attack = new Attack(owner.isRight, getAttackHitBox(), "player", attackTiming);
		if (name.equals("Fireman")) {
			return;
		}
		try {
			attackNoises.get(name).play();
		} catch (Exception e) {
		}
	}
	
	public void assignOwner(Dude owner) {
		this.owner = owner;
	}
	
	public void drop() {
		this.x = owner.pos[0];
		this.y = owner.pos[1];
		this.owner = null;
		
	}
	
	public Shape getAttackHitBox(){
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
		return hitbox;
	}
	
	public Shape getPlayerHitBox(float ownerX, float ownerY) {
		return new Rectangle(ownerX + offsetX, ownerY + offsetY, playerSizeX, playerSizeY);
	}
	
	public Shape getHitBox() {
		return new Rectangle(x, y, groundSprite.getWidth(), groundSprite.getHeight());
	}
	
	public void draw() {
		if (this.owner == null) {
			groundSprite.draw(x, y);
		} else {
		}
	}
	
	public void createGroundSprite() throws SlickException {
		groundSprite = null;
	}
	
	public static void initSoundEffects() throws SlickException {
		attackNoises.put("Fist", new Sound("Assets/Sound/punch.wav"));
		attackNoises.put("Wizard", new Sound("Assets/Sound/attackWizard.wav"));
		attackNoises.put("Bear", new Sound("Assets/Sound/punch.wav"));
		attackNoises.put("Diglet", new Sound("Assets/Sound/punch.wav"));
		attackNoises.put("Mecha", new Sound("Assets/Sound/punch.wav"));
	}
}
