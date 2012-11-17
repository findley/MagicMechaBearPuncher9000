package core;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import transitions.TransitionWindowOne;
import framework.DialogBox;
import framework.HubWindow;
import framework.Player;
import framework.TownWindow;
import framework.TransitionWindow;

public class InstructionsStateOld extends BasicGameState{

	Image background = null;
	
	public InstructionsStateOld(int stateID) {
		super();
	}
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
        background = new Image("Assets/Black.jpg");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
        String Title = "Instructions";
        g.drawString(Title, container.getWidth()/2f-50, 15);
        String leftPlay = "Player 1\na to move left\nw to move up\ns to move down\nd to move right\n";
        g.drawString(leftPlay,container.getWidth()/3f-50,container.getHeight()/2);
        String rightPlay = "Player 2\nuse the arrow keys\nto move left, right, up, and down";
        g.drawString(rightPlay,container.getWidth()*2/3f-50,container.getHeight()/2);
        String navKeys = "P for play\nM for menu\nEsc for Exit";
        g.drawString(navKeys, container.getWidth()/2f-50, container.getHeight()-75f);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
            container.exit();
        }
        if (container.getInput().isKeyPressed(Input.KEY_M)){
            game.enterState(0);
        }
        if (container.getInput().isKeyPressed(Input.KEY_P)){
            game.enterState(1);
        }
	}
	
    @Override
    public void enter(GameContainer container, StateBasedGame game)
            throws SlickException {
    	background = new Image("Assets/Black.jpg");
    }

	@Override
	public int getID() {
		return 2;
	}

}
