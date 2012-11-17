package core;
import org.newdawn.slick.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.Effect;
import org.newdawn.slick.state.StateBasedGame;

import dudes.Player;

import states.TownState;

public class MainGame extends StateBasedGame {
 
	public static final int GAME_WIDTH = 1024;
	public static final int GAME_HEIGHT = 768;
	
	public static Player[] players = new Player[2];

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
			app.setShowFPS(true);
			
			HashMap<String, Integer> p0Buttons = new HashMap<String, Integer>();
			p0Buttons.put("up", Input.KEY_W);
			p0Buttons.put("left", Input.KEY_A);
			p0Buttons.put("down", Input.KEY_S);
			p0Buttons.put("right", Input.KEY_D);
			p0Buttons.put("action", Input.KEY_T);
			HashMap<String, Integer> p1Buttons = new HashMap<String, Integer>();
			p1Buttons.put("up", Input.KEY_UP);
			p1Buttons.put("left", Input.KEY_LEFT);
			p1Buttons.put("down", Input.KEY_DOWN);
			p1Buttons.put("right", Input.KEY_RIGHT);
			p1Buttons.put("action", Input.KEY_PERIOD);
			
			// set up players
			players[0] = new Player(p0Buttons, 100f, 650f);
			players[1] = new Player(p1Buttons, 150f, 600f);
			
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        
        addState(new MainMenuState(0));
        addState(new TownState(1));
        addState(new InstructionsState(2));
    }
}
