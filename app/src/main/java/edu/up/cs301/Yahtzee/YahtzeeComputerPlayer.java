package edu.up.cs301.Yahtzee;

import android.app.Activity;
import android.util.Log;
import android.widget.Button;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;


/**
 * Created by Michael, Grayson Abhinav on 10/20/2015.
 *
 * Controls the Hard and Easy Computer Player
 */
public class YahtzeeComputerPlayer extends GameComputerPlayer{

    private YahtzeeHumanPlayer opponent;
    private Button[] computerButtons;

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


        opponent = ((YahtzeeGameState)(info)).getPlayer1();

        computerButtons = opponent.computerButtons;
        ScoreCalc calc = new ScoreCalc(computerButtons,opponent.thedice);
        opponent.onClick(opponent.roll);
        RollAction rollAction1 = new RollAction(this);
        super.game.sendAction(rollAction1);
        opponent.onClick(opponent.roll);
        RollAction rollAction2 = new RollAction(this);
        super.game.sendAction(rollAction1);
        opponent.onClick(opponent.roll);
        RollAction rollAction3 = new RollAction(this);
        super.game.sendAction(rollAction1);

        // TODO  You will implement this method
        int move = (int) ((Math.random() *2 )+1);
        int counter = 1;
        while ( counter < 3) {
            if (move == 1) {

                RollAction rollAction = new RollAction(this);
                super.game.sendAction(rollAction);


            } else {


                SelectScoreAction selectScoreAction = new SelectScoreAction(this);
                super.game.sendAction(selectScoreAction);

            }

            counter++;
        }

        SelectScoreAction selectScoreAction1 = new SelectScoreAction(this);
        super.game.sendAction(selectScoreAction1);

    }//receiveInfo

}
