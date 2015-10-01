package net.simplyadvanced.exampleflipanimation.app.flip3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.ViewAnimator;

import net.simplyadvanced.exampleflipanimation.app.R;

// Source: https://github.com/genzeb/flip
/**
 *
 */
public class Flip3Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip3);

        final ViewAnimator viewAnimator = (ViewAnimator)this.findViewById(R.id.viewFlipper);

        /**
         * Bind a click listener to initiate the flip transitions
         */
        viewAnimator.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // This is all you need to do to 3D flip
                AnimationFactory.flipTransition(viewAnimator, AnimationFactory.FlipDirection.LEFT_RIGHT);
            }

        });

        // The following click listeners are not needed (only here to test that clicks
        // are routed to the correct subview of the view animator).

        this.findViewById(R.id.imageView1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // This is all you need to do to 3D flip
                AnimationFactory.flipTransition(viewAnimator, AnimationFactory.FlipDirection.LEFT_RIGHT);
                Toast.makeText(Flip3Activity.this, "Side A Touched", Toast.LENGTH_SHORT).show();
            }
        });

        this.findViewById(R.id.imageView2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // This is all you need to do to 3D flip
                AnimationFactory.flipTransition(viewAnimator, AnimationFactory.FlipDirection.LEFT_RIGHT);
                Toast.makeText(Flip3Activity.this, "Side B Touched", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
