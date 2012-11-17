package dudes;

import java.util.HashMap;

public abstract class Player extends Dude{
	HashMap<String, Integer> buttons;
	
	void move(String button){
		//something to do with modifying loc based on button
	}
	
	void pickup(){
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
