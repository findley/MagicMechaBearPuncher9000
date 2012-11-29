package core;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Text {
	public float duration;
    public float[] pos = new float[2];
	public String text;
	
	public Text(float[] pos, String text) {
		this.duration = 50.0f;
		this.pos[0] = pos[0];
		this.pos[1] = pos[1];
		
		this.text = text;
	}

    public void render(Graphics g) throws SlickException {
    	g.setColor(Color.blue);
    	g.drawString(text,  pos[0], pos[1]);
    }
    
    public void update() {
    	this.duration -= 1;
    	this.pos[1] -= 1;
    }
}
