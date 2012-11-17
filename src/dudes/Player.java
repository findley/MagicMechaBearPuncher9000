package dudes;

import java.util.HashMap;

public class Player extends Dude{
	public HashMap<String, Integer> buttons;
	
	public Player(HashMap<String, Integer> buttons) {
		this.buttons = buttons;
	}
	
	void move(String button){
		//something to do with modifying loc based on button
	}
	
	void pickup(){
		// tries to pick up what might be nearby.
	}
	
	public float[] weaponLoc(){
		return new float[] {pos[0]+16, pos[1] + 6};
	}
}
