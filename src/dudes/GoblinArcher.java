package dudes;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import weapons.*;

public class GoblinArcher extends Monster {
	float homeToleranceX;
	float homeToleranceY;
	boolean movingUp;
	boolean movingDown;
	boolean movingLeft;
	boolean movingRight;
	boolean canMove;
	float range;
	float screenWidth;
	
	public GoblinArcher(float xPos, float yPos, int k, GameContainer container) {
		super();
		this.container = container;
		maxHealth = 37;
		health = maxHealth;
		pos[0] = xPos;
		pos[1] = yPos;
		isRight = false;
		moveSpeed = 4;
		healthFill = new Color(Color.red);
		//hitbox = new Rectangle(pos[0], pos[1], 64, 64);
		kind = k;
		value = 8;
		range = 200;
		screenWidth = container.getWidth();
		this.weapon = new GoblinBow(this);
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

	
	public void aiLoop2(Player[] players, ArrayList<Monster> monsters, int delta)
			throws SlickException {
		if (this.isAttacking){
			if(currentAnimation.isStopped()){
				this.isAttacking = false;
				this.weapon.attack = null;
			}
			else {
				return;
			}
		}
		if (health <= 0){
			currentAnimation = handleAnimation("die");
		}
		if (doingNothing) {
			if (locked == null) {
				doingNothing = this.doNothing(700, delta);
				if(this.movingLeft){
					this.moveLeft(1, players, monsters);
				}
				if(this.movingRight){
					this.moveRight(1, players, monsters);
				}
				if(this.movingUp){
					this.moveUp(1, players, monsters);
				}
				if(this.movingDown){
					this.moveDown(1, players, monsters);
				}
				if(!doingNothing){
					this.movingLeft = false;
					this.movingRight = false;
					this.movingDown = false;
					this.movingUp = false;
				}

			} else {
				doingNothing = this.doNothing(300, delta);
				if (locked.getHitBox().getCenterX() > this.pos[0]) {
					this.moveRight(0, players, monsters);
				} else {
					this.moveLeft(0, players, monsters);
				}
				if (!doingNothing && Math.random() < .3) {
					currentAnimation = handleAnimation("punch");
					this.isAttacking = true;
					this.weapon.attack();
				} else {
				}
				currentAnimation.start();
			}
			return;
		}
		if(locked != null && locked.isRespawning) {
			locked = null;
		}
		if (locked == null) {
			if (Math.abs(players[0].getHitBox().getCenterX() - this.pos[0]) < homeToleranceX) {
				locked = players[0];
			} else if (Math.abs(players[1].getHitBox().getCenterX() - this.pos[0]) < homeToleranceX) {
				locked = players[1];
			}
			else {
				if (Math.random() < .5) {
					this.movingLeft = true;
				}
				else if (Math.random() < .5) {
					this.movingRight = true;
				}
				if (Math.random() < .5) {
					this.movingUp = true;
				}
				else if (Math.random() < .5) {
					this.movingDown = true;
				}
				currentAnimation = handleAnimation("walk");
				currentAnimation.start();
				doingNothing = true;
			}

		} else {
			if (Math.abs(locked.getHitBox().getCenterX() - this.pos[0]) > 1.5 * homeToleranceX) {
				locked = null;
				homing = false;
			} else {
				if (!homing) {
					homing = true;
				} else {
					homing = home(locked.getHitBox().getCenter(), players, monsters);
					if (!homing) {
						this.doNothing(300, delta);
						doingNothing = true;
					}
					currentAnimation = handleAnimation("walk");
					currentAnimation.start();
					return;
				}
			}
			currentAnimation = handleAnimation("walk");
			currentAnimation.start();
			return;
		}
	}
	
	@Override
	public void aiLoop(Player[] players, ArrayList<Monster> monsters, int delta) throws SlickException {
		if (flinching) {
            flinchTime += delta;
            if (flinchTime < flinchDur) {
                return;
            } else {
                flinching = false;
            }
        }
		
		ArrayList<Dude> dudeAr = new ArrayList<Dude>();
		dudeAr.addAll(monsters);
		dudeAr.addAll(Arrays.asList(players));
		Dude[] dudes = new Dude[dudeAr.size()];
		dudeAr.toArray(dudes);
		if (homing) {
			homing = home(locked.pos, players, monsters);
			return;
		}

		
		if (aiCurTime > aiDelay || aiCurTime == 0) {
			canMove = true;
			aiCurTime = delta;
			if (locked == null || Math.random() > .3) {
				if (Math.abs(players[0].pos[0] - this.pos[0]) < Math
						.abs(players[1].pos[0] - this.pos[0])) {
					locked = players[0];
				} else {
					locked = players[1];
				}
			}
			float diff;
			if (Math.random() > .15) {
				if (locked.pos[0] > this.pos[0]) {
					diff = locked.pos[0] - this.pos[0];
					if (diff > range) {
						this.moveRight = true;
						this.isRight = true;
						this.weapon.attack();
					} else {
						if (this.pos[0] < 10) {
							canMove = false;
							this.weapon.attack();
							this.isRight = true;
						} else {
							this.moveRight = false;
							
						}
					}
				} else { 
					diff = this.pos[0] - locked.pos[0];
					if (diff > range) {
						this.moveRight = false;
						this.isRight = false;
						this.weapon.attack();
					} else {
						if (this.pos[0] > screenWidth - 100 ) {
							canMove = false;
							this.weapon.attack();
							this.isRight = false;
						} else {
							this.moveRight = true;							
						}
					}
				}
			}
			
			if (locked.pos[1] > this.pos[1] && Math.random() > .15) {
				this.moveUp = false;
			} else {
				this.moveUp = true;;
			}
		}
		else{
			aiCurTime += delta;
			if (canMove) {
				if(this.moveRight){
					this.moveRight(1, players, monsters);
				}
				else {
					this.moveLeft(1, players, monsters);
				}
			} else {
			}
			
			if(this.moveUp){
				this.moveUp(1, players, monsters);
			}
			else {
				this.moveDown(1, players, monsters);
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
	
	
	
}