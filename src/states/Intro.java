package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Intro extends BasicGameState{

	private Image background;
	private Image begin;
	
	public Intro(int stateID) {
		super();
	}
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
        background = new Image("Assets/Black.jpg").getScaledCopy(container.getWidth(), container.getHeight());
        begin = new Image("Assets/JewelsAndMisc/opening.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		background.draw(0, 0);
		begin.draw((background.getWidth() - begin.getWidth()) / 2,
				(background.getHeight() - begin.getHeight()) / 2);
        
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
            container.exit();
        }
        if (container.getInput().isKeyPressed(Input.KEY_C)){
            game.enterState(2);
        }
	}
	
	@Override
	public int getID() {
		return 6;
	}
}
