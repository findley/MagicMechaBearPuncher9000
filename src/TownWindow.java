import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class TownWindow extends HubWindow {
    private Double timer;

    public TownWindow(Player[] players) throws SlickException {
        super(players);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game,
            Graphics g, Player[] player) throws SlickException {
   
    }

    @Override
    public void init(GameContainer container, StateBasedGame game,
            Player[] players) throws SlickException {
        super.init(container, game, players);
        Music loop = new Music("Assets/Town2.wav");
        loop.loop();
        bgImage = new Image("Assets/Hub 1/FinalImageRef.png");
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta,
            Player[] player) throws SlickException {
        Input input = container.getInput();
        for (int i = 0; i < players.length; i++) {
            if (inNode[i]) {
                float moveValue = delta * .2f;
                super.movePlayer(input, moveValue, players[i]);
            }
        }
        timer -= 1.0 / 60;
        if (timer <= 0) {
            this.over = true;
        }
    }
}