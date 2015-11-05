package edu.up.cs301.Yahtzee;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by taylorg18 on 11/4/2015.
 */
public class YahtzeeGameStateTest {

    @Test
    public void testSetTurns() throws Exception {
        YahtzeeGameState game = new YahtzeeGameState(1,5,2);
        int turn = game.getTurns();
        game.setTurns(2);
        assertNotEquals(turn,game.getTurns());

    }

    @Test
    public void testSetRolls() throws Exception {
        YahtzeeGameState game = new YahtzeeGameState(1,5,2);
        int roll = game.getRolls();
        game.setRolls(1);
        assertNotEquals(roll,game.getRolls());

    }

    @Test
    public void testSetPlayer1Score() throws Exception {
        YahtzeeGameState game = new YahtzeeGameState(1,5,2);
        int score[] = {1,2,3};
        game.setPlayer1Score(score);
        assertEquals(score, game.getPlayer1Score());

    }

    @Test
    public void testSetPlayer2Score() throws Exception {
        YahtzeeGameState game = new YahtzeeGameState(1,5,2);
        int score[] = {1,2,3};
        game.setPlayer2Score(score);
        assertEquals(score,game.getPlayer2Score());

    }

    @Test
    public void testSetDiceValue() throws Exception {
        YahtzeeGameState game = new YahtzeeGameState(1,5,2);
        int score[] = {1,2,3,4,5};
        game.setDiceValue(score);
        assertEquals(score,game.getDiceValue());

    }

    @Test
    public void testplayerID() throws Exception {

        YahtzeeGameState game = new YahtzeeGameState(1,5,2);
        int id = 1;
        game.setPlayer1Id(id);
        assertEquals(id,game.getPlayer1Id());

    }
}