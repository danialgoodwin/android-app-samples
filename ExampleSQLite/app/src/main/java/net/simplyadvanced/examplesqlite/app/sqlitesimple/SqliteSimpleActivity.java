package net.simplyadvanced.examplesqlite.app.sqlitesimple;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import net.simplyadvanced.examplesqlite.app.R;
import net.simplyadvanced.examplesqlite.app.sqlitesimple.model.Student;

import java.util.List;

// Source: http://examples.javacodegeeks.com/android/core/database/sqlite/sqlitedatabase/android-sqlite-example/
/**
 * Created by Danial on 6/22/2014.
 */
public class SqliteSimpleActivity extends ListActivity {

    private StudentOperations studentDBoperation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_simple);

        studentDBoperation = new StudentOperations(this);
        studentDBoperation.open();

        List values = studentDBoperation.getAllStudents();

        // Use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    public void addUser(View view) {
        ArrayAdapter adapter = (ArrayAdapter) getListAdapter();

        EditText text = (EditText) findViewById(R.id.editText1);
        Student stud = studentDBoperation.addStudent(text.getText().toString());

        adapter.add(stud);
    }

    public void deleteFirstUser(View view) {
        ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
        Student stud = null;

        if (getListAdapter().getCount() > 0) {
            stud = (Student) getListAdapter().getItem(0);
            studentDBoperation.deleteStudent(stud);
            adapter.remove(stud);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentDBoperation.open();
    }

    @Override
    protected void onPause() {
        studentDBoperation.close();
        super.onPause();
    }

}
