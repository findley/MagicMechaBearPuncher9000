package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import core.MainGame;

import dudes.Player;


public class AreaState extends BasicGameState {
	protected Player[] players;
	protected TiledMap bgImage;
	
	private int progression;
	
	public AreaState(int stateID) {
		super();
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		progression = 0;
		players = MainGame.players;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		bgImage.render(-progression%32, 0, progression/32, 0, 32 + 1, 24);
		for (int i = 0; i < players.length; i++) {
			players[i].render(g);
		}
	}
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
		//progression++;
		for (int i = 0; i < players.length; i++) {
			players[i].move(container.getInput(), delta);
		}
		
		float backPlayerPos = Math.min(players[0].pos[0], players[1].pos[0]);
		if (backPlayerPos > MainGame.GAME_WIDTH/3) {
			float shift = backPlayerPos - MainGame.GAME_WIDTH/3;
			progression += shift;
			players[0].pos[0] -= shift;
			players[1].pos[0] -= shift;
		}
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
}
