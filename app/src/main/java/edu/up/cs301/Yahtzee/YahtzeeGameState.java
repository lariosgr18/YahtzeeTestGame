package edu.up.cs301.Yahtzee;

import edu.up.cs301.game.infoMsg.GameState;

/**
 * Created by Michael on 10/20/2015.
 */
public class YahtzeeGameState extends GameState {

    private int player1Id; //player 1 id
    private int player2Id; // player 1 id
    private int player1turns; // total turns counter
    private int player2turns;//total turns counter
    private int rolls; //tootal rolls counter
    private int currentPlayerID;
    private int[] player1Score = new int[13]; // player 1 score
    private int[] player2Score = new int[13]; //player 1 score;
    private int[] diceValue = new int[5]; //dice values
    private boolean[] buttonsPressed = new boolean[13]; // keeps track of which buttons have been pressed
    private boolean[] buttonsPressed2 = new boolean[13];


    public YahtzeeGameState( YahtzeeGameState state) {
        this.player1Id = state.getPlayer1Id();
        this.player2Id = state.getPlayer2Id();
        this.player1turns = state.getPlayer1turns();
        this.player2turns = state.getPlayer2turns();
        this.rolls = state.getRolls();
        this.player1Score = state.getPlayer1Score();
        this.player2Score = state.getPlayer2Score();
        this.diceValue = state.getDiceValue();
        this.buttonsPressed = state.getButtonsPressed();
        this.buttonsPressed2 = state.getButtonsPressed2();
        this.currentPlayerID = state.getCurrentPlayerID();
    }

    public YahtzeeGameState(int playerId) {
        for (int i=0; i < buttonsPressed.length ;i++)
        {
            buttonsPressed[i]= false;
            buttonsPressed2[i] = false;
        }

        this.player1Id = 1;
        this.player2Id = 2;
        this.player1turns = 0;
        this.player2turns = 0;
        this.rolls = 1;
        this.currentPlayerID = playerId;
    }

    public void rollDice(int DiceValues[],int rollerID)
    {
        if(rolls < 3 && rollerID == currentPlayerID)
        {
            for(int i = 0; i < diceValue.length; i++)
            {
                diceValue[i]= DiceValues[i];
            }
            rolls++;
        }
        else
        {
            return;
        }
    }

    public void selectScore(int score, int index, int player, boolean buttonPressed)
    {
        if(player == currentPlayerID) {
            if (player == player1Id) {
                player1Score[index] = score;
                buttonsPressed[index] = buttonPressed;
                currentPlayerID = player2Id;
                player1turns++;
            } else
            {
                player2Score[index] = score;
                buttonsPressed[index] = buttonPressed;
                currentPlayerID = player1Id;
                player2turns++;
            }
            rolls = 1;
        }
    }



    public int getPlayer1Id() {
        return player1Id;
    }

    public int getPlayer2Id() {
        return player2Id;
    }

    public int getPlayer1turns() {
        return player1turns;
    }

    public int getRolls() {
        return rolls;
    }

    public int[] getPlayer1Score() {
        return player1Score;
    }

    public int[] getPlayer2Score() {
        return player2Score;
    }

    public int[] getDiceValue() {
        return diceValue;
    }

    public boolean[] getButtonsPressed() {
        return buttonsPressed;
    }

    public void setPlayer1Id(int player1Id) {
        this.player1Id = player1Id;
    }

    public void setPlayer2Id(int player2Id) {
        this.player2Id = player2Id;
    }

    public void setPlayer1turns(int turns) {
        this.player1turns = turns;
    }

    public void setRolls(int rolls) {
        this.rolls = rolls;
    }

    public void setPlayer1Score(int[] player1Score) {
        this.player1Score = player1Score;
    }

    public void setPlayer2Score(int[] player2Score) {
        this.player2Score = player2Score;
    }

    public void setDiceValue(int[] diceValue) {
        this.diceValue = diceValue;
    }

    public void setButtonsPressed(boolean[] buttonsPressed) {
        this.buttonsPressed = buttonsPressed;
    }

    public int getCurrentPlayerID() {
        return currentPlayerID;
    }

    public void setCurrentPlayerID(int currentPlayerID) {
        this.currentPlayerID = currentPlayerID;
    }

    public boolean[] getButtonsPressed2() {
        return buttonsPressed2;
    }

    public int getPlayer2turns() {
        return player2turns;
    }

    public void setPlayer2turns(int player2turns) {
        this.player2turns = player2turns;
    }

    public void setButtonsPressed2(boolean[] buttonsPressed2) {
        this.buttonsPressed2 = buttonsPressed2;
    }
}

