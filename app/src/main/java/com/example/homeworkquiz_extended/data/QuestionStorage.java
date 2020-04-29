package com.example.homeworkquiz_extended.data;

import java.util.ArrayList;

public class QuestionStorage {

    private ArrayList<Question> questions = new ArrayList<Question>();

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
