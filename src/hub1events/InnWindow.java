package hub1events;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import framework.EventWindow;

public class InnWindow extends EventWindow {
    private Image broSprite;
    private Image pillowSprite;
	//Where each brother is located
	private float[][] broLoc = new float[4][2];
	//Time till next time he can get hit
	private float[] broDelay = new float[]{0,0,0,0};
	private float[] broFlashTime = new float[]{100,100,100,100};
	//Time till can get hit again, 1 second.
	private float timeDelay = 1000;
	//Is bro visible?
	private boolean[] broVis = new boolean[]{false, false, false, false};
	//# of hits per bro remaining.
	private int[] broHP = new int[]{3,3,3,3};
	//Are all the bros dead?
	private boolean win = false;
	
	private ArrayList<Point> pillows = new ArrayList<Point>();

	private ArrayList<String> text;
	private int state; //-1, 0, 1, 2, 3;

	
    public InnWindow() throws SlickException {
    	//TODO: INSERT BROSPRITE && PILLOWSPRITE
    	state = -1;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
    /*	//TODO: INSERT BACKGROUND TO INN
    	//Player is in the middle of the screen
        player.eventLoc[0] = player.windowPos[0] + player.windowSize[0] / 2;
        player.eventLoc[1] = player.windowPos[1] + player.windowSize[1] / 2;
        //Bro 0 is left of the player;
        broLoc[0][0] = player.windowPos[0] + player.windowSize[0] / 4;
        broLoc[0][1] = player.windowPos[1] + player.windowSize[1] / 2;
        //Bro 1 is right of the player;
        broLoc[1][0] = player.windowPos[0] + player.windowSize[0] * 3 / 4;
        broLoc[1][1] = player.windowPos[1] + player.windowSize[1] / 2;
        //Bro 2 is above the player;
        broLoc[2][0] = player.windowPos[0] + player.windowSize[0] / 2;
        broLoc[2][1] = player.windowPos[1] + player.windowSize[1] / 4;
        //Bro 3 is below the player;
        broLoc[3][0] = player.windowPos[0] + player.windowSize[0] / 2;
        broLoc[3][1] = player.windowPos[1] + player.windowSize[1] * 3 / 4;*/
    }
    
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        this.displayMinigameBackground(g);
        player.render(container, game, g, player.eventLoc[0], player.eventLoc[1]);
        for (int i = 0; i < 4; i++) {
        	//If hittable and hp is above 0, draw the bro
        	if (broVis[i] == true && broHP[i] > 0) {
        		broSprite.draw(broLoc[i][0], broLoc[i][1]);
        	}
        }
        for (Point i: pillows) {
        	pillowSprite.draw(i.x, i.y);
        }
    }
    
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();
        // Case -1: Intro State
        if (state == -1) {
    		//TODO: ADD DIALOGUE;
        	state = 0;
        } else {
	        // Case  0: Input State: checks inputs, generates Pillows;
			if (!dialogBox.isActive()) {
		        if (input.isKeyDown(player.getButton("left"))) {
		        	//Throw Left
		        }
		        if (input.isKeyDown(player.getButton("right"))) {
		        	//Throw Right
		        }
		        if (input.isKeyDown(player.getButton("up"))) {
		        	//Throw Up
		        }
		        if (input.isKeyDown(player.getButton("down"))) {
		        	//Throw Down
		        }
			}
			// Case  1: Spawn State: spawns hittable bros if possible;
	        if (win) {
	            inside[player.playerNum - 1] = false;
	        } else {
	        	//Randomly determine whether or not Sprite[x] is available to hit.
	        	for (int i = 0; i < 4; i++ ) {
	        		//Done Cooldown, now has a chance to be spawned again.
	        		if (broDelay[i] == 0) {
	        			if (Math.random()*100 < 20) {
	        				broVis[i] = true;
	        			}
	        		} else {
	            		broFlashTime[i] -= delta;
	            		if (broFlashTime[i] <= 0 ) {
	            			broVis[i] = !broVis[i];
	            			broFlashTime[i] = 100;
	            		}
	        			broDelay[i] -= delta;
	        		}
	        	}
	        }
	        // CASE  2: CHECK STATE: CHECK FOR COLLISIONS BETWEEN
        }
    }
}
