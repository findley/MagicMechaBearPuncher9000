

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class CatchWindow extends EventWindow {
    private float[][] objPos = new float[10][2];
    private Image objSprite;
    private boolean[] objVis = new boolean[10];
    private float[][] objSpd = new float[10][2];
    private int counter; 
	private Image bgImage;
    private UnicodeFont uFont;


    public CatchWindow(Player player) throws SlickException {
        super(player);
        objSprite = new Image("Assets/sheep.png");
        uFont = new UnicodeFont(new Font("Arial Monospace", Font.BOLD, 40));
        // TODO Change objSprite
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g, Player player) throws SlickException {
        this.displayMinigameBackground(g, player);
        for (int i = 0; i < objPos.length; i++) {
            if (objVis[i])
                g.drawImage(objSprite, objPos[i][0], objPos[i][1]);
        }
        player.render(container, game, g, player.eventLoc[0], player.eventLoc[1]);
        g.drawString(Double.toString(Math.ceil(counter)), player.windowPos[0] + 350, player.windowPos[1]);

        g.setColor(Color.white);
        g.setFont(uFont);
        g.drawString("Collect most objects", 100 + player.windowPos[0], 65);
        g.setColor(Color.black);
    }
    
	public void displayMinigameBackground(Graphics g, Player player) {
		g.drawImage(bgImage.getSubImage(1000, 1000, 24 * 32, 16 * 32)
				.getScaledCopy(590, 720), (int) (player.windowPos[0]),
				(int) (player.windowPos[1]));    	
	}

    @Override
    public void init(GameContainer container, StateBasedGame game, Player player) throws SlickException {
        super.init(container, game, player);
      //bg for each player... in case they're different?
        Image tempImage = new Image("Assets/Hub 1/FinalImageRef.png");
        bgImage = tempImage.getSubImage(1000, 1000, 24*32, 16*32).getScaledCopy(590, 720);
        player.eventLoc[0] = player.windowPos[0] + player.windowSize[0] - player.pWidth;
        player.eventLoc[1] = player.windowPos[1] + (int) player.windowSize[1] / 2;
        for (int i = 0; i < objPos.length; i++) {
            objPos[i][0] = player.windowPos[0] + (int) ((player.windowSize[0] - 100) * Math.random());
            objPos[i][1] = player.windowPos[1] + (int) (player.windowSize[1] * Math.random());
            objVis[i] = true;
            double x = Math.random();
            if (Math.random() < .5)
                x *= -1;
            double y = Math.pow(1 - Math.pow(x, 2), .5);
            if (Math.random() < .5)
                y *= -1;
            objSpd[i] = new float[] { (float) x, (float) y };
        }
        counter = objVis.length;
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta, Player player) throws SlickException {
        Input input = container.getInput();

        float moveValue = delta * .2f;
        if (input.isKeyDown(player.getButton("left"))) {
            if (player.eventLoc[0] - moveValue > player.windowPos[0]) {
                player.eventLoc[0] -= moveValue;
            }
        }
        if (input.isKeyDown(player.getButton("right"))) {
            if (player.eventLoc[0] + player.pWidth + moveValue < player.windowPos[0] + player.windowSize[0]) {
                player.eventLoc[0] += moveValue;
            }
        }
        if (input.isKeyDown(player.getButton("up"))) {
            if (player.eventLoc[1] - moveValue > player.windowPos[1]) {
                player.eventLoc[1] -= moveValue;
            }
        }
        if (input.isKeyDown(player.getButton("down"))) {
            if (player.eventLoc[1] + player.pHeight + moveValue < player.windowPos[1] + player.windowSize[1]) {
                player.eventLoc[1] += moveValue;
            }
        }
        for (int i = 0; i < objPos.length; i++) {
            if (objVis[i]) {
                if (player.windowPos[0] + player.windowSize[0] < objPos[i][0] + 40 + moveValue * objSpd[i][0]
                        || objPos[i][0] + moveValue * objSpd[i][0] < player.windowPos[0]) {
                    objSpd[i][0] *= -1;
                }
                objPos[i][0] += moveValue * objSpd[i][0];
                if (player.windowPos[1] + player.windowSize[1] < objPos[i][1] + 40 + moveValue * objSpd[i][1]
                        || objPos[i][1] + moveValue * objSpd[i][1] < player.windowPos[1]) {
                    objSpd[i][1] *= -1;
                }
                objPos[i][1] += moveValue * objSpd[i][1];
            }
        }

        for (int i = 0; i < objPos.length; i++) {
            if (objVis[i] && (new Rectangle(objPos[i][0], objPos[i][1], 32,32)).intersects(player.collisionRect)) {
                counter -= 1;
                objVis[i] = false;
            }
        }
        if (counter <= 2) {
            this.over = true;
        }
    }
}
