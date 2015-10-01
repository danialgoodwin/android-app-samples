package net.simplyadvanced.getdeviceid;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends Activity {

    @OnClick(R.id.button)
    void submit() {
        sendEmail("spencesouthard@gmail.com", "Device Id: " + getDeviceId());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }


    private String getDeviceId() {
        // Get unique id for device. More info: http://android-developers.blogspot.com/2011/03/identifying-app-installations.html
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String id = tm.getDeviceId(); // If device is a phone, basically.
//        if (TextUtils.isEmpty(id)) { id = Build.SERIAL; }
        return id;
    }

    private void sendEmail(String to, String message) {
        try {
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("vnd.android.cursor.dir/email"); // Or "text/plain" "text/html" "video/mp4"
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {to, "danialgoodwin@gmail.com" /*,"example@email.com"*/ });
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Device Id");
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
//            emailIntent.putExtra(Intent.EXTRA_STREAM, mVideoUri); // Uri.Parse(file:// + "/path/to/file.type") // Uri.fromFile(new File(Environment.getExternalStorageDirectory(), FILENAME))
            startActivity(emailIntent); // Has option to permanently set option
            //startActivity(Intent.createChooser(emailIntent, "Send email:")); // Use if you want options every time
        } catch (ActivityNotFoundException e) {
            Log.e("Emailing contact", "Email failed", e);
            Toast.makeText(this, "Email Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
