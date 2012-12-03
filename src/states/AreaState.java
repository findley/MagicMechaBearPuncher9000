package states;

import java.util.ArrayList;
import java.util.Collections;

import obstacles.Obstacle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

import projectiles.Projectile;
import weapons.Attack;
import weapons.Coin;
import weapons.Fist;
import weapons.Weapon;
import core.MainGame;
import core.Text;
import dudes.Monster;
import dudes.Monster.enemyState;
import dudes.Player;

public class AreaState extends BasicGameState {
    protected Player[]                      players;
    protected TiledMap                      bgImage;
    protected ArrayList<ArrayList<Monster>> monsters;
    protected ArrayList<Obstacle>			obstacles;
    protected ArrayList<Monster>            currBattle;
    protected Image							princess;
    private ArrayList<Weapon>               floorweapons;
    private ArrayList<Coin>					floorcoins;
    private boolean                         inBattle;
    private boolean                         completed;
    private int                             progression;
    protected int[]                         battleStops;
    protected int                           areaLength;
    private ArrayList<Player>               sPlayers;
    private final int                       PLAYER_STUN_LENGTH = 500;
    private final int						MONSTER_STUN_LENGTH = 200;
    private ArrayList<Projectile>           liveProjectiles;
    private ArrayList<Projectile>           monsterProjectiles;
    private ArrayList<Text>                 screenTexts;
    protected Sound                         attackNoise;
    protected int                           noiseCooldown;
    public AreaState(int stateID) {
        super();
    }
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        progression = 0;
        players = MainGame.players;
        monsters = new ArrayList<ArrayList<Monster>>();
        obstacles = new ArrayList<Obstacle>();
        currBattle = new ArrayList<Monster>();
        floorweapons = new ArrayList<Weapon>();
        liveProjectiles = new ArrayList<Projectile>();
        monsterProjectiles = new ArrayList<Projectile>();
        floorcoins = new ArrayList<Coin>();
        inBattle = false;
        completed = false;
        areaLength = 0;
        
        sPlayers = new ArrayList<Player>();
        sPlayers.add(players[0]);
        sPlayers.add(players[1]);
        
        screenTexts = new ArrayList<Text>();
        
        attackNoise = new Sound("Assets/Sound/punch.wav");
        noiseCooldown = 0;
    }
    
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
    	int top = 24-container.getHeight()*24/768;
        bgImage.render(-progression % 32, 0, progression / 32, top, 32 + 1, 24);
    	
        
        Collections.sort(sPlayers);
        for (Player p : sPlayers) {
            p.render(g);
            g.setColor(Color.green);
            g.drawString("PLAYER " + (p.playerID + 1), 25 + (MainGame.GAME_WIDTH - 200) * p.playerID, 50);
            g.drawString("POINTS: " + p.score, 25 + (MainGame.GAME_WIDTH - 200)  * p.playerID, 100);
        }
        
        for (Text t : screenTexts) {
        	t.render(g);
        }
        
        if (inBattle) {
        	g.setColor(Color.green);
            g.drawString("FIGHT", container.getWidth()/2, 170);
            
            Collections.sort(currBattle);
            for (Monster m : currBattle) {
                m.render(g);
                if (m.weapon.attacks.size() > 0){
                	//g.draw(m.weapon.attacks.get(0).hitbox);
                }
            }
        }
        
        for (Obstacle o : obstacles) {
        	o.render(g);
        }
        
        for (Weapon i : floorweapons) {
            i.Draw();
        }
        
        for (Coin c : floorcoins){
        	c.Draw();
        }
        
        for (Projectile p : liveProjectiles) {
        	p.render(g);
        }
        
        for (Projectile p : monsterProjectiles) {
        	p.render(g);
        }
        
        if (completed && game.getCurrentStateID()==4){
        	princess.draw(container.getWidth()-100, container.getHeight() - 80);
        	if(players[0].score > players[1].score){
            	g.setColor(Color.green);
                container.getGraphics().drawString("THANK YOU FOR SAVING ME PLAYER 1. YOU WIN!", container.getWidth()/2-200, 170);
        	} else if(players[1].score > players[0].score){
            	g.setColor(Color.green);
                g.drawString("THANK YOU FOR SAVING ME PLAYER 2. YOU WIN!", container.getWidth()/2-200, 170);
        	} else{
            	g.setColor(Color.green);
                g.drawString("A TIE? WELL I GUESS YOU'RE BOTH OUT OF LUCK. THANKS THOUGH!", container.getWidth()/2-200, 170);
        	}
        }
    }
    
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            container.exit();
        }
        
        for (int i = 0; i < players.length; i++) {
            players[i].move(container.getInput(), delta);
            runOverCoins(players[i]);
        }
        
        for (Projectile p : liveProjectiles) {
        	p.move();
        }

        for (Projectile p : monsterProjectiles) {
        	p.move();
        }
        
        for (Obstacle o : obstacles){
        	for (Player p : players){
        		if(o.getHitbox().intersects(p.getHitbox())){
        			o.effect(p);
        		}
        		o.endEffect(p);
        	}
        }
        
        removeTexts();
        for (Text t : screenTexts) {
        	t.update();
        }
        
        if (inBattle) {
            for (Monster m : currBattle) {
                m.move(container.getInput(), delta);
            }
        }
        
        float backPlayerPos = Math.min(players[0].pos[0], players[1].pos[0]);
        
        if (progression > 32 * (areaLength - 32)) {// don't scroll if you're at the end of the screen
            completed = true;
        } else {
            if (backPlayerPos > MainGame.GAME_WIDTH / 10) {// don't scroll unless both players are far right enough
                if (!inBattle) {
                    // float shift = backPlayerPos - MainGame.GAME_WIDTH/3;
                    float shift = 4;
                    for (int stop : battleStops) {
                        if (progression < stop && progression + shift >= stop) {
                            progression = stop;
                            inBattle = true;
                            currBattle = monsters.remove(0);
                        }
                    }
                    if (!inBattle) {
                        progression += shift;
                        players[0].pos[0] -= shift;
                        players[1].pos[0] -= shift;
                        
                        floorItemsMove(shift);                        
                    }
                }
            }
        }
        
        for (Player p : players) {
        	while (p.weapon.projectiles.size() > 0) {
        		liveProjectiles.add(p.weapon.projectiles.get(0));
        		p.weapon.projectiles.remove(0);
        	}
        }
        
        for (Monster m : currBattle) {
        	while (m.weapon.projectiles.size() > 0) {
        		monsterProjectiles.add(m.weapon.projectiles.get(0));
        		m.weapon.projectiles.remove(0);
        	}
        }
        removeProjectiles();
        for (Projectile p: liveProjectiles) {
        	for (Monster monster : this.currBattle) {
        		if (p.getHitbox().intersects(monster.getHitbox()) && p.hasHit == false) {
        			monster.hurt(p.damage, 500);
        			monster.setLastHit(p.owner);
        			p.hasHit = true;
        		}
        	}
        	
        	 
        	for (Player player : players) {
        		if (p.getHitbox().intersects(player.getHitbox()) && p.hasHit == false) {
        			if (p.owner != player){
        				if (!player.isRespawning) {
        					player.hurt(player.weapon.damage, PLAYER_STUN_LENGTH);
        					p.hasHit = true;
        				}
        			}
        		}
        	}
        }
        
        for (Projectile p : monsterProjectiles) {
        	for (Player player : players) {
        		if (p.getHitbox().intersects(player.getHitbox()) && p.hasHit == false) {
        			if (p.owner != player){
        				if (!player.isRespawning) {
        					player.hurt(player.weapon.damage, PLAYER_STUN_LENGTH);
        					p.hasHit = true;
        				}
        			}
        		}
        	}
        }
        
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            player.invincibleTimer += delta;
            player.weapon.updateAttacks();
            for (Attack attack : player.weapon.attacks) {
                for (Monster monster : this.currBattle) {
                    if (attack.hitbox.intersects(monster.getHitbox())) {
                    	tryPunchNoise();
                    	
                        monster.hurt(player.weapon.damage, MONSTER_STUN_LENGTH);
                        monster.setLastHit(player);
                    }
                }
                if (attack.hitbox.intersects(players[(i + 1) % 2].getHitbox())) {
                	if (!players[(i + 1) % 2].isRespawning) {
                    	tryPunchNoise();
                    	
                		players[(i + 1) % 2].hurt(player.weapon.damage, PLAYER_STUN_LENGTH);
                	}
                }
            }
        }
        
        for (Monster monster : this.currBattle) {
            monster.invincibleTimer += delta;
            monster.weapon.updateAttacks();
            if (monster.state == enemyState.ALIVE) {
            	monster.aiLoop(players, this.currBattle, delta);
                for (Attack attack : monster.weapon.attacks) {
                    for (Player player : players) {
                        if (attack.hitbox.intersects(player.getHitbox())) {
                        	if (!player.isRespawning) {
                        		player.hurt(monster.weapon.damage, PLAYER_STUN_LENGTH);
                        	}
                        }
                    }
                }
            }
        }
        
        ArrayList<Weapon> remove = new ArrayList<Weapon>();
        ArrayList<Weapon> add = new ArrayList<Weapon>();
        for (Player p : players) {
            if (container.getInput().isKeyPressed(p.buttons.get("pickup"))) {
                for (Weapon w : floorweapons) {
                    if (p.getHitbox().intersects(w.getHitBox())) {
                        p.weapon.drop();
                        if (p.weapon.groundSprite == null) {
                            
                        } else {
                            add.add(p.weapon);
                        }
                        p.weapon = w;
                        w.assignOwner(p);
                        w.init();
                        remove.add(w);
                        
                    }
                }
                //runOverCoins(p);
            }
        }
        
        checkIfMonsterDead();
        for (Player p : players) {
        	p.deathCheck(delta);
        	if(p.isRespawning && !p.weapon.isFist){
        		Weapon w = new Fist(p.pos[0],p.pos[1]);
        		p.weapon.drop();
                if (p.weapon.groundSprite == null) {
                    
                } else {
                    add.add(p.weapon);
                }
                p.weapon = w;
                w.assignOwner(p);
                w.init();
                remove.add(w);
        	}
        }
        
        for (Weapon r : remove) {
            floorweapons.remove(r);
        }
        
        for (Weapon a : add) {
            floorweapons.add(a);
        }
        
        if (currBattle.size() == 0 && inBattle) {
            inBattle = false;
        }
        
        if (completed && game.getCurrentStateID()==2){
        	game.enterState(3, new FadeOutTransition(), new FadeInTransition());
        } 
        if (completed && game.getCurrentStateID()==3){
        	game.enterState(4, new FadeOutTransition(), new FadeInTransition());
        }
        
        if (noiseCooldown <= 0) {
        	noiseCooldown = 0;
        } else {
        	noiseCooldown -= delta;
        }
    }
    
    public ArrayList<Weapon> makeInitItems() throws SlickException {
        return new ArrayList<Weapon>();
    }
    
    public void runOverCoins(Player p){
        ArrayList<Coin> out = new ArrayList<Coin>();
        for (Coin c : floorcoins){
            if (p.getHitbox().intersects(c.getHitBox())) {
            	p.score += c.value;
            	this.screenTexts.add(new Text(p.pos, Integer.toString(c.value), c.color));
                out.add(c);
            }
        }
        for (Coin c : out){
        	floorcoins.remove(c);
        }
    }
    
    public void floorItemsMove(float shift){
        ArrayList<Weapon> remove = new ArrayList<Weapon>();
        for (Weapon i : floorweapons) {
            i.x -= shift;
            if (i.x < 0) {
                remove.add(i);
            }
        }

        for (Weapon i : remove) {
            floorweapons.remove(i);
        }
        
        ArrayList<Coin> rid = new ArrayList<Coin>();
        for (Coin c : floorcoins) {
            c.pos[0] -= shift;
            if (c.pos[0] < 0) {
                rid.add(c);
            }
        }
        
        for (Coin i : rid) {
            floorcoins.remove(i);
        }
        
        ArrayList<Obstacle> getRidOf = new ArrayList<Obstacle>();
        for (Obstacle i : obstacles) {
            i.pos[0] -= shift;
            if (i.pos[0] < 0) {
                getRidOf.add(i);
            }
        }

        for (Obstacle i : getRidOf) {
           // obstacles.remove(i);
        }
    }
    
    public void removeTexts() {
    	ArrayList<Text> removeTexts = new ArrayList<Text>();
    	for (Text t : screenTexts) {
    		if (t.duration < 0) {
    			removeTexts.add(t);
    		}
    		
    	}
    	
    	for (Text t : removeTexts) {
    		screenTexts.remove(t);
    	}
    }
    
    public void removeProjectiles() {
    	ArrayList<Projectile> removeProjectiles = new ArrayList<Projectile>();
    	for (Projectile p : liveProjectiles) {
    		if (p.hasHit) {
    			removeProjectiles.add(p);
    		} else if (p.pos[0] > MainGame.GAME_WIDTH + 200) {
    			removeProjectiles.add(p);
    		}    		
    		
    	}
    	
    	
    	for (Projectile p : removeProjectiles) {
    		liveProjectiles.remove(p);
    	}
    	
    	
    	removeProjectiles = new ArrayList<Projectile>();
    	for (Projectile p : monsterProjectiles) {
    		if (p.hasHit) {
    			removeProjectiles.add(p);
    		} else if (p.pos[0] > MainGame.GAME_WIDTH + 200) {
    			removeProjectiles.add(p);
    		}    		
    		
    	}
    	
    	
    	for (Projectile p : removeProjectiles) {
    		monsterProjectiles.remove(p);
    	}
    }

    public void checkIfMonsterDead() throws SlickException{
        ArrayList<Monster> removeMonster = new ArrayList<Monster>();
        for (Monster m : this.currBattle) {
            if (m.state == enemyState.ALIVE && m.health <= 0) {
        		m.state = enemyState.DYING;
        		m.renderDeath();
                m.getLastHit().incrementScore(100);

            	this.screenTexts.add(new Text(m.getLastHit().pos, Integer.toString(m.value), Color.red));
            }
            else if (m.state == enemyState.DYING && m.currentAnimation.isStopped()) {
            	m.state = enemyState.DEAD;
            }
            else if (m.state == enemyState.DEAD) {
        		removeMonster.add(m);
            }
        }
        
        monsterDrop(removeMonster);
    }
   
    
    public void monsterDrop(ArrayList<Monster> removeMonster) throws SlickException{
        for (Monster m : removeMonster) {
        	m.renderDeath();
            this.currBattle.remove(m);
            double rand = Math.random();
            Weapon w = m.getDropWeapon();
            if (w == null) {
            	
            } else {
         		floorweapons.add(w);	
            }            
            
            Coin c = m.getDropCoin();
            if (c == null) {
            	
            } else {
                floorcoins.add(c);	
            }
            
        }
    }
    
    public void tryPunchNoise() {
    	if (noiseCooldown == 0) {
    		attackNoise.play();
    		noiseCooldown = 500;
    	}
    }
    
    @Override
    public int getID(){return 0;}
    /*
    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 1;
    }*/
}
