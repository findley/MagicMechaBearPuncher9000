package dudes;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public abstract class Monster extends Dude{
	
	// dunno the fuck this is gonna do yet.
	abstract void aiLoop(Player[] players);

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
				return;
			}
			else{
				isAttacking = false;
				delayed = true;
				currentAnimation.restart();
				delayTime = 0;
			}
		}
	}
}
