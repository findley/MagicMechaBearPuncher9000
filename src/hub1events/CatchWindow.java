package hub1events;


import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import framework.*;

public class CatchWindow extends EventWindow {
    private float[][] objPos = new float[10][2];
    private Image objSprite;
    private boolean[] objVis = new boolean[10];
    private float[][] objSpd = new float[10][2];
    private int counter; 
    private UnicodeFont font;
    private int state; // 0, 1, 2 // intro dialogue, GAME, ending
    private Image farmer;

    public CatchWindow() throws SlickException {
        objSprite = new Image("Assets/Hub 1/Images/sheep_1.png");
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        super.init(container, game);
        //bgImage = tempImage.getSubImage(1000, 1000, 24*32, 16*32).getScaledCopy(590, 720);
        bg = new Image("Assets/Hub 1/Images/catch_bg.png");
        Font awtFont = new Font("Arial Monospaced", Font.BOLD, 24);
        font = new UnicodeFont(awtFont);
        font.getEffects().add(new ColorEffect(java.awt.Color.black));
        font.addAsciiGlyphs();
        font.loadGlyphs();
    }
    
    public void start() {
        player.eventLoc[0] = player.windowPos[0] + player.windowSize[0] - player.pWidth;
        player.eventLoc[1] = player.windowPos[1] + (int) player.windowSize[1] / 2;
        for (int i = 0; i < objPos.length; i++) {
            objPos[i][0] = (float) 1.22;
            objPos[i][0] = player.windowPos[0] + (int) ((player.windowSize[0] - 100) * Math.random());
            objPos[i][1] = player.windowPos[1] + (int) ((player.windowSize[1] - 50) * Math.random());
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
		state = 0;
		text.add("Ah, Hero! How do you fare on this day?");
		text.add("Me? I'm actually in a pinch right now. My sheep seem to have lost all sense of, well, "
				+ "what it means to be a sheep, and I'm having difficulty herding them back behind the fence.");
		text.add("Actually, you��ve come at a good time! Do you think you could help me get my sheep back to the farm?");
	}

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        this.displayMinigameBackground(g);
        for (int i = 0; i < objPos.length; i++) {
            if (objVis[i])
                g.drawImage(objSprite, objPos[i][0], objPos[i][1]);
        }
        player.render(container, game, g, player.eventLoc[0], player.eventLoc[1]);
        g.setFont(font);
        g.drawString(Double.toString(Math.ceil(counter)), player.windowPos[0] + 350, player.windowPos[1]);
        g.setColor(Color.white);
        g.drawString("Collect The Sheep!", 100 + player.windowPos[0], 65);
        g.setColor(Color.black);
    }
    
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
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
        if (counter == 0) {
            inside[player.playerNum - 1] = false;
            player.gridLoc[1]++;
        }
    }
}
