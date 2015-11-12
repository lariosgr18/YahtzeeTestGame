package edu.up.cs301.Yahtzee;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import android.util.Log;

/**
 * Created by Michael, Abhinav, Grayson on 10/20/2015.
 *
 * Starts the game
 */
public class YahtzeeLocalGame extends LocalGame {
    YahtzeeGameState currentGame;

    /*
        state sent to player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {


    }

    /*
        if the current player is allowed to make a move
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if(playerIdx == currentGame.getCurrentPlayerID())
        {
            return true;
        }

        return false;
    }

    /*
        check for when the game is over
     */
    @Override
    protected String checkIfGameOver() {
        if((currentGame.getPlayer1turns() > 12) && (currentGame.getPlayer2turns() > 12)){
            return "dsdsfsfsfs";
        }
        return null;
    }

    /*
        the user makes a move
     */
    @Override
    protected boolean makeMove(GameAction action) {
        return false;
    }
}
