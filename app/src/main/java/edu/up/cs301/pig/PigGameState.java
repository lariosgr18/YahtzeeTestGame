package edu.up.cs301.pig;

import edu.up.cs301.game.infoMsg.GameState;

/**
 * Created by lariosgr18 on 10/14/2015.
 */


public class PigGameState extends GameState{

    private int playerID;
    private int player1Score;
    private int player2Score;
    private int currentTotal;
    private int currentDie;


    public PigGameState (){
        playerID=0;
        player1Score=0;
        player2Score=0;
        currentTotal=0;
        currentDie=0;


    }



    public PigGameState( GameState gameState){
        gameState = (PigGameState)gameState;
        this.playerID = ((PigGameState) gameState).getPlayerID();
        this.player1Score = ((PigGameState) gameState).getPlayer1Score();
        this.player2Score = ((PigGameState) gameState).getPlayer2Score();
        this.currentTotal = ((PigGameState) gameState).getCurrentTotal();
        this.currentDie = ((PigGameState) gameState).getCurrentDie();

    }

    public int getPlayerID()
    {
        return playerID;
    }

    public int getPlayer1Score()
    {
        return player1Score;
    }

    public int getPlayer2Score(){
    return player2Score;
    }


    public int getCurrentTotal()
    {
        return currentTotal;
    }

    public int getCurrentDie() {

        return currentDie;
    }



    public void hold(){
        if( playerID == 1){

            player1Score = player1Score + currentTotal;
            currentTotal=0;
            playerID=2;
        }

        if (playerID == 2){
            player2Score = player2Score + currentTotal;
            currentTotal=0;
            playerID=1;

        }


    }

    public void roll( ){

        int dieValue = (int) ((Math.random() *6 )+1);
        if(dieValue != 1) {
            currentTotal = currentTotal + dieValue;
        }
        else
        {
            currentDie=dieValue;
            currentTotal=0;
            hold();
        }



    }




}
