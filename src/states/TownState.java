package states;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import weapons.KnightKnife;
import weapons.Sword;
import weapons.Weapon;

import dudes.Knight;
import dudes.Monster;

public class TownState extends AreaState {

	public TownState(int stateID) {
		super(stateID);
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.init(container, game);
		bgImage = new TiledMap("Assets/Transition 1/Map.tmx");
		areaLength = 200;
		
		//note: players are initialized ONLY in the first area
		players[0].init(0);
		players[1].init(1);
		
		ArrayList<Monster> group_1 = new ArrayList<Monster>();
		Knight g1_knight1 = new Knight(950, 512);
		Knight g1_knight2 = new Knight(950, 544);
		Knight g1_knight3 = new Knight(950, 576);
		g1_knight1.init();
		g1_knight2.init();
		g1_knight3.init();
		group_1.add(g1_knight1);
		group_1.add(g1_knight2);
		group_1.add(g1_knight3);

		ArrayList<Monster> group_2 = new ArrayList<Monster>();
		Knight g2_knight1 = new Knight(950, 512);
		Knight g2_knight2 = new Knight(950, 544);
		Knight g2_knight3 = new Knight(950, 576);
		g2_knight1.init();
		g2_knight2.init();
		g2_knight3.init();
		group_2.add(g2_knight1);
		group_2.add(g2_knight2);
		group_2.add(g2_knight3);
		
		ArrayList<Monster> group_3 = new ArrayList<Monster>();
		Knight g3_knight1 = new Knight(950, 512);
		Knight g3_knight2 = new Knight(950, 544);
		Knight g3_knight3 = new Knight(950, 576);
		g3_knight1.init();
		g3_knight2.init();
		g3_knight3.init();
		group_3.add(g3_knight1);
		group_3.add(g3_knight2);
		group_3.add(g3_knight3);
		
		monsters.add(group_1);
		monsters.add(group_2);
		monsters.add(group_3);
		
		battleStops = new int[3];
		battleStops[0] = 1000;
		battleStops[1] = 2000;
		battleStops[2] = 3000;
		
	}

	@Override
	public ArrayList<Weapon> makeInitItems() throws SlickException {
		ArrayList<Weapon> o = new ArrayList<Weapon>();
		Weapon k1 = new Sword(1200f, 550f);
		Weapon k2 = new Sword(1300f, 550f);
		k1.init();
		k2.init();
		o.add(k1);
		o.add(k2);
		return o;

	}
}
