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
 * Created by Michael, Grayson Abhinav on 10/20/2015.
 *
 * Controls the Hard and Easy Computer Player
 */
public class YahtzeeComputerPlayer extends GameComputerPlayer{

    private YahtzeeHumanPlayer opponent;
    private Button[] computerButtons = new Button[13];
    private Dice[] thedice;
    private ScoreCalc calc;
    private int[] diceValues;
    private int rollNum;

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
    try {
        for (int i = 0; i < computerButtons.length; i++) {
            computerButtons[i] = opponent.computerButtons[i];
        }
        thedice = opponent.thedice;


        calc = new ScoreCalc(computerButtons,thedice);

        this.roll();
        this.roll();
        this.roll();

    }catch (NullPointerException NPE)
    {
        Log.d("******ERROR*****","ERROR");
    }

    }//receiveInfo


    public void roll()
    {
        if(rollNum <= 3) {
            for (int i = 0; i < thedice.length; i++) {
                thedice[i].roll();
                thedice[i].invalidate();
                diceValues[i] = thedice[i].dieNum;
            }
            RollAction action = new RollAction(this);
            super.game.sendAction(action);
            calc.updateScoreCard();
            rollNum++;

        }

    }

}
