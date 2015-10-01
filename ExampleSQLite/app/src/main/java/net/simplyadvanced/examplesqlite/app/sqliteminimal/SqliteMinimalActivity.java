package net.simplyadvanced.examplesqlite.app.sqliteminimal;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.simplyadvanced.examplesqlite.app.R;

// Source: http://www.coderzheaven.com/2011/04/17/using-sqlite-in-android-a-really-simple-example/
/**
 * Created by Danial on 6/22/2014.
 */
public class SqliteMinimalActivity extends Activity {

    private LinearLayout mLinearLayout;
    private SQLiteDatabase mSqliteDatabase;
    private static String DBNAME = "PERSONS.db";    // THIS IS THE SQLITE DATABASE FILE NAME.
    private static String TABLE = "MY_TABLE";       // THIS IS THE TABLE NAME

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_minimal);

        mLinearLayout = (LinearLayout) findViewById(R.id.linear);
        Toast.makeText(getApplicationContext(), "Creating table.", Toast.LENGTH_SHORT).show();

        // Drop and create to start with a fresh instance of the database.
        dropTable();
        createTable();

        TextView t0 = new TextView(this);
        t0.setText("This tutorial covers CREATION, INSERTION, UPDATION AND DELETION USING SQLITE DATABASES.\nCreating table complete........");
        mLinearLayout.addView(t0);
        Toast.makeText(getApplicationContext(), "Creating table complete.", Toast.LENGTH_SHORT).show();

        insertIntoTable();
        TextView t1 = new TextView(this);
        t1.setText("Insert into table complete........");
        mLinearLayout.addView(t1);
        Toast.makeText(getApplicationContext(), "Insert into table complete", Toast.LENGTH_SHORT).show();
        TextView t2 = new TextView(this);
        t2.setText("Showing table values............");
        mLinearLayout.addView(t2);

        showTableValues();
        Toast.makeText(getApplicationContext(), "Showing table values", Toast.LENGTH_SHORT).show();

        updateTable();
        TextView t3 = new TextView(this);
        t3.setText("Updating table values............");
        mLinearLayout.addView(t3);
        Toast.makeText(getApplicationContext(), "Updating table values", Toast.LENGTH_SHORT).show();
        TextView t4 = new TextView(this);
        t4.setText("Showing table values after updation..........");
        mLinearLayout.addView(t4);
        Toast.makeText(getApplicationContext(), "Showing table values after updation.", Toast.LENGTH_SHORT).show();

        showTableValues();
        deleteValues();
        TextView t5 = new TextView(this);
        t5.setText("Deleting table values..........");
        mLinearLayout.addView(t5);
        Toast.makeText(getApplicationContext(), "Deleting table values", Toast.LENGTH_SHORT).show();
        TextView t6 = new TextView(this);
        t6.setText("Showing table values after deletion.........");
        mLinearLayout.addView(t6);
        Toast.makeText(getApplicationContext(), "Showing table values after deletion.", Toast.LENGTH_SHORT).show();

        showTableValues();
        setColor(t0);
        setColor(t1);
        setColor(t2);
        setColor(t3);
        setColor(t4);
        setColor(t5);
        setColor(t6);
    }

    // THIS FUNCTION SETS COLOR AND PADDING FOR THE TEXTVIEWS
    public void setColor(TextView t) {
        t.setTextColor(Color.BLACK);
        t.setPadding(20, 5, 0, 5);
        t.setTextSize(1, 15);
    }

    // CREATE TABLE IF NOT EXISTS
    public void createTable(){
        try {
            mSqliteDatabase = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            mSqliteDatabase.execSQL("CREATE TABLE IF  NOT EXISTS " + TABLE + " (ID INTEGER PRIMARY KEY, NAME TEXT, PLACE TEXT);");
            mSqliteDatabase.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error in creating table", Toast.LENGTH_LONG);
        }
    }

    // THIS FUNCTION INSERTS DATA TO THE DATABASE
    public void insertIntoTable() {
        try {
            mSqliteDatabase = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            mSqliteDatabase.execSQL("INSERT INTO " + TABLE + "(NAME, PLACE) VALUES('CODERZHEAVEN','GREAT INDIA')");
            mSqliteDatabase.execSQL("INSERT INTO " + TABLE + "(NAME, PLACE) VALUES('ANTHONY','USA')");
            mSqliteDatabase.execSQL("INSERT INTO " + TABLE + "(NAME, PLACE) VALUES('SHUING','JAPAN')");
            mSqliteDatabase.execSQL("INSERT INTO " + TABLE + "(NAME, PLACE) VALUES('JAMES','INDIA')");
            mSqliteDatabase.execSQL("INSERT INTO " + TABLE + "(NAME, PLACE) VALUES('SOORYA','INDIA')");
            mSqliteDatabase.execSQL("INSERT INTO " + TABLE + "(NAME, PLACE) VALUES('MALIK','INDIA')");
            mSqliteDatabase.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error in inserting into table", Toast.LENGTH_LONG);
        }
    }

    // THIS FUNCTION SHOWS DATA FROM THE DATABASE
    public void showTableValues() {
        try {
            mSqliteDatabase = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            Cursor cursor  = mSqliteDatabase.rawQuery("SELECT * FROM "+  TABLE, null);
            System.out.println("COUNT : " + cursor.getCount());
            Integer cindex = cursor.getColumnIndex("NAME");
            Integer cindex1 = cursor.getColumnIndex("PLACE");

            TextView t = new TextView(this);
            t.setText("========================================");
            //mLinearLayout.removeAllViews();
            mLinearLayout.addView(t);

            if (cursor.moveToFirst()) {
                do {
                    LinearLayout id_row = new LinearLayout(this);
                    LinearLayout name_row = new LinearLayout(this);
                    LinearLayout place_row = new LinearLayout(this);

                    final TextView id_ = new TextView(this);
                    final TextView name_ = new TextView(this);
                    final TextView place_ = new TextView(this);
                    final TextView sep = new TextView(this);

                    String ID = cursor.getString(0);
                    String NAME = cursor.getString(1);
                    String PLACE = cursor.getString(2);

                    id_.setTextColor(Color.RED);
                    id_.setPadding(20, 5, 0, 5);
                    name_.setTextColor(Color.RED);
                    name_.setPadding(20, 5, 0, 5);
                    place_.setTextColor(Color.RED);
                    place_.setPadding(20, 5, 0, 5);

                    System.out.println("NAME " + cursor.getString(cindex) + " PLACE : "+ cursor.getString(cindex1));
                    System.out.println("ID : "+ ID  + " || NAME " + NAME + "|| PLACE : "+ PLACE);

                    id_.setText("ID : " + ID);
                    id_row.addView(id_);
                    mLinearLayout.addView(id_row);
                    name_.setText("NAME : " + NAME);
                    name_row.addView(name_);
                    mLinearLayout.addView(name_row);
                    place_.setText("PLACE : " + PLACE);
                    place_row.addView(place_);
                    mLinearLayout.addView(place_row);
                    sep.setText("---------------------------------------------------------------");
                    mLinearLayout.addView(sep);
                }
                while (cursor.moveToNext());
            }
            mSqliteDatabase.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error encountered.", Toast.LENGTH_LONG);
        }
    }

    // THIS FUNCTION UPDATES THE DATABASE ACCORDING TO THE CONDITION
    public void updateTable() {
        try {
            mSqliteDatabase = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            mSqliteDatabase.execSQL("UPDATE " + TABLE + " SET NAME = 'MAX' WHERE PLACE = 'USA'");
            mSqliteDatabase.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error encountered", Toast.LENGTH_LONG);
        }
    }

    // THIS FUNCTION DELETES VALUES FROM THE DATABASE ACCORDING TO THE CONDITION
    public void deleteValues() {
        try {
            mSqliteDatabase = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            mSqliteDatabase.execSQL("DELETE FROM " + TABLE + " WHERE PLACE = 'USA'");
            mSqliteDatabase.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error encountered while deleting.", Toast.LENGTH_LONG);
        }
    }

    // THIS FUNCTION DROPS A TABLE
    public void dropTable(){
        try {
            mSqliteDatabase = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
            mSqliteDatabase.execSQL("DROP TABLE " + TABLE);
            mSqliteDatabase.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error encountered while dropping.", Toast.LENGTH_LONG);
        }
    }
}
