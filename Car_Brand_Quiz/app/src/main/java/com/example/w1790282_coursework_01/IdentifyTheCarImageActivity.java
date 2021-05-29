package com.example.w1790282_coursework_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class IdentifyTheCarImageActivity extends AppCompatActivity {
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

    private ImageView carImage1;
    private ImageView carImage2;
    private ImageView carImage3;
    private Button btnNext;
    private TextView textViewResult;
    private TextView textViewCarName;

    private int ranNumber1;
    private int ranNumber2;
    private int ranNumber3;
    private int ranLast;
    private String resCarName;
    private boolean switchFlag;

    private static long startTimeInMilli = 20000;
    private TextView textViewTimer;
    private CountDownTimer countDownTimer;
    private long newStartTimeInMillis = startTimeInMilli;

    private ArrayList<Integer> tempNumberArray = new ArrayList<>();
    private ArrayList<Integer> tempNumberArray_1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_car_image);

        //checking if the switch in the mainActivity is on
        Intent intent = getIntent();
        switchFlag = intent.getBooleanExtra("switchFlag",false);
        if (switchFlag){
            startTimeRun();
        }

        runIdentify();

        //Onclick of next button
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runIdentify();
            }
        });

        //onClick of image 01
        carImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carImage1.setEnabled(false);
                carImage2.setEnabled(false);
                carImage3.setEnabled(false);
                timerPause();
                if (  imagesList[tempNumberArray.get(ranNumber1)].equals(imagesList[tempNumberArray_1.get(ranLast)]) ){
                    textViewResult.setTextColor(Color.parseColor("#00FF00"));
                    textViewResult.setText("CORRECT !!! , move to next");
                }else{
                    textViewResult.setTextColor(Color.parseColor("#FF0000"));
                    textViewResult.setText("WRONG !!! , move to next");
                }
            }
        });

        //onClick of image 02
        carImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carImage1.setEnabled(false);
                carImage2.setEnabled(false);
                carImage3.setEnabled(false);
                timerPause();
                if (imagesList[tempNumberArray.get(ranNumber2)].equals(imagesList[tempNumberArray_1.get(ranLast)])){
                    textViewResult.setTextColor(Color.parseColor("#00FF00"));
                    textViewResult.setText("CORRECT !!! , move to next");
                }else {
                    textViewResult.setTextColor(Color.parseColor("#FF0000"));
                    textViewResult.setText("WRONG !!! , move to next");
                }
            }
        });

        //onClick of image 03
        carImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carImage1.setEnabled(false);
                carImage2.setEnabled(false);
                carImage3.setEnabled(false);
                timerPause();
                if (imagesList[tempNumberArray.get(ranNumber3)].equals(imagesList[tempNumberArray_1.get(ranLast)])){
                    textViewResult.setTextColor(Color.parseColor("#00FF00"));
                    textViewResult.setText("CORRECT !!! , move to next");
                }else {
                    textViewResult.setTextColor(Color.parseColor("#FF0000"));
                    textViewResult.setText("WRONG !!! , move to next");
                }
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
                    runIdentify();
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


    private void runIdentify(){
        timerPause();
        resetTimeRun();

        carImage1 = findViewById(R.id.imageView_car_identify_1);
        carImage2 = findViewById(R.id.imageView_car_identify_2);
        carImage3 = findViewById(R.id.imageView_car_identify_3);
        btnNext = findViewById(R.id.btn_next_identify);
        textViewResult = findViewById(R.id.textView_result_identify);
        textViewCarName = findViewById(R.id.textView_car_name_identify);
        textViewTimer = findViewById(R.id.textView_timer_identify);

        //clearing past data
        carImage1.setEnabled(true);
        carImage2.setEnabled(true);
        carImage3.setEnabled(true);
        textViewResult.setText(" ");
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
        carImage1.setImageResource(imagesList[tempNumberArray.get(ranNumber1)]);
        carImage2.setImageResource(imagesList[tempNumberArray.get(ranNumber2)]);
        carImage3.setImageResource(imagesList[tempNumberArray.get(ranNumber3)]);

        ranLast = (int) (Math.random() * tempNumberArray_1.size());
        resCarName = getResources().getResourceName(imagesList[tempNumberArray_1.get(ranLast)]);

        String[] splitList = resCarName.split("/", 0);
        String[] splitList_1 = splitList[1].split("_", 0);
        System.out.println(splitList_1[0].toUpperCase());

        textViewCarName.setText(splitList_1[0].toUpperCase());
    }


}