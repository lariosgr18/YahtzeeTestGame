package edu.up.cs301.Yahtzee;

import android.graphics.Color;
import android.util.Log;
import android.widget.Button;

import edu.up.cs301.animation.Dice;

/**
 * Created by Grayson, Michael, Abhinav on 11/8/2015.
 *
 * Controls the scores associated with each of the buttons.
 */
public class ScoreCalc {

    private Dice[] diceObjects = new Dice[6];// the dice objects passed in

    private Button[] playerButtons = new Button[13];//the players buttons
    private Button[] computerButtons = new Button[13];//the buttons used by the computer

    public int scoreValues[] = new int[13];//values of each of the scores available
    public int diceVals[] = new int[6];//the number of values of each dice


    private static final int FULLHOUSESCORE = 25;//value of a fullhouse
    private static final int SMALLSTRAIGHTSCORE = 30;//value of a small straight
    private static final int LARGESTRAIGHTSCORE = 40;//value of a large straight
    private static final int YAHTZEESCORE = 50;//value of a yahtzee
    public static boolean YAHTZEE = false;//check to see if a player has gotten at least one yahtzee


    private int currentYahtzeeScore = 0;//the current score of the yahtzee category
    public int yahtzeeCount=0; // Records the number of yahtzees the human player has selected

    /**
    *
    *Constructor for the Human player
    * Takes in an array of buttons, dice objects, and the computers buttons and sets them
     *  */
    public ScoreCalc(Button[] buttons, Dice[] dice, Button[] computerButtons1) {
        //Activity card = player.mainActivity;
        for (int i = 0; i < playerButtons.length; i++) {
            this.playerButtons[i] = buttons[i];
            this.computerButtons[i] = computerButtons1[i];
        }

        this.diceObjects = dice;
    }

    /*
    Constructor for the computer player
    Takes in array of integer values
     */
    public ScoreCalc(int Values[]) {
        //Sets our array of integer values based on the array taken in
        for (int j = 0; j < Values.length; j++) {
            diceVals[Values[j] - 1]++;
            Log.d("score constructor:", ""+ Values[j]);
        }
    }

    /*
    Updates the Human Player Score card/buttons
     */
    public void updateScoreCard() {

        //updates our array so the correct score can be calculated
        for (int j = 0; j < diceObjects.length; j++) {
            diceVals[diceObjects[j].dieNum - 1]++;
        }

        //Updates the button if it has not been chosen yet.
        if(playerButtons[0].isEnabled()) {
            playerButtons[0].setText("" + aceScore());
        }
        //Updates the button if it has not been chosen yet.
        if(playerButtons[1].isEnabled()) {
            playerButtons[1].setText("" + twoScore());
        }
        //Updates the button if it has not been chosen yet.
        if(playerButtons[2].isEnabled()) {
            playerButtons[2].setText("" + threeScore());
        }
        //Updates the button if it has not been chosen yet.
        if(playerButtons[3].isEnabled()) {
            playerButtons[3].setText("" + fourScore());
        }
        //Updates the button if it has not been chosen yet.
        if(playerButtons[4].isEnabled()) {
            playerButtons[4].setText("" + fiveScore());
        }
        //Updates the button if it has not been chosen yet.
        if(playerButtons[5].isEnabled()) {
            playerButtons[5].setText("" + sixScore());
        }
        //Updates the button if it has not been chosen yet.
        if(playerButtons[6].isEnabled()) {
            playerButtons[6].setText("" + threeOfaKind());
        }
        //Updates the button if it has not been chosen yet.
        if(playerButtons[7].isEnabled()) {
            playerButtons[7].setText("" + fourOfaKind());
        }
        //Updates the button if it has not been chosen yet.
        if(playerButtons[8].isEnabled()) {
            playerButtons[8].setText("" + fullHouse());
        }
        //Updates the button if it has not been chosen yet.
        if(playerButtons[9].isEnabled()) {
            playerButtons[9].setText("" + smallStraight());
        }
        //Updates the button if it has not been chosen yet.
        if(playerButtons[10].isEnabled()) {
            playerButtons[10].setText("" + largeStraight());
        }
        //Updates the button if it has not been chosen yet.
        if(CheckYahtzee() && Integer.parseInt((String)playerButtons[11].getText()) >= 50) {
            playerButtons[11].setBackgroundColor(Color.LTGRAY);
            playerButtons[11].setEnabled(true);
            playerButtons[11].setTextSize(15);
        }
        //Updates the button if it has not been chosen yet.
        if(playerButtons[11].isEnabled()) {
            playerButtons[11].setText("" + yahtzeePlayer());
        }
        //Updates the button if it has not been chosen yet.
        if(playerButtons[12].isEnabled()) {
            playerButtons[12].setText("" + chance());
        }

        //Resets our values to zero to get ready to take in the next roll
        for (int j = 0; j < diceVals.length; j++) {
            diceVals[j] = 0;
        }

    }

    /*
   Updates the Computer Player Score card/buttons
    */
    public  void updateComputerCard(){

        //update the dice values fso our score can be calculated correctly
        for (int j = 0; j < diceObjects.length; j++) {
            diceVals[diceObjects[j].dieNum - 1]++;
            Log.d("updateComputerCard: ", "" + diceObjects[j]);
        }

        //Updates the button if it has not been chosen yet.
        if(computerButtons[0].isEnabled()) {
            computerButtons[0].setText("" + aceScore());
        }
        //Updates the button if it has not been chosen yet.
        if(computerButtons[1].isEnabled()) {
            computerButtons[1].setText("" + twoScore());
        }
        //Updates the button if it has not been chosen yet.
        if(computerButtons[2].isEnabled()) {
            computerButtons[2].setText("" + threeScore());
        }
        //Updates the button if it has not been chosen yet.
        if(computerButtons[3].isEnabled()) {
            computerButtons[3].setText("" + fourScore());
        }
        //Updates the button if it has not been chosen yet.
        if(computerButtons[4].isEnabled()) {
            computerButtons[4].setText("" + fiveScore());
        }
        //Updates the button if it has not been chosen yet.
        if(computerButtons[5].isEnabled()) {
            computerButtons[5].setText("" + sixScore());
        }
        //Updates the button if it has not been chosen yet.
        if(computerButtons[6].isEnabled()) {
            computerButtons[6].setText("" + threeOfaKind());
        }
        //Updates the button if it has not been chosen yet.
        if(computerButtons[7].isEnabled()) {
            computerButtons[7].setText("" + fourOfaKind());
        }
        //Updates the button if it has not been chosen yet.
        if(computerButtons[8].isEnabled()) {
            computerButtons[8].setText("" + fullHouse());
        }
        //Updates the button if it has not been chosen yet.
        if(computerButtons[9].isEnabled()) {
            computerButtons[9].setText("" + smallStraight());
        }
        //Updates the button if it has not been chosen yet.
        if(computerButtons[10].isEnabled()) {
            computerButtons[10].setText("" + largeStraight());
        }
        //Updates the button if it has not been chosen yet.

        if(computerButtons[11].isEnabled()) {
            computerButtons[11].setText("" + yahtzeeComputer());
        }
        //Updates the button if it has not been chosen yet.
        if(computerButtons[12].isEnabled()) {
            computerButtons[12].setText("" + chance());
        }

        //Resets all the values to get ready for the next roll
        for (int j = 0; j < diceVals.length; j++) {
            diceVals[j] = 0;
        }

    }

    /*
   Calculates the available scores for the computer player to pick from
    */
    public void computerCalculator() {

        for(int i = 0; i < scoreValues.length; i++) {
            switch (i) {   //Updates the scoreValue index based on its associated score
                case 0:
                    scoreValues[i] = aceScore();
                    break;
                //Updates the scoreValue index based on its associated score
                case 1:
                    scoreValues[i] = twoScore();
                    break;
                //Updates the scoreValue index based on its associated score
                case 2:
                    scoreValues[i] = threeScore();
                    break;
                //Updates the scoreValue index based on its associated score
                case 3:
                    scoreValues[i] = fourScore();
                    break;
                //Updates the scoreValue index based on its associated score
                case 4:
                    scoreValues[i] = fiveScore();
                    break;
                //Updates the scoreValue index based on its associated score
                case 5:
                    scoreValues[i] = sixScore();
                    break;
                //Updates the scoreValue index based on its associated score
                case 6:
                    scoreValues[i] = threeOfaKind();
                    break;
                //Updates the scoreValue index based on its associated score
                case 7:
                    scoreValues[i] = fourOfaKind();
                    break;
                //Updates the scoreValue index based on its associated score
                case 8:
                    scoreValues[i] = fullHouse();
                    break;
                //Updates the scoreValue index based on its associated score
                case 9:
                    scoreValues[i] = smallStraight();
                    break;
                //Updates the scoreValue index based on its associated score
                case 10:
                    scoreValues[i] = largeStraight();
                    break;
                //Updates the scoreValue index based on its associated score
                case 11:
                    scoreValues[i] = yahtzeeComputer();
                    break;
                //Updates the scoreValue index based on its associated score
                case 12:
                    scoreValues[i] = chance();
                    break;
            }
        }
    }

    /*
    updates the values of the dice
     */
    public void updateDiceVals( int diceV[]) {
        //Resets to zero to be ready to be updated
        for (int j = 0; j < diceVals.length; j++) {
            diceVals[j] = 0;
        }
        //Updates based on the array taken in
        for (int j = 0; j < diceV.length; j++) {
            diceVals[diceV[j] - 1]++;
        }

    }


    /*
       Calculates the number of aces rolled
        */
    public int aceScore() {return diceVals[0] * 1; }

    /*
    Calculates the number of twos rolled
    */
    public int twoScore() {
        return diceVals[1] * 2;
    }

    /*
   Calculates the number of threes rolled
   */
    public int threeScore() {
        return diceVals[2] * 3;
    }

    /*
   Calculates the number of fours rolled
   */
    public int fourScore() {
        return diceVals[3] * 4;
    }

    /*
   Calculates the number of fives rolled
   */
    public int fiveScore() {
        return diceVals[4] * 5;
    }

    /*
   Calculates the number of sixes rolled
   */
    public int sixScore() {
        return diceVals[5] * 6;
    }

    /*
   Calculates if a three of a kind is rolled
   */
    public int threeOfaKind() {
        for (int i = 0; i < diceVals.length; i++) {
            //Checks if there are an three of a kind
            if (diceVals[i] > 2) {
                return sum();
            }
        }
        return 0;
    }

    /*
   Calculates if a four of a kind is rolled
   */
    public int fourOfaKind(){
        for (int i = 0; i < diceVals.length; i++) {
            if (diceVals[i] > 3) {
                return sum();
            }
        }
        return 0;
    }

    /*
   Calculates if a full house is rolled
   */
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

    /*
   Calculates if a small straight is rolled
   */
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
                }
                else if(diceVals[4] > 0){
                    if(diceVals[5] > 0){
                        return SMALLSTRAIGHTSCORE;
                    }
                }
            }
        }
        return 0;
    }


    /*
       Calculates if a large straight is rolled
       */
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

    /*
   Calculates if a yahtzee is rolled
   */
    public int yahtzeePlayer(){
        for(int i = 0; i < diceVals.length; i++){
            if(diceVals[i] == 5){
             currentYahtzeeScore = YAHTZEESCORE + 100* yahtzeeCount;
                return currentYahtzeeScore;
            }
        }
        return 0;
    }

    public int yahtzeeComputer(){
        for(int i = 0; i < diceVals.length; i++){
            if(diceVals[i] == 5){
                currentYahtzeeScore = YAHTZEESCORE;
                return currentYahtzeeScore;
            }
        }
        return 0;
    }


    /*
    Checks if a yahtzee is rolled
   */
    public boolean CheckYahtzee(){
        for(int i = 0; i < diceVals.length; i++){
            if(diceVals[i] == 5){
                return true;
            }
        }
        return false;
    }


    /*
   Calculates the chance category - sum of all the dice values
   */
    public int chance()
    {
        return sum();
    }

    /*
   Calculates the sum of the dice
   */
    public int sum(){
        return ((diceVals[0] * 1) + (diceVals[1] * 2) + (diceVals[2] * 3) + (diceVals[3] * 4) + (diceVals[4] * 5) + (diceVals[5] * 6));
    }



    /*
    Setter/Getter for the Dice Objects
   */
    public Dice[] getDiceObjects() {
        return diceObjects;
    }

    public void setDiceObjects(Dice[] diceObjects) {
        for(int i = 0; i < diceObjects.length; i++) {
            this.diceObjects[i] = diceObjects[i];
        }
    }

    public void incrementYahtzee()
    {
        this.yahtzeeCount++;
    }
}

