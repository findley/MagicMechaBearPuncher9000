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
	private Event eventOne;
	private Event eventTwo;
	
    public TownWindow(Player[] players, float[] locp1, float[] locp2) throws SlickException {
        super(players, locp1, locp2);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game,
            Graphics g) throws SlickException {
    	for(int i = 0; i < players.length; i++){
    	}
		//render player one screen		
		if (eventOne != null) {
		} else {    
		    cameras[0].drawMap();
		    cameras[0].translateGraphics();
			players[0].render(container, game, g, players[0].hubLoc[0], players[0].hubLoc[1]);
			if(players[1].hubLoc[0] < players[0].hubLoc[0] + players[0].windowSize[0] - 32){
			    players[1].render(container, game, g, players[1].hubLoc[0], players[1].hubLoc[1]);
			}
			cameras[0].untranslateGraphics();
			
		}
		
		
		//render player two screen
		Player p2 = players[1];
		if (eventTwo != null) {
			
		} else {
		    
		    cameras[1].drawMap();
            cameras[1].translateGraphics();
            if(players[0].hubLoc[0] + 32> players[1].hubLoc[0] - players[1].windowSize[0]/2){
                players[0].render(container, game, g, players[0].hubLoc[0], players[0].hubLoc[1]);
            }
            players[1].render(container, game, g, players[1].hubLoc[0], players[1].hubLoc[1]);
            cameras[1].untranslateGraphics();
		}
		container.getGraphics().resetTransform();
		float leftLoc = players[0].windowPos[0]+ players[0].windowSize[0];
		float rightLoc = players[1].windowPos[0]-players[0].windowSize[0];
		g.setColor(Color.black);
		g.fillRect(leftLoc, (float) 0, rightLoc, (float) container.getHeight());
		
    }

    @Override
    public void init(GameContainer container, StateBasedGame game,
            Player[] players) throws SlickException {
        //bgImage = new Image("Assets/Hub 1/FinalImageRef.png");
        this.bgImage = new TiledMap("Assets/TiledEditor/DanielHub.tmx");
        this.blocked = new boolean[bgImage.getWidth()][bgImage.getHeight()];
        super.init(container, game, players);
        

		for (int xAxis = 0; xAxis < bgImage.getWidth(); xAxis++) {
			for (int yAxis = 0; yAxis < bgImage.getHeight(); yAxis++) {
				int tileID = bgImage.getTileId(xAxis, yAxis, 0);
				String value = bgImage.getTileProperty(tileID, "blocked",
						"false");
				System.out.println(value);
				if ("true".equals(value)) {
					blocked[xAxis][yAxis] = true;
				}
			}
		}
        
        this.cameras = new Camera[2];
        cameras[0] = new Camera(container, bgImage, this.players[0]);
        cameras[1] = new Camera(container, bgImage, this.players[1]);
        Music loop = new Music("Assets/Town2.wav");
        loop.loop();
    }

    @Override
	public void displayHubBackground(Graphics g, Player player) {
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