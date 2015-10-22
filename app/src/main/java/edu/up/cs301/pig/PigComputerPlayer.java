package edu.up.cs301.pig;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigComputerPlayer extends GameComputerPlayer {

    /**
     * ctor does nothing extra
     */
    public PigComputerPlayer(String name) {
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
        // TODO  You will implement this method
        int move = (int) ((Math.random() *2 )+1);

        if(move ==1){

            PigRollAction rollMove = new PigRollAction(this);
            super.game.sendAction(rollMove);
        }
        else{


            PigHoldAction holdMove = new PigHoldAction(this);
            super.game.sendAction(holdMove);

        }


    }//receiveInfo

}
