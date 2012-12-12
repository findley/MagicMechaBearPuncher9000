package states;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import weapons.Coin;
import weapons.Mecha;
import weapons.Weapon;

public class End extends AreaState {
	
	public End(int stateID) {
		super(stateID);
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.init(container, game);
		credit = new Image("Assets/JewelsAndMisc/credits.png");
		loop = new Music("Assets/Sound/Loops/EndLoop.wav");
		bgImage = new TiledMap("Assets/World/blackScreen.tmx");
		areaLength = 20;
		battleStops = new int[1];
		battleStops[0] = 0;
		winImgs = new Image[3];
		winImgs[0] = new Image("Assets/JewelsAndMisc/p0wins.png");
		winImgs[1] = new Image("Assets/JewelsAndMisc/p1wins.png");
		winImgs[2] = new Image("Assets/JewelsAndMisc/tie.png");
	}
	
	@Override
	public int getID() {
		return 5;
	}
}
