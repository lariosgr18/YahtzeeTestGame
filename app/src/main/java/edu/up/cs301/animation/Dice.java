package edu.up.cs301.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageButton;

/**
 * Created by Grayson, Michael, Abhinav on 11/8/2015.
 */
public class Dice extends ImageButton {


    public boolean keep;
    public int dieNum;


    /**
     * Constructor for the AnimationSurface class. In order to be useful, an
     * object must be supplied that implements the Animator interface. This
     * can either be done by overriding the 'createAnimator' method (which by
     * default give null, or by invoking the setAnimator method.
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
     * in the layout. It is expected that the subclass will have overridden
     * the 'createAnimator' method.
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

    public void roll()
    {
        if(this.keep == false) {
            dieNum = (int) (Math.random() * 6 + 1);
        }
    }



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
