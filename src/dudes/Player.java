package dudes;

import java.util.HashMap;

import org.newdawn.slick.Input;

public class Player extends Dude {
	public HashMap<String, Integer> buttons;
	
	public Player(HashMap<String, Integer> buttons, float xPos, float yPos) {
		this.buttons = buttons;
		pos[0] = xPos;
		pos[1] = yPos;
		moveSpeed = 5;
	}
	
	public void move(Input input, int delta){
		if (input.isKeyDown(buttons.get("right"))) {
			pos[0] += .1*delta*moveSpeed;
		}
		if (input.isKeyDown(buttons.get("left"))) {
			pos[0] -= .1*delta*moveSpeed;
		}
		if (input.isKeyDown(buttons.get("down"))) {
			pos[1] += .1*delta*moveSpeed;
		}
		if (input.isKeyDown(buttons.get("up"))) {
			pos[1] -= .1*delta*moveSpeed;
		}
	}
	
	public void pickup(){
		// tries to pick up what might be nearby.
	}
	
	public float[] weaponLoc(){
		return new float[] {pos[0]+16, pos[1] + 6};
	}
}
