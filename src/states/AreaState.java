package states;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import weapons.Attack;
import weapons.KnightKnife;
import weapons.Weapon;
import core.MainGame;
import dudes.Monster;
import dudes.Player;

public class AreaState extends BasicGameState {
	protected Player[] players;
	protected TiledMap bgImage;
	protected ArrayList<ArrayList<Monster>> monsters;
	protected ArrayList<Monster> currBattle;
	private ArrayList<Weapon> floorweapons;
	private boolean inBattle;
	private boolean completed;
	private int progression;
	protected int[] battleStops;

	public AreaState(int stateID) {
		super();
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		progression = 0;
		players = MainGame.players;
		monsters = new ArrayList<ArrayList<Monster>>();
		currBattle = new ArrayList<Monster>();
		floorweapons = makeInitItems();
		inBattle = false;
		completed = false;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		bgImage.render(-progression % 32, 0, progression / 32, 0, 32 + 1, 24);
		for (int i = 0; i < players.length; i++) {
			players[i].render(g);
		}
		
		if (inBattle) {
			g.drawString("FIGHT", 400, 200);
			
			for (Monster m: currBattle) {
				m.render(g);
			}
		}
		
		for (Weapon i : floorweapons) {
			i.Draw();
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
		// progression++;
		
		for (int i = 0; i < players.length; i++) {
			players[i].move(container.getInput(), delta);
		}
		
		if (inBattle) {
			for (Monster m: currBattle) {
				m.move(container.getInput(), delta);
			}
		}

		float backPlayerPos = Math.min(players[0].pos[0], players[1].pos[0]);
		
		if (progression > 32*(200 - 33)) {// don't scroll if you're at the end of the screen
			completed = true;
		} else {
			if (backPlayerPos > MainGame.GAME_WIDTH/10) {// don't scroll unless both players are far right enough
				if (!inBattle) {
					//float shift = backPlayerPos - MainGame.GAME_WIDTH/3;
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
						
						ArrayList<Weapon> remove = new ArrayList<Weapon>();
						for(Weapon i : floorweapons) {
							i.x -=shift;
							if (i.x < 0) {
								remove.add(i);
							}
						}
						
						for (Weapon i : remove){
							floorweapons.remove(i);
						}
						
					}
				}
			}
		}

		for (int i = 0; i < players.length; i++) {
			Player player = players[i];
			player.invincibleTimer+=delta;
			player.weapon.updateAttacks();
			for (Attack attack : player.weapon.attacks) {
				for (Monster monster : this.currBattle) {
					if (attack.hitbox.intersects(monster.hitbox)) {
						monster.hurt(player.weapon.damage, 500);
					}
				}
				if (attack.hitbox.intersects(players[(i + 1) % 2].hitbox)) {
					// players[(i + 1) % 2].flinch(0);
					players[(i + 1) % 2].hurt(players[i].weapon.damage, 500);
				}
			}
		}
		for (Monster monster : this.currBattle) {
			monster.invincibleTimer+=delta;
			for (Attack attack : monster.weapon.attacks) {
				for (Player player : players) {
					if (attack.hitbox.intersects(player.hitbox)) {
						player.hurt(monster.weapon.damage, 500);
					}
				}
			}
		}
		for(Player player : players){
			player.hitbox.setX(player.pos[0]);
			player.hitbox.setY(player.pos[1]);
		}
		
		
		ArrayList<Weapon> remove = new ArrayList<Weapon>();
		ArrayList<Weapon> add = new ArrayList<Weapon>();
		for (Player p : players) {
			if (container.getInput().isKeyPressed(p.buttons.get("pickup"))) {
				for (Weapon w: floorweapons) {
					if (p.hitbox.intersects(w.getHitBox())) {
						p.weapon.drop();
						if (p.weapon.weaponSprite == null ) {
							
						} else {
							add.add(p.weapon);
						}
						p.weapon = w;
						w.assignOwner(p);
						remove.add(w);
												
					} 
				}
			}
		}
		
		ArrayList<Monster> removeMonster = new ArrayList<Monster>();
		for (Monster m : this.currBattle){
			if (m.health <= 0) {
				removeMonster.add(m);
			}
		}
		
		for (Monster m : removeMonster) {
			this.currBattle.remove(m);
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
	}

	public ArrayList<Weapon> makeInitItems() {
		ArrayList<Weapon> o = new ArrayList<Weapon>();
		Weapon k1 = new KnightKnife( 1200, 550);
		Weapon k2 = new KnightKnife( 1300f, 550f);
		o.add(k1);
		o.add(k2);
		return o;
		
	}
	
	public void addNewItem() {
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
}
