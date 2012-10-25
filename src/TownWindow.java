import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class TownWindow extends NodeWindow {
    private Double timer;

    public TownWindow(Player[] players) throws SlickException {
        super(players);
        // TODO Change objSprite
    }

    @Override
    public void render(GameContainer container, StateBasedGame game,
            Graphics g, Player[] player) throws SlickException {
        for(int i = 0; i < player.length; i++){
            if(inNode[i]){
                this.displayMinigameBackground(g, player[i]);
            }
        }
        for (int i = 0; i < players.length; i++) {
            if (inNode[i]) {
                player[i].render(container, game, g, playerPos[i][0] + container.getWidth()/2,
                        playerPos[i][1]);
                player[i].render(container, game, g, playerPos[i][0],
                        playerPos[i][1]);
                GameState state = (GameState) (game.getCurrentState());
                g.setFont(state.uFont);
                g.setColor(Color.black);
                g.drawString(Double.toString(Math.ceil(timer)),
                        players[i].windowPos[0] + container.getWidth() / 4,
                        players[i].windowPos[1]);
                // UnicodeFont uFont = state.uFont;
            }
        }
    }

    @Override
    public void init(GameContainer container, StateBasedGame game,
            Player[] players) throws SlickException {
        super.init(container, game, players);
        for (int i = 0; i < players.length; i++) {
            /*
             * playerPos[i][0] = players[i].windowPos[0] +
             * players[i].windowSize[0] - players[i].pWidth; playerPos[i][1] =
             * players[i].windowPos[1] + (int) players[i].windowSize[1] / 2;
             */
            playerPos[i][0] = 0;
            playerPos[i][1] = 0;
            timer = 4000.0;
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta,
            Player[] player) throws SlickException {
        Input input = container.getInput();
        for (int i = 0; i < players.length; i++) {
            if (inNode[i]) {
                float moveValue = delta * .2f;
                if (input.isKeyDown(players[i].getButton("left"))) {
                    if (playerPos[i][0] - moveValue > players[i].windowPos[0]) {
                        playerPos[i][0] -= moveValue;
                    }
                }
                if (input.isKeyDown(players[i].getButton("right"))) {
                    if (playerPos[i][0] + players[i].pWidth + moveValue < players[i].windowPos[0]
                            + players[i].windowSize[0]) {
                        playerPos[i][0] += moveValue;
                    }
                }
                if (input.isKeyDown(players[i].getButton("up"))) {
                    if (playerPos[i][1] - moveValue > players[i].windowPos[1]) {
                        playerPos[i][1] -= moveValue;
                    }
                }
                if (input.isKeyDown(players[i].getButton("down"))) {
                    if (playerPos[i][1] + players[i].pHeight + moveValue < players[i].windowPos[1]
                            + players[i].windowSize[1]) {
                        playerPos[i][1] += moveValue;
                    }
                }
            }
        }
        timer -= 1.0 / 60;
        if (timer <= 0) {
            this.over = true;
        }
    }
}