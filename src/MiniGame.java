import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;


public class MiniGame {
    boolean[] playerDone = {false, false};
    Rectangle location;
    int index;
    
    MiniGame(Rectangle location, int index){
        this.location = location;
        this.index = index;
    }
    
    public boolean enterGame(GameContainer container,StateBasedGame game, Player[] players, int playerIndex) throws SlickException{
        if(!playerDone[playerIndex]){
            GameState state = (GameState) game.getCurrentState();
            if(index == 0){
                state.triggerMinigame(container, game, players[playerIndex], new DodgeWindow(players[playerIndex]));
            }
            playerDone[playerIndex] = true;
            return true;
        }
        else{
            return false;
        }
    }

}
