package transitions;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import framework.DialogBox;
import framework.Player;
import framework.TransitionWindow;

public class TransitionWindowZero extends TransitionWindow {
	private Image bgIm;
	private ArrayList<String> text;
	private int state;

	public TransitionWindowZero(Player[] players, DialogBox dialogBox,
			int[] locp1, int[] locp2) {
		super(players, dialogBox, locp1, locp2);
	}

	public void displayHubBackground(Graphics g, Player player) {

	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.drawImage(bgIm, 0, 0);
	}

	public void init(GameContainer container, StateBasedGame game,
			Player[] players) throws SlickException {
		this.bgIm = new Image("Assets/transitionZero.png");
		text = new ArrayList<String>();
		text.add("Hero! Welcome to the world of Poke...I mean the world.   "+
				 "I'm Bob McDonald, the local farmer.  I've known you since "+
				"you were a little boy! This here is your twin brother and rival! "+
				"As you boys probably know, we've experienced peace for quite " +
				"a long time. In fact, today makes it 1000 years of peace right " +
				"on the dot! What could possibly go wrong? You boys are lucky... " +
				"You will never have to fight evil or go out on some dangerous "+
				"journey to save the world. You'll just stay here helping me on "+
				"the farm. Speaking of which, do you remember my beautiful daughter? "+
				"Many say she is the fairest maiden in all the lands. Stay away from her! "+
				"Now go! Do obscenely boring things in the town where you will probably " +
				"spend the rest of your life... ");
		state = 0;
	}

	public void update(GameContainer container, StateBasedGame game,
			Player[] players, int delta) throws SlickException {
		if (state == 0) {
			transBox.playMsg(text);
			state = 1;
		}
		if (state == 1) {
			if (!transBox.isActive()) {
				over = true;
			}
		}
	}

}
