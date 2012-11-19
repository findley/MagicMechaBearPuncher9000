package dudes;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import weapons.Fist;
import weapons.KnightKnife;

public class Knight extends Monster {

	private Animation[] anims = new Animation[8];

	public Knight(float xPos, float yPos) {
		maxHealth = 37;
		health = maxHealth;
		pos[0] = xPos;
		pos[1] = yPos;
		isRight = false;
		moveSpeed = 4;
		healthFill = new Color(Color.red);
		attackTime = 0;
		hitbox = new Rectangle(pos[0], pos[1], 64, 64);
		this.weapon = new KnightKnife(this);
		try {
			this.init();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void init() throws SlickException {
		sprites = new SpriteSheet("Assets/Enemies/enemy_1_sheet.png", 64, 64);
		spriteIndex[0] = 0;
		spriteIndex[1] = 0;
		aiDelay = 2000;
		initAnimation();
	}

	public void initAnimation() {
		// walk left
		anims[0] = new Animation(sprites, 0, 0, 3, 0, true, 10, true);
		// attack left
		anims[1] = new Animation(sprites, 0, 1, 2, 1, true, 10, true);
		// flinch left
		anims[2] = new Animation(sprites, 0, 2, 3, 2, true, 10, true);
		// die left
		anims[3] = new Animation(sprites, 0, 3, 2, 3, true, 10, true);
		// walk right
		anims[4] = new Animation(sprites, 0, 4, 3, 4, true, 10, true);
		// attack right
		anims[5] = new Animation(sprites, 0, 5, 2, 5, true, 10, true);
		// flinch right
		anims[6] = new Animation(sprites, 0, 6, 3, 6, true, 10, true);
		// die right
		anims[7] = new Animation(sprites, 0, 7, 2, 7, true, 10, true);
	}

	public float[] weaponLoc() {
		if (this.isRight) {
			return new float[] { pos[0] + 64 + 16, pos[1] + 30 };
		} else {
			return new float[] { pos[0] - 16, pos[1] + 30 };
		}
	}

	@Override
	public void aiLoop(Player[] players, int delta) {
		if (aiCurTime > aiDelay || aiCurTime == 0) {
			aiCurTime = delta;
			if (locked == null || Math.random() > .8) {
				if (Math.abs(players[0].pos[0] - this.pos[0]) < Math
						.abs(players[1].pos[0] - this.pos[0])) {
					locked = players[0];
				} else {
					locked = players[1];
				}
			}
			if (locked.pos[0] > this.pos[0] && Math.random() > .2) {
				this.moveRight = true;
			} else {
				this.moveRight = false;
			}
			if (locked.pos[1] > this.pos[1] && Math.random() > .2) {
				this.moveUp = false;
			} else {
				this.moveUp = true;;
			}
		}
		else{
			aiCurTime += delta;
			if(this.moveRight){
				this.moveRight(1);
			}
			else {
				this.moveLeft(1);
			}
			if(this.moveUp){
				this.moveUp(1);
			}
			else {
				this.moveDown(1);
			}
		}
		
	}

	@Override
	public Animation handleAnimation(String whichAnim) {
		if(isRight){
			if(whichAnim.equals("punch")){
				return anims[0];
			} else {
				//else, the walk animation for now
				return anims[1];
			}
		} else{
			if(whichAnim.equals("punch")){
				return anims[2];
			} else {
				//else, the walk animation for now
				return anims[3];
			} 
		}
	}
}
