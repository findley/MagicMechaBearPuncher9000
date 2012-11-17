package states;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import weapons.Attack;
import core.MainGame;
import dudes.Monster;
import dudes.Player;

public class AreaState extends BasicGameState {
	protected Player[] players;
	protected TiledMap bgImage;
	protected ArrayList<Monster> monsters;

	private int progression;

	public AreaState(int stateID) {
		super();
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		progression = 0;
		players = MainGame.players;
		monsters = new ArrayList<Monster>();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		bgImage.render(-progression % 32, 0, progression / 32, 0, 32 + 1, 24);
		for (int i = 0; i < players.length; i++) {
			players[i].render(g);
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

		float backPlayerPos = Math.min(players[0].pos[0], players[1].pos[0]);
		if (progression < 32 * (200 - 31)) {
			if (backPlayerPos > MainGame.GAME_WIDTH / 3) {
				float shift = backPlayerPos - MainGame.GAME_WIDTH / 3;
				progression += shift;
				players[0].pos[0] -= shift;
				players[1].pos[0] -= shift;
			}
		}

		for (int i = 0; i < players.length; i++) {
			Player player = players[i];
			player.invincibleTimer+=delta;
			player.weapon.updateAttacks();
			for (Attack attack : player.weapon.attacks) {
				for (Monster monster : this.monsters) {
					if (attack.hitbox.intersects(monster.hitbox)) {
						monster.hurt(player.weapon.damage, 0);
					}
				}
				if (attack.hitbox.intersects(players[(i + 1) % 2].hitbox)) {
					// players[(i + 1) % 2].flinch(0);
					players[(i + 1) % 2].hurt(players[i].weapon.damage, 0);
				}
			}
		}
		for (Monster monster : this.monsters) {
			for (Attack attack : monster.weapon.attacks) {
				for (Player player : players) {
					if (attack.hitbox.intersects(player.hitbox)) {
						player.hurt(monster.weapon.damage, 0);
					}
				}
			}
		}
		for(Player player : players){
			player.hitbox.setX(player.pos[0]);
			player.hitbox.setY(player.pos[1]);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
}
