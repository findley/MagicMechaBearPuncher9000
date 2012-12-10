package core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class InstructionsState extends BasicGameState{

	private Image background;
	private Image instro;
	
	public InstructionsState(int stateID) {
		super();
	}
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
        background = new Image("Assets/JewelsAndMisc/menu.png");
        instro = new Image("Assets/JewelsAndMisc/ins.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
        String Title = "Instructions";
        g.drawString(Title, container.getWidth()/2f-50, 15);
        String leftPlay = "Player 1\na to move left\nw to move up\ns to move down\nd to move right\nf to attack\nq to pick up things";
        g.drawString(leftPlay,container.getWidth()/3f-50,container.getHeight()/2);
        String rightPlay = "Player 2\nuse the arrow keys\nto move left, right, up, and down\n. to attack\n, to pick up things";
        g.drawString(rightPlay,container.getWidth()*2/3f-50,container.getHeight()/2);
        String navKeys = "P for play\nM for menu\nEsc for Exit";
		g.drawString(navKeys, container.getWidth() / 2f - 50,
				container.getHeight() - 75f);
		background.draw(0, 0);
		instro.draw((background.getWidth() - instro.getWidth()) / 2,
				(background.getHeight() - instro.getHeight()) / 2);
        
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
            game.enterState(2);
        }
	}
	
	@Override
	public int getID() {
		return 1;
	}

}
