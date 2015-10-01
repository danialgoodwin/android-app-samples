package com.danialgoodwin.openapnsettings;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout root = (LinearLayout) findViewById(R.id.root);
        addShortcutButton(this, root, "Settings", "com.android.settings", null);
//        addShortcutButton(this, root, "ApnEditor", "com.android.settings", "ApnEditor");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Didn't work on "Samsung SM-G900V", "Verizon Wireless", "Release Version: 4.4.2"
            addShortcutButton(this, root, "APN Settings", "com.android.settings", "Settings$ApnSettingsActivity");
        } else {
            addShortcutButton(this, root, "APN Settings", "com.android.settings", "ApnSettings");
        }

        // Force-closed on "Samsung SM-G900V", "Verizon Wireless", "Release Version: 4.4.2"
        addShortcutButton(this, root, "Radio Info", "com.android.settings", "RadioInfo");

        // Force-closed on "Samsung SM-G900V", "Verizon Wireless", "Release Version: 4.4.2"
        addShortcutButton(this, root, "Testing Settings", "com.android.settings", "TestingSettings");
        addShortcutButton(this, root, "Mobile Network Settings", "com.android.phone", "MobileNetworkSettings");
    }

    private static void addShortcutButton(final Context context, ViewGroup viewGroup,
            CharSequence title, final String packageName, final String activityName) {
        Button button = new Button(context);
        button.setText(title);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activityName == null) {
                    openApp(context, packageName);
                } else {
                    openApp(context, packageName, activityName);
                }
            }
        });
        viewGroup.addView(button);
    }

    /** Open an Application via its package name.
     *
     *  @param packageName the full java package name of Application. Ex: "com.android.settings" */
    private static void openApp(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        try {
            //Intent intent = new Intent("android.intent.action.MAIN"); // Possibly add?
            Intent intent = pm.getLaunchIntentForPackage(packageName);
            if (intent == null) {
                throw new PackageManager.NameNotFoundException();
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(context, "Application " + packageName + " not found", Toast.LENGTH_LONG).show();
        }
    }

    /** Open a specific Activity of an Application. A Toast message appears if Activity
     * can not be found.
     *
     * @param packageName the full java package name. Ex: "com.android.settings"
     * @param activityName the name of the Activity to open. Ex: "TestingSettings"
     */
    private static void openApp(Context context, String packageName, String activityName) {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setClassName(packageName, packageName + "." + activityName);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "Application " +  packageName + "." + activityName + " not found.", Toast.LENGTH_LONG).show();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

}
