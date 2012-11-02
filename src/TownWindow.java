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
//	private Image bgImage;
	private TiledMap bgImage;
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
//			g.drawImage(bgImage.getSubImage(1000, 1000, 24*32, 16*32).getScaledCopy(590, 720), 0, 0);
		    //bgImage.render(0, 0, 1);
			//bgImage.render(0, 0, 2);
		    /*
		    cameras[0].drawMap();
		    cameras[0].translateGraphics();
			players[0].render(container, game, g, players[0].hubLoc[0], players[0].hubLoc[1]);
			players[1].render(container, game, g, players[1].hubLoc[0], players[1].hubLoc[1]);
			cameras[0].untranslateGraphics();
			*/
		}
		
		
		//render player two screen
		Player p2 = players[1];
		if (eventTwo != null) {
			
		} else {
		    cameras[1].drawMap();
            cameras[1].translateGraphics();
            players[0].render(container, game, g, players[0].hubLoc[0], players[0].hubLoc[1]);
            players[1].render(container, game, g, players[1].hubLoc[0], players[1].hubLoc[1]);
            cameras[1].untranslateGraphics();
		    //bgImage.render(container.getWidth()/2, 0,1);
		    //bgImage.render(container.getWidth()/2, 0,2);
//			g.drawImage(bgImage.getSubImage(1000, 1000, 24*32, 16*32).getScaledCopy(590, 720), width, 0);
			//players[1].render(container, game, g, players[1].hubLoc[0] + width, players[1].hubLoc[1]);
			//players[0].render(container, game, g, players[0].hubLoc[0] + width, players[0].hubLoc[1]);
		}
		
		
		
    }

    @Override
    public void init(GameContainer container, StateBasedGame game,
            Player[] players) throws SlickException {
//        bgImage = new Image("Assets/Hub 1/FinalImageRef.png");
        bgImage = new TiledMap("Assets/GameBackground1.tmx");
        super.init(container, game, players);
        this.cameras = new Camera[2];
        cameras[0] = new Camera(container, bgImage, this.players[0]);
        cameras[1] = new Camera(container, bgImage, this.players[1]);
        Music loop = new Music("Assets/Town2.wav");
        loop.loop();
    }

    @Override
	public void displayHubBackground(Graphics g, Player player) {
		// do we want the +21? Probably, but easy to fix
		// hacky image instead of tileset deal
/*		g.drawImage(bgImage.getSubImage(1000, 1000, 24 * 32, 16 * 32)
				.getScaledCopy(590, 720), (int) (players[0].windowPos[0]),
				(int) (players[0].windowPos[1]));
		g.drawImage(bgImage.getSubImage(1000, 1000, 24 * 32, 16 * 32)
				.getScaledCopy(590, 720), (int) (players[1].windowPos[0] + 10),
				(int) (players[1].windowPos[1]));
*/
	}
    
    @Override
    public void update(GameContainer container, StateBasedGame game,
            Player[] players, int delta) throws SlickException {
        Input input = container.getInput();
        for(int i = 0; i < players.length; i++){
            super.movePlayer(input, 5, players[i], delta); 
            cameras[i].centerOn(players[i].hubLoc[0],players[i].hubLoc[1]);
        }
    }
}