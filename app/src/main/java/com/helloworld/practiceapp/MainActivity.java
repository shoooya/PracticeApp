package com.helloworld.practiceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setScreenMain();
    }

    private void setScreenMain() {
        setContentView(R.layout.activity_main);

        Button addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                // インテントのインスタンス生成
                Intent intent = new Intent(getApplication(),NameAgeActivity.class);
                // 画面呼び出し
                startActivityForResult(intent,001);
            }
        });
    }
}
