package flooritems;

import org.newdawn.slick.Image;

public class Sword extends FloorItem {

	public Sword(Image s, float x, float y) {
		super(x, y);
	}

	public Image weaponSprite;
	
	@Override
	public void Draw() {
		super.Draw();
		
	}

	@Override
	public void Pickup() {
	}
	

}
