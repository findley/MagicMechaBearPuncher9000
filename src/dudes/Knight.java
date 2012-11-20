package dudes;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import weapons.KnightKnife;

public class Knight extends Monster {
	float homeToleranceX;
	float homeToleranceY;

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
		homeToleranceX = 100;
		homeToleranceY = 75;
		this.weapon = new KnightKnife(this);
		try {
			this.init();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void init() throws SlickException {
		//create spritesheets for the weapon:
		this.weapon.init();
		aiDelay = 500;
	}

	// return leftmost point of weapon
	public float[] weaponLoc() {
		if(this.isRight){
			return new float[] {pos[0] + 64 + 4, pos[1]+ 40};
		}
		else {
			return new float[] {pos[0] - 4, pos[1]+40};
		}
	}

	@Override
	public void aiLoop(Player[] players, int delta) {
		if (homing) {
			homing = home(locked);
			return;
		}
		if (aiCurTime > aiDelay || aiCurTime == 0) {
			aiCurTime = delta;
			if (locked == null || Math.random() > .3) {
				if (Math.abs(players[0].pos[0] - this.pos[0]) < Math
						.abs(players[1].pos[0] - this.pos[0])) {
					locked = players[0];
				} else {
					locked = players[1];
				}
			}
			if (locked.pos[0] > this.pos[0] && Math.random() > .15) {
				this.moveRight = true;
			} else {
				this.moveRight = false;
			}
			if (locked.pos[1] > this.pos[1] && Math.random() > .15) {
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
		if (Math.random() > .99 && Math.abs(this.pos[0] - locked.pos[0]) < homeToleranceX && 
				Math.abs(this.pos[1] - locked.pos[1]) < homeToleranceY){
			homing = true;
		}
		
	}

	@Override
	public Animation handleAnimation(String whichAnim) {
		if(isRight){
			if(whichAnim.equals("punch")){
				return weapon.anims[0];
			} else {
				//else, the walk animation for now
				return weapon.anims[1];
			}
		} else{
			if(whichAnim.equals("punch")){
				return weapon.anims[2];
			} else {
				//else, the walk animation for now
				return weapon.anims[3];
			} 
		}
	}
}
