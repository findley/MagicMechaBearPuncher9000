package dudes;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import core.MainGame;

public class Player extends Dude {
	public HashMap<String, Integer> buttons;
	
	public Player(HashMap<String, Integer> buttons, float xPos, float yPos) {
		this.buttons = buttons;
		pos[0] = xPos;
		pos[1] = yPos;
		moveSpeed = 3;
	}
	
	public void move(Input input, int delta) {
		if (input.isKeyDown(buttons.get("right"))) {
			if (pos[0] < MainGame.GAME_WIDTH - 64) {
				pos[0] += .1*delta*moveSpeed;
			}
		}
		if (input.isKeyDown(buttons.get("left"))) {
			if (pos[0] > 0) {
				pos[0] -= .1*delta*moveSpeed;
			}
		}
		if (input.isKeyDown(buttons.get("down"))) {
			if (pos[1] < MainGame.GAME_HEIGHT - 32*3 - 5) {
				pos[1] += .1*delta*moveSpeed;
			}
		}
		if (input.isKeyDown(buttons.get("up"))) {
			if (pos[1] > MainGame.GAME_HEIGHT - 32*10 + 10) {
				pos[1] -= .1*delta*moveSpeed;
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
