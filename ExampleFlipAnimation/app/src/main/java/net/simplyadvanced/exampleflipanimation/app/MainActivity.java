package net.simplyadvanced.exampleflipanimation.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import net.simplyadvanced.exampleflipanimation.app.flip1.Flip1Activity;
import net.simplyadvanced.exampleflipanimation.app.flip2.Flip2Activity;
import net.simplyadvanced.exampleflipanimation.app.flip3.Flip3Activity;
import net.simplyadvanced.exampleflipanimation.app.flip4.Flip4Activity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/** The main activity, which displays links to test view the flip animations. */
public class MainActivity extends Activity {

    @OnClick(R.id.buttonFlip1) void onClickGoToFlip1Activity() {
        startActivity(new Intent(this, Flip1Activity.class));
    }

    @OnClick(R.id.buttonFlip2) void onClickGoToFlip2Activity() {
        startActivity(new Intent(this, Flip2Activity.class));
    }

    @OnClick(R.id.buttonFlip3) void onClickGoToFlip3Activity() {
        startActivity(new Intent(this, Flip3Activity.class));
    }

    @OnClick(R.id.buttonFlip4) void onClickGoToFlip4Activity() {
        startActivity(new Intent(this, Flip4Activity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

}
