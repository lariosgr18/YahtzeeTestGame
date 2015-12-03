package edu.up.cs301.Yahtzee;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by Michael, Grayson, Abhinav on 11/8/2015.
 *
 * The action for when a player hits the roll button in order to roll the dice
 */
public class RollAction  extends GameAction{
    /**
     * constructor for GameAction
     *
     *
     * @param player the player who created the action
     */
    public RollAction(GamePlayer player) {
        super(player);
    }

    @Override
    public GamePlayer getPlayer(){
        return super.getPlayer();
    }

    @Override
    public void setPlayer(GamePlayer p){
        super.setPlayer(p);
    }

}
