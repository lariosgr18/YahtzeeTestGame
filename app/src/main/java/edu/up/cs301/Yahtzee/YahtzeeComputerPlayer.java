package edu.up.cs301.Yahtzee;

import android.util.Log;
import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;


/**
 * Created by Michael, Grayson, Abhinav on 10/20/2015.
 *
 * This represents the easy AI
 *
 * Controls the Easy Computer Player
 */
public class YahtzeeComputerPlayer extends GameComputerPlayer{

    private int[] diceValues = new int[5];// Represents the dice values of a roll
    private int scoreSelected; //Represents the value of the score to be selected
    private int index; // Is the index of the button to be selected in the array

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

        //attempts to cast the information sent into a YahtzeeGameState
        try {
            //represents the current game info in terms of a YahtzeeGameState
            YahtzeeGameState state = (YahtzeeGameState) info;

            //checks if it us the computer's turn
            if(state.getCurrentPlayerID()== 1) {
                Log.d("COMPUTER PLAYER", "COMPUTERS TURN");

                //Give random values for the dice
                for (int i = 0; i < diceValues.length; i++) {
                    diceValues[i] = (int) (Math.random() * 6 + 1);
                }

                //send in the roll action
                RollAction rollMove = new RollAction(this);
                super.game.sendAction(rollMove);
                Thread.sleep(1000);
                //decide whether to roll twice or select a score randomly
                int move = (int) ((Math.random() * 2) + 1);
                if (move == 1) {
                    for (int i = 0; i < 2; i++) {
                        //sleeps
                        Thread.sleep(2000*(i+1));
                        //represents a random roll
                        for (int j = 0; j < diceValues.length; j++) {
                            diceValues[j] = (int) (Math.random() * 6 + 1);

                        }

                        //sends the second roll
                        RollAction rollMove2 = new RollAction(this);
                        super.game.sendAction(rollMove2);
                    }
                }
                //print dice to the log for testing purposes
                for (int i = 0; i < diceValues.length; i++) {
                    Log.d("diceValues: ", "" + diceValues[i]);
                }

                //sleeps so the dice can be drawn
                Thread.sleep(4000);

                //creates score calculator that decides the scores available
                ScoreCalc calc = new ScoreCalc(diceValues);
                //updates the scores available to the computer
                calc.computerCalculator();
                //sets the variable to zero so the new score selected  can be recorded
                scoreSelected = 0;

                //picks the highest available score
                for(int i = 0; i < calc.scoreValues.length; i++) {
                    if(calc.scoreValues[i] >= scoreSelected && ((YahtzeeGameState) info).getButtonsPressed2()[i] == true) {
                        index = i;
                        scoreSelected = calc.scoreValues[index];
                    }
                }
                //send the select score action
                SelectScoreAction selectMove = new SelectScoreAction(this);
                super.game.sendAction(selectMove);
            }
        }
        //catch if the gameState is not a gameState
        catch (ClassCastException cast) {
            Log.d("Error", "ClassCast");
        }
        //catch for Thread.Sleep
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }//receiveInfo


    /*
    GETTER AND SETTER METHODS

     */
    public int[] getDiceValues() {
        return diceValues;
    }

    public void setDiceValues(int[] diceValues) {
        this.diceValues = diceValues;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    public int getScoreSelected() {
        return scoreSelected;
    }

    public void setScoreSelected(int scoreSelected) {
        this.scoreSelected = scoreSelected;
    }


}
