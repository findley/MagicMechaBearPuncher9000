package projectiles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import dudes.Player;

public class FireballProjectile extends Projectile{

	public FireballProjectile(float[] pos) throws SlickException {
		super(pos);
		this.moveSpeed = 5.0f;
		this.sprite = new Image("Assets/Weapons/Fireball/Fireball.png");
	}
}
