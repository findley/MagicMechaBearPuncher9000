package framework;
import hub1events.*;

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

	public TownWindow(Player[] players, DialogBox[] dialogBoxes, int[] locp1, int[] locp2)
			throws SlickException {
		super(players, dialogBoxes, locp1, locp2);

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		super.render(container, game, g);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game,
			Player[] players) throws SlickException {
		this.bgImage = new TiledMap("Assets/TiledEditor/DanielHub.tmx");
		this.blocked = new boolean[bgImage.getWidth()][bgImage.getHeight()];
		this.miniArray = new String[bgImage.getWidth()][bgImage.getHeight()];
		super.init(container, game, players);

		for (int xAxis = 0; xAxis < bgImage.getWidth(); xAxis++) {
			for (int yAxis = 0; yAxis < bgImage.getHeight(); yAxis++) {
				int tileID = bgImage.getTileId(xAxis, yAxis, 0);
				String value = bgImage.getTileProperty(tileID, "blocked",
						"false");
				if ("true".equals(value)) {
					blocked[xAxis][yAxis] = true;
				}
				
				String miniID = bgImage.getTileProperty(tileID, "minigame", "none");
				miniArray[xAxis][yAxis] = miniID;
			}
		}
		
		// create events:
		events = new EventWindow[6]; // this is the number of events in this hub
		events[0] = new CatchWindow();
		events[1] = new DodgeWindow();
		events[2] = new InnWindow();
		events[3] = new LockedEvent(this);
		events[4] = new OldManEvent();
		events[5] = new ShopWindow();
		for (EventWindow event : events) {
			event.init(container, game);
		}
		miniNames = new String[] {"catch", "dodge", "inn", "locked", "oldman", "shop"};

		this.cameras = new Camera[2];
		cameras[0] = new Camera(container, bgImage, this.players[0]);
		cameras[1] = new Camera(container, bgImage, this.players[1]);

		//Music loop = new Music("Assets/Hub 1/Sound/Town2.wav");
		//loop.loop();
	}

	@Override
	public void displayHubBackground(Graphics g, Player player) {
	}

	@Override
	public void update(GameContainer container, StateBasedGame game,
			Player[] players, int delta) throws SlickException {
		super.update(container, game, players, delta);
	}
}