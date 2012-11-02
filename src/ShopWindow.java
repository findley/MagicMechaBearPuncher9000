import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class ShopWindow extends EventWindow {

	private final static int mysteryVal = 70;
	private int guess;
	private int state; // 0, 1, 2, 3, 4, 5. talking, waiting, too low, too high, just right, ERROR.
	
	public ShopWindow(Player player) {
		super(player);
		state = 0;
		guess = 0;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g, Player player) throws SlickException {
		g.drawString("" + guess, 100 + player.windowPos[0], 100);
		
	}
	
	@Override
    public void update(GameContainer container, StateBasedGame game, int delta, Player player) throws SlickException {
        Input input = container.getInput();
        if (state == 1) {
	        if (input.isKeyPressed(player.getButton("up"))) {
	        	guess += 10;
	        }
	        if (input.isKeyPressed(player.getButton("down"))) {
	        	guess -= 10;
	        }
	        if (input.isKeyPressed(player.getButton("right"))) {
	        	state = priceCheck(guess);
	        }
        } else if (state == 2) {
			state = 0;
			// START TOO LOW TALK
		} else if (state == 3) {
			state = 0;
			// START TOO HIGH TALK
		} else if (state == 4) {
			state = 0;
			// START JUST RIGHT TALK
		}
		
        
        
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
