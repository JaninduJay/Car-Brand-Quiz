package com.example.w1790282_coursework_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HintActivity extends AppCompatActivity {
    Integer[] imagesList = {
            R.drawable.audi_1,
            R.drawable.audi_2,
            R.drawable.audi_3,
            R.drawable.audi_4,
            R.drawable.audi_5,
            R.drawable.audi_6,
            R.drawable.audi_7,
            R.drawable.benz_1,
            R.drawable.benz_2,
            R.drawable.benz_3,
            R.drawable.benz_4,
            R.drawable.benz_5,
            R.drawable.benz_6,
            R.drawable.benz_7,
            R.drawable.benz_8,
            R.drawable.benz_9,
            R.drawable.lamborghini_1,
            R.drawable.lamborghini_2,
            R.drawable.lamborghini_3,
            R.drawable.lamborghini_4,
            R.drawable.lamborghini_5,
            R.drawable.lamborghini_6,
            R.drawable.lamborghini_7,
            R.drawable.maserati_1,
            R.drawable.maserati_2,
            R.drawable.maserati_3,
            R.drawable.maserati_4,
            R.drawable.porsche_1,
            R.drawable.porsche_2,
            R.drawable.porsche_3,
            R.drawable.porsche_4
    };

    private TextView txtUserAnswer;
    private ImageView imageViewHint;
    private Button btnSubmit;

    private int ranNumber;
    private String tempCarName;
    private List<Character> tempCharList;
    private int falseCount = 0;
    private EditText txtEditorHint;
    private TextView txtViewGuess;
    private TextView txtViewCarMakeHint;

    private static long startTimeInMilli = 20000;
    private TextView textViewTimer;
    private CountDownTimer countDownTimer;
    private long newStartTimeInMillis = startTimeInMilli;
    private boolean switchFlag;

    ArrayList<String> guessAnswer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        //checking if the switch in the mainActivity is on
        Intent intent = getIntent();
        switchFlag = intent.getBooleanExtra("switchFlag",false);
        if (switchFlag){
            startTimeRun();
        }

        imageViewHint = findViewById(R.id.imageView_car_hint);
        btnSubmit = findViewById(R.id.btn_submit);
        txtUserAnswer = findViewById(R.id.txt_user_answer);
        txtEditorHint = findViewById(R.id.txtEdit_letter);
        txtViewGuess = findViewById(R.id.textView_guess_answer);
        txtViewCarMakeHint = findViewById(R.id.textView_car_make_hint);
        textViewTimer = findViewById(R.id.textView_timer_hint);

        ranNumber = (int) (Math.random()*imagesList.length);
        imageViewHint.setImageResource(imagesList[ranNumber]);

        if(ranNumber <= 6){
            tempCarName = "audi";
        }else if(ranNumber > 6 && ranNumber <= 15){
            tempCarName = "benz";
        }else if(ranNumber > 15 && ranNumber <= 22) {
            tempCarName = "lamborghini";
        }else if(ranNumber > 22 && ranNumber <= 26){
            tempCarName = "maserati";
        }else {
            tempCarName = "porsche";
        }

        if (guessAnswer.size() == 0){
            for (int i=0 ; i < tempCarName.length() ; i++){
                guessAnswer.add("_");
            }
        }

        //converting string arrayList which contains user answered letters and spaces into a string
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : guessAnswer){
            stringBuilder.append(string);
            stringBuilder.append("  ");
        }

        txtViewGuess.setText(stringBuilder.toString());

        //Onclick of submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnSubmit.getText().equals("Submit")){
                    timerPause();
                    resetTimeRun();
                    onSubmitRun();

                }else if(btnSubmit.getText().equals("Next")){
                    resetTimeRun();
                    guessAnswer.clear();
                    falseCount = 0;
                    ranNumber = (int) (Math.random()*imagesList.length);
                    imageViewHint.setImageResource(imagesList[ranNumber]);
                    btnSubmit.setText("Submit");
                    txtUserAnswer.setText(" ");
                    txtViewGuess.setText(" ");
                    txtViewCarMakeHint.setText(" ");


                    if(ranNumber <= 6){
                        tempCarName = "audi";
                    }else if(ranNumber > 6 && ranNumber <= 15){
                        tempCarName = "benz";
                    }else if(ranNumber > 15 && ranNumber <= 22) {
                        tempCarName = "lamborghini";
                    }else if(ranNumber > 22 && ranNumber <= 26){
                        tempCarName = "maserati";
                    }else {
                        tempCarName = "porsche";
                    }

                    if (guessAnswer.size() == 0){
                        for (int i=0 ; i < tempCarName.length() ; i++){
                            guessAnswer.add("_");
                        }
                    }

                    //converting string arrayList which contains user answered letters and spaces into a string
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String string : guessAnswer){
                        stringBuilder.append(string);
                        stringBuilder.append("  ");
                    }

                    txtViewGuess.setText(stringBuilder.toString());


                }


            }

        });

    }


    private void startTimeRun() {
        if (switchFlag){
            countDownTimer = new CountDownTimer(newStartTimeInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    newStartTimeInMillis = millisUntilFinished;
                    updateCount();
                }

                @Override
                public void onFinish() {
                    timerPause();
                    resetTimeRun();
                    onSubmitRun();
                }
            }.start();
        }
    }

    private void resetTimeRun() {
        if (switchFlag){
            newStartTimeInMillis = startTimeInMilli;
            startTimeRun();
        }
    }

    private void timerPause(){
        if (switchFlag){
            countDownTimer.cancel();
        }
    }

    private void updateCount() {
        if (switchFlag){
            int seconds = (int) (newStartTimeInMillis / 1000) % 60;
            textViewTimer.setText(String.valueOf(seconds));
        }
    }


    private void onSubmitRun(){
        //clearing number of strikes output
        txtUserAnswer.setText(" ");

        if(ranNumber <= 6){
            tempCarName = "audi";
        }else if(ranNumber > 6 && ranNumber <= 15){
            tempCarName = "benz";
        }else if(ranNumber > 15 && ranNumber <= 22) {
            tempCarName = "lamborghini";
        }else if(ranNumber > 22 && ranNumber <= 26){
            tempCarName = "maserati";
        }else {
            tempCarName = "porsche";
        }

        String userInput = txtEditorHint.getText().toString().toLowerCase();

        //If user enters wrong letter count +1
        if(!tempCarName.contains(userInput)) {
            falseCount += 1;
            txtUserAnswer.setTextColor(Color.parseColor("#FF0000"));
            txtUserAnswer.setText("Strike " + falseCount);
        }

        System.out.println(falseCount);
        if (guessAnswer.size() == 0){
            for (int i=0 ; i < tempCarName.length() ; i++){
                guessAnswer.add("_");
            }
        }

        for (int i=0 ; i < tempCarName.length() ; i++){
            if (String.valueOf(tempCarName.charAt(i)).equals(userInput)){
                guessAnswer.set(i , userInput) ;
            }
        }

        //converting string arrayList which contains user answered letters and spaces into a string
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : guessAnswer){
            stringBuilder.append(string);
            stringBuilder.append("  ");
        }
        txtViewGuess.setText(stringBuilder.toString());

        if (falseCount >= 3){
            timerPause();
            txtUserAnswer.setTextColor(Color.parseColor("#FF0000"));
            txtUserAnswer.setText("WRONG !!!");
            txtViewCarMakeHint.setText(tempCarName.toUpperCase());
            btnSubmit.setText("Next");
        }

        if (!guessAnswer.contains("_")){
            timerPause();
            txtUserAnswer.setTextColor(Color.parseColor("#00FF00"));
            txtUserAnswer.setText("CORRECT !!!");
            txtViewCarMakeHint.setText(tempCarName.toUpperCase());
            btnSubmit.setText("Next");
        }


    }
}