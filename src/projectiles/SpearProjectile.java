package projectiles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import dudes.Dude;
import dudes.Player;

public class SpearProjectile extends Projectile{

	public SpearProjectile(float[] pos, Player owner, boolean isRight) throws SlickException {
		super(pos, owner, isRight);
		this.moveSpeed = 3.5f;
		this.damage = 12;
		if (isRight) {
			this.sprite = new Image("Assets/Weapons/Spear/RightSpear.png");
		} else {
			this.sprite = new Image("Assets/Weapons/Spear/LeftSpear.png");
		}
		this.hitbox = new Rectangle(pos[0], pos[1], sprite.getWidth(), sprite.getHeight());
	}
}
