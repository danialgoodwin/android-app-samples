package net.simplyadvanced.testingframeworks;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView(R.id.editText);
        final EditText editText = findView(R.id.editText);

        findViewById(R.id.key1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + "1");
            }
        });
        findViewById(R.id.keyPlusPlus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(String.valueOf(Integer.parseInt(editText.getText().toString()) + 1));
            }
        });
    }

    protected <T> T findView(int resId) {
        return (T) findViewById(resId);
    }

}
