package edu.up.cs301.Yahtzee;

import android.app.Activity;
import android.widget.Button;
import android.widget.SeekBar;

import edu.up.cs301.animation.Dice;
import edu.up.cs301.game.R;

/**
 * Created by Grayson, Michael, Abhinav on 11/8/2015.
 *
 * Controls the scores associated with each of the buttons.
 */
public class ScoreCalc {

    private static final int[] buttonIndices = {
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

    private Dice[] thedice;

    private Button[] numberedButtons1;

    //Constructor for a human player
    public ScoreCalc(YahtzeeHumanPlayer player)
    {
        Activity card = player.mainActivity;
        for (int i = 0; i < numberedButtons1.length; i++) {
            numberedButtons1[i] =
                    (Button) card.findViewById(buttonIndices[i]);
        }
    }

    //Updates the buttons to reflect the score
   public void updateScoreCard()
    {
        int displayCount;
        for(int i = 0; i < numberedButtons1.length; i++)
        {
            displayCount = 0;
            if(i<6)
            {
                for(int j = 0; j < thedice.length; j++)
                {
                    if(thedice[j].dieNum == i+1)
                    {
                        displayCount = displayCount + thedice[j].dieNum;
                    }
                }
                if(numberedButtons1[i].isEnabled()) {
                    numberedButtons1[i].setText("" + displayCount);
                }
            }
            else
            {

            }
        }
    }
}
