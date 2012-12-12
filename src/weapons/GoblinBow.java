package weapons;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import projectiles.Arrow;
import projectiles.RedArrow;

import dudes.Dude;
import dudes.Player;

public class GoblinBow extends Weapon{
	
	public GoblinBow(Dude owner) {
		this(owner.pos[0], owner.pos[1], owner.kind);
		assignOwner(owner);
	}

	public GoblinBow(float x, float y, int k) {
		super();
		groundSprite = null;
		this.x = x;
		this.y = y;
		damage = 0;
		attackWidth = 100;
		attackHeight = 6;
		delayTime = 500;
		kind = k;
		ranged = true;
	}
	
	@Override
	public void init() throws SlickException {
		if(kind==0){
			weaponSheet = new SpriteSheet("Assets/Weapons/GoblinArcher/goblinarcher.png", spriteSizeX, spriteSizeY);
		} else if(kind==1){
			weaponSheet = new SpriteSheet("Assets/Weapons/GoblinArcher/purpleGoblinarcher.png", spriteSizeX, spriteSizeY);
		} else{
			weaponSheet = new SpriteSheet("Assets/Weapons/GoblinArcher/redGoblinarcher.png", spriteSizeX, spriteSizeY);
		}
		defaultSprite[0] = weaponSheet.getSprite(0, 5);
		defaultSprite[1] = weaponSheet.getSprite(0, 4);
		initAnimations();
	}
	
	@Override
	public void attack() throws SlickException {
		if (this.ranged){
			if (kind == 0) {
				projectiles.add(new Arrow(owner.pos, null, owner.isRight));
			} else {
				projectiles.add(new RedArrow(owner.pos, null, owner.isRight));				
			}
		}
	}

	@Override
	public void initAnimations(){
		// flinch left
		anims[0] = new Animation(weaponSheet, 0, 4, 3, 4, true, 80, true);
		anims[0].setLooping(false);
		// flinch right
		anims[1] = new Animation(weaponSheet, 0, 5, 3, 5, true, 80, true);
		anims[1].setLooping(false);

		// punch left
		anims[2] = new Animation(weaponSheet, 0, 0, 2, 0, true, 80, true);
		anims[2].setLooping(false);
		// punch right
		anims[3] = new Animation(weaponSheet, 0, 1, 2, 1, true, 80, true);
		anims[3].setLooping(false);

		// walk left
		anims[4] = new Animation(weaponSheet, 0, 2, 3, 2, true, 80, true);
		// walk right
		anims[5] = new Animation(weaponSheet, 0, 3, 3, 3, true, 80, true);
		
		// die left
		anims[6] = new Animation(weaponSheet, 3, 6, 3, 6, true, 200, true);
		anims[6].setLooping(false);
		// die right
		anims[7] = new Animation(weaponSheet, 3, 7, 3, 7, true, 200, true);
		anims[7].setLooping(false);

	}
}
