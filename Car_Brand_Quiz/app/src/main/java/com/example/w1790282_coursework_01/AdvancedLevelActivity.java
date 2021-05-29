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

import java.util.ArrayList;

public class AdvancedLevelActivity extends AppCompatActivity {
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

    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textViewResult;
    private TextView textViewMarks;
    private Button btnSubmit;

    private int ranNumber1;
    private int ranNumber2;
    private int ranNumber3;
    private String resCarName1;
    private String resCarName2;
    private String resCarName3;
    private String realCarName1;
    private String realCarName2;
    private String realCarName3;
    private int count = 0;
    private int marks;

    private static long startTimeInMilli = 20000;
    private TextView textViewTimer;
    private CountDownTimer countDownTimer;
    private long newStartTimeInMillis = startTimeInMilli;
    private boolean switchFlag;

    private ArrayList<Integer> tempNumberArray = new ArrayList<>();
    private ArrayList<Integer> tempNumberArray_1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level);

        //checking if the switch in the mainActivity is on
        Intent intent = getIntent();
        switchFlag = intent.getBooleanExtra("switchFlag",false);
        if (switchFlag){
            startTimeRun();
        }
        runAdvance();

        //On click of the submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnSubmit();
            }
        });
    }


    private void startTimeRun() {
        if (switchFlag) {
            countDownTimer = new CountDownTimer(newStartTimeInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    newStartTimeInMillis = millisUntilFinished;
                    updateCount();
                }

                @Override
                public void onFinish() {
                    runOnSubmit();
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

    private void runAdvance(){
        btnSubmit = findViewById(R.id.button_submit_advance);
        textViewResult = findViewById(R.id.textView_result_advance);
        textViewMarks = findViewById(R.id.textView_marks_advance);
        textViewTimer = findViewById(R.id.textView_timer_advance);

        imageView1 = findViewById(R.id.imageView_advance_1);
        imageView2 = findViewById(R.id.imageView_advance_2);
        imageView3 = findViewById(R.id.imageView_advance_3);

        editText1 = findViewById(R.id.editText_advace_1);
        editText2 = findViewById(R.id.editText_advace_2);
        editText3 = findViewById(R.id.editText_advace_3);

        textView1 = findViewById(R.id.textView_advance_1);
        textView2 = findViewById(R.id.textView_advance_2);
        textView3 = findViewById(R.id.textView_advance_3);

        //Clearing previous data
        if (tempNumberArray.size() >= 5 || tempNumberArray_1.size() >= 3 ){
            tempNumberArray.clear();
            tempNumberArray_1.clear();
        }

        //Taking index of each random image from one brand and adding them into a new arrayList
        int tempRanNumber1 = (int) (Math.random() * 7);
        int tempRanNumber2 = (int) (Math.random() * 9 + 7);
        int tempRanNumber3 = (int) (Math.random() * 7 + 16);
        int tempRanNumber4 = (int) (Math.random() * 4 + 23);
        int tempRanNumber5 = (int) (Math.random() * 4 + 27);

        tempNumberArray.add(tempRanNumber1);
        tempNumberArray.add(tempRanNumber2);
        tempNumberArray.add(tempRanNumber3);
        tempNumberArray.add(tempRanNumber4);
        tempNumberArray.add(tempRanNumber5);


        //taking three different random index numbers from new arrayList
        while (true){
            ranNumber1 = (int) (Math.random() * tempNumberArray.size());
            ranNumber2 = (int) (Math.random() * tempNumberArray.size());
            ranNumber3 = (int) (Math.random() * tempNumberArray.size());
            if(ranNumber1 != ranNumber2 && ranNumber1 != ranNumber3 && ranNumber2 != ranNumber3){
                break;
            }
        }
        tempNumberArray_1.add(tempNumberArray.get(ranNumber1));
        tempNumberArray_1.add(tempNumberArray.get(ranNumber2));
        tempNumberArray_1.add(tempNumberArray.get(ranNumber3));

        //Taking relevant image to the random numbers generated and displaying those images
        imageView1.setImageResource(imagesList[tempNumberArray.get(ranNumber1)]);
        imageView2.setImageResource(imagesList[tempNumberArray.get(ranNumber2)]);
        imageView3.setImageResource(imagesList[tempNumberArray.get(ranNumber3)]);

        //getting name of the resource file
        resCarName1 = getResources().getResourceName(imagesList[tempNumberArray.get(ranNumber1)]);
        resCarName2 = getResources().getResourceName(imagesList[tempNumberArray.get(ranNumber2)]);
        resCarName3 = getResources().getResourceName(imagesList[tempNumberArray.get(ranNumber3)]);

        //splitting the resource file and getting relevant part
         realCarName1 = splitResourceName(resCarName1);
         realCarName2 = splitResourceName(resCarName2);
         realCarName3 = splitResourceName(resCarName3);

    }

    private void runOnSubmit() {
        //Restoring past data
        textViewResult.setText(" ");
        editText1.setEnabled(true);
        editText2.setEnabled(true);
        editText3.setEnabled(true);
        textView1.setText(" ");
        textView2.setText(" ");
        textView3.setText(" ");
        textViewMarks.setText(String.valueOf(marks));
        timerPause();
        resetTimeRun();

        String userInputName1 = editText1.getText().toString().toUpperCase().trim();
        String userInputName2 = editText2.getText().toString().toUpperCase().trim();
        String userInputName3 = editText3.getText().toString().toUpperCase().trim();

        if(!userInputName1.equals(realCarName1) || !userInputName2.equals(realCarName2) || !userInputName3.equals(realCarName3)){
            count += 1;
        }

        if (btnSubmit.getText().equals("Submit")){
            if (userInputName1.equals(realCarName1)){
                editText1.setBackgroundColor(Color.parseColor("#00FF00"));
                editText1.setEnabled(false);
            }else {
                editText1.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            if (userInputName2.equals(realCarName2)){
                editText2.setBackgroundColor(Color.parseColor("#00FF00"));
                editText2.setEnabled(false);
            }else {
                editText2.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            if (userInputName3.equals(realCarName3)){
                editText3.setBackgroundColor(Color.parseColor("#00FF00"));
                editText3.setEnabled(false);
            }else {
                editText3.setBackgroundColor(Color.parseColor("#FF0000"));
            }

            if (userInputName1.equals(realCarName1)){
                marks = 1;
            }else if (userInputName2.equals(realCarName2)) {
                marks = 1;
            }else if (userInputName3.equals(realCarName3)) {
                marks = 1;
            }
            if (userInputName1.equals(realCarName1) && userInputName2.equals(realCarName2)){
                marks = 2;
            }else if (userInputName1.equals(realCarName1) && userInputName3.equals(realCarName3)) {
                marks = 2;
            }else if (userInputName3.equals(realCarName3) && userInputName2.equals(realCarName2)) {
                marks = 2;
            }
            if (userInputName1.equals(realCarName1) && userInputName2.equals(realCarName2) && userInputName3.equals(realCarName3)){
                marks = 3;
            }

            if (count >= 3){
                timerPause();
                btnSubmit.setText("Next");
                textViewResult.setTextColor(Color.parseColor("#FF0000"));
                textViewResult.setText("WRONG !!!, move to next");
                if (!userInputName1.equals(realCarName1)){
                    textView1.setText(realCarName1);
                }
                if (!userInputName2.equals(realCarName2)){
                    textView2.setText(realCarName2);
                }
                if(!userInputName3.equals(realCarName3)) {
                    textView3.setText(realCarName3);
                }

            }
            if(userInputName1.equals(realCarName1) && userInputName2.equals(realCarName2) && userInputName3.equals(realCarName3)){
                timerPause();
                btnSubmit.setText("Next");
                textViewResult.setTextColor(Color.parseColor("#00FF00"));
                textViewResult.setText("CORRECT !!!, move to next");
            }
            textViewMarks.setText(String.valueOf(marks));

        }else if (btnSubmit.getText().equals("Next")){
            count = 0;
            marks = 0;
            textViewMarks.setText("0");
            editText1.setText(" ");
            editText1.setBackgroundColor(Color.parseColor("#FFFFFF"));

            editText2.setText(" ");
            editText2.setBackgroundColor(Color.parseColor("#FFFFFF"));

            editText3.setText(" ");
            editText3.setBackgroundColor(Color.parseColor("#FFFFFF"));
            btnSubmit.setText("Submit");

            runAdvance();
        }

    }


    private String splitResourceName(String resCarName) {
        String[] splitList = resCarName.split("/", 0);
        String[] splitList_1 = splitList[1].split("_", 0);

        return splitList_1[0].toUpperCase();
    }

}