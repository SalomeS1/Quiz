package com.example.homeworkquiz_extended;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.homeworkquiz_extended.data.Question;
import com.example.homeworkquiz_extended.data.QuestionStorage;
import com.example.homeworkquiz_extended.data.Storage;
import com.example.homeworkquiz_extended.data.StorageQuestionsImpl;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private Button addQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addQuestion = (Button) findViewById(R.id.addQuestion);
        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddQuestionActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.takeQuiz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage storage = new StorageQuestionsImpl();
                QuestionStorage quizStorage;
                Object storageAsObject = storage
                        .getObj(MainActivity.this, StorageQuestionsImpl.QUESTIONS, QuestionStorage.class);
                if (storageAsObject != null) {
                    quizStorage = (QuestionStorage) storageAsObject;
                } else {
                    quizStorage = new QuestionStorage();
                }
                if(quizStorage.getQuestions().size() > 0) {
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(MainActivity.this,"There are no questions is the Quiz", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.showBoard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BoardActivity.class);
                startActivity(intent);
            }
        });
    }

}
