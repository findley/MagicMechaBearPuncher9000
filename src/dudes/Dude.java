package dudes;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Shape;

import weapons.Attack;
import weapons.Weapon;
import core.MainGame;

public abstract class Dude implements Comparable<Dude> {
    public double      health;
    public Weapon      weapon;
    public Animation   currentAnimation;
    public float[]     pos         = new float[2];
    public Shape       hitbox;
    public boolean     isRight;
    public boolean     isAttacking;
    public float       moveSpeed;
    public int         maxHealth;
    public Color       healthFill;
    public int         attackTime;
    public boolean     invincible;
    public int         invincibleDuration;
    public int         invincibleTimer;

    public int         deathTimer;
    
    public boolean     flinching;
    public int         flinchDur;
    public int         flinchTime;
    
    public boolean     delayed;
    public int         delayDur;
    public int         delayTime;
    public float cooldown;
        
    // only for enemies
    public SpriteSheet sprites;
    public int[]       spriteIndex = new int[2];
    public int		   kind; //0 for town, 1 for forest, 2 for castle
    
    public abstract float[] weaponLoc();
    
    public Dude() {
        invincibleDuration = 500;
        cooldown = 0;
    }
    
    public void attack() throws SlickException {
        this.weapon.attack();
    }
    
    public void flinch(int milliseconds) {
        if (!flinching) {
            currentAnimation = handleAnimation("flinch");
            this.flinchTime = 0;
            this.flinchDur = milliseconds;
            this.flinching = true;
            isAttacking = false;
            delayed = false;
        }
        
    }
    
    public void moveLeft(float moveDist) {
        isRight = false;
        pos[0] -= moveDist;
        if (pos[0] < 0) {
            pos[0] = 0;
        }
    }
    
    public void moveRight(float moveDist) {
        isRight = true;
        pos[0] += moveDist;
        if (pos[0] > MainGame.GAME_WIDTH - weapon.playerSizeX) {
            pos[0] = MainGame.GAME_WIDTH - weapon.playerSizeX;
        }
    }
    
    public void moveUp(float moveDist) {
        pos[1] -= moveDist;
        if (pos[1] < MainGame.GAME_HEIGHT - 32 * 8 - weapon.playerSizeY + 5) {
            pos[1] = MainGame.GAME_HEIGHT - 32 * 8 - weapon.playerSizeY + 5;
        }
    }
    
    public void moveDown(float moveDist) {
        pos[1] += moveDist;
        if (pos[1] > MainGame.GAME_HEIGHT - 32 - weapon.playerSizeY - 5) {
            pos[1] = MainGame.GAME_HEIGHT - 32 - weapon.playerSizeY - 5;
        }
    }
    
    public void hurt(int damage, int flinch) {
        if (!invincible) {
            this.health = Math.max(0, this.health - damage);
            invincible = true;
            invincibleTimer = 0;
            flinch(flinch);
        } else {
            if (invincibleTimer > invincibleDuration) {
                invincible = false;
                this.health = Math.max(0, this.health - damage);
                flinch(flinch);
            }
        }
    }
    
    public abstract Animation handleAnimation(String whichAnim);
    
    public void render(Graphics g) throws SlickException {
        if (currentAnimation != null) {
            if (flinching) {
                if ((flinchTime % (flinchDur / 3)) < flinchDur / 6) {
                    currentAnimation.draw(pos[0], pos[1]);
                }
            } else {
                currentAnimation.draw(pos[0], pos[1]);
            }
        } else {
            if (isRight) {
                weapon.defaultSprite[0].draw(pos[0], pos[1]);
            } else {
                weapon.defaultSprite[1].draw(pos[0], pos[1]);
            }
        }
        
        // Render a health bar for the Dude
        this.renderHealthBar(g);
        for (Attack attack : this.weapon.attacks) {
            //g.draw(attack.hitbox);
        }
    }
    
    public void renderHealthBar(Graphics g) {
        int offset = -10;
        float x = pos[0];
        float y = pos[1] + offset;
        int width = 100;
        int height = 10;
        int padding = 2;
        double healthRemaining = width * health / maxHealth;
        g.setColor(healthFill);
        g.drawRect(x - padding - width / 2, y - padding - height / 2, width + padding * 2, height + padding * 2);
        g.fillRect(x - width / 2, y - height / 2, (float) healthRemaining, height);
    }
    
    @Override
    public int compareTo(Dude other) {
        return (int) ((pos[1]+weapon.spriteSizeY) - (other.pos[1]+other.weapon.spriteSizeY));
    }
}