package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import players.Player;

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
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		bgImage.render(-progression, 0, 0, 0, 100, 100);
		
	}
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		progression++;
		
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	public void movePlayer(Input input, float moveValue, Player player,
			int delta) {
		
	}
}
