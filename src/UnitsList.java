import java.util.ArrayList;

import org.newdawn.slick.geom.Rectangle;

public class UnitsList {
	public ArrayList<PlayerObj> unitCollision;

	public UnitsList() {
		unitCollision = new ArrayList<PlayerObj>();
	}
	
	/*
	 * Cleaning up some of the collision functions in this method.
	 * All collision functions are now of the type "isCollidingWith_____(object you want to check collision for"
	 * and return the object they collide with, or null if no collision exists.
	 */
	
	public PlayerObj isCollidingWithPlayer(PlayerObj obj){
		for (PlayerObj player : unitCollision) {
			if (player != obj && player.getBoundingBox().intersects(obj.getBoundingBox())) {
				return player;
			}
		}
		return null;
	}
	
	public boolean collideWithUnitAt(float x, float y, PlayerObj unit) {
		Rectangle box = unit.getBoundingBox();
		box.setBounds(x, y, box.getWidth(), box.getHeight());
		for (PlayerObj p : unitCollision) {
			if (p != unit && p.getBoundingBox().intersects(box)) {
				return true;
			}
		}
		return false;
	}
	
}