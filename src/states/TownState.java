package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class TownState extends AreaState {

	public TownState(int stateID) {
		super(stateID);
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.init(container, game);
		//bgImage = new TiledMap("Assets/TiledEditor/DanielHub.tmx");
		bgImage = new TiledMap("Assets/Transition 1/Map.tmx");
	}
}
