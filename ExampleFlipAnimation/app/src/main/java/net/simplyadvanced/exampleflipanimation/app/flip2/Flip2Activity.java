package net.simplyadvanced.exampleflipanimation.app.flip2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import net.simplyadvanced.exampleflipanimation.app.R;

// Source: http://2cupsoftech.wordpress.com/2012/09/18/3d-flip-between-two-view-or-viewgroup-on-android/
/**
 *
 */
public class Flip2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip2);
    }

    public void onCardClick(View view) {
        flipCard();
    }

    private void flipCard() {
        View rootLayout = (View) findViewById(R.id.main_activity_root);
        View cardFace = (View) findViewById(R.id.main_activity_card_face);
        View cardBack = (View) findViewById(R.id.main_activity_card_back);

        FlipAnimation flipAnimation = new FlipAnimation(cardFace, cardBack);

        if (cardFace.getVisibility() == View.GONE) {
            flipAnimation.reverse();
        }
        rootLayout.startAnimation(flipAnimation);
    }

}
