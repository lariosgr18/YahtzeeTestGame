package edu.up.cs301.Yahtzee;

import edu.up.cs301.animation.Dice;
import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    YahtzeeGameState state = null;//the state to load

    int round; // what round it is
    int rollNum; // the number of rolls the player has done
    private int[] scores = new int[13]; // the players scores
    private int yahtzeeCount;

    public int[] getDiceValues() {
        return diceValues;
    }

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

    private Button[] numberedButtons1; // the buttons the player can click

    private Button roll; // the roll button
    private Dice[] thedice; //the five dice drawn on the screen
    private static final int[] dieID = // the ID of the imageButtons of the dice
            {
                    R.id.imageButton,
                    R.id.imageButton2,
                    R.id.imageButton3,
                    R.id.imageButton4,
                    R.id.imageButton5,
            };

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
        thedice = new Dice[dieID.length];

        // fill the array using the indices in the buttonIndices array
        for (int i = 0; i < thedice.length; i++) {
            thedice[i] =
                    (Dice) mainActivity.findViewById(dieID[i]);
            thedice[i].setBackgroundColor(Color.WHITE);
        }

        roll = (Button) mainActivity.findViewById(R.id.rollButton);
        roll.setOnClickListener(this);
    }

    /*
        set the title of the activity, change the player names, and start the round and roll number
     */
    protected void initAfterReady() {

        // update the title, including the player names
        mainActivity.setTitle("Yahtzee: " + allPlayerNames[0] + " vs. " + allPlayerNames[1]);

        // update the TextFields that contain the players' names
        updatePlayerNames();
        round = 0;
        rollNum = 0;
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
        if(info == null)
        {
            state = new YahtzeeGameState(0);
        }
        else
        {
            state = (YahtzeeGameState)info;
        }

    }

    /*
        set as an interactable gui for the user
     */
    public void setAsGui(GameMainActivity activity) {
        // remember the activity
        mainActivity = activity;


        // Load the layout resource for the new configuration
        activity.setContentView(R.layout.yahtzee_game);
        initializeButtons();

        // put the player names into the GUI
        updatePlayerNames();

        // listen to each of the buttons
        for (int i = 0; i < numberedButtons1.length; i++) {
            numberedButtons1[i].setOnClickListener(this);
        }

        for (int i = 0; i < thedice.length; i++) {
            thedice[i].setOnLongClickListener(this);
        }


        // if we have state, update the GUI based on the state
        if (state != null) {
            receiveInfo(state);
        }


    }
    /*
        onClick method for the buttons
     */
    public void onClick(View view) {
        if(view == roll) {

            int diceValues[] = new int[5];
            for (int i = 0; i < thedice.length; i++) {
                thedice[i].roll();
                thedice[i].invalidate();
                diceValues[i] = thedice[i].dieNum;
            }
        }

    }
    /*
        onLong click for the dice image buttons
     */
    public boolean onLongClick(View view) {
        if(((Dice)view).keep == true) {
            ((Dice) view).keep = false;
            ((Dice)view).setBackgroundColor(Color.WHITE);
            return false;
        }
        else
        {
            ((Dice)view).keep = true;
            ((Dice)view).setBackgroundColor(Color.BLUE);
            return true;
        }
    }
}
