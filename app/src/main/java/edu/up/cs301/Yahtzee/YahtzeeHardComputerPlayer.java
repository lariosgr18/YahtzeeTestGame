package edu.up.cs301.Yahtzee;

import android.util.Log;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Created by Michael on 11/29/2015.
 */
public class YahtzeeHardComputerPlayer extends YahtzeeComputerPlayer {
    // 93, 146, 103, 113, 78, 101
    //125, 132. 127,






    private int[] diceValues = new int[5]; //Represents the values of a roll
    private int scoreSelected;  //Represents the value of the Score to be selected
    private int index; //Is the index of the button to be "clicked on"

    private int counter;//Keeps track if a score has been selected or not
    private int LIMIT = 10; // A threshold to keep track of the minimum score we need to record a score
    private int highest;   //Represents the highes score we can record durign this turn
    private int highestIndex; //Represents the index of the butto nof the highest score

    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public YahtzeeHardComputerPlayer(String name) {
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
        //Attempts to cast the GameInfo into a GameState
        try {

            YahtzeeGameState state = (YahtzeeGameState) info;
            //Checks if it is the computer players turn
            if(state.getCurrentPlayerID()== 1) {
                Log.d("COMPUTER PLAYER", "COMPUTERS TURN");


                //Random Roll 1
                for (int i = 0; i < diceValues.length; i++) {
                    diceValues[i] = (int) (Math.random() * 6 + 1);
                }
                //Sends the roll move
                RollAction rollMove = new RollAction(this);
                super.game.sendAction(rollMove);

                //Sleeps to let the dice be drawn
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //Declares our score calculator
                ScoreCalc calc = new ScoreCalc(diceValues);
                //calc.updateDiceVals(diceValues);
                calc.computerCalculator();

                //updates our highes score possible
                highest = 0;
                for(int i = 0; i < calc.scoreValues.length; i++)
                {
                    if(calc.scoreValues[i] >= highest && ((YahtzeeGameState) info).getButtonsPressed2()[i] == true)
                    {
                        highestIndex = i;
                        highest = calc.scoreValues[i];
                    }
                }

               counter = 0;
                highest = 0;
                //Keeps track of the turn. All moves happen in the this while loop
                while (counter == 0 ) {

                    // Checks for the first roll to see if any is more than our threshold. If so, the it chooses it
                    for (int i = 0; i < calc.scoreValues.length; i++) {
                        if ((calc.scoreValues[i] > LIMIT) && ((YahtzeeGameState) info).getButtonsPressed2()[i] == true) {

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }


                            scoreSelected = highest;
                            index = highestIndex;
                            SelectScoreAction selectMove = new SelectScoreAction(this);
                            super.game.sendAction(selectMove);
                            counter = 1;
                        }

                    }

                    //Checks to see if we have scored something yet. Checks for our threshold condition and selects it if it is above our threshold
                    if ( counter !=1){

                        //Random roll
                        for (int i = 0; i < diceValues.length; i++)
                        {
                            diceValues[i] = (int) (Math.random() * 6 + 1);
                        }

                        //Sleep so dice can be drawn
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                        //Sends our second Roll Action
                        RollAction rollMove2 = new RollAction(this);
                        super.game.sendAction(rollMove2);
                        calc.updateDiceVals(diceValues);
                        calc.computerCalculator();

                        //Updates our highest score available
                        highest = 0;
                        for(int i = 0; i < calc.scoreValues.length; i++)
                        {
                            if(calc.scoreValues[i] >= highest && ((YahtzeeGameState) info).getButtonsPressed2()[i] == true)
                            {
                                highestIndex = i;
                                highest = calc.scoreValues[i];
                            }
                        }


                        //Checks to see if we can select a score
                        for (int i = 0; i < calc.scoreValues.length; i++) {
                            if (calc.scoreValues[i] > LIMIT && ((YahtzeeGameState) info).getButtonsPressed2()[i] == true) {

                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                index=highestIndex;
                                calc.updateDiceVals(diceValues);
                                calc.computerCalculator();
                                scoreSelected = highest;
                                SelectScoreAction selectMove = new SelectScoreAction(this);
                                super.game.sendAction(selectMove);
                                counter = 1;
                            }

                        }
                    }

                    //Checks is computer should roll for a third time
                    if ( counter !=1){

                        //Random Roll
                        for (int i = 0; i < diceValues.length; i++)
                        {
                            diceValues[i] = (int) (Math.random() * 6 + 1);
                        }

                        //Sleep
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                        //Sends in our third roll
                        RollAction rollMove3 = new RollAction(this);
                        super.game.sendAction(rollMove3);

                        calc.updateDiceVals(diceValues);
                        calc.computerCalculator();

                        //Updates our highest possible available score
                        highest = 0;
                        for(int i = 0; i < calc.scoreValues.length; i++)
                        {
                            if(calc.scoreValues[i] >= highest && ((YahtzeeGameState) info).getButtonsPressed2()[i] == true)
                            {
                                highestIndex = i;
                                highest = calc.scoreValues[i];
                            }
                        }

                        for (int i = 0; i < calc.scoreValues.length; i++) {

                            //Checks our condition
                            if (calc.scoreValues[i] > LIMIT && ((YahtzeeGameState) info).getButtonsPressed2()[i] == true) {
//

                                //Sleep
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                //If condition met, we select
                                index=highestIndex;
                                scoreSelected = highest;
                                SelectScoreAction selectMove = new SelectScoreAction(this);
                                super.game.sendAction(selectMove);
                                counter = 1;
                            }
                            //else we randomly pick
                            else{
                                    int select ;
                                while (true) {
                                    select = (int) ((Math.random() * 13));
                                    if (select == 13) {
                                        select = 12;
                                    }
                                    if (((YahtzeeGameState) info).getButtonsPressed2()[select] == true) {
                                        index = select;
                                        break;
                                    }
                                }//

                                    //random
                                calc.updateDiceVals(diceValues);
                                calc.computerCalculator();
                                scoreSelected = calc.scoreValues[index];
                                SelectScoreAction selectMove = new SelectScoreAction(this);
                                super.game.sendAction(selectMove);
                                counter = 1;
                            }

                        }
                    }

                }


                // scoreSelected = calc.scoreValues[index];



                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
        catch (ClassCastException cast)
        {
            Log.d("Error", "ClassCast");
        }







    }//receiveInfo



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
