package dudes;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import weapons.Coin;
import weapons.Weapon;

public abstract class Monster extends Dude {
    Player  locked    = null;
    boolean moveRight;
    boolean moveUp;
    boolean homing    = false;
    int     aiDelay;
    int     aiCurTime = 0;
    int     moveSpeed;
    Player  lastHit;
    
    // dunno the fuck this is gonna do yet.
    abstract public void aiLoop(Player[] players, int delta);
    
    public void move(Input input, int delta) {
        if (flinching) {
            flinchTime += delta;
            if (flinchTime < flinchDur) {
                return;
            } else {
                flinching = false;
            }
        }
        
        if (isAttacking) {
            attackTime += delta;
            if (attackTime < this.weapon.attackTime) {
                return;
            } else {
                isAttacking = false;
                delayed = true;
                currentAnimation.restart();
                delayTime = 0;
            }
        }
    }
    
    public boolean home(Player target) {
        boolean xFlag = false;
        boolean yFlag = false;
        if (target.pos[0] - this.pos[0] > this.weapon.attackWidth) {
            this.moveRight(this.moveSpeed);
        } else if (this.pos[0] - target.pos[0] > this.weapon.attackWidth) {
            this.moveLeft(this.moveSpeed);
        } else {
            xFlag = true;
            if (this.pos[0] > target.pos[0]) {
                this.moveLeft(0);
            } else {
                this.moveRight(0);
            }
        }
        
        if (target.pos[1] - this.pos[1] > this.weapon.attackHeight) {
            this.moveDown(this.moveSpeed);
        } else if (this.pos[1] - target.pos[1] > this.weapon.attackHeight) {
            this.moveUp(this.moveSpeed);
        } else {
            yFlag = true;
        }
        
        if (yFlag && xFlag) {
            this.attack();
            return false;
        }
        return true;
    }
    
    public void renderDeath(){
    	if(isRight){
    		currentAnimation = weapon.anims[7];
    	} else{
    		currentAnimation = weapon.anims[6];
    	}
    	currentAnimation.start();
	    currentAnimation.draw(pos[0], pos[1]);
    }
    
    public void setLastHit(Player player) {
        this.lastHit = player;
    }
    
    public Player getLastHit() {
        return this.lastHit;
    }
    
    abstract public Weapon getDropWeapon() throws SlickException;
    
    abstract public Coin getDropCoin() throws SlickException;
    
}
