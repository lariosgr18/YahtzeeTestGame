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
       // this.roll();
        Thread.sleep(1000);
       // this.roll();
        Thread.sleep(1000);
       // this.roll();
        Thread.sleep(10000);

            SelectScoreAction select = new SelectScoreAction(this);
            super.game.sendAction(select);
    }catch (NullPointerException NPE)
    {
        Log.d("******ERROR*****","ERROR");
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    }//receiveInfo


    public void roll() throws InterruptedException {
        if(rollNum <= 3) {
            for (int i = 0; i < thedice.length; i++) {
                Thread.sleep(1000);
                thedice[i].roll();
                thedice[i].invalidate();
                diceValues[i] = thedice[i].dieNum;
                Thread.sleep(1000);
            }
            RollAction action = new RollAction(this);
            super.game.sendAction(action);
            calc.updateScoreCard();
            rollNum++;

        }

    }

}
