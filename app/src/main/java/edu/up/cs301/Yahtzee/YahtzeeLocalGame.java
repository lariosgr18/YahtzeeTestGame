package edu.up.cs301.Yahtzee;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;

import android.graphics.Color;
import android.util.Log;

/**
 * Created by Michael, Abhinav, Grayson on 10/20/2015.
 *
 * Starts the game
 */
public class YahtzeeLocalGame extends LocalGame {
    YahtzeeGameState currentGame = new YahtzeeGameState();


    /*
        state sent to player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //sends info
        p.sendInfo(currentGame);

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
        if the current player is allowed to make a move
     */
    protected boolean canRoll(int playerIdx) {
        if(playerIdx == currentGame.getCurrentPlayerID() && currentGame.getRolls() != 3)
        {
            return true;
        }

        return false;
    }

    /*
        check for when the game is over//
     */
    @Override
    protected String checkIfGameOver() {
        if((currentGame.getPlayer1turns() > 12) && (currentGame.getPlayer2turns() > 12)){
            int totalScore1=0;
            int totalScore2=0;
            for(int i = 0; i < 13; i++)
            {
               totalScore1 +=  currentGame.getPlayer1Score()[i];
                totalScore2 += currentGame.getPlayer2Score()[i];
                Log.d("gameOver scores:", "" + currentGame.getPlayer2Score()[i]);
            }
            if(totalScore1 > totalScore2) {
                return "PLAYER 1 WINS \n" + "Player 1 Score: "+totalScore1 + "\nPlayer 2 Score: " + totalScore2;
            }
            else
            {
                return "PLAYER 2 WINS \n" + "Player 1 Score: "+totalScore1 + "\n Player 2 Score: " + totalScore2;
            }
        }
        return null;
    }

    /*
        the user makes a move
     */
    @Override
    protected boolean makeMove(GameAction action) {
        if(canMove(getPlayerIdx(action.getPlayer())))
        {
            if(getPlayerIdx(action.getPlayer()) == 1)
            {
                if(action instanceof RollAction)
                {

                    currentGame.rollDice(((YahtzeeComputerPlayer)(action.getPlayer())).getDiceValues(),getPlayerIdx(action.getPlayer()));
                    Log.d("PLAYER ID", "" + getPlayerIdx(action.getPlayer()));
                    return true;
                }

                if(action instanceof SelectScoreAction)
                {
                    currentGame.selectScore(((YahtzeeComputerPlayer)(action.getPlayer())).getScoreSelected(),((YahtzeeComputerPlayer)(action.getPlayer())).getIndex(),getPlayerIdx(action.getPlayer()),false);
                    Log.d("PLAYER ID", "" + getPlayerIdx(action.getPlayer()));
                    return true;
                }
                return false;
            }

            if(action instanceof RollAction)
            {
                currentGame.rollDice(((YahtzeeHumanPlayer)(action.getPlayer())).getDiceValues(),getPlayerIdx(action.getPlayer()));
                Log.d("PLAYER ID", "" + getPlayerIdx(action.getPlayer()));
            }

            if(action instanceof SelectScoreAction)
            {

                currentGame.selectScore(((YahtzeeHumanPlayer) (action.getPlayer())).getScoreChosen(), ((YahtzeeHumanPlayer) (action.getPlayer())).getCurrentScoreIndex(), getPlayerIdx(action.getPlayer()), false);
                Log.d("PLAYER ID", "" + getPlayerIdx(action.getPlayer()));

            }
            return true;
        }

        return false;
    }
}
