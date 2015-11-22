package edu.up.cs301.Yahtzee;

import android.app.Activity;
import android.util.Log;
import android.widget.Button;

import edu.up.cs301.animation.Dice;
import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;


/**
 * Created by Michael, Grayson, Abhinav on 10/20/2015.
 *
 * Controls the Hard and Easy Computer Player
 */
public class YahtzeeComputerPlayer extends GameComputerPlayer{

    private YahtzeeHumanPlayer opponent;
    private Button[] computerButtons = new Button[13];
    private Dice[] thedice;
    private ScoreCalc calc;
    private int[] diceValues = new int[5];
    private int rollNum;
    private int move;


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

        for(int i = 0; i < thedice.length; i++)
        {
            diceValues[i] = (int) (Math.random() * 6 + 1);
        }
        RollAction rollMove = new RollAction(this);
        super.game.sendAction(rollMove);

        int move = (int) ((Math.random() *2 )+1);

            for(int i = 0; i < thedice.length; i++)
            {
                diceValues[i] = (int) (Math.random() * 6 + 1);
            }
        for(int i = 0; i < 50; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RollAction rollMove2 = new RollAction(this);
            super.game.sendAction(rollMove2);
        }

            SelectScoreAction selectMove = new SelectScoreAction(this);
            super.game.sendAction(selectMove);







    }//receiveInfo

    public int[] getDiceValues() {
        return diceValues;
    }

    public void setDiceValues(int[] diceValues) {
        this.diceValues = diceValues;
    }

}
