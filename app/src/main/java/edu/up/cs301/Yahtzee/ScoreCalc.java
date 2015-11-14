package edu.up.cs301.Yahtzee;

import android.app.Activity;
import android.widget.Button;
import android.widget.SeekBar;

import edu.up.cs301.animation.Dice;
import edu.up.cs301.game.R;

/**
 * Created by Grayson, Michael, Abhinav on 11/8/2015.
 *
 * Controls the scores associated with each of the buttons.
 */
public class ScoreCalc {
    private int[] diceVals = new int[6];

    private Dice[] thedice;

    private Button[] numberedButtons1 = new Button[13];

    private static final int FULLHOUSESCORE = 25;

    //Constructor for a human player
    public ScoreCalc(YahtzeeHumanPlayer player)
    {


        Activity card = player.mainActivity;
        for (int i = 0; i < numberedButtons1.length; i++) {
            this.numberedButtons1[i] = player.numberedButtons1[i];
        }
        this.thedice = player.thedice;
    }

    //Updates the buttons to reflect the score
    public void updateScoreCard() {
        /*int displayCount;
        for (int i = 0; i < numberedButtons1.length; i++) {
            displayCount = 0;
            if (i < 6) {
                for (int j = 0; j < thedice.length; j++) {
                    if (thedice[j].dieNum == i + 1) {
                        displayCount = displayCount + thedice[j].dieNum;
                    }
                }
                if (numberedButtons1[i].isEnabled()) {
                    numberedButtons1[i].setText("" + displayCount);
                }
            } else {

            }
        }*/

        for (int j = 0; j < thedice.length; j++) {
            diceVals[thedice[j].dieNum - 1]++;
        }
        numberedButtons1[0].setText("" + aceScore());
        numberedButtons1[1].setText("" + twoScore());
        numberedButtons1[2].setText("" + threeScore());
        numberedButtons1[3].setText("" + fourScore());
        numberedButtons1[4].setText("" + fiveScore());
        numberedButtons1[5].setText("" + sixScore());
        numberedButtons1[6].setText("" + threeOfaKind());
        numberedButtons1[7].setText("" + fourOfaKind());
        numberedButtons1[8].setText("" + fullHouse());






        numberedButtons1[12].setText("" + chance());


        for (int j = 0; j < diceVals.length; j++) {
            diceVals[j] = 0;
        }

    }

    public int aceScore() {
        return diceVals[0] * 1;
    }

    public int twoScore() {
        return diceVals[1] * 2;
    }

    public int threeScore() {
        return diceVals[2] * 3;
    }

    public int fourScore() {
        return diceVals[3] * 4;
    }

    public int fiveScore() {
        return diceVals[4] * 5;
    }

    public int sixScore() {
        return diceVals[5] * 6;
    }

    public int threeOfaKind() {
        for (int i = 0; i < diceVals.length; i++) {
            if (diceVals[i] > 2) {
                return sum();
            }
        }
        return 0;
    }

    public int fourOfaKind(){
        for (int i = 0; i < diceVals.length; i++) {
            if (diceVals[i] > 3) {
                return sum();
            }
        }
        return 0;
    }
//
    public int fullHouse(){
        for (int i = 0; i < diceVals.length; i++) {
            if (diceVals[i] == 2 || diceVals[i] == 3) {
                if(diceVals[i] == 2){
                    for(int j = i+1; j < diceVals.length; j++){
                        if(diceVals[j] == 3) {
                            return FULLHOUSESCORE;
                        }
                    }
                }
                if(diceVals[i] == 3){
                    for(int j = i+1; j < diceVals.length; j++){
                        if(diceVals[j] == 2) {
                            return FULLHOUSESCORE;
                        }
                    }
                }
            }
        }
        return 0;
    }








    public int chance()
    {
        return sum();
    }
    public int sum(){
        return ((diceVals[0] * 1) + (diceVals[1] * 2) + (diceVals[2] * 3) + (diceVals[3] * 4) + (diceVals[4] * 5) + (diceVals[5] * 6));
    }
}

