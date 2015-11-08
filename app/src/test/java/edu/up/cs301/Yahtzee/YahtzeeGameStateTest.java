package edu.up.cs301.Yahtzee;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Grayson, Michael, Abhinav on 11/4/2015.
 */
public class YahtzeeGameStateTest {

    //Tests the Constructors
    @Test
    public void testConstructor() throws Exception {

        //Create a gamestate
        YahtzeeGameState newGame = new YahtzeeGameState(1);
        newGame.setPlayer1turns(12);
        newGame.setCurrentPlayerID(2);
        int Values[] = {1,2,3,4,5};
        newGame.setDiceValue(Values);
        //copyt the game state
        YahtzeeGameState otherGame = new YahtzeeGameState(newGame);
        //make a change
        otherGame.setCurrentPlayerID(1);

        //test if the game state has equal or changed aspects
        assertEquals(otherGame.getPlayer1turns(), newGame.getPlayer1turns());
        assertArrayEquals(newGame.getDiceValue(), otherGame.getDiceValue());
        assertNotEquals(newGame.getCurrentPlayerID(),otherGame.getCurrentPlayerID());
    }

    //test the roll method for the gamestate
    @Test
    public void testRoll() throws Exception {
        YahtzeeGameState newGame = new YahtzeeGameState(1);
        int curRolls = newGame.getRolls();
        int values[] = {1,2,3,4,5};
        newGame.rollDice(values,1);
        //test that the number of rolls is incremented
        assertNotEquals(curRolls, newGame.getRolls());
        values[0] = 5;
        values[1] = 6;
        newGame.rollDice(values,0);
        //test that the player can't role because the ID is not correct
        assertEquals(curRolls + 1, newGame.getRolls());
        newGame.rollDice(values, 1);
        //show that the dice values were saved to the game state
        assertArrayEquals(values, newGame.getDiceValue());
        newGame.rollDice(values, 1);
        newGame.rollDice(values,1);
        //show the user can't roll because the number of rolls is 3
        assertEquals(3, newGame.getRolls());

    }


    //test the selectScore method for the game state
    @Test
    public void testSelectScore() throws Exception {
        YahtzeeGameState newGame = new YahtzeeGameState(1);
        int playerID = 1;
        int values[] = {1,2,3,4,5};
        newGame.selectScore(10, 5, 1, false);
        //test that the ID was changed, the score was saved in the right location, the button was made false
        //and turns was incremented
        assertNotEquals(playerID, newGame.getCurrentPlayerID());
        assertEquals(10, newGame.getPlayer1Score()[5]);
        assertEquals(1, newGame.getRolls());
        assertEquals(1, newGame.getPlayer1turns());
        newGame.selectScore(15, 2, 2, false);
        //test above except for player 2 and not 1
        assertEquals(1, newGame.getCurrentPlayerID());
        assertEquals(1,newGame.getPlayer2turns());
        int curplayer = newGame.getCurrentPlayerID();
        newGame.selectScore(15,2,3,false);
        //test nothing was changed because the player ID was not correct
        assertEquals(curplayer,newGame.getCurrentPlayerID());




    }
}