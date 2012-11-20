package weapons;


import org.newdawn.slick.geom.Shape;
import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import dudes.Dude;

/**
 * Class for a weapon. Keeps track of attack pattern, image, and damage.
 * @author msalvato
 *
 */
public abstract class Weapon {
	public Image groundSprite;
	public SpriteSheet weaponSheet;
	public Animation[] anims = new Animation[6];
	// animations are flinch L, flinch R, punch L, punch R, walk L, walk R
	public Image defaultSprite;
	
	public int damage;
	int attackWidth;
	int attackHeight;
	public float attackTime;
	public int delayTime;
	public ArrayList<Attack> attacks;
	Dude owner;
	public float x;
	public float y;
	
	public Weapon() {
		attacks = new ArrayList<Attack>();
	}
	
	public void init() throws SlickException {
		
	}
	
	public void initAnimations() {
		// flinch left
		anims[0] = new Animation(weaponSheet, 0, 0, 3, 0, true, 40, true);
		anims[0].setLooping(false);
		// flinch right
		anims[1] = new Animation(weaponSheet, 0, 1, 3, 1, true, 40, true);
		anims[1].setLooping(false);

		// punch left
		anims[2] = new Animation(weaponSheet, 0, 2, 3, 2, true, 40, true);
		anims[2].setLooping(false);
		// punch right
		anims[3] = new Animation(weaponSheet, 0, 3, 3, 3, true, 40, true);
		anims[3].setLooping(false);

		// walk right
		anims[4] = new Animation(weaponSheet, 0, 4, 3, 4, true, 80, true);
		// walk left
		anims[5] = new Animation(weaponSheet, 0, 5, 3, 5, true, 80, true);
	}

	// to start attack
	
	public abstract void attack();
		
	public void assignOwner(Dude owner) {
		this.owner = owner;
	}
	
	public void drop() {
		this.x = owner.pos[0];
		this.y = owner.pos[1];
		this.owner = null;
		
	}
	
	public Shape getHitBox() {
		return new Rectangle(x, y, groundSprite.getWidth(), groundSprite.getHeight());
	}
	
	public void Draw() {
		if (this.owner == null) {
			System.out.println(x);
			System.out.println(y);
			groundSprite.draw(x, y);
		} else {
		}
	}
	// method for the movement of an attack based on current info.
	// kinda state machine-y
	protected abstract boolean updateAttack(Attack attack);
	
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
}
