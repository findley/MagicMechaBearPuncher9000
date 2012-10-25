/*
 * Object: Window
 * Displays a single game state, but does so by displaying it to only a section
 * of the screen specified by parameters to the render call. This allows for the
 * rendering of this view multiple times in different locations on the screen,
 * or for the movement of this view around the screen.
 */

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;

public class NodeWindow {

    protected boolean over = false;
    protected Player[] players;
    protected float[][] playerPos = new float[2][2];
    protected boolean[] inNode = {true,true};
    private Image bgImageOne;
    private Image bgImageTwo;

    /*
     * Constructor that allows for providing of a stateID
     */
    public NodeWindow(Player[] players) {
        this.players = players;
    }

    public void displayMinigameBackground(Graphics g, Player player) {
        g.drawImage(bgImageOne, players[0].windowPos[0] + 21,
                players[0].windowPos[1] + 21);
        g.drawImage(bgImageTwo, players[1].windowPos[0] + 21,
                players[1].windowPos[1] + 21);
    }

    public void render(GameContainer container, StateBasedGame game,
            Graphics g, Player[] player) throws SlickException {

    }

    public void init(GameContainer container, StateBasedGame game, Player[] players)
            throws SlickException {
        try {
            bgImageOne = new Image("Assets/Background.png");
            bgImageTwo = new Image("Assets/Background.png");
        } catch (SlickException e) {
            System.out.println("Error loading mini game resources.");
        }
    }

    public void update(GameContainer container, StateBasedGame game, int delta,
            Player[] players) throws SlickException {
    }

    public void enter(GameContainer container, StateBasedGame game,
            Player[] players) {
    }

    public boolean over() {
        return over;
    }
}