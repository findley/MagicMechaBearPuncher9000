package hub1events;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.ResourceLoader;


import org.newdawn.slick.Color;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;

import framework.*;

public class DodgeWindow extends EventWindow {
    private float[][] objPos = new float[20][2];
    private Image objSprite;
    private boolean[] objVis = new boolean[20];
    private float[][] objSpd = new float[20][2];
    private Double timer;
    private UnicodeFont font;


    public DodgeWindow() throws SlickException {
        objSprite = new Image("Assets/Hub 1/Images/rock.png");
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
        g.setColor(Color.black);
        g.drawString(Double.toString(Math.ceil(timer)), player.windowPos[0] + container.getWidth()/4, player.windowPos[1]);
        g.setColor(Color.white);        
        g.drawString("Survive for 6 seconds!", 60 + player.windowPos[0], 65);
        
    }
    
    public void start() {
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
        timer = 6.0;
    }
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        super.init(container, game);
        this.bg = new Image("Assets/Hub 1/Images/dodge_bg.png");
        //bg = new Image("Assets/Hub 1/Images/shop_bg.png");
        Font awtFont = new Font("Arial Monospaced", Font.BOLD, 24);
        font = new UnicodeFont(awtFont);
        font.getEffects().add(new ColorEffect(java.awt.Color.black));
        font.addAsciiGlyphs();
        font.loadGlyphs();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();

        float moveValue = delta * .2f;
        
        super.movePlayer(input, moveValue, 0);
        
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

        //Rectangle playerShape = new Rectangle(player.eventLoc[0], player.eventLoc[1], 20, 20);
        for (int i = 0; i < objPos.length; i++) {
            if (objVis[i] && (new Rectangle(objPos[i][0], objPos[i][1], 40, 40)).intersects(player.collisionRect)) {
                timer = 6.0;
                objVis[i] = false;
            }
        }
        timer -= 1.0 / 60;
        if (timer <= 0) {
            inside[player.playerNum - 1] = false;
            player.gridLoc[1]++;
        }
    }
}
