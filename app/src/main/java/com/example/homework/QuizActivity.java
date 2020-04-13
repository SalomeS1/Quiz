package com.example.homework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homework.data.Questions;
import com.example.homework.data.Storage;
import com.example.homework.data.StorageSharedPreferencesImpl;

import java.util.Map;

public class QuizActivity  extends Activity {

    Button option;
    Button answer;
    Button aOption;
    Button bOption;
    Button cOption;
    Button dOption;
    Questions questions;
    TextView question;
    String currentAnswer;
    int lastScore ;
    int counter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        question = (TextView)findViewById(R.id.question);
        answer = (Button)findViewById(R.id.footerButton);
        aOption = (Button)findViewById(R.id.aOption);
        bOption = (Button)findViewById(R.id.bOption);
        cOption = (Button)findViewById(R.id.cOption);
        dOption = (Button)findViewById(R.id.dOption);
        setDefaultColor();
        questions = new Questions();
        counter = 0;
        lastScore = 0;
        currentAnswer ="";
        askQuestions();
    }

    public void finishOrNext(View view)
    {
        if(!currentAnswer.equals("Correct") && !currentAnswer.equals("Incorrect"))
        {
            Toast.makeText(QuizActivity.this, "Answer the question", Toast.LENGTH_SHORT).show();
        }
        else {
            counter += 1;

            if (currentAnswer == "Correct") {
                lastScore += 1;
            }

            if (counter == questions.length) {
                Storage storage = new StorageSharedPreferencesImpl();
                storage.save(this, "score", Integer.toString(lastScore));
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                return;
            }
            if(counter == questions.length - 1)
            {
                answer.setText("Finish Quiz");
            }

            askQuestions();
            setDefaultColor();
            currentAnswer = "";
        }
    }

    public void askQuestions()
    {
        question.setText(questions.questions.get(counter));
        int j = 0;
        aOption.setText((CharSequence) questions.answers.get(counter).get(j++));
        bOption.setText((CharSequence) questions.answers.get(counter).get(j++));
        cOption.setText((CharSequence) questions.answers.get(counter).get(j++));
        dOption.setText((CharSequence) questions.answers.get(counter).get(j++));
    }

    public void colorChange(View view)
    {
        setDefaultColor();
        option = (Button) view;
        currentAnswer = (String) option.getText();
        option.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }

    public  void setDefaultColor()
    {
        aOption.setBackgroundColor(getResources().getColor(R.color.option_buttons));
        bOption.setBackgroundColor(getResources().getColor(R.color.option_buttons));
        cOption.setBackgroundColor(getResources().getColor(R.color.option_buttons));
        dOption.setBackgroundColor(getResources().getColor(R.color.option_buttons));
    }
}
