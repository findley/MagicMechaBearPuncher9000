/*
 * Object: Window
 * Displays a single game state, but does so by displaying it to only a section
 * of the screen specified by parameters to the render call. This allows for the
 * rendering of this view multiple times in different locations on the screen,
 * or for the movement of this view around the screen.
 */


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.Image;

/**
 * Superclass for nodes (towns, field, castle, etc.)
 * @author msalvato
 *
 */

public class NodeWindow {

    protected boolean over = false;
    protected Player[] players;
    protected float[][] playerPos = new float[2][2];
    protected boolean[] inNode = {true,true};
    protected Image bgImage;
    public MiniGame[] miniGames;

    /*
     * Constructor that allows for providing of a stateID
     */
    public NodeWindow(Player[] players) {
        this.players = players;
    }

    public void displayMinigameBackground(Graphics g, Player player) {
        //do we want the +21? Probably, but easy to fix
    	//hacky image instead of tileset deal
        g.drawImage(bgImage.getSubImage(1000, 1000, 24*32, 16*32).getScaledCopy(590, 720),(int) (players[0].windowPos[0]),
                (int)(players[0].windowPos[1]));
        g.drawImage(bgImage.getSubImage(1000, 1000, 24*32, 16*32).getScaledCopy(590, 720),(int) (players[1].windowPos[0]+10),
                (int)(players[1].windowPos[1]));
    }

    public void render(GameContainer container, StateBasedGame game,
            Graphics g, Player[] player) throws SlickException {

    }

    public void init(GameContainer container, StateBasedGame game, Player[] players) throws SlickException{
    }

    public void update(GameContainer container, StateBasedGame game, int delta,
            Player[] players) throws SlickException {
    }

    public void enter(GameContainer container, StateBasedGame game,
            Player[] players) {
    }
    
    public void movePlayer(Input input, float moveValue, int playerIndex){
        if (input.isKeyDown(players[playerIndex].getButton("left"))) {
            if (playerPos[playerIndex][0] - moveValue > players[0].windowPos[0]) {
                playerPos[playerIndex][0] -= moveValue;
            }
        }
        if (input.isKeyDown(players[playerIndex].getButton("right"))) {
            if (playerPos[playerIndex][0] + players[playerIndex].pWidth + moveValue < players[0].windowPos[0]
                    + players[playerIndex].windowSize[0]) {
                playerPos[playerIndex][0] += moveValue;
            }
        }
        if (input.isKeyDown(players[playerIndex].getButton("up"))) {
            if (playerPos[playerIndex][1] - moveValue > players[playerIndex].windowPos[1]) {
                playerPos[playerIndex][1] -= moveValue;
            }
        }
        if (input.isKeyDown(players[playerIndex].getButton("down"))) {
            if (playerPos[playerIndex][1] + players[playerIndex].pHeight + moveValue < players[playerIndex].windowPos[1]
                    + players[playerIndex].windowSize[1]) {
                playerPos[playerIndex][1] += moveValue;
            }
        }
    }
    
    public boolean over() {
        return over;
    }
}