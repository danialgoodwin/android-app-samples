package net.simplyadvanced.listallapps;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import net.simplyadvanced.util.PackageManagerUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.text);

        StringBuilder sb = new StringBuilder();
        List<PackageInfo> listOfAllInstalledApps = PackageManagerUtils.getInstance(this).getListOfAllInstalledApps();
        Collections.sort(listOfAllInstalledApps, new Comparator<PackageInfo>() {
            @Override
            public int compare(PackageInfo lhs, PackageInfo rhs) {
                return lhs.packageName.compareToIgnoreCase(rhs.packageName);
            }
        });
        for (PackageInfo info : listOfAllInstalledApps) {
            sb.append(info.packageName).append("\n");
        }
        mTextView.setText(sb.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_email:
                sendEmail("feedback@simplyadvanced.net", mTextView.getText().toString());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendEmail(String to, String message) {
        try {
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("vnd.android.cursor.dir/email"); // Or "text/plain" "text/html" "video/mp4"
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {to});
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "List All Apps");
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
            startActivity(emailIntent); // Has option to permanently set option
            //startActivity(Intent.createChooser(emailIntent, "Send email:")); // Use if you want options every time
        } catch (ActivityNotFoundException e) {
            Log.e("Emailing contact", "Email failed", e);
            Toast.makeText(this, "Email Failed", Toast.LENGTH_SHORT).show();
        }
    }

}
