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

	public TownWindow(Player[] players, int[] locp1, int[] locp2)
			throws SlickException {
		super(players, locp1, locp2);

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// check to start a minigame
		// list of TownWindow (Hub1) events:
		// dodge, catch, oldman, locked, shop, inn
		for (int i = 0; i < players.length; i++) {
			if (miniArray[players[i].gridLoc[0]][players[i].gridLoc[1]].equals("dodge")) {
				System.out.println("yo what up dodge");
			} else if (miniArray[players[i].gridLoc[0]][players[i].gridLoc[1]].equals("catch")) {
				System.out.println("yo what up catch");
			} else if (miniArray[players[i].gridLoc[0]][players[i].gridLoc[1]].equals("oldman")) {
				System.out.println("yo what up oldman");
			} else if (miniArray[players[i].gridLoc[0]][players[i].gridLoc[1]].equals("locked")) {
				System.out.println("yo what up locked");
			} else if (miniArray[players[i].gridLoc[0]][players[i].gridLoc[1]].equals("shop")) {
				System.out.println("yo what up shop");
			} else if (miniArray[players[i].gridLoc[0]][players[i].gridLoc[1]].equals("inn")) {
				System.out.println("yo what up inn");
			}
		}
		// render player one screen
		if (eventOne != null) {
		} else {
			cameras[0].drawMap();
			cameras[0].translateGraphics();
			players[0].render(container, game, g, players[0].gridLoc[0] * 32
					+ players[0].floatLoc[0], players[0].gridLoc[1] * 32
					+ players[0].floatLoc[1]);
			if (players[1].gridLoc[0] < players[0].gridLoc[0]
					+ players[0].windowSize[0] - 32) {
				players[1].render(container, game, g, players[1].gridLoc[0]
						* 32 + players[1].floatLoc[0], players[1].gridLoc[1]
						* 32 + players[1].floatLoc[1]);
			}
			cameras[0].untranslateGraphics();

		}

		// render player two screen
		Player p2 = players[1];
		if (eventTwo != null) {

		} else {

			cameras[1].drawMap();
			cameras[1].translateGraphics();
			if (players[0].gridLoc[0] + 1 > players[1].gridLoc[0]
					- players[1].windowSize[0] / 2) {
				players[0].render(container, game, g, players[0].gridLoc[0]
						* 32 + players[0].floatLoc[0], players[0].gridLoc[1]
						* 32 + players[0].floatLoc[1]);
			}
			players[1].render(container, game, g, players[1].gridLoc[0] * 32
					+ players[1].floatLoc[0], players[1].gridLoc[1] * 32
					+ players[1].floatLoc[1]);
			cameras[1].untranslateGraphics();
		}
		container.getGraphics().resetTransform();
		float leftLoc = players[0].windowPos[0] + players[0].windowSize[0];
		float rightLoc = players[1].windowPos[0] - players[0].windowSize[0];
		g.setColor(Color.black);
		g.fillRect(leftLoc, (float) 0, rightLoc, (float) container.getHeight());

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

		this.cameras = new Camera[2];
		cameras[0] = new Camera(container, bgImage, this.players[0]);
		cameras[1] = new Camera(container, bgImage, this.players[1]);

		Music loop = new Music("Assets/Hub 1/Sound/Town2.wav");
		loop.loop();
	}

	@Override
	public void displayHubBackground(Graphics g, Player player) {
	}

	@Override
	public void update(GameContainer container, StateBasedGame game,
			Player[] players, int delta) throws SlickException {
		Input input = container.getInput();
		for (int i = 0; i < players.length; i++) {
			super.movePlayer(input, 5, players[i], delta);
			cameras[i].centerOn(players[i].gridLoc[0] * 32
					+ players[i].floatLoc[0], players[i].gridLoc[1] * 32
					+ players[i].floatLoc[1]);
		}
	}
}