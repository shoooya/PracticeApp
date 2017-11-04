package com.helloworld.practiceapp;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowDataBase extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_database);
        createTextView();
    }

    private void createTextView() {
        SQLiteDatabase db = new SqliteHelper(this).getReadableDatabase();
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        // queryメソッドの実行例
        Cursor c = db.query("person", new String[] { "name", "age" }, null, null, null, null, null);

        boolean mov = c.moveToFirst();
        while (mov) {
            TextView textView = new TextView(this);
            textView.setText(String.format("%s : %d歳", c.getString(0), c.getInt(1)));
            mov = c.moveToNext();
            layout.addView(textView);
        }
        c.close();
        db.close();
    }
}
