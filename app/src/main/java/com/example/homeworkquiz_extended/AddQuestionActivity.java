package com.example.homeworkquiz_extended;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.homeworkquiz_extended.data.Question;
import com.example.homeworkquiz_extended.data.QuestionStorage;
import com.example.homeworkquiz_extended.data.Storage;
import com.example.homeworkquiz_extended.data.StorageQuestionsImpl;

import java.util.ArrayList;
import java.util.List;

public class AddQuestionActivity extends Activity {

    private EditText question;
    private EditText rightAns;
    private EditText seconAns;
    private EditText thirdAns;
    private EditText forthAns;
    private ListView questions;
    private QuestionStorage quizStorage;
    private ArrayList<Question> questionStorage;
    private ArrayAdapter<Question> questionArrayAdapter;
    private String[] mAnswer;
    private Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        question = (EditText)findViewById(R.id.question);
        rightAns = (EditText)findViewById(R.id.rightAnswer);
        seconAns = (EditText)findViewById(R.id.answer2);
        thirdAns = (EditText)findViewById(R.id.answer3);
        forthAns= (EditText)findViewById(R.id.answer4);
        questions = (ListView) findViewById(R.id.questions);
        storage = new StorageQuestionsImpl();
        questionArrayAdapter = new QuestionArrayAdapter(this, 0, new ArrayList<Question>());
        questions.setAdapter(questionArrayAdapter);
        getStorageFillAdapter();
    }

    private void getStorageFillAdapter()
    {
        Object storageAsObject = storage
                .getObj(this, StorageQuestionsImpl.QUESTIONS, QuestionStorage.class);
        if (storageAsObject != null) {
            quizStorage = (QuestionStorage) storageAsObject;
        } else {
            quizStorage = new QuestionStorage();
        }
        questionStorage = quizStorage.getQuestions();
        questionArrayAdapter.addAll(questionStorage);
    }

    public void addQuestion(View view) {
        if (!check())
            return;
        String answers [] = new String[4];
        answers[0] = rightAns.getText().toString();
        answers[1] = seconAns.getText().toString();
        answers[2] = thirdAns.getText().toString();
        answers[3] = forthAns.getText().toString();

        Question quizQuestion = new Question();
        quizQuestion.setQuestion(question.getText().toString());
        quizQuestion.setAnswers(answers);
        quizQuestion.setRightAnswer(answers[0]);
        quizStorage.getQuestions().add(quizQuestion);
        storage.add(this, StorageQuestionsImpl.QUESTIONS, quizStorage);

        getStorageFillAdapter();

        Toast.makeText(this, "Question Added Successfully", Toast.LENGTH_SHORT).show();
        question.setText("");
        rightAns.setText("");
        seconAns.setText("");
        thirdAns.setText("");
        forthAns.setText("");
    }

    public void deleteQuestion(int position)
    {
        quizStorage.getQuestions().remove(position);
        storage.add(this, StorageQuestionsImpl.QUESTIONS, quizStorage);
        getStorageFillAdapter();
    }

    public boolean check()
    {
        if(question.getText().toString().trim().equals("") || rightAns.getText().toString().trim().equals("")
                || seconAns.getText().toString().trim().equals("")|| thirdAns.getText().toString().trim().equals("")
                        || forthAns.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Question or Answers are Empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    class QuestionArrayAdapter extends ArrayAdapter<Question> {

        private Context mContext;

        public QuestionArrayAdapter(@NonNull Context context,
                                    int resource,
                                    @NonNull List<Question> objects) {
            super(context, resource, objects);
            mContext = context;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final int mPosition = position;
            Question current = getItem(position);
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.view_question, parent, false);
            TextView textView = view.findViewById(R.id.question);
            textView.setText(current.getQuestion());
            final Button rightBut = view.findViewById(R.id.rightAnswer);
            Button seconBut = view.findViewById(R.id.answer2);
            Button thirdBut = view.findViewById(R.id.answer3);
            Button forthBut = view.findViewById(R.id.answer4);


            if (questionStorage.get(position) != null) {
                rightBut.setText(questionStorage.get(position).getAnswers()[0]);
                seconBut.setText(questionStorage.get(position).getAnswers()[1]);
                thirdBut.setText(questionStorage.get(position).getAnswers()[2]);
                forthBut.setText(questionStorage.get(position).getAnswers()[3]);
            }

            Button delete = (Button)view.findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteQuestion(mPosition);
                }
            });

            return view;
        }
    }

}
