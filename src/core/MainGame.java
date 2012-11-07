package core;
import org.newdawn.slick.Color;
import java.awt.Font;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.Effect;
import org.newdawn.slick.state.StateBasedGame;

public class MainGame extends StateBasedGame {
 
	public static final int GAME_WIDTH = 1280;
	public static final int GAME_HEIGHT = 720;
	
	@SuppressWarnings("unchecked")
    public static UnicodeFont loadFont(String name, int style, int size,
            java.awt.Color color) throws SlickException {
        UnicodeFont font = new UnicodeFont(new Font(name, style, size));
        font.addAsciiGlyphs();
        ((List<Effect>) font.getEffects()).add(new ColorEffect(color));
        font.loadGlyphs();
        return font;
    }
	
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
