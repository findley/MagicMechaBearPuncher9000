

import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MainGame extends StateBasedGame {
 
	public static final int GAME_WIDTH = 1200;
	public static final int GAME_HEIGHT = 720;

	public MainGame() {
        super("How to Save the World in 15 Minutes");
    }

    /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			AppGameContainer app = new AppGameContainer(new MainGame());
			app.setDisplayMode(GAME_WIDTH, GAME_HEIGHT, false);
			app.setTargetFrameRate(60);
			app.setShowFPS(false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        
        addState(new MainMenuState(0));
        addState(new GameState(1));

    }
}
