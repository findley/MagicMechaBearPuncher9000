package weapons;


import org.newdawn.slick.geom.Shape;
import java.util.ArrayList;

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
	
	public Sound attackSound;
	
	//public int playerSize;
	public int spriteSizeX;
	public int spriteSizeY;
	public int playerSizeX;
	public int playerSizeY;
	public int offsetX;
	public int offsetY;
	
	public int kind; //0 for town, 1 for forest, 2 for castle
	
	public boolean isFist = false;
	public int damage;
	public int attackWidth;
	
	public float cooldown;
	public int attackHeight;
	public float attackTime;
	public int delayTime;
	public ArrayList<Attack> attacks;
	public Dude owner;
	public float x;
	public float y;
	public boolean ranged;
	public ArrayList<Projectile> projectiles;
	
	public Weapon() {
		attacks = new ArrayList<Attack>();
		projectiles = new ArrayList<Projectile>();
		cooldown = 0;
		spriteSizeX = 64;
		spriteSizeY = 64;
		playerSizeX = 64;
		playerSizeY = 64;
		offsetX = 0;
		offsetY = 0;
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
	
	public abstract void attack() throws SlickException;
		
	public void assignOwner(Dude owner) {
		this.owner = owner;
	}
	
	public void drop() {
		this.x = owner.pos[0];
		this.y = owner.pos[1];
		this.owner = null;
		
	}
	
	public Shape getPlayerHitBox(float ownerX, float ownerY) {
		return new Rectangle(ownerX + offsetX, ownerY + offsetY, playerSizeX, playerSizeY);
	}
	
	public Shape getHitBox() {
		System.out.println(groundSprite);
		return new Rectangle(x, y, groundSprite.getWidth(), groundSprite.getHeight());
	}
	
	public void Draw() {
		if (this.owner == null) {
			groundSprite.draw(x, y);
		} else {
		}
	}
	
	// method for the movement of an attack based on current info.
	// kinda state machine-y
	protected boolean updateAttack(Attack attack) {
		if(owner.isAttacking) {
			return true;
		}
		else{
			return false;
		}
	}
	
	public void updateAttacks(){
		ArrayList<Attack> removals = new ArrayList<Attack>();
		for(int i = 0; i < attacks.size(); i++){
			if(!updateAttack(attacks.get(i))){
				removals.add(attacks.get(i));
			}
		}
		for(Attack attack:removals){
			attacks.remove(attack);
		}
	}
	
	public void createGroundSprite() throws SlickException {
		groundSprite = null;
	}
}
