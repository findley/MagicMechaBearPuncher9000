

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState {

    
    int stateID = -1;
    
    MainMenuState( int stateID ) {
        this.stateID = stateID;
    }
    
    
    Image background = null;
    


    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        // TODO Auto-generated method stub
        background = new Image("Assets/Background.png");
        System.out.println("init Menu");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        // TODO Auto-generated method stub
        background.draw(0,0);
        String Title = "Main Menu";
        g.drawString(Title, container.getWidth()/2f, 15);
        String navKeys = "P for play\nI for instructions\nEsc for Exit";
        g.drawString(navKeys, container.getWidth()/2f-50, container.getHeight()-75f);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        // TODO Auto-generated method stub
        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
            container.exit();
        }
        if (container.getInput().isKeyPressed(Input.KEY_I)){
            game.enterState(0);
        }
        if (container.getInput().isKeyPressed(Input.KEY_P)){
            game.enterState(1);
        }
        
    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return stateID;
    }

}
