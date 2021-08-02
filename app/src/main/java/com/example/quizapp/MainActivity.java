package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String SCORE_KEY = "userScore";
    final String QUESTION_KEY = "questionKey";
    TextView textView;
    Button btnTrue;
    Button btnWrong;
    int questionIndex;
    int mQuestions;
    ProgressBar progressBar;
    TextView textStats;
    int userScore;

    private QuizModel[] questions = new QuizModel[]{
            new QuizModel(R.string.q1,true),
            new QuizModel(R.string.q2,false),
            new QuizModel(R.string.q3,true),
            new QuizModel(R.string.q4,false),
            new QuizModel(R.string.q5,true),
            new QuizModel(R.string.q6,false),
            new QuizModel(R.string.q7,true),
            new QuizModel(R.string.q8,false),
            new QuizModel(R.string.q9,true),
            new QuizModel(R.string.q10,false)



    };

    final int USER_PROGRESS = (int) Math.ceil(100.0 / questions.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){

            userScore = savedInstanceState.getInt(SCORE_KEY);
            questionIndex = savedInstanceState.getInt(QUESTION_KEY);
        }else {

            userScore = 0;
            questionIndex = 0;
        }

        textView = findViewById(R.id.textQuestion);
        btnTrue = findViewById(R.id.btnTrue);
        btnWrong = findViewById(R.id.btnWrong);
        progressBar = findViewById(R.id.progressBar);
        textStats = findViewById(R.id.textStats);
        textStats.setText(userScore + "");

        mQuestions = questions[questionIndex].getQuestion();
        textView.setText(mQuestions);

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedBackUser(true);
                questionChangedOnTapped();

            }
        });

        btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedBackUser(false);
                questionChangedOnTapped();


            }
        });


    }

    public void questionChangedOnTapped(){

        questionIndex = (questionIndex + 1) % 10;
        if (questionIndex == 0){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Quiz is finished");
            alertDialog.setMessage("Your Score is" + userScore);
            alertDialog.setPositiveButton("Finish The Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    finish();
                }
            });
            alertDialog.show();
        }
        mQuestions = questions[questionIndex].getQuestion();
        textView.setText(mQuestions);
        progressBar.incrementProgressBy(USER_PROGRESS);

    }

    private void feedBackUser(boolean userGuess){

        boolean currentQuestionAnswer = questions[questionIndex].isAnswer();

        if (currentQuestionAnswer == userGuess){
            Toast.makeText(getApplicationContext(),R.string.correct_toast_message,Toast.LENGTH_SHORT).show();
            userScore = userScore + 1;
            textStats.setText(userScore + "");

        }else{
            Toast.makeText(getApplicationContext(),R.string.incorrect_toas_message,Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(SCORE_KEY, userScore);
        outState.putInt(QUESTION_KEY, questionIndex);
    }
}