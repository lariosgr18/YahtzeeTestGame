package edu.up.cs301.Yahtzee;

import edu.up.cs301.animation.Dice;
import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.R;
import edu.up.cs301.game.config.GameConfig;
import edu.up.cs301.game.infoMsg.GameInfo;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

/**
 * Created by Michael, Abhinav, Grayson on 10/20/2015.
 *
 * Creates a interactive GUI so the player can play the game
 */
public class YahtzeeHumanPlayer extends GameHumanPlayer implements OnClickListener, View.OnLongClickListener {

    GameMainActivity mainActivity = null; //game activity
    // the game's state
    YahtzeeGameState state = null;//the state to

    private SoundPool rollingSound;
    private int sound;

    int round; // what round it is
    int rollNum; // the number of rolls the player has done
    private int[] scores = new int[13]; // the players scores
    private ScoreCalc scoreCard;
    private int currentScoreIndex;
    private int scoreChosen;
    private int diceValues[] = new int[5];
    private int yahtzeeCount; //the number of times the player has gotten a yahtzee
    private static final int[] buttonIndices = { //the button ids the player can click
            R.id.p1_ace,
            R.id.p1_two,
            R.id.p1_three,
            R.id.p1_four,
            R.id.p1_five,
            R.id.p1_six,
            R.id.p1_3kind,
            R.id.p1_4kind,
            R.id.p1_house,
            R.id.p1_smstraight,
            R.id.p1_lgstraight,
            R.id.p1_yahtzee,
            R.id.p1_chance,
    };
    private static final int[] computerButtonIndices = { //the button ids the player can click
            R.id.p2_ace,
            R.id.p2_two,
            R.id.p2_three,
            R.id.p2_four,
            R.id.p2_five,
            R.id.p2_six,
            R.id.p2_3kind,
            R.id.p2_4kind,
            R.id.p2_house,
            R.id.p2_smstraight,
            R.id.p2_lgstraight,
            R.id.p2_yahtzee,
            R.id.p2_chance,
    };

    public Button[] numberedButtons1; // the buttons the player can click
    public Button[] computerButtons = {null}; // the buttons the player can click

    public Button roll; // the roll button
    public Dice[] thedice; //the five dice drawn on the screen
    private static final int[] dieID = // the ID of the imageButtons of the dice
            {
                    R.id.imageButton,
                    R.id.imageButton2,
                    R.id.imageButton3,
                    R.id.imageButton4,
                    R.id.imageButton5,
            };

    public Button quit;
    public Button howToPlay;

    public TextView humanName;
    public TextView computerName;

    public int DICE_COLOR;
    public int DICE_HOLD_COLOR;
    public Menu settings;
    /**
     * constructor
     *
     * @param name
     */
    public YahtzeeHumanPlayer(String name) {
        super(name);
    } // initialize the player name

    /*
        Initialize all the ID's to buttons
    */
    private void initializeButtons() {
        // create the button array
        numberedButtons1 = new Button[buttonIndices.length];

        // fill the array using the indices in the buttonIndices array
        for (int i = 0; i < numberedButtons1.length; i++) {
            numberedButtons1[i] =
                    (Button) mainActivity.findViewById(buttonIndices[i]);
        }

        // create the button array
        computerButtons = new Button[computerButtonIndices.length];

        // fill the array using the indices in the buttonIndices array
        for (int i = 0; i < computerButtons.length; i++) {
            computerButtons[i] =
                    (Button) mainActivity.findViewById(computerButtonIndices[i]);
        }


        thedice = new Dice[dieID.length];

        // fill the array using the indices in the buttonIndices array
        for (int i = 0; i < thedice.length; i++) {
            thedice[i] =
                    (Dice) mainActivity.findViewById(dieID[i]);
            thedice[i].setBackgroundColor(Color.WHITE);
        }

        //comment
        humanName = (TextView) mainActivity.findViewById(R.id.player1_title);
        computerName =(TextView) mainActivity.findViewById(R.id.player2_title);
        roll = (Button) mainActivity.findViewById(R.id.rollButton);
        roll.setOnClickListener(this);
        scoreCard = new ScoreCalc(numberedButtons1, thedice, computerButtons);
        quit = (Button) mainActivity.findViewById(R.id.quit);
        quit.setOnClickListener(this);
        howToPlay = (Button) mainActivity.findViewById(R.id.howtoplay_button);
        howToPlay.setOnClickListener(this);

        rollingSound = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
        sound = rollingSound.load(mainActivity.getApplicationContext(),R.raw.diceroll,1);


    }
    //

    /*
        set the title of the activity, change the player names, and start the round and roll number
     */
    protected void initAfterReady() {

        // update the title, including the player names
        mainActivity.setTitle("Yahtzee: " + allPlayerNames[0] + " vs. " + allPlayerNames[1]);

        // update the TextFields that contain the players' names
        updatePlayerNames();
        round = 1;
        rollNum = 1;
    }

    /**
     * Updates the player-name TextFields
     */
    private void updatePlayerNames() {
        // if we haven't yet gotten the player names, ignore
        if (allPlayerNames == null) return;

        // get the two text fields
        TextView oppName = (TextView) mainActivity.findViewById(R.id.player2_title);
        TextView myName = (TextView) mainActivity.findViewById(R.id.player1_title);

        // update them each with the appropriate name, so that the current player
        // is listed on the bottom.
        oppName.setText(allPlayerNames[1 - playerNum]);
        myName.setText(allPlayerNames[playerNum]);
    }

    /*
        get the layout of the activity
     */
    @Override
    public View getTopView() {
        return (RelativeLayout) mainActivity.findViewById(R.id.top_gui_layout);
    }

    /*
        get the gamestate info
     */
    @Override
    public void receiveInfo(GameInfo info) {

        state = (YahtzeeGameState) info;

        if ( state.getCurrentPlayerID() ==1)
        {
            humanName.setBackgroundColor( Color.WHITE);
            computerName.setBackgroundColor( Color.rgb(8,197,245));
        }
        else
        {
            computerName.setBackgroundColor( Color.WHITE);
            humanName.setBackgroundColor( Color.rgb(8,197,245));
        }

        //Set the dice to the inputted values
        for(int i = 0; i < thedice.length; i++)
        {
            thedice[i].dieNum = state.getDiceValue()[i];
            thedice[i].invalidate();
            Log.d("recieveInfo: ", "" + state.getDiceValue()[i]);

        }
        //change the dice objects int the score calculator
       scoreCard.setDiceObjects(thedice);
        //update the computer score card
        if(((YahtzeeGameState) info).getCurrentPlayerID() == 1) {
            rollingSound.play(sound,1.0f,1.0f,0,0,1.5f);
            scoreCard.updateComputerCard();
        }

        //update the computers buttons depending if they have been pressed or not
        for(int i = 0; i<13;i++ ) {
            computerButtons[i].setEnabled(((YahtzeeGameState) info).getButtonsPressed2()[i]);
            if(((YahtzeeGameState) info).getButtonsPressed2()[i] == false) {
                computerButtons[i].setBackgroundColor(Color.MAGENTA);
            }
        }

        DICE_HOLD_COLOR = ((YahtzeeMainActivity)mainActivity).DICE_HOLD_COLOR;
        DICE_COLOR = ((YahtzeeMainActivity)mainActivity).DICE_COLOR;
        for(int i = 0; i < thedice.length; i++)
        {
            if(thedice[i].keep == false) {
                thedice[i].setBackgroundColor(DICE_COLOR);
            }
            else
            {
                thedice[i].setBackgroundColor(DICE_HOLD_COLOR);
            }
        }
    }

    /*
        set as an interactable gui for the user
     */
    public void setAsGui(GameMainActivity activity) {
        // remember the activity
        mainActivity = activity;

        mainActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // Load the layout resource for the new configuration
        activity.setContentView(R.layout.yahtzee_game);
        initializeButtons();

        // put the player names into the GUI
        updatePlayerNames();

        // listen to each of the buttons
        for (int i = 0; i < numberedButtons1.length; i++) {
            numberedButtons1[i].setOnClickListener(this);
        }

        //set the long click for the dice
        for (int i = 0; i < thedice.length; i++) {
            thedice[i].setOnLongClickListener(this);
        }


        DICE_HOLD_COLOR = ((YahtzeeMainActivity)mainActivity).DICE_HOLD_COLOR;
        DICE_COLOR = ((YahtzeeMainActivity)mainActivity).DICE_COLOR;
        for(int i = 0; i < thedice.length; i++)
        {
            thedice[i].setBackgroundColor(DICE_COLOR);

        }
        // if we have state, update the GUI based on the state
        if (state != null) {
            receiveInfo(state);
        }


    }



    /*//
        onClick method for the buttons//
     */
    public void onClick(View view) {

        if(view == quit)
        {
            return;
        }
        if(view == howToPlay){
            Intent intent = new Intent(mainActivity, HowToPlayActivity.class);
            mainActivity.startActivity(intent);
            return;
        }
        if(((YahtzeeLocalGame)super.game).canMove(playerNum))
        {
            Log.d("HUMAN PLAYER", "CAN MAKE MOVE");
            //if the user clicks the roll button and hasnt rolled 3 times, change dice values and send to gamestate
            if (view == roll && rollNum <= 3) {
                rollingSound.play(sound,1.0f,1.0f,0,0,1.5f);
                for (int i = 0; i < thedice.length; i++) {
                    thedice[i].roll();
                    thedice[i].invalidate();
                    diceValues[i] = thedice[i].dieNum;
                }
                RollAction action = new RollAction(this);
                super.game.sendAction(action);
                scoreCard.updateScoreCard();
                rollNum++;

            }


            //if the user selects a score and they have at least rolled once
            if (view != roll && rollNum >= 2) {
                for (int i = 0; i < numberedButtons1.length; i++) {
                    if (numberedButtons1[i] == view) {
                        numberedButtons1[i].setEnabled(false);
                        numberedButtons1[i].setBackgroundColor(Color.MAGENTA);
                        scores[i] = Integer.parseInt(((String) numberedButtons1[i].getText()));
                        currentScoreIndex = i;
                        scoreChosen = scores[i];
                        scores[i] = Integer.parseInt(((String) numberedButtons1[i].getText()));
                        currentScoreIndex = i;
                        scoreChosen = scores[i];
                    }

                }
                //set rolls back to 1
                rollNum = 1;
                SelectScoreAction select = new SelectScoreAction(this);
                super.game.sendAction(select);

                //reset the dice from being held
                for (int i = 0; i < thedice.length; i++) {
                    thedice[i].keep = false;
                    thedice[i].setBackgroundColor(DICE_COLOR);
                }

            }
        }

    }
    /*
        onLong click for the dice image buttons
     */
    public boolean onLongClick(View view) {
        //change the dice hold/color when the user long clicks
        if( rollNum >= 2) {
            if (((Dice) view).keep == true) {
                ((Dice) view).keep = false;
                ((Dice) view).setBackgroundColor(DICE_COLOR);
                return false;
            } else {
                ((Dice) view).keep = true;
                ((Dice) view).setBackgroundColor(DICE_HOLD_COLOR);
                return true;
            }
        }
        return false;
    }

    /*
    GETTER AND SETTER METHODS
     */

    public int[] getDiceValues() {
        return diceValues;
    }

    public int getCurrentScoreIndex() {
        return currentScoreIndex;
    }

    public int getScoreChosen() {
        return scoreChosen;
    }
}
