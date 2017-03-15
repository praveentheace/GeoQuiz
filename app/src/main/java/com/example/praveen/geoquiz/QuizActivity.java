package com.example.praveen.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.praveen.geoquiz.model.Question;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    private int mCurrentIndex;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_africa, true),
            new Question(R.string.question_americas, false),
            new Question(R.string.question_asia, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_oceans, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton = (Button) findViewById(R.id.true_button);
        Button falseButton = (Button) findViewById(R.id.false_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mNextButton = (Button) findViewById(R.id.next_button);

        mCurrentIndex = 0;
        mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getTextResId());

        mTrueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getTextResId());
            }
        });
    }

    private void checkAnswer(boolean isTruePressed){
        boolean answer = mQuestionBank[mCurrentIndex].isAnswerTrue();

        if(isTruePressed == answer){
            Toast.makeText(QuizActivity.this,R.string.correct_toast,Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(QuizActivity.this,R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
    }
}
