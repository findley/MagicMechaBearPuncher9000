package hub1events;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import framework.*;

public class LockedEvent extends EventWindow {
	private ArrayList<String> text;
	private int state;
	
	public LockedEvent() {
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if (state == 0) {
			dialogBox.playMsg(text);
			state = 1;
		}
		if (state == 1) {
			if (!dialogBox.isActive()) {
				inside[player.playerNum - 1] = false;
			}
		}
	}
	
	public void start() {
		text = new ArrayList<String>();
		text.add("The door won't budge.");
		state = 0;
	}
}
