import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class TownWindow extends HubWindow {
    private Double timer;
	private Image bgImage;
	private Event eventOne;
	private Event eventTwo;
	
    public TownWindow(Player[] players, float[] locp1, float[] locp2) throws SlickException {
        super(players, locp1, locp2);
        
    }

    @Override
    public void render(GameContainer container, StateBasedGame game,
            Graphics g) throws SlickException {
		int width = container.getWidth()/2;		
		int height = container.getHeight();
		//render player one screen		
		if (eventOne != null) {
		} else {
			g.drawImage(bgImage.getSubImage(1000, 1000, 24*32, 16*32).getScaledCopy(590, 720), 0, 0);
			players[0].render(container, game, g, players[0].hubLoc[0], players[0].hubLoc[1]);
			players[1].render(container, game, g, players[1].hubLoc[0], players[1].hubLoc[1]);
		}
		
		
		//render player two screen
		Player p2 = players[1];
		if (eventTwo != null) {
			
		} else {
			g.drawImage(bgImage.getSubImage(1000, 1000, 24*32, 16*32).getScaledCopy(590, 720), width, 0);
			players[1].render(container, game, g, players[1].hubLoc[0] + width, players[1].hubLoc[1]);
			players[0].render(container, game, g, players[0].hubLoc[0] + width, players[0].hubLoc[1]);
		}
		
		
		
    }

    @Override
    public void init(GameContainer container, StateBasedGame game,
            Player[] players) throws SlickException {
        bgImage = new Image("Assets/Hub 1/FinalImageRef.png");
        super.init(container, game, players);
        Music loop = new Music("Assets/Town2.wav");
        loop.loop();
    }

    @Override
	public void displayHubBackground(Graphics g, Player player) {
		// do we want the +21? Probably, but easy to fix
		// hacky image instead of tileset deal
		g.drawImage(bgImage.getSubImage(1000, 1000, 24 * 32, 16 * 32)
				.getScaledCopy(590, 720), (int) (players[0].windowPos[0]),
				(int) (players[0].windowPos[1]));
		g.drawImage(bgImage.getSubImage(1000, 1000, 24 * 32, 16 * 32)
				.getScaledCopy(590, 720), (int) (players[1].windowPos[0] + 10),
				(int) (players[1].windowPos[1]));
	}
    
    @Override
    public void update(GameContainer container, StateBasedGame game,
            Player[] players) throws SlickException {
        Input input = container.getInput();
        
        //timer -= 1.0 / 60;
        //if (timer <= 0) {
        //   this.over = true;
        //}
    }
}