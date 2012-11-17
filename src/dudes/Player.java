package dudes;

import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import core.MainGame;

import weapons.Fist;

public class Player extends Dude {
	public HashMap<String, Integer> buttons;
	
	public Player(HashMap<String, Integer> buttons, float xPos, float yPos) {
		this.buttons = buttons;
		pos[0] = xPos;
		pos[1] = yPos;
		moveSpeed = 5;
		maxHealth = 100;
		health = maxHealth;
		healthFill = new Color(0f, 1f, 0f, 1f);
		this.weapon = new Fist(this);
	}
	
	public void move(Input input, int delta){
		if (input.isKeyDown(buttons.get("action"))) {
			this.weapon.attack();
			return;
		}
		
		double moveDist = .1*delta*moveSpeed;
		if (input.isKeyDown(buttons.get("right"))) {
			pos[0] += moveDist;
			if (pos[0] > MainGame.GAME_WIDTH - 64) {
				pos[0] = MainGame.GAME_WIDTH - 64;
			}
		}
		if (input.isKeyDown(buttons.get("left"))) {
			pos[0] -= moveDist;
			if (pos[0] < 0) {
				pos[0] = 0;
			}
		}
		if (input.isKeyDown(buttons.get("down"))) {
			pos[1] += moveDist;
			if (pos[1] > MainGame.GAME_HEIGHT - 32*3 - 5) {
				pos[1] = MainGame.GAME_HEIGHT - 32*3 - 5;
			}
		}
		if (input.isKeyDown(buttons.get("up"))) {
			pos[1] -= moveDist;
			if (pos[1] < MainGame.GAME_HEIGHT - 32*10 + 5) {
				pos[1] = MainGame.GAME_HEIGHT - 32*10 + 5;
			}
		}
	}
	
	public void pickup(){
		// tries to pick up what might be nearby.
	}
	
	public float[] weaponLoc(){
		if(this.isRight){
			return new float[] {pos[0] + 16, pos[1] + 6};
		}
		else {
			return new float[] {pos[0] - 16, pos[1] - 6};
		}
	}
}
