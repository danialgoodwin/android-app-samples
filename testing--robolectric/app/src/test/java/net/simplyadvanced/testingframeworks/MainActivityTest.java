package net.simplyadvanced.testingframeworks;

import android.view.View;
import android.widget.EditText;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

//@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void shouldHaveApplicationName() throws Exception {
        String appName = new MainActivity().getResources().getString(R.string.app_name);
        Assert.assertTrue(appName.equals("TestingFrameworks"));
    }

    @Test
    public void testKey1works() {
        MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .start()
                .resume()
                .get();
        View key1 = mainActivity.findViewById(R.id.key1);
        View keyPlusPlus = mainActivity.findViewById(R.id.keyPlusPlus);
        EditText editText = (EditText) mainActivity.findViewById(R.id.editText);
        key1.performClick();
        keyPlusPlus.performClick();
        Assert.assertTrue(editText.getText().toString().equals("2"));
    }

}
