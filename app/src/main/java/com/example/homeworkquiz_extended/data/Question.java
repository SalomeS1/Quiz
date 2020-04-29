package com.example.homeworkquiz_extended.data;

public class Question {

    private String question;
    private String rightAnswer;
    private String answers[];

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public String[] getAnswers() {
        return answers;
    }
}
