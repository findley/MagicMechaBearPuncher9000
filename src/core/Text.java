package core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Text {
	public float duration;
	public float[] pos;
	public String text;
	
	public Text(float[] pos, String text) {
		this.duration = 10.0f;
		this.pos = pos;
		this.text = text;
	}

    public void render(Graphics g) throws SlickException {
    	 g.drawString("asas",  pos[0], pos[1]);
    }
    
    public void update() {
    	this.duration -= 1;
    }
}
