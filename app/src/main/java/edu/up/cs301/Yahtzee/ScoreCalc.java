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

    private static final int SMALLSTRAIGHTSCORE = 30;

    private static final int LARGESTRAIGHTSCORE = 40;

    private static final int YAHTZEESCORE = 50;

    public static boolean YAHTZEE = false;

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
        if(numberedButtons1[0].isEnabled())
        {
            numberedButtons1[0].setText("" + aceScore());
        }
        if(numberedButtons1[1].isEnabled()) {
            numberedButtons1[1].setText("" + twoScore());
        }
        if(numberedButtons1[2].isEnabled()) {
            numberedButtons1[2].setText("" + threeScore());
        }
        if(numberedButtons1[3].isEnabled()) {
            numberedButtons1[3].setText("" + fourScore());
        }
        if(numberedButtons1[4].isEnabled()) {
            numberedButtons1[4].setText("" + fiveScore());
        }
        if(numberedButtons1[5].isEnabled()) {
            numberedButtons1[5].setText("" + sixScore());
        }
        if(numberedButtons1[6].isEnabled()) {
            numberedButtons1[6].setText("" + threeOfaKind());
        }
        if(numberedButtons1[7].isEnabled()) {
            numberedButtons1[7].setText("" + fourOfaKind());
        }
        if(numberedButtons1[8].isEnabled()) {
            numberedButtons1[8].setText("" + fullHouse());
        }
        if(numberedButtons1[9].isEnabled()) {
            numberedButtons1[9].setText("" + smallStraight());
        }
        if(numberedButtons1[10].isEnabled()) {
            numberedButtons1[10].setText("" + largeStraight());
        }
        if(numberedButtons1[11].isEnabled()) {
            numberedButtons1[11].setText("" + yahtzee());
        }
        if(numberedButtons1[12].isEnabled()) {
            numberedButtons1[12].setText("" + chance());
        }


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

    public int smallStraight(){
        for(int i = 0; i < diceVals.length; i++){
            if(diceVals[2] > 0 && diceVals[3] > 0){
                if(diceVals[1] > 0){
                    if(diceVals[0] > 0){
                        return SMALLSTRAIGHTSCORE;
                    }
                    else if(diceVals[4] > 0){
                        return SMALLSTRAIGHTSCORE;
                    }
                } else if(diceVals[4] > 0){
                    if(diceVals[5] > 0){
                        return SMALLSTRAIGHTSCORE;
                    }
                }
            }
        }
        return 0;
    }

    public int largeStraight(){
        for(int i = 0; i < diceVals.length; i++){
            if(diceVals[1] > 0 && diceVals[2] > 0 && diceVals[3] > 0 && diceVals[4] > 0 ){
                if(diceVals[0] > 0){
                    return LARGESTRAIGHTSCORE;
                }
                if(diceVals[5] > 0){
                    return LARGESTRAIGHTSCORE;
                }
            }
        }
        return 0;
    }

    public int yahtzee(){

        for(int i = 0; i < diceVals.length; i++){
            if(diceVals[i] == 5){
                if(YAHTZEE == true ){
                    return Integer.parseInt((String)(numberedButtons1[11].getText())) + 100;
                }
                else
                {
                    YAHTZEE = true;
                    return YAHTZEESCORE;
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

