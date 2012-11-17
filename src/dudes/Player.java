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

import weapons.Fist;

public class Player extends Dude {
	public HashMap<String, Integer> buttons;
	private int playerID;
	
	public Player(HashMap<String, Integer> buttons, float xPos, float yPos) {
		this.buttons = buttons;
		this.isRight = true;
		pos[0] = xPos;
		pos[1] = yPos;
		moveSpeed = 5;
		maxHealth = 100;
		health = maxHealth;
		healthFill = new Color(0f, 1f, 0f, 1f);
		attackTime = 0;
		hitbox = new Rectangle(pos[0], pos[1], 64, 64);
		this.weapon = new Fist(this);
	}
	
	public void init(int playerID) throws SlickException {
		this.playerID = playerID;
		sprites = new SpriteSheet("Assets/players/player"+playerID+"Basic.png", 64, 64);
		spriteIndex[0] = 0;
		spriteIndex[1] = 3;
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
		if (delayed){
			delayTime += delta;
			if(delayTime < this.weapon.delayTime){
				return;
			}
			else{
				delayed = false;
			}
		}
		if(isAttacking){
			attackTime+=delta;
			if(attackTime < this.weapon.attackTime){
				currentAnimation = handleAnimation("punch");
				return;
			}
			else{
				isAttacking = false;
				delayed = true;
				delayTime = 0;
			}
		}
		if (input.isKeyPressed(buttons.get("action"))) {
			this.isAttacking = true;
			currentAnimation = handleAnimation("punch");
			attackTime = 0;
			this.weapon.attack();
			return;
		}
		
		double moveDist = .1*delta*moveSpeed;
		if (input.isKeyDown(buttons.get("right"))) {
			isRight = true;
			currentAnimation = handleAnimation("walk");
			pos[0] += moveDist;
			if (pos[0] > MainGame.GAME_WIDTH - 64) {
				pos[0] = MainGame.GAME_WIDTH - 64;
			}
		}
		if (input.isKeyDown(buttons.get("left"))) {
			isRight = false;
			currentAnimation = handleAnimation("walk");
			pos[0] -= moveDist;
			if (pos[0] < 0) {
				pos[0] = 0;
			}
		}
		if (input.isKeyDown(buttons.get("down"))) {
			currentAnimation = handleAnimation("walk");
			pos[1] += moveDist;
			if (pos[1] > MainGame.GAME_HEIGHT - 32*3 - 5) {
				pos[1] = MainGame.GAME_HEIGHT - 32*3 - 5;
			}
		}
		if (input.isKeyDown(buttons.get("up"))) {			
			currentAnimation = handleAnimation("walk");
			pos[1] -= moveDist;
			if (pos[1] < MainGame.GAME_HEIGHT - 32*10 + 5) {
				pos[1] = MainGame.GAME_HEIGHT - 32*10 + 5;
			}
		}
	}
	
	public Animation handleAnimation(String whichAnim){
		if(isRight){
			if(whichAnim.equals("punch")){
				return new Animation(sprites,0,1,3,1,true,100,true);
			} else {
				//else, the walk animation for now
				return new Animation(sprites,0,3,3,3,true,100,true);
			}
		} else{
			if(whichAnim.equals("punch")){
				return new Animation(sprites,0,0,3,0,true,100,true);
			} else {
				//else, the walk animation for now
				return new Animation(sprites,0,2,3,2,true,100,true);
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
