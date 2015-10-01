package net.simplyadvanced.exampleflipanimation.app.flip1;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import net.simplyadvanced.exampleflipanimation.app.R;

// Source: http://www.studyoverflow.org/2013/07/flip-animation-in-android-like-card-flip.html
/**
 * A simple 2D flip trying to be 3D.
 */
public class Flip1Activity extends Activity implements View.OnClickListener, Animation.AnimationListener {

    private Animation animation1;
    private Animation animation2;
    private boolean isBackOfCardShowing = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip1);

        animation1 = AnimationUtils.loadAnimation(this, R.anim.to_middle);
        animation1.setAnimationListener(this);
        animation2 = AnimationUtils.loadAnimation(this, R.anim.from_middle);
        animation2.setAnimationListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        v.setEnabled(false);
        ((ImageView)findViewById(R.id.imageView1)).clearAnimation();
        ((ImageView)findViewById(R.id.imageView1)).setAnimation(animation1);
        ((ImageView)findViewById(R.id.imageView1)).startAnimation(animation1);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation==animation1) {
            if (isBackOfCardShowing) {
                ((ImageView)findViewById(R.id.imageView1)).setImageResource(R.drawable.face);
            } else {
                ((ImageView)findViewById(R.id.imageView1)).setImageResource(R.drawable.back);
            }
            ((ImageView)findViewById(R.id.imageView1)).clearAnimation();
            ((ImageView)findViewById(R.id.imageView1)).setAnimation(animation2);
            ((ImageView)findViewById(R.id.imageView1)).startAnimation(animation2);
        } else {
            isBackOfCardShowing=!isBackOfCardShowing;
            findViewById(R.id.button1).setEnabled(true);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

    }

}
