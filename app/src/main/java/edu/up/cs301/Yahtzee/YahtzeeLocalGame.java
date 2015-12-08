package edu.up.cs301.Yahtzee;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.R;
import edu.up.cs301.game.actionMsg.GameAction;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

/**
 * Created by Michael, Abhinav, Grayson on 10/20/2015.
 *
 * Starts the game
 */
public class YahtzeeLocalGame extends LocalGame {
    //create new game state when a local game is started
    YahtzeeGameState currentGame = new YahtzeeGameState();

    /**
        sends state info to player
    */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(currentGame);
    }

    /**
        checks if the current player is allowed to make a move
    */
    @Override
    protected boolean canMove(int playerIdx) {
        if(playerIdx == currentGame.getCurrentPlayerID()) {
            return true;
        }
        return false;
    }

    /**
        check for when the game is over
     */
    @Override
    protected String checkIfGameOver() {
        if((currentGame.getPlayer1turns() > 12) && (currentGame.getPlayer2turns() > 12)){
            //initialize
            int totalScore1=0;
            int totalScore2=0;
            int upperBonus=35;
            String notifyOfBonus1="";
            String notifyOfBonus2="";

            //take sum of all scores
            for(int i = 0; i < 13; i++) {
                totalScore1 +=  currentGame.getPlayer1Score()[i];
                totalScore2 += currentGame.getPlayer2Score()[i];
                if(i == 5) {
                    //check if player qualifies for upper section bonus
                    if(totalScore1 >= 63) {
                        totalScore1 += upperBonus;
                        notifyOfBonus1 = "\nUpper Bonus Achieved: +35 points";
                    }
                    if(totalScore2 >= 63) {
                        totalScore2 += upperBonus;
                        notifyOfBonus2 = "\nUpper Bonus Achieved: +35 points";
                    }
                }
                Log.d("gameOver scores:", "" + currentGame.getPlayer2Score()[i]);
            }
            //determine winner by comparing scores
            if(totalScore1 > totalScore2) {
                return playerNames[0] + " WINS \n" + playerNames[0] + notifyOfBonus1 + "\nTotal Score: "+totalScore1 +"\n"
                        + playerNames[1] + notifyOfBonus2 + "\nTotal Score: " + totalScore2;
            }
            else if(totalScore1 == totalScore2) {
                return  "TIE! \n" +  playerNames[0] + notifyOfBonus1 + "\nTotal Score: "+totalScore1 +"\n"
                        + playerNames[1] + notifyOfBonus2 + "\nTotal Score: " + totalScore2;
            }
            else {
                return playerNames[1] + " WINS \n" +  playerNames[0] + notifyOfBonus1 + "\nTotal Score: "+totalScore1 +"\n"
                        + playerNames[1] + notifyOfBonus2 + "\nTotal Score: " + totalScore2;
            }
        }
        return null;
    }

    /**
        the user makes a move
     */
    @Override
    protected boolean makeMove(GameAction action) {
        //checks if player can move
        if(canMove(getPlayerIdx(action.getPlayer()))) {
            //checks if it is computer player's turn
            if(getPlayerIdx(action.getPlayer()) == 1) {
                //computer chooses to roll
                if(action instanceof RollAction) {
                    currentGame.rollDice(((YahtzeeComputerPlayer)(action.getPlayer())).getDiceValues(),getPlayerIdx(action.getPlayer()));
                    Log.d("PLAYER ID", "" + getPlayerIdx(action.getPlayer()));
                    return true;
                }
                //computer decides to select a score
                if(action instanceof SelectScoreAction) {
                    currentGame.selectScore(((YahtzeeComputerPlayer)(action.getPlayer())).getScoreSelected(),
                            ((YahtzeeComputerPlayer)(action.getPlayer())).getIndex(),getPlayerIdx(action.getPlayer()),false);
                    Log.d("PLAYER ID", "" + getPlayerIdx(action.getPlayer()));
                    return true;
                }
                return false;
            }
            //human player rolls
            if(action instanceof RollAction) {
                currentGame.rollDice(((YahtzeeHumanPlayer)(action.getPlayer())).getDiceValues(),getPlayerIdx(action.getPlayer()));
                Log.d("PLAYER ID", "" + getPlayerIdx(action.getPlayer()));
            }
            //human player selects a score
            if(action instanceof SelectScoreAction) {
                currentGame.selectScore(((YahtzeeHumanPlayer) (action.getPlayer())).getScoreChosen(),
                        ((YahtzeeHumanPlayer) (action.getPlayer())).getCurrentScoreIndex(), getPlayerIdx(action.getPlayer()), false);
                Log.d("PLAYER ID", "" + getPlayerIdx(action.getPlayer()));
            }
            return true;
        }
        return false;
    }
}
