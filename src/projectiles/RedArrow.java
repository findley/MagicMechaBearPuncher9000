package projectiles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import dudes.Dude;
import dudes.Player;

public class RedArrow extends Projectile{

	public RedArrow(float[] pos, Player owner, boolean isRight) throws SlickException {
		super(pos, owner, isRight);
		this.moveSpeed = 5.0f;
		this.damage = 20;
		if (isRight) {
			this.sprite = new Image("Assets/Weapons/Bow/RightArrow.png");
		} else {
			this.sprite = new Image("Assets/Weapons/Bow/LeftArrow.png");
		}
		this.hitbox = new Rectangle(pos[0], pos[1], sprite.getWidth(), sprite.getHeight());
	}
}