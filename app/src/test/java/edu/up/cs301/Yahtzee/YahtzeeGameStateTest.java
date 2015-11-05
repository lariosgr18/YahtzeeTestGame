package edu.up.cs301.Yahtzee;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by taylorg18 on 11/4/2015.
 */
public class YahtzeeGameStateTest {
    @Test
    public void testConstructor() throws Exception {

        YahtzeeGameState newGame = new YahtzeeGameState(1);
        newGame.setPlayer1turns(12);
        newGame.setCurrentPlayerID(2);
        int Values[] = {1,2,3,4,5};
        newGame.setDiceValue(Values);
        YahtzeeGameState otherGame = new YahtzeeGameState(newGame);
        otherGame.setCurrentPlayerID(1);

        assertEquals(otherGame.getPlayer1turns(), newGame.getPlayer1turns());
        assertArrayEquals(newGame.getDiceValue(), otherGame.getDiceValue());
        assertNotEquals(newGame.getCurrentPlayerID(),otherGame.getCurrentPlayerID());
    }

    @Test
    public void testRoll() throws Exception {
        YahtzeeGameState newGame = new YahtzeeGameState(1);
        int curRolls = newGame.getRolls();
        int values[] = {1,2,3,4,5};
        newGame.rollDice(values,1);
        assertNotEquals(curRolls, newGame.getRolls());
        values[0] = 5;
        values[1] = 6;
        newGame.rollDice(values,0);
        assertEquals(curRolls + 1, newGame.getRolls());
        newGame.rollDice(values, 1);
        assertArrayEquals(values, newGame.getDiceValue());
        newGame.rollDice(values, 1);
        newGame.rollDice(values,1);
        assertEquals(3, newGame.getRolls());

    }



    @Test
    public void testSelectScore() throws Exception {
        YahtzeeGameState newGame = new YahtzeeGameState(1);
        int playerID = 1;
        int values[] = {1,2,3,4,5};
        newGame.selectScore(10, 5, 1, false);
        assertNotEquals(playerID, newGame.getCurrentPlayerID());
        assertEquals(10, newGame.getPlayer1Score()[5]);
        assertEquals(1, newGame.getRolls());
        assertEquals(1, newGame.getPlayer1turns());
        newGame.selectScore(15, 2, 2, false);
        assertEquals(1, newGame.getCurrentPlayerID());
        assertEquals(1,newGame.getPlayer2turns());
        int curplayer = newGame.getCurrentPlayerID();
        newGame.selectScore(15,2,3,false);
        assertEquals(curplayer,newGame.getCurrentPlayerID());




    }
}