package projectiles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import dudes.Dude;
import dudes.Player;

public class Bolt extends Projectile{

	public Bolt(float[] pos, Player owner, boolean isRight) throws SlickException {
		super(pos, owner, isRight);
		this.moveSpeed = 5.0f;
		this.damage = 10;
		if (isRight) {
			this.sprite = new Image("Assets/Weapons/Crossbow/RightBolt.png");
		} else {
			this.sprite = new Image("Assets/Weapons/Crossbow/LeftBolt.png");
		}
		this.hitbox = new Rectangle(pos[0], pos[1], sprite.getWidth(), sprite.getHeight());
	}
}
