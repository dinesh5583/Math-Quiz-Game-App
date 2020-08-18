package com.example.mathquizgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    int locationOfCorrecrAnswer;
    TextView resultTextView;
    int score=0;
    int numOfQuestion=0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView timerTextView;
    TextView sumTextView;
    ConstraintLayout gameLayout;


    ArrayList<Integer> answer=new ArrayList<Integer>(); // Store Correct Answer and incorrect answer

    public void playAgain(final View view)
    {
        score=0;
        numOfQuestion=0;
        timerTextView.setText("30s");
        playAgainButton.setVisibility(view.INVISIBLE);
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numOfQuestion));
        newQuestion();
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000 +"s"));
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Time Over");
                playAgainButton.setVisibility(view.VISIBLE);
            }
        }.start();

    }

    public void chooseAnswer(View view)    //update Score question and display correct or incorrect
    {
        if(Integer.toString(locationOfCorrecrAnswer).equals(view.getTag().toString()))//View.getTag display that tag of that button which is clicked by user
        {
            resultTextView.setText("CORRECT!");
            score++;

        }
        else {
            resultTextView.setText("INCORRECT!");
        }
        numOfQuestion++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numOfQuestion));
        newQuestion();
    }

    public void start(View view)  //Hide the Go! button
    {
        goButton.setVisibility(view.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView)); //when time is over and we click on play again button this function reset the game
    }
    public void newQuestion()  //Change question every time after select the option
    {
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);

        sumTextView.setText(Integer.toString(a)+" + " +Integer.toString(b));

        locationOfCorrecrAnswer=rand.nextInt(4);  //for one of the button which will be correct
        answer.clear();   //delete all the elements in arrayList
        for(int i=0;i<4;i++) {
            if (i == locationOfCorrecrAnswer)
            {
                answer.add(a+b);  //store in array
            }
            else{
                int wrongAnswer=rand.nextInt(41);
                while(wrongAnswer==a+b)
                {
                    wrongAnswer=rand.nextInt(41);
                }
                answer.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answer.get(0)));  //display correct and incorrect answer in button
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sumTextView=findViewById(R.id.sumTextView);
        goButton=findViewById(R.id.goButton);
         timerTextView=findViewById(R.id.timerTextView);
         button0=findViewById(R.id.button0);
         button1=findViewById(R.id.button1);
         button2=findViewById(R.id.button2);
         button3=findViewById(R.id.button3);
         playAgainButton=findViewById(R.id.playAgain);
        scoreTextView=findViewById(R.id.scoreTextView); //display score after Correct Answer
        resultTextView=findViewById(R.id.resultTextView);  //display Correct or incorrect
        gameLayout=findViewById(R.id.gameLayout);



        gameLayout.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.VISIBLE);


    }
}