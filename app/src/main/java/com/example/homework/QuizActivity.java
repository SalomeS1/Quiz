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
    int counter;
    Questions questions;
    TextView question;
    Button answer;
    Button a_option;
    Button b_option;
    Button c_option;
    Button d_option;
    String current_answer;
    int LastScore ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        question = (TextView)findViewById(R.id.question);
        answer = (Button)findViewById(R.id.footer_button);
        a_option = (Button)findViewById(R.id.A_option);
        b_option = (Button)findViewById(R.id.B_option);
        c_option = (Button)findViewById(R.id.C_option);
        d_option = (Button)findViewById(R.id.D_option);
        set_color_to_default();
        questions = new Questions();
        counter = 0;
        LastScore = 0;
        current_answer ="";
        ask_questions();
    }

    public void finish_vs_next(View view)
    {
        if(!current_answer.equals("Correct") && !current_answer.equals("Incorrect"))
        {
            Toast.makeText(QuizActivity.this, "Answer the question", Toast.LENGTH_SHORT).show();
        }
        else {
            counter += 1;

            if (current_answer == "Correct") {
                LastScore += 1;
            }

            if (counter == questions.length) {
                Storage storage = new StorageSharedPreferencesImpl();
                storage.save(this, "score", Integer.toString(LastScore));
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                return;
            }
            if(counter == questions.length - 1)
            {
                answer.setText("Finish Quiz");
            }

            ask_questions();
            set_color_to_default();
            current_answer = "";
        }
    }

    public void ask_questions()
    {
        question.setText(questions.questions.get(counter));
        int j = 0;
        a_option.setText((CharSequence) questions.answers.get(counter).get(j++));
        b_option.setText((CharSequence) questions.answers.get(counter).get(j++));
        c_option.setText((CharSequence) questions.answers.get(counter).get(j++));
        d_option.setText((CharSequence) questions.answers.get(counter).get(j++));
    }

    public void color_change(View view)
    {
        set_color_to_default();
        option = (Button) view;
        current_answer = (String) option.getText();
        option.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }

    public  void set_color_to_default()
    {
        a_option.setBackgroundColor(getResources().getColor(R.color.option_buttons));
        b_option.setBackgroundColor(getResources().getColor(R.color.option_buttons));
        c_option.setBackgroundColor(getResources().getColor(R.color.option_buttons));
        d_option.setBackgroundColor(getResources().getColor(R.color.option_buttons));
    }
}
