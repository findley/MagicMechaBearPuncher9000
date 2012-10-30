import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Event {
	boolean[] hasEntered = {false, false};
	String eventName;
	
	public Event(String eventName){
		this.eventName = eventName;
	}
	
    public boolean enterGame(GameContainer container,StateBasedGame game, Player[] players, int playerIndex) throws SlickException{
        if(!hasEntered[playerIndex]){
            GameStateOld state = (GameStateOld) game.getCurrentState();
            if(eventName.equalsIgnoreCase("dodge")){
                state.triggerMinigame(container, game, players[playerIndex], new DodgeWindow(players[playerIndex]));
            }
            if(eventName.equalsIgnoreCase("catch")){
                state.triggerMinigame(container, game, players[playerIndex], new CatchWindow(players[playerIndex]));
            }
            hasEntered[playerIndex] = true;
            return true;
        }
        else{
            return false;
        }
    }
	
}
