package dudes;

import java.util.ArrayList;

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
    int		nothingTime = 0;
    boolean doingNothing = false;
    int     moveSpeed;
    boolean attackNow = false;
    Player  lastHit;
    public int value;
    public enemyState state = enemyState.ALIVE;
        
    // dunno the fuck this is gonna do yet.
    abstract public void aiLoop(Player[] players, ArrayList<Monster> currBattle, int delta) throws SlickException;
    
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
    
    public boolean home(float[] targetpos) throws SlickException {
        boolean xFlag = true;
        boolean yFlag = false;
        float actualDist = Math.abs(targetpos[0] - this.pos[0]);
        if(this.isRight){
        	if (targetpos[0] > this.weaponLoc()[0] + this.weapon.attackWidth) {
        		this.moveRight(this.moveSpeed);
        		xFlag = false;
        	}
        	else if (targetpos[0] < this.weaponLoc()[0] && actualDist > 64){
        		this.moveLeft(this.moveSpeed);
        		xFlag = false;
        	}
        	else{
        		return false;
        	}

        }
        else{
        	if (targetpos[0] < this.weaponLoc()[0] - this.weapon.attackWidth) {
        		this.moveLeft(this.moveSpeed);
        		xFlag = false;
        	}
        	else if (targetpos[0] > this.weaponLoc()[0]&& actualDist > 64){
        		this.moveRight(this.moveSpeed);
        		xFlag = false;
        	}
        	else {
        		return false;
        	}
        } 
        
        if (targetpos[1] - this.pos[1] > this.weapon.attackHeight) {
            this.moveDown(this.moveSpeed);
        } else if (this.pos[1] - targetpos[1] > this.weapon.attackHeight) {
            this.moveUp(this.moveSpeed);
        } else {
            yFlag = true;
        }
        
        if (yFlag && xFlag) {
            return false;
        }
        return true;
    }
    
    public boolean doNothing(int time, int delta){
    	if (nothingTime > time){
    		nothingTime = 0;
    		return false;
    	}
    	else {
    		nothingTime += delta;
    		return true;
    	}
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
    
	public enum enemyState {
		ALIVE, DYING, DEAD
	}

}
