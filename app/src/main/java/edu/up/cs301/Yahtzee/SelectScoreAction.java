package edu.up.cs301.Yahtzee;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by Michael on 11/8/2015.
 */
public class SelectScoreAction  extends GameAction{
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public SelectScoreAction(GamePlayer player) {
        super(player);
    }

    @Override
    public GamePlayer getPlayer() {
        return super.getPlayer();
    }

    @Override
    public void setPlayer(GamePlayer p){
        super.setPlayer(p);
    }
}
