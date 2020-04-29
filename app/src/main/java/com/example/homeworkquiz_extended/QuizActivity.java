package com.example.homeworkquiz_extended;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.homeworkquiz_extended.data.UserBoardStorage;
import com.example.homeworkquiz_extended.data.Question;
import com.example.homeworkquiz_extended.data.QuestionStorage;
import com.example.homeworkquiz_extended.data.Storage;
import com.example.homeworkquiz_extended.data.StorageBoardImpl;
import com.example.homeworkquiz_extended.data.StorageQuestionsImpl;
import com.example.homeworkquiz_extended.data.User;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class QuizActivity extends Activity {

    Button option;
    Button answer;
    Button aOption;
    Button bOption;
    Button cOption;
    Button dOption;
    TextView question;
    String currentAnswer;
    int lastScore ;
    int counter;
    QuestionStorage quizStorage;
    ArrayList<Question> questionStorage;
    UserBoardStorage boardStorage;
    ArrayList<User> users;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        question = (TextView)findViewById(R.id.quizQuestion);
        answer = (Button)findViewById(R.id.footerButton);
        aOption = (Button)findViewById(R.id.aOption);
        bOption = (Button)findViewById(R.id.bOption);
        cOption = (Button)findViewById(R.id.cOption);
        dOption = (Button)findViewById(R.id.dOption);
        setDefaultColor();
        boardStorage = new UserBoardStorage();

        Storage storageQuestion = new StorageQuestionsImpl();
        Object storageAsObject = storageQuestion
                .getObj(this, StorageQuestionsImpl.QUESTIONS, QuestionStorage.class);
        if (storageAsObject != null) {
            quizStorage = (QuestionStorage) storageAsObject;
        } else {
            quizStorage = new QuestionStorage();
        }


        questionStorage = quizStorage.getQuestions();
        counter = 0;
        lastScore = 0;
        currentAnswer ="";
        askQuestions();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void finishOrNext(View view)
    {
        if(currentAnswer.equals(""))
        {
            Toast.makeText(QuizActivity.this, "Answer the question", Toast.LENGTH_SHORT).show();
        }
        else {

            if (currentAnswer.equals(questionStorage.get(counter).getRightAnswer())) {
                lastScore += 1;
            }

            counter += 1;

            if (counter == questionStorage.size()) {
                Toast.makeText(QuizActivity.this, "Your score is " + Integer.toString(lastScore), Toast.LENGTH_SHORT).show();

                Storage storageBoard = new StorageBoardImpl();
                Object storageAsObject = storageBoard
                        .getObj(this, StorageBoardImpl.BOARD, UserBoardStorage.class);
                if (storageAsObject != null) {
                    boardStorage = (UserBoardStorage) storageAsObject;
                } else {
                    boardStorage = new UserBoardStorage();
                }

                User user = new User();
                user.setScore(Integer.toString(lastScore));
                String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
                user.setTime(timeStamp);
                boardStorage.getUsers().add(user);
                storageBoard.add(this, "LeaderBoard",boardStorage);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
            if(counter == questionStorage.size() - 1)
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
        question.setText(questionStorage.get(counter).getQuestion());
        int j = 0;
        aOption.setText(questionStorage.get(counter).getAnswers()[j++]);
        bOption.setText(questionStorage.get(counter).getAnswers()[j++]);
        cOption.setText(questionStorage.get(counter).getAnswers()[j++]);
        dOption.setText(questionStorage.get(counter).getAnswers()[j++]);
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
        aOption.setBackgroundColor(getResources().getColor(R.color.optionButton));
        bOption.setBackgroundColor(getResources().getColor(R.color.optionButton));
        cOption.setBackgroundColor(getResources().getColor(R.color.optionButton));
        dOption.setBackgroundColor(getResources().getColor(R.color.optionButton));
    }
}
