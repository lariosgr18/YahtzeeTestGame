package edu.up.cs301.Yahtzee;

import android.util.Log;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Created by Michael on 11/29/2015.
 */
public class YahtzeeHardComputerPlayer extends YahtzeeComputerPlayer {

    private int[] diceValues = new int[5];
    private int scoreSelected;
    private int index;
    private int scoreCalc[] = new int[5];
    private int counter;
    private int LIMIT = 10;




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

        try {

            YahtzeeGameState state = (YahtzeeGameState) info;
            if(state.getCurrentPlayerID()== 1) {
                Log.d("COMPUTER PLAYER", "COMPUTERS TURN");


                for (int i = 0; i < diceValues.length; i++) {
                    diceValues[i] = (int) (Math.random() * 6 + 1);
                }
                RollAction rollMove = new RollAction(this);
                super.game.sendAction(rollMove);

                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }


                ScoreCalc calc = new ScoreCalc(diceValues);
                //calc.updateDiceVals(diceValues);
                calc.computerCalculator();

               counter = 0;

                while (counter == 0 ) {

                    for (int i = 0; i < calc.scoreValues.length; i++) {
                        if ((calc.scoreValues[i] > LIMIT) && ((YahtzeeGameState) info).getButtonsPressed2()[i] == true) {

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            //calc.updateDiceVals(diceValues);
                            //calc.computerCalculator();
                            scoreSelected = calc.scoreValues[i];
                            index = i;
                            SelectScoreAction selectMove = new SelectScoreAction(this);
                            super.game.sendAction(selectMove);
                            counter = 1;
                        }

                    }

                    if ( counter !=1){

                        for (int i = 0; i < diceValues.length; i++)
                        {
                            diceValues[i] = (int) (Math.random() * 6 + 1);
                        }

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                        RollAction rollMove2 = new RollAction(this);
                        super.game.sendAction(rollMove2);
                        calc.updateDiceVals(diceValues);
                        calc.computerCalculator();


                        for (int i = 0; i < calc.scoreValues.length; i++) {
                            if (calc.scoreValues[i] > LIMIT && ((YahtzeeGameState) info).getButtonsPressed2()[i] == true) {

                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                index=i;
                                calc.updateDiceVals(diceValues);
                                calc.computerCalculator();
                                scoreSelected = calc.scoreValues[i];
                                SelectScoreAction selectMove = new SelectScoreAction(this);
                                super.game.sendAction(selectMove);
                                counter = 1;
                            }

                        }
                    }

                    if ( counter !=1){

                        for (int i = 0; i < diceValues.length; i++)
                        {
                            diceValues[i] = (int) (Math.random() * 6 + 1);
                        }

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                        RollAction rollMove3 = new RollAction(this);
                        super.game.sendAction(rollMove3);


                        for (int i = 0; i < calc.scoreValues.length; i++) {

                            if (calc.scoreValues[i] > LIMIT && ((YahtzeeGameState) info).getButtonsPressed2()[i] == true) {


                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                index=1;
                                calc.updateDiceVals(diceValues);
                                calc.computerCalculator();
                                scoreSelected = calc.scoreValues[i];
                                SelectScoreAction selectMove = new SelectScoreAction(this);
                                super.game.sendAction(selectMove);
                                counter = 1;
                            }
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
