package core;


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
        background = new Image("Assets/Black.jpg");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        // TODO Auto-generated method stub
        //background.draw(0,0);
        String Title = "WHO IS BETTER AT SAVING THE PRINCESS?";
        g.drawString(Title, container.getWidth()/2f - 170, container.getHeight()/8f);
        String Story = "THE PRINCESS HAS BEEN KIDNAPPED!\nWHOEVER CAN SAVE HER BETTER WILL WIN HER HAND.";
        g.drawString(Story, container.getWidth()/2f - 300, container.getHeight()/5f);
        String explain = "Kill monsters and collect the jewels they drop to gain points\n"+
        				 "Monsters also drop interesting weapons! Pick them up with your item key!\n"+
        				 "Avoid obstacles and try to get more points than your opponent!";
        g.drawString(explain, container.getWidth()/2f - 300, container.getHeight()/3f);
        String leftPlay = "Player 1\na to move left\nw to move up\ns to move down\nd to move right\nt to attack\nq to pick up things";
        g.drawString(leftPlay,container.getWidth()/2f - 300,container.getHeight()/2);
        String rightPlay = "Player 2\nuse the arrow keys\nto move left, right, up, and down\n. to attack\n, to pick up things";
        g.drawString(rightPlay,container.getWidth()/2f,container.getHeight()/2);
        String navKeys = "P for play\nEsc for Exit";
        g.drawString(navKeys, container.getWidth()/2f-50, container.getHeight()-100f);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        // TODO Auto-generated method stub
        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
            container.exit();
        }
        if (container.getInput().isKeyPressed(Input.KEY_I)){
            game.enterState(1);
        }
        if (container.getInput().isKeyPressed(Input.KEY_P)){
            game.enterState(2);
        }
        
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game)
            throws SlickException {
    	background = new Image("Assets/Black.jpg");
    }
    
    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return stateID;
    }

}
