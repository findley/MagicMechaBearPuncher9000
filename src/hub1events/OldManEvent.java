package hub1events;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import framework.*;

public class OldManEvent extends EventWindow {
	private ArrayList<String> text;
	private int state;
	
	public OldManEvent() {
		text = new ArrayList<String>();
		text.add("Why hello there, young adventurer!");
		text.add("What? \nYou say you're not an adventurer?");
		text.add("Well of course you are! \nJust look how much you stand out from us regular folk! " +
				"\nYou probably just don't know how to be an adventurer!");
		text.add("What? You say could just read the instructions?");
		text.add("Nonsense I say! Here, let me explain how to move around!");
		text.add("Just press the directional buttons!");  
		text.add("Okay, you probably figured that out on your own, so how about this? " +
				"Did you know that while talking to people, you can press right to make them talk faster?");
		text.add("Quite a handy trick, I'd say. Try it out on our villagers to see what I mean.");
		text.add("But be warned! \nIn times of great peril such as these, people often ask for pointless favors in return for slightly relevant aid toward your cause. " +
				"\nIt is not in your nature to refuse such requests, so I must implore you, talk with caution! "); 
		state = 0;
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
}
