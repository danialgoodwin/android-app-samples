package com.danialgoodwin.get_user_email;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.AccountPicker;

public class MainActivity extends Activity {

    private static final int REQUEST_CODE_EMAIL = 100; // Arbitrary unique number.
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout rootContent = new LinearLayout(this);
        rootContent.setOrientation(LinearLayout.VERTICAL);

        Button getUserEmailButton = new Button(this);
        getUserEmailButton.setText("Get email");
        getUserEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestUserEmail();
            }
        });

        mText = new TextView(this);

        rootContent.addView(getUserEmailButton);
        rootContent.addView(mText);
        setContentView(rootContent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_EMAIL && resultCode == RESULT_OK) {
            String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
            mText.setText(accountName);
        }
    }

    private void requestUserEmail() {
        try {
            Intent intent = AccountPicker.newChooseAccountIntent(null, null,
                    new String[]{GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE}, false, null, null, null, null);
            startActivityForResult(intent, REQUEST_CODE_EMAIL);
        } catch (ActivityNotFoundException e) {
            mText.setText("Google Play Services not available");
        }
    }

}
