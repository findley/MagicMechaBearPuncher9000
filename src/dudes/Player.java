package dudes;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import core.MainGame;

import weapons.Fist;
import weapons.Weapon;

public class Player extends Dude {
    public HashMap<String, Integer> buttons;
    public int                      playerID;
    public int                      score;
    private final int               RESPAWN_TIMER = 5000;
    public boolean                  isRespawning;
    public Animation[]       playerDeath = new Animation[2];
    public boolean					webbed = false;
    public float					constSpeed;
    
    public Player(HashMap<String, Integer> buttons, float xPos, float yPos) {
        this.buttons = buttons;
        this.isRight = true;
        pos[0] = xPos;
        pos[1] = yPos;
        moveSpeed = 3;
        constSpeed = moveSpeed;
        maxHealth = 100;
        health = maxHealth;
        score = 0;
        healthFill = new Color(0f, 1f, 0f, 1f);
        attackTime = 0;
        this.weapon = new Fist(this);
        hitbox = new Rectangle(pos[0], pos[1], weapon.playerSize, weapon.playerSize);
        deathTimer = 0;
    }
    
    public void init(int playerID) throws SlickException {
        this.playerID = playerID;
        this.sprites = new SpriteSheet("Assets/players/player"+playerID+"Death.png",64,64);
        // create spritesheets for the weapon:
        this.weapon.init();
        
        SpriteSheet deathSprites = new SpriteSheet("Assets/players/player" + playerID + "Death.png", 64, 64);
        playerDeath[0] = new Animation(deathSprites, 0, 0, 2, 0, true, 100, true);
        playerDeath[0].setLooping(false);
        playerDeath[1] = new Animation(deathSprites, 3, 0, 5, 0, true, 100, true);
        playerDeath[1].setLooping(false);
    }
    
    public void move(Input input, int delta) throws SlickException{
    	if (isRespawning) {
    		return;
    	}
        if (currentAnimation != null) {
            if (currentAnimation.isStopped()) {
                currentAnimation.restart();
                currentAnimation = null;
            }
        }
        if (flinching) {
            flinchTime += delta;
            if (flinchTime < flinchDur) {
                return;
            } else {
                flinching = false;
            }
        }
        
        if (isAttacking) {
            attackTime += delta;
            if (attackTime < this.weapon.attackTime) {
                return;
            } else {
                isAttacking = false;
                delayed = true;
                delayTime = 0;
            }
        }
        
        float moveDist = (float) .1 * delta * moveSpeed;
        
        if (cooldown > 0) {
        	cooldown-= 1;
        }
        if (input.isKeyPressed(buttons.get("action"))) {
        	if (cooldown > 0) {
        		return;
        	} else {
        		this.isAttacking = true;
                currentAnimation = handleAnimation("punch");
                currentAnimation.start();
                cooldown = this.weapon.cooldown;
                attackTime = 0;
                this.weapon.attack();
                return;
        	}
            
        } else if (input.isKeyDown(buttons.get("right")) || input.isKeyDown(buttons.get("left")) || input.isKeyDown(buttons.get("down"))
                || input.isKeyDown(buttons.get("up"))) {
            currentAnimation = handleAnimation("walk");
            currentAnimation.start();
            if (input.isKeyDown(buttons.get("right")))
                this.moveRight(moveDist);
            if (input.isKeyDown(buttons.get("left")))
                this.moveLeft(moveDist);
            if (input.isKeyDown(buttons.get("down")))
                this.moveDown(moveDist);
            if (input.isKeyDown(buttons.get("up")))
                this.moveUp(moveDist);
        } else {
            if (currentAnimation != null) {
                currentAnimation.stop();
            }
        }
    }
    
    @Override
    public Animation handleAnimation(String whichAnim) {
        if (isRight) {
            if (whichAnim.equals("flinch")) {
                return weapon.anims[1];
            } else if (whichAnim.equals("punch")) {
                return weapon.anims[3];
            } else {
                // else, the walk animation for now
                return weapon.anims[5];
            }
        } else {
            if (whichAnim.equals("flinch")) {
                return weapon.anims[0];
            } else if (whichAnim.equals("punch")) {
                return weapon.anims[2];
            } else {
                // else, the walk animation for now
                return weapon.anims[4];
            }
        }
    }
    
    public void pickup() {
        // tries to pick up what might be nearby.
    }
    
    public void incrementScore(int points) {
        this.score += points;
    }
    
    @Override
    public void renderHealthBar(Graphics g) {
        float x = 25 + (MainGame.GAME_WIDTH - 200) * playerID;
        float y = 75;
        int width = 150;
        int height = 10;
        int padding = 2;
        double healthRemaining = width * health / maxHealth;
        g.setColor(healthFill);
        g.drawRect(x - padding, y - padding, width + padding * 2, height + padding * 2);
        g.fillRect(x, y, (float) healthRemaining, height);
    }
    
    @Override
    public float[] weaponLoc() {
        if (this.isRight) {
            return new float[] { pos[0] + 64 + 4, pos[1] + 40 };
        } else {
            return new float[] { pos[0] - 4, pos[1] + 40 };
        }
    }
    
    public void deathCheck(int delta) {
    	if ((health <= 0) && (deathTimer == 0)) {
    		deathTimer = RESPAWN_TIMER;
    		isRespawning = true;
    		if (isRight)
    			currentAnimation = playerDeath[0];
    		if (!isRight)
    			currentAnimation = playerDeath[1];
    		currentAnimation.start();
    	} else if (deathTimer > 0) {
    		deathTimer = Math.max(deathTimer-delta, 0);
    		health = health + (double)delta / RESPAWN_TIMER * maxHealth;
    		if (health >= maxHealth) {
    			health = maxHealth;
    			isRespawning = false;
    		}
    	}
    }
}