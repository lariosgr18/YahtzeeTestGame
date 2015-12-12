package edu.up.cs301.Yahtzee;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;

import edu.up.cs301.animation.Dice;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.R;
import edu.up.cs301.game.config.GameConfig;
import edu.up.cs301.game.config.GamePlayerType;


/**
 * Created by Michael, Grayson, Abhinav on 10/20/2015.
 *
 * Sets up the main activity, so the user can select the type of players to play the game
 */

public class YahtzeeMainActivity extends GameMainActivity{

    // the port number that this game will use when playing over the network
    private static final int PORT_NUMBER = 2278;

    //set default color for dice when they are held
    public int DICE_COLOR = Color.WHITE;
    public int DICE_HOLD_COLOR = Color.BLUE;
    public MediaPlayer MediaPlayer = new MediaPlayer();
    public Dice[] menudice; //the five dice drawn on the screen
    private static final int[] dieID = {// the ID of the imageButtons of the dice
                    R.id.menuDie1,
                    R.id.menuDie2,
                    R.id.menuDie3,
                    R.id.menuDie4,
                    R.id.menuDie5,
            };

    //set up MediaPlayer to play music
    public MediaPlayer mMediaPlayer = new MediaPlayer();

    /**
     * Create the default configuration for this game:
     * - one human player vs. one computer player
     * - minimum of 1 player, maximum of 2
     *
     * @return
     * 		the new configuration object, representing the default configuration
     */
    @Override
    public GameConfig createDefaultConfig() {

        //start song with loop when app is opened
        mMediaPlayer = MediaPlayer.create(this, R.raw.mouse);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setVolume(0.1f, 0.1f);
        mMediaPlayer.setLooping(true);
       mMediaPlayer.start();
        menudice = new Dice[dieID.length];

        // fill the array using the indices in the buttonIndices array
        for (int i = 0; i < menudice.length; i++) {
            menudice[i] =
                    (Dice) this.findViewById(dieID[i]);
            menudice[i].setBackgroundColor(DICE_COLOR);
        }


        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // Yahtzee has three player types:  human player, easy computer and hard computer
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                if(name.length()>14) {
                    name = "HUMAN";
                }
                return new YahtzeeHumanPlayer(name);
            }});
        playerTypes.add(new GamePlayerType("Computer Player (Easy)") {
            public GamePlayer createPlayer(String name) {
                if(name.length()>20) {
                    name = "COMPUTER";
                }
                return new YahtzeeComputerPlayer(name);
            }});
        playerTypes.add(new GamePlayerType("Computer Player (Hard)") {
            public GamePlayer createPlayer(String name) {
                if(name.length()>20) {
                    name = "COMPUTER";
                }
                return new YahtzeeHardComputerPlayer(name);
            }
        });

        //Create a game configuration class for Yahtzee
        GameConfig defaultConfig = new GameConfig(playerTypes, 1, 2, "Yahtzee", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer", 1); // player 2: a computer player
        defaultConfig.addPlayer("Computer", 1); // player 2: a computer player

        return defaultConfig;
    }//createDefaultConfig

    /**
     * create a local game
     *
     * @return
     * 		the local game, a counter game
     */
    @Override

    /**
     * creates local game
     */
    public LocalGame createLocalGame() {
        //stop playing music when local game is created
        if(mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        return new YahtzeeLocalGame();
    }


    /**
     * creates menu options
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_main, menu);
        return true;
    }

    /**
     * allow users to change dice color
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // respond to menu item selection
        int itemId = item.getItemId();
        if (itemId == R.id.WhiteDiceeMenuItem) {
            this.DICE_COLOR = Color.WHITE;
            this.DICE_HOLD_COLOR = Color.BLUE;
            this.updateDiceColor();
            return true;
        } else if (itemId == R.id.redDiceMenuItem) {
            this.DICE_COLOR = Color.RED;
            this.DICE_HOLD_COLOR = Color.YELLOW;
            this.updateDiceColor();
            return true;
        } else if (itemId == R.id.CyanDiceMenuItem) {
            this.DICE_COLOR = Color.CYAN;
            this.DICE_HOLD_COLOR = Color.MAGENTA;
            this.updateDiceColor();
            return true;
        }
        return false;
    }// onOptionsItemSelected
//
    /*
        Change the dice color from the menu selection
     */
    public void updateDiceColor()
    {
        //If the game is going catch and return, do not update menu dice.
        try {
            for (int i = 0; i < menudice.length; i++) {
                menudice[i] =
                        (Dice) this.findViewById(dieID[i]);
                menudice[i].setBackgroundColor(DICE_COLOR);
            }
        }
        catch (NullPointerException e) {
            return;
        }
    }

}
