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

    Button  startQuiz;
    TextView title;
    TextView scoreView;
    String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        title = (TextView)findViewById(R.id.title);
        scoreView = (TextView)findViewById(R.id.score);

        Storage storage = new StorageSharedPreferencesImpl();
        if (storage.exists(this, "score") || storage.get(this,"score") == "") {
            score =  storage.get(this, "score");
            scoreView.setText(score);
            title.setVisibility(View.VISIBLE);
            scoreView.setVisibility(View.VISIBLE);
        }

        startQuiz = (Button)findViewById(R.id.StartAction);
        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });



    }


}
