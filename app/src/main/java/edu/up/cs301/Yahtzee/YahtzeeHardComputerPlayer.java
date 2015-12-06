package edu.up.cs301.Yahtzee;

import android.util.Log;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Created by Michael on 11/29/2015.
 */
public class YahtzeeHardComputerPlayer extends YahtzeeComputerPlayer {
    // 93, 146, 103, 113, 78, 101
    //125, 132. 127,dg

    private int placement;
    private int rollDecider;
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




                //first roll
                rollAI();
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
               // highest = 0;
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

                    //if we roll for a second time
                    if(counter != 1){

                        //second roll
                        rollAI();
                        calc.updateDiceVals(diceValues);
                        calc.computerCalculator();


                        //Sends the roll move
                        RollAction rollMove1 = new RollAction(this);
                        super.game.sendAction(rollMove1);

                        //Sleeps to let the dice be drawn
                        try {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }

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



                    }

                    //third roll
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
                        //Sleeps to let the dice be drawn
                        try {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }

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
                                    Thread.sleep(2000);
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

                    if( counter !=1){



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

                            //Sleeps to let the dice be drawn
                             try {
                                    Thread.sleep(1000);
                                }
                             catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                    //random
                                calc.updateDiceVals(diceValues);
                                calc.computerCalculator();
                                scoreSelected = calc.scoreValues[index];
                                SelectScoreAction selectMove = new SelectScoreAction(this);
                                super.game.sendAction(selectMove);
                                counter = 1;
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

    public void rollAI(){

        rollDecider = (int) (Math.random()*20);

        //fullhouse
        if( rollDecider == 1  ){
            diceValues[0] = (int) (Math.random() * 6 + 1);
            diceValues[2] = (int) (Math.random() * 6 + 1);
            diceValues[3] = diceValues[0];
            diceValues[1] = diceValues[2];
            diceValues[4] = diceValues[2];
        }
        //four of a kind
        else if( rollDecider == 2  ){
            diceValues[0] = (int) (Math.random() * 6 + 1);
            diceValues[2] = (int) (Math.random() * 6 + 1);
            diceValues[3] = diceValues[0];
            diceValues[1] = diceValues[0];
            diceValues[4] = diceValues[0];
        }
        //small straight
        else if( rollDecider == 3  ){
            placement = (int) (Math.random() * 3 +1);
            if( placement == 1){
                diceValues[0] = (int) (Math.random() * 6 + 1);
                diceValues[2] = 2;
                diceValues[3] = 3;
                diceValues[1] = 4;
                diceValues[4] = 1;

            }
            else if( placement == 2){
                diceValues[0] = (int) (Math.random() * 6 + 1);
                diceValues[2] = 2;
                diceValues[3] = 4;
                diceValues[1] = 3;
                diceValues[4] = 5;

            }
            else
            {
                diceValues[0] = (int) (Math.random() * 6 + 1);
                diceValues[2] = 6;
                diceValues[3] = 5;
                diceValues[1] = 3;
                diceValues[4] = 4;
            }

        }
        //large Straight
        else if ( rollDecider == 3){
            placement = (int) (Math.random() * 2 + 1);
            if( placement == 1){
                diceValues[0] = 2;
                diceValues[2] = 6;
                diceValues[3] = 5;
                diceValues[1] = 3;
                diceValues[4] = 4;
            }
            else {
                diceValues[0] = 1;
                diceValues[2] = 4;
                diceValues[3] = 3;
                diceValues[1] = 5;
                diceValues[4] = 2;
            }

        }
        //three of a kind
        else if( rollDecider == 4){
            diceValues[0] = (int) (Math.random() *6 +1);
            diceValues[1] = (int) (Math.random() *6 +1);
            diceValues[2] = diceValues[0] ;
            diceValues[3] = (int) (Math.random() *6 +1);
            diceValues[4] = diceValues[0];
        }
        else if( rollDecider == 5){
            diceValues[0] = (int) (Math.random() *6 +1);
            diceValues[1] = diceValues[0];
            diceValues[2] = diceValues[0] ;
            diceValues[3] = diceValues[0];
            diceValues[4] = diceValues[0];
        }
        else{
            //Random Roll 1
            for (int i = 0; i < diceValues.length; i++) {
                diceValues[i] = (int) (Math.random() * 6 + 1);
            }
        }
    }



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
