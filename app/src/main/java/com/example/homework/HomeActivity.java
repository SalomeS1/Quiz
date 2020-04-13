package com.example.homework;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.homework.data.StorageSharedPreferencesImpl;
import com.example.homework.data.Storage;

import org.w3c.dom.Text;

public class HomeActivity extends Activity {

    Button  start_quiz;
    TextView title;
    TextView score_view;
    String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        title = (TextView)findViewById(R.id.title);
        score_view = (TextView)findViewById(R.id.score);

        Storage storage = new StorageSharedPreferencesImpl();
        if (storage.exists(this, "score") || storage.get(this,"score") == "") {
            score =  storage.get(this, "score");
            score_view.setText(score);
            title.setVisibility(View.VISIBLE);
            score_view.setVisibility(View.VISIBLE);
        }

        start_quiz = (Button)findViewById(R.id.StartAction);
        start_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });



    }


}
