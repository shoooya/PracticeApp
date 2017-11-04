package com.helloworld.practiceapp;
        
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NameAgeActivity extends Activity {
    private SQLiteDatabase db;
    private EditText nameText;
    private EditText ageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_age);

        db       = new SqliteHelper(this).getWritableDatabase();
        nameText = (EditText) findViewById(R.id.editName);
        ageText  = (EditText) findViewById(R.id.editAge);
    }

    public void insert(View v) {
        String name = nameText.getText().toString();
        String age  = ageText.getText().toString();

        if (validateName(name) && validateAge(age)) {
            ContentValues insertValues = new ContentValues();
            insertValues.put("name", name);
            insertValues.put("age", age);
            db.insert("person", name, insertValues);
            createToast("登録が完了しました。");
        }
    }

    public void update(View v) {
        String name = nameText.getText().toString();
        String age = ageText.getText().toString();

        if (validateName(name) && validateAge(age)) {
            ContentValues updateValues = new ContentValues();
            updateValues.put("age", age);
            int updated = db.update("person", updateValues, "name=?", new String[] { name });
            if (updated > 0) {
                createToast(updated + "件、更新が完了しました。");
            } else {
                createToast("更新されませんでした。");
            }
        }

    }

    public void delete(View v) {
        String name = nameText.getText().toString();
        if (validateName(name)) {
            int deleted = db.delete("person", "name=?", new String[]{name});
            if (deleted > 0) {
                createToast(deleted + "件、削除が完了しました。");
            } else {
                createToast("削除されませんでした。");
            }
        }
    }

    public void deleteAll(View v) {
        int deleted = db.delete("person", null, null);
        if (deleted > 0) {
            createToast(deleted + "件削除しました。");
        } else {
            createToast("削除されませんでした。");
        }
    }

    public void moveShowDataBase(View v) {
        Intent dbIntent = new Intent(NameAgeActivity.this, ShowDataBase.class);
        startActivity(dbIntent);
    }

    private void createToast(String text) {
        Toast.makeText(NameAgeActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    private boolean validateName(String name) {
        if (name.equals("")) {
            createToast("名前を入力してください。");
            return false;
        }
        return true;
    }

    private boolean validateAge(String age) {
        if (age.equals("")) {
            createToast("年齢を入力してください。");
            return false;
        }
        return true;
    }
}