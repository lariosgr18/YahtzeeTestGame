package edu.up.cs301.Yahtzee;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;


/**
 * Created by Michael on 10/20/2015.
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

    }
}