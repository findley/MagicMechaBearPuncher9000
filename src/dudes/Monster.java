package dudes;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public abstract class Monster extends Dude {
	Player locked = null;
	boolean moveRight;
	boolean moveUp;
	int aiDelay;
	int aiCurTime = 0;
	int moveSpeed;

	// dunno the fuck this is gonna do yet.
	abstract public void aiLoop(Player[] players, int delta);

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

	public void home(Player target) {
		boolean xFlag = false;
		boolean yFlag = false;
		if (target.pos[0] - this.pos[0] > this.weapon.attackWidth) {
			this.moveRight(this.moveSpeed);
		} else if (this.pos[0] - target.pos[0] > this.weapon.attackWidth) {
			this.moveLeft(this.moveSpeed);
		} else {
			xFlag = true;
			if (this.pos[0] > target.pos[0]) {
				this.moveLeft(0);
			} else {
				this.moveRight(0);
			}
		}

		if (target.pos[1] - this.pos[1] > this.weapon.attackHeight) {
			this.moveDown(this.moveSpeed);
		} else if (this.pos[1] - target.pos[1] > this.weapon.attackHeight) {
			this.moveUp(this.moveSpeed);
		}
		else{
			yFlag = true;
		}
		
		if (yFlag && xFlag){
			this.attack();
		}
	}
}
