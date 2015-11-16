package edu.up.cs301.Yahtzee;

import android.util.Log;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;


/**
 * Created by Michael, Grayson Abhinav on 10/20/2015.
 *
 * Controls the Hard and Easy Computer Player
 */
public class YahtzeeComputerPlayer extends GameComputerPlayer{


    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public YahtzeeComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        Log.d("COMPUTER PLAYER", "COMPUTERS TURN");


        // TODO  You will implement this method
        int move = (int) ((Math.random() *2 )+1);

        if(move ==1){

            SelectScoreAction selectScoreAction = new SelectScoreAction(this);
            super.game.sendAction(selectScoreAction);

        }
        else{


            SelectScoreAction selectScoreAction = new SelectScoreAction(this);
            super.game.sendAction(selectScoreAction);

        }


    }//receiveInfo

    }
