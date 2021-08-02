package com.example.quizapp;

public class QuizModel {

    public int mQuestion;
    public boolean mAnswer;

    public QuizModel(int Question, boolean Answer) {
        this.mQuestion = Question;
        this.mAnswer = Answer;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean mAnswer) {
        this.mAnswer = mAnswer;
    }
}
