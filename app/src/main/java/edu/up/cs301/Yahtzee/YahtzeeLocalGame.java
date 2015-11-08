package edu.up.cs301.Yahtzee;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import android.util.Log;

/**
 * Created by Michael on 10/20/2015.
 */
public class YahtzeeLocalGame extends LocalGame {
    YahtzeeGameState currentGame;

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {


    }

    @Override
    protected boolean canMove(int playerIdx) {
        if(playerIdx == currentGame.getCurrentPlayerID())
        {
            return true;
        }

        return false;
    }

    @Override
    protected String checkIfGameOver() {
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        return false;
    }
}
