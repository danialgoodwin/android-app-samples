package net.simplyadvanced.examplemultipleflavors;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

// More info: http://developer.android.com/guide/topics/manifest/meta-data-element.html
/** Example on how to use info in AndroidManifest in the Java code. */
public class MainActivity extends Activity {

    private static final String LOG_TAG = "DEBUG: MainActivity";

    @InjectView(R.id.manifestMetaDataText) TextView mManifestMetaDataText;
    @InjectView(R.id.productFlavorText) TextView mProductFlavorText;
    @InjectView(R.id.buildTypeText) TextView mBuildTypeText;
    @InjectView(R.id.versionNameText) TextView mVersionNameText;
    @InjectView(R.id.openActivityButton) Button mOpenActivityButton;

    @OnClick(R.id.openActivityButton) void onClickOpenActivity() {
        startActivity(new Intent(this, ProductActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mManifestMetaDataText.setText("Example meta data: " + getExampleMetaData());
        mProductFlavorText.setText("Product flavor: " + getFlavor());
        mBuildTypeText.setText("Build type: " + getBuildType());
        mVersionNameText.setText("Version name: " + getVersionName());

        if (isPro()) {
            mOpenActivityButton.setText("Go to pro");
        } else {
            mOpenActivityButton.setText("Go to free");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** Returns the ExampleMetaData that is in the AndroidManifest.xml. */
    private String getExampleMetaData() {
        String exampleMetaData;
        try {
            ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(),
                    PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            exampleMetaData = bundle.getString("exampleMetaData");
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(LOG_TAG, "Failed to load meta-data, NameNotFound: " + e.getMessage());
            exampleMetaData = "NameNotFoundError";
        } catch (NullPointerException e) {
            Log.e(LOG_TAG, "Failed to load meta-data, NullPointer: " + e.getMessage());
            exampleMetaData = "NullPointerException";
        }
        return exampleMetaData;
    }

    /** Returns true if user has purchased app, otherwise false. */
    private boolean isPro() {
        return BuildConfig.FLAVOR.equalsIgnoreCase("pro");
    }

    private String getFlavor() {
        return BuildConfig.FLAVOR;
    }

    private String getBuildType() {
        return BuildConfig.BUILD_TYPE;
    }

    private String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }

}
