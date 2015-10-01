package net.simplyadvanced.examplesqlite.app.provider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import net.simplyadvanced.examplesqlite.app.R;

// Source: http://examples.javacodegeeks.com/android/core/content/contentprovider/android-content-provider-example/
/**
 * Created by Danial on 6/22/2014.
 */
public class SimpleProviderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_simple);
    }

    public void deleteAllBirthdays(View view) {
        // delete all the records and the table of the database provider
        String URL = "content://com.javacodegeeks.provider.Birthday/friends";
        Uri friends = Uri.parse(URL);
        int count = getContentResolver().delete(
                friends, null, null);
        String countNum = "Javacodegeeks: " + count + " records are deleted.";
        Toast.makeText(getBaseContext(),
                countNum, Toast.LENGTH_LONG).show();
    }

    public void addBirthday(View view) {
        // Add a new birthday record
        ContentValues values = new ContentValues();

        values.put(BirthProvider.NAME,
                ((EditText) findViewById(R.id.name)).getText().toString());

        values.put(BirthProvider.BIRTHDAY,
                ((EditText) findViewById(R.id.birthday)).getText().toString());

        Uri uri = getContentResolver().insert(
                BirthProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                "Javacodegeeks: " + uri.toString() + " inserted!", Toast.LENGTH_LONG).show();
    }

    public void showAllBirthdays(View view) {
        // Show all the birthdays sorted by friend's name
        String URL = "content://com.javacodegeeks.provider.Birthday/friends";
        Uri friends = Uri.parse(URL);
        Cursor c = getContentResolver().query(friends, null, null, null, "name");
        String result = "Javacodegeeks Results:";

        if (!c.moveToFirst()) {
            Toast.makeText(this, result + " no content yet!", Toast.LENGTH_LONG).show();
        } else {
            do {
                result = result + "\n" + c.getString(c.getColumnIndex(BirthProvider.NAME)) +
                        " with id " +  c.getString(c.getColumnIndex(BirthProvider.ID)) +
                        " has birthday: " + c.getString(c.getColumnIndex(BirthProvider.BIRTHDAY));
            } while (c.moveToNext());
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        }
    }

}
