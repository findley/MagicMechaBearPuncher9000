package flooritems;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import weapons.Weapon;

public class KnightKnifeFloor extends FloorItem {

	public Image weaponSprite;
	public Weapon weapon;
	public KnightKnifeFloor(float x, float y) {
		super(x, y);
		try {
			weaponSprite = new Image("Assets/FloorItems/sword.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Draw() {
		weaponSprite.draw(x, y);	
	}
	
}
