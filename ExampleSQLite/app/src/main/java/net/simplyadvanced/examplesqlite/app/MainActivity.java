package net.simplyadvanced.examplesqlite.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import net.simplyadvanced.examplesqlite.app.provider.SimpleProviderActivity;
import net.simplyadvanced.examplesqlite.app.providervogellafull.TodosOverviewActivity;
import net.simplyadvanced.examplesqlite.app.sqlitesimple.SqliteSimpleActivity;
import net.simplyadvanced.examplesqlite.app.sqlitevogella.SqliteVogellaActivity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Uses a single-page way of using SQLite.
//        startActivity(new Intent(this, SqliteMinimalActivity.class));

        // Has basic encapsulation and modularity, but still not truly dependable.
//        startActivity(new Intent(this, SqliteSimpleActivity.class));

        // Simple ContentProvider example.
//        startActivity(new Intent(this, SimpleProviderActivity.class));

        // Source: http://www.vogella.com/tutorials/AndroidSQLite/article.html
//        startActivity(new Intent(this, SqliteVogellaActivity.class));

        // Source: http://www.vogella.com/tutorials/AndroidSQLite/article.html
        startActivity(new Intent(this, TodosOverviewActivity.class));
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
}
