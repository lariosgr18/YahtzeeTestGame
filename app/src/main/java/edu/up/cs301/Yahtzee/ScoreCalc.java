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

    private int currentYahtzeeScore = 0;

    private Button[] numberedButtons1 = new Button[13];

    private Button[] computerButtons = new Button[13];

    private static final int FULLHOUSESCORE = 25;

    private static final int SMALLSTRAIGHTSCORE = 30;

    private static final int LARGESTRAIGHTSCORE = 40;

    private static final int YAHTZEESCORE = 50;

    public static boolean YAHTZEE = false;

    //Constructor for a human player
    public ScoreCalc(Button[] buttons, Dice[] dice, Button[] computerButtons1)
    {
        //Activity card = player.mainActivity;
        for (int i = 0; i < numberedButtons1.length; i++) {
            this.numberedButtons1[i] = buttons[i];
            this.computerButtons[i] = computerButtons1[i];
        }


        this.thedice = dice;
    }

    //Updates the buttons to reflect the score
    public void updateScoreCard() {

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

    public  void updateComputerCard(){

        for (int j = 0; j < thedice.length; j++) {
            diceVals[thedice[j].dieNum - 1]++;
        }


        if(computerButtons[0].isEnabled())
        {
            computerButtons[0].setText("" + aceScore());
        }
        if(computerButtons[1].isEnabled()) {
            computerButtons[1].setText("" + twoScore());
        }
        if(computerButtons[2].isEnabled()) {
            computerButtons[2].setText("" + threeScore());
        }
        if(computerButtons[3].isEnabled()) {
            computerButtons[3].setText("" + fourScore());
        }
        if(computerButtons[4].isEnabled()) {
            computerButtons[4].setText("" + fiveScore());
        }
        if(computerButtons[5].isEnabled()) {
            computerButtons[5].setText("" + sixScore());
        }
        if(computerButtons[6].isEnabled()) {
            computerButtons[6].setText("" + threeOfaKind());
        }
        if(computerButtons[7].isEnabled()) {
            computerButtons[7].setText("" + fourOfaKind());
        }
        if(computerButtons[8].isEnabled()) {
            computerButtons[8].setText("" + fullHouse());
        }
        if(computerButtons[9].isEnabled()) {
            computerButtons[9].setText("" + smallStraight());
        }
        if(computerButtons[10].isEnabled()) {
            computerButtons[10].setText("" + largeStraight());
        }
        if(computerButtons[11].isEnabled()) {
            computerButtons[11].setText("" + yahtzee());
        }
        if(computerButtons[12].isEnabled()) {
            computerButtons[12].setText("" + chance());
        }


        for (int j = 0; j < diceVals.length; j++) {
            diceVals[j] = 0;
        }


    }

    public int aceScore() {return diceVals[0] * 1; }

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
//
        for(int i = 0; i < diceVals.length; i++){
            if(diceVals[i] == 5){
                if(YAHTZEE == true ){
                    currentYahtzeeScore += 100;
                }
                else
                {
                    YAHTZEE = true;
                    currentYahtzeeScore = YAHTZEESCORE;
                }
            }
        }
        return currentYahtzeeScore;
    }



    public int chance()
    {
        return sum();
    }
    public int sum(){
        return ((diceVals[0] * 1) + (diceVals[1] * 2) + (diceVals[2] * 3) + (diceVals[3] * 4) + (diceVals[4] * 5) + (diceVals[5] * 6));
    }
}

