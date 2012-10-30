import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;


public class Sprite {
	
	Image image;
	Event event;
	Rectangle locRect;
	Rectangle eventRect;
	
	public Sprite(Image image, Event event, Rectangle locRect){
		this.image = image;
		this.event = event;
		this.locRect = locRect;
		//automatically generating eventRect from locRect
		//this.eventRect = something FILL IN HERE
	}

}
