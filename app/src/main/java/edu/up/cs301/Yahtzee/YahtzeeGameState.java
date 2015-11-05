package edu.up.cs301.Yahtzee;

import edu.up.cs301.game.infoMsg.GameState;

/**
 * Created by Michael on 10/20/2015.
 */
public class YahtzeeGameState extends GameState {

    private int player1Id; //player 1 id



    private int player2Id; // player 1 id
    private int turns; // total turns counter
    private int rolls; //tootal rolls counter

    private int[] player1Score = new int[13]; // player 1 score
    private int[] player2Score = new int[13]; //player 1 score;
    private int[] diceValue = new int[6]; //dice values
    private boolean[] buttonsPressed = new boolean[13]; // keeps track of which buttons have been pressed


    public YahtzeeGameState( YahtzeeGameState state) {
        this.player1Id = state.getPlayer1Id();
        this.player2Id = state.getPlayer2Id();
        this.turns = state.getTurns();
        this.rolls = state.getRolls();
        this.player1Score = state.getPlayer1Score();
        this.player2Score = state.getPlayer2Score();
        this.diceValue = state.getDiceValue();
        this.buttonsPressed = state.getButtonsPressed();
    }

    public YahtzeeGameState(int playerId, int turns, int rolls) {
        for (int i=0; i < buttonsPressed.length ;i++)
        {
            buttonsPressed[i]= false;
        }

        this.player1Id = 0 ;
        this.player2Id = 0;
        this.turns = 0;
        this.rolls = 0;
    }

    public void rollDice()
    {

    }

    public void selectScore()
    {

    }



    public int getPlayer1Id() {
        return player1Id;
    }

    public int getPlayer2Id() {
        return player2Id;
    }

    public int getTurns() {
        return turns;
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

    public void setTurns(int turns) {
        this.turns = turns;
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
}

