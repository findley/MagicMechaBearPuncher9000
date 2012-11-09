package hub1events;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.StateBasedGame;

import framework.*;

public class ShopWindow extends EventWindow {

	private final static int mysteryVal = 70;
	private int[] guess;
	private int state; // -1, 0, 1, 2, 3, 4, 5. start, talking, waiting, too
						// low, too high,
						// just right, ERROR.
	private UnicodeFont font;

	public ShopWindow() {
		state = -1;
		guess = new int[] { 0, 0 };
	}

	@SuppressWarnings("unchecked")
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.init(container, game);
		bg = new Image("Assets/Hub 1/Images/shop_bg.png");
		Font awtFont = new Font("Arial Monospaced", Font.BOLD, 24);
		font = new UnicodeFont(awtFont);
		font.getEffects().add(new ColorEffect(java.awt.Color.black));
		font.addAsciiGlyphs();
		font.loadGlyphs();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		displayMinigameBackground(g);

		g.setFont(font);
		g.drawString("" + guess[0], 473 + player.windowPos[0], 483);
		g.drawString("" + guess[1], 516 + player.windowPos[0], 483);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		if (state == -1) {
			state = 0;
			text = new ArrayList<String>();
			text.add("Hehe how we doing on this fine day? "
					+ "Well, well, I see you're eyeing that magnificent sword! "
					+ "It's a masterpiece, my most prized possession. "
					+ "I'll tell you what, if you can guess its price, "
					+ "I'll give it to you for free!!! "
					+ "\nPress left to make a guess.");
			dialogBox.playMsg(text);
		}
		if (state == 0) {
			if (!dialogBox.isActive()) {
				if (guess[0] * 10 + guess[1] == mysteryVal) {
					// YOU WIN
					inside[player.playerNum - 1] = false;
					player.gridLoc[1]++;
				}
				state = 1;
			}
		}
		if (state == 1) {
			if (input.isKeyDown(player.getButton("left"))) {
				state = priceCheck(guess[0] * 10 + guess[1]);
			}
			if (input.isKeyDown(player.getButton("up"))) {
				if (input.isKeyPressed(player.getButton("up"))) {
					if (guess[0] < 9 || guess[1] == 0) {
						if (guess[1] == 5) {
							guess[0] += 1;
						}
						guess[1] = (guess[1] + 5) % 10;
					}
				}
			}
			if (input.isKeyDown(player.getButton("down"))) {
				if (input.isKeyPressed(player.getButton("down"))) {
					if (guess[0] != 0 || guess[1] != 0) {
						if (guess[1] == 0) {
							guess[0] -= 1;
						}
						guess[1] = (guess[1] + 5) % 10;
					}
				}
			}
		} else if (state == 2) {
			// TOO LOW
			state = 0;
			text = new ArrayList<String>();
			text.add("What do you think this is? A dollar store?");
			dialogBox.playMsg(text);
		} else if (state == 3) {
			// TOO HIGH
			state = 0;
			text = new ArrayList<String>();
			text.add("That sword's not made out of gold you know...");
			dialogBox.playMsg(text);
		} else if (state == 4) {
			// JUST RIGHT
			state = 0;
			text = new ArrayList<String>();
			text.add("Wow great guess! Now fork over $70.");
			text.add("...\nHahaha you didn't actually think I would give it to you for free did you?");
			text.add("...");
			text.add("WHATTTTT!!! You're broke? ... Ugh fine, just take it.");
			dialogBox.playMsg(text);
		}
	}

	public void start() {
		state = -1;
		guess = new int[] { 0, 0 };
	}

	private int priceCheck(int guess) {
		if (guess < mysteryVal) {
			return 2;
		} else if (guess > mysteryVal) {
			return 3;
		} else if (guess == mysteryVal) {
			return 4;
		}
		return 5; // ERROR
	}
}
