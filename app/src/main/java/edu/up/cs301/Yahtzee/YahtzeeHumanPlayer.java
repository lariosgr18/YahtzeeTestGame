package edu.up.cs301.Yahtzee;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;

/**
 * Created by Michael on 10/20/2015.
 */
public class YahtzeeHumanPlayer extends GameHumanPlayer implements OnClickListener, View.OnLongClickListener {

    GameMainActivity mainActivity = null;
    /**
     * constructor
     *
     * @param name
     */
    public YahtzeeHumanPlayer(String name) {
        super(name);
    }

    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {

    }

    public void setAsGui(GameMainActivity activity) {
        // remember the activity
        mainActivity = activity;

        // Load the layout resource for the new configuration
        activity.setContentView(R.layout.yahtzee_game);



    }

    public void onClick(View view) {

    }

    public boolean onLongClick(View view) {
        return false;
    }
}
