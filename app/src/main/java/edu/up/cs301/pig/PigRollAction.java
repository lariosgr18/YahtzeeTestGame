package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by lariosgr18 on 10/14/2015.
 */
public class PigRollAction extends GameAction {
    @Override
    public GamePlayer getPlayer() {
        return super.getPlayer();
    }

    @Override
    public void setPlayer(GamePlayer p) {
        super.setPlayer(p);
    }

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PigRollAction(GamePlayer player) {
        super(player);
    }
}
