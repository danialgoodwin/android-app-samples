package net.simplyadvanced.autohidetoolbar;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import net.simplyadvanced.autohidetoolbar.quickhide.QuickHideActivity;
import net.simplyadvanced.autohidetoolbar.slowhide.SlowHideActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** The main page for this app to choose between the different AutoHideToolbar implementations. */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout rootContainer = new LinearLayout(this);
        rootContainer.setOrientation(LinearLayout.VERTICAL);
        rootContainer.addView(createButton(this, QuickHideActivity.class, "Quick hide"));
        rootContainer.addView(createButton(this, SlowHideActivity.class, "Slow hide"));
        setContentView(rootContainer);
    }

    private static Button createButton(final Activity activity, final Class<?> activityTo, String title) {
        Button button = new Button(activity);
        button.setText(title);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, activityTo));
            }
        });
        return button;
    }

}