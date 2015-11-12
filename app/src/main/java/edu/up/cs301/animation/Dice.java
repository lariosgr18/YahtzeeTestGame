package edu.up.cs301.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageButton;

/**
 * Created by Grayson, Michael, Abhinav on 11/8/2015.
 *
 * Draws and gives a value to the dice on the board, inherits from image button
 */
public class Dice extends ImageButton {


    public boolean keep;
    public int dieNum;


    /**
     * Constructor for the Dice Class
     *
     * @param context
     *            - a reference to the activity this animation is run under
     */
    public Dice(Context context) {
        super(context);
        init();


    }// ctor

    /**
     * An alternate constructor for use when a subclass is directly specified
     * in the layout.
     *
     * @param context
     *            - a reference to the activity this animation is run under
     * @param attrs
     *            - set of attributes passed from system
     */
    public Dice(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }// ctor

    /**
     * Helper-method for the constructors
     */
    private void init() {

        // Tell the OS that *yes* I will draw stuff
        setWillNotDraw(false);
        keep = false;
        roll();



    }
    /*
        for when the user rolls the dice, give it another value
     */
    public void roll()
    {
        if(this.keep == false) {
            dieNum = (int) (Math.random() * 6 + 1);
        }
    }


    /*
        draw the dice based on the value
     */
    public void onDraw(Canvas g)
    {
        Paint black = new Paint();
        black.setColor(Color.BLACK);

        switch (dieNum)
        {
            case 1: g.drawCircle(g.getHeight()/2,g.getWidth()/2,20, black);
                break;
            case 2: g.drawCircle(40,40,20, black);
                g.drawCircle(g.getHeight()-40,g.getWidth()-40,20,black);
                break;
            case 3: g.drawCircle(g.getHeight()/2,g.getWidth()/2,20, black);
                g.drawCircle(40, 40, 20, black);
                g.drawCircle(g.getHeight()-40,g.getWidth()-40,20,black);
                break;
            case 5: g.drawCircle(g.getHeight()/2,g.getWidth()/2,20, black);
            case 4: g.drawCircle(40,40,20, black);
                g.drawCircle(g.getHeight()-40,g.getWidth()-40,20,black);
                g.drawCircle(40,getWidth()-40,20, black);
                g.drawCircle(g.getHeight()-40,40,20,black);
                break;
            case 6: g.drawCircle(40,40,20, black);
                g.drawCircle(g.getHeight()-40,g.getWidth()-40,20,black);
                g.drawCircle(40,getWidth()-40,20, black);
                g.drawCircle(g.getHeight()-40,40,20,black);
                g.drawCircle(getWidth()-40,getHeight()/2,20, black);
                g.drawCircle(40,getHeight()/2,20,black);

                break;
        }


    }
}
