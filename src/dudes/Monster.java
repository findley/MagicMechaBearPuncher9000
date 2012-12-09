package dudes;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import weapons.*;

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
            if (!currentAnimation.isStopped()) {
                return;
            } else {
                isAttacking = false;
                delayed = true;
                currentAnimation.restart();
                currentAnimation = null;
                delayTime = 0;
            }
        }
    }
    
    public boolean home(float[] targetpos, Player[] players, ArrayList<Monster> monsters) throws SlickException {
        boolean xFlag = true;
        boolean yFlag = false;
        float actualDist = Math.abs(targetpos[0] - this.pos[0]);
        if (targetpos[1] +10> this.weapon.getAttackHitBox().getMaxY()-2) {
            this.moveDown(this.moveSpeed, players, monsters);
        } else if (targetpos[1] +10< this.weapon.getAttackHitBox().getMinY() + 2) {
            this.moveUp(this.moveSpeed, players, monsters);
        } else {
            yFlag = true;
        }
        if(this.isRight){
        	if (targetpos[0] > this.weapon.getAttackHitBox().getMaxX() - 5) {
        		this.moveRight(this.moveSpeed, players, monsters);
        		xFlag = false;
        	}
        	if (targetpos[0] < this.weapon.getAttackHitBox().getMinX()) {
        		this.moveLeft(this.moveSpeed, players, monsters);
        		xFlag = false;
        	}
        }
        else{
        	if (targetpos[0] < this.weapon.getAttackHitBox().getMinX() + 5) {
        		this.moveLeft(this.moveSpeed, players, monsters);
        		xFlag = false;
        	}
        	if (targetpos[0] > this.weapon.getAttackHitBox().getMaxX()) {
        		this.moveRight(this.moveSpeed, players, monsters);
        		xFlag = false;
        	}
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
    
    public Weapon getDropWeapon() throws SlickException {
    	Weapon[] lootItems = new Weapon[]{ new Bear(pos[0], pos[1]), new Mecha(pos[0],pos[1]), new Diglet(pos[0], pos[1]) } ;
    	
    	float[] pos = this.pos;
        double rand = Math.random();
        Weapon w;

    	int index = (int) (rand*lootItems.length);
    	System.out.println("hello:"+ index);
    	w = lootItems[index];
		w.createGroundSprite();
		return w;
    }
    
    abstract public Coin getDropCoin() throws SlickException;

    @Override
    public void renderHealthBar(Graphics g) {
    	if (this.state == enemyState.ALIVE) {
    		super.renderHealthBar(g);
    	}
    }
    
	public enum enemyState {
		ALIVE, DYING, DEAD
	}
}