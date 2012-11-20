package dudes;

import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import core.MainGame;

import weapons.*;

public class Player extends Dude {
	public HashMap<String, Integer> buttons;
	public int playerID;
	
	public Player(HashMap<String, Integer> buttons, float xPos, float yPos) {
		this.buttons = buttons;
		this.isRight = true;
		pos[0] = xPos;
		pos[1] = yPos;
		moveSpeed = 3;
		maxHealth = 100;
		health = maxHealth;
		healthFill = new Color(0f, 1f, 0f, 1f);
		attackTime = 0;
		hitbox = new Rectangle(pos[0], pos[1], 64, 64);
		this.weapon = new Fist(this);
	}
	
	public void init(int playerID) throws SlickException {
		this.playerID = playerID;
		
		//create spritesheets for the weapon:
		this.weapon.init();
	}
	
	public void move(Input input, int delta){
		if(flinching){
			flinchTime += delta;
			if(flinchTime < flinchDur){
				return;
			}
			else{
				flinching = false;
			}
		}
		
		if(isAttacking){
			attackTime+=delta;
			if(attackTime < this.weapon.attackTime){
				//currentAnimation = handleAnimation("punch");
				return;
			}
			else{
				isAttacking = false;
				delayed = true;
				currentAnimation.restart();
				delayTime = 0;
			}
		}
		
		float moveDist = (float) .1*delta*moveSpeed;
		
		for (String key : buttons.keySet()) {
			//TODO: fix diagonally
		}
		
		if (input.isKeyPressed(buttons.get("action"))) {
			this.isAttacking = true;
			currentAnimation = handleAnimation("punch");
			currentAnimation.start();
			attackTime = 0;
			this.weapon.attack();
			return;
		} else if (input.isKeyDown(buttons.get("right"))) {
			this.moveRight(moveDist);
		} else if (input.isKeyDown(buttons.get("left"))) {
			this.moveLeft(moveDist);
		} else if (input.isKeyDown(buttons.get("down"))) {
			this.moveDown(moveDist);
		} else if (input.isKeyDown(buttons.get("up"))) {			
			this.moveUp(moveDist);
		} else{
			if(currentAnimation!=null){
				currentAnimation.stop();
			}
		}
	}

	public Animation handleAnimation(String whichAnim) {
		if (isRight) {
			if (whichAnim.equals("flinch")) {
				return weapon.anims[1];
			} else if (whichAnim.equals("punch")) {
				return weapon.anims[3];
			} else {
				// else, the walk animation for now
				return weapon.anims[5];
			}
		} else {
			if (whichAnim.equals("flinch")) {
				return weapon.anims[0];
			} else if (whichAnim.equals("punch")) {
				return weapon.anims[2];
			} else {
				// else, the walk animation for now
				return weapon.anims[4];
			}
		}
	}
	
	public void pickup(){
		// tries to pick up what might be nearby.
	}
	
	public float[] weaponLoc(){
		if(this.isRight){
			return new float[] {pos[0] + 64 + 16, pos[1] + 30};
		}
		else {
			return new float[] {pos[0] - 16, pos[1] +30};
		}
	}
}