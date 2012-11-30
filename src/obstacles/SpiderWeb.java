package obstacles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import dudes.Player;

public class SpiderWeb extends Obstacle{
	
	public SpiderWeb(float[] pos) throws SlickException {
		super(pos);
		this.sprite = new Image("Assets/JewelsAndMisc/spiderweb.png");
		this.hitbox = new Rectangle(pos[0], pos[1], sprite.getWidth(), sprite.getHeight());
		this.speedEffect = 0.5f;
	}
	
	public void effect(Player p){
		if(p.moveSpeed == p.constSpeed){
			p.moveSpeed = p.moveSpeed*speedEffect;
		}
		p.webbed = true;
	}
	
	public void endEffect(Player p){
		if(p.webbed && p.pos[0]-this.pos[0]>300){
			p.webbed = false;
			p.moveSpeed = p.moveSpeed/speedEffect;
		}
	}
}
