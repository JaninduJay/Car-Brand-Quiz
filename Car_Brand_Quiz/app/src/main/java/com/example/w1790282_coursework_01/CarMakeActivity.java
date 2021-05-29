package com.example.w1790282_coursework_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CarMakeActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button button;
    private Spinner spinnerCars;
    private TextView txtViewCar;
    private TextView txtRealCarName;
    private int ranNumber;
    private String tempCarName;

    private static long startTimeInMilli = 20000;
    private TextView textViewTimer;
    private CountDownTimer countDownTimer;
    private long newStartTimeInMillis = startTimeInMilli;
    private boolean switchFlag;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_make);

        //checking if the switch in the mainActivity is on
        Intent intent = getIntent();
        switchFlag = intent.getBooleanExtra("switchFlag",false);
        if (switchFlag){
            startTimeRun();
        }

        imageView = (ImageView) findViewById(R.id.imageView_cars);
        button = (Button) findViewById(R.id.btn_identify);
        spinnerCars = findViewById(R.id.spinner_cars);
        txtViewCar = findViewById(R.id.txt_selected_car);
        txtRealCarName = findViewById(R.id.txt_real_car_name);
        textViewTimer = findViewById(R.id.textView_timer_car_make);

        List<String> carNameList= new ArrayList<String>();
        carNameList.add("Audi");
        carNameList.add("Mercedes Benz");
        carNameList.add("Lamborghini");
        carNameList.add("Maserati");
        carNameList.add("Porsche");

        ArrayAdapter<String> arrayAdapterCars = new ArrayAdapter(this, android.R.layout.simple_spinner_item, carNameList);
        arrayAdapterCars.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCars.setAdapter(arrayAdapterCars);

        //display random image on the startup
        ranNumber = (int) (Math.random()*imagesList.length);
        imageView.setImageResource(imagesList[ranNumber]);


        //Displaying random image when the btn_identify is clicked
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.getText().equals("Identify")){
                    onIdentifyButton();
                }else if(button.getText().equals("Next")){
                    resetTimeRun();
                    ranNumber = (int) (Math.random()*imagesList.length);
                    imageView.setImageResource(imagesList[ranNumber]);
                    button.setText("Identify");
                    txtRealCarName.setText(" ");
                    txtViewCar.setText(" ");
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
                    onIdentifyButton();
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

    private void onIdentifyButton(){
        if(ranNumber <= 6){
            tempCarName = "Audi";
            txtRealCarName.setText("It's an Audi");
        }else if(ranNumber > 6 && ranNumber <= 15){
            tempCarName = "Mercedes Benz";
            txtRealCarName.setText("It's a Mercedes Benz");
        }else if(ranNumber > 15 && ranNumber <= 22) {
            tempCarName = "Lamborghini";
            txtRealCarName.setText("It's a Lamborghini");
        }else if(ranNumber > 22 && ranNumber <= 26){
            tempCarName = "Maserati";
            txtRealCarName.setText("It's a Maserati");
        }else {
            tempCarName = "Porsche";
            txtRealCarName.setText("It's a Porsche");
        }

        if(spinnerCars.getSelectedItem().toString() == tempCarName){
            timerPause();
            txtViewCar.setText("CORRECT !!!");
            txtViewCar.setTextColor(Color.parseColor("#00FF00"));
        }else {
            timerPause();
            txtViewCar.setText("WRONG !!!");
            txtViewCar.setTextColor(Color.parseColor("#FF0000"));
        }
        button.setText("Next");

    }

}