package states;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import dudes.Knight;
import dudes.Monster;

public class TownState extends AreaState {

	public TownState(int stateID) {
		super(stateID);
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.init(container, game);
		bgImage = new TiledMap("Assets/Transition 1/Map.tmx");
		players[0].init(0);
		players[1].init(1);
		
		ArrayList<Monster> group_1 = new ArrayList<Monster>();
		Knight g1_knight1 = new Knight(950, 512);
		Knight g1_knight2 = new Knight(950, 544);
		Knight g1_knight3 = new Knight(950, 576);
		g1_knight1.init();
		g1_knight2.init();
		g1_knight3.init();
		group_1.add(g1_knight1);
		group_1.add(g1_knight2);
		group_1.add(g1_knight3);

		monsters.add(group_1);
		
		battleStops = new int[3];
		battleStops[0] = 1000;
		battleStops[1] = 2000;
		battleStops[2] = 3000;
	}
}
