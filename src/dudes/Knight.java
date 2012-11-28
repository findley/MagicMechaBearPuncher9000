package dudes;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import weapons.*;

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
		aiDelay = 1000;
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
		if(flinching){
			currentAnimation = handleAnimation("flinch");
		} else if(isAttacking){
			currentAnimation = handleAnimation("punch");
		} else if (health <= 0){
			currentAnimation = handleAnimation("die");
		} else{
			currentAnimation = handleAnimation("walk");
		}
		currentAnimation.start();
	}

	@Override
	public Animation handleAnimation(String whichAnim) {
        if (isRight) {
            if (whichAnim.equals("flinch")) {
                return weapon.anims[1];
            } else if (whichAnim.equals("punch")) {
                return weapon.anims[3];
            } else if (whichAnim.equals("die")){
            	return weapon.anims[7];
            } else {
                // else, the walk animation for now
                return weapon.anims[5];
            }
        } else {
            if (whichAnim.equals("flinch")) {
                return weapon.anims[0];
            } else if (whichAnim.equals("punch")) {
                return weapon.anims[2];
            } else if (whichAnim.equals("die")){
            	return weapon.anims[6];
            } else {
                // else, the walk animation for now
                return weapon.anims[4];
            }
        }
	}
	
	@Override
	public Weapon getDropWeapon() throws SlickException {
		//float[] pos = {m.pos[0]+90,m.pos[1]};
    	float[] pos = this.pos;
        double rand = Math.random();
        Weapon w;
        if(rand < 0.5){
        	if(rand < 0.25){
        		w = new Sword(pos[0],pos[1]);
        	} else{
            	w = new Bear(pos[0],pos[1]);
        	}
        	w = new Mini(pos[0], pos[1]);
    		w.createGroundSprite();
    		return w;
        }
        
        return null;
	}
	
	@Override
	public Coin getDropCoin() throws SlickException {
		double rand2 = Math.random();
        if(rand2<0.5){
        	return new Coin("yellow",pos);
        } else if(rand2<0.7){
        	return new Coin("red",pos);
        } else if(rand2<0.85){
        	return new Coin("blue",pos);
        } else if(rand2<0.95){
        	return new Coin("green",pos);
        } else if (rand2 < 1){
        	return new Coin("purple",pos);
        } 
        
        return null;
	}
}
