package com.example.w1790282_coursework_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private Button btnIdentifyCar;
    private Button btnHints;
    private Button btnCarImage;
    private Button btnAdvanced;

    public Switch switchTimer;
    public boolean switchFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button 01
        btnIdentifyCar = (Button) findViewById(R.id.btn_car_make);
        btnIdentifyCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityCarMake();
            }
        });

        //Button 02
        btnHints = findViewById(R.id.btn_hints);
        btnHints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityHints();
            }
        });

        //Button 03
        btnCarImage = findViewById(R.id.btn_car_image);
        btnCarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityCarImage();
            }
        });

        //Button 04
        btnAdvanced = findViewById(R.id.btn_advanced_level);
        btnAdvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityAdvance();
            }
        });

        //Timer Switch
        switchTimer = findViewById(R.id.switch_timer_main);
        switchTimer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    switchFlag = true;
                }else {
                    switchFlag = false;
                }
            }
        });

    }

    public void openActivityAdvance() {
        Intent intent = new Intent(this, AdvancedLevelActivity.class);
        intent.putExtra("switchFlag", switchFlag);
        startActivity(intent);
    }

    public void openActivityCarImage(){
        Intent intent = new Intent(this, IdentifyTheCarImageActivity.class);
        intent.putExtra("switchFlag", switchFlag);
        startActivity(intent);
    }

    public void openActivityCarMake(){
        Intent intent = new Intent(this, CarMakeActivity.class);
        intent.putExtra("switchFlag", switchFlag);
        startActivity(intent);
    }

    public void openActivityHints() {
        Intent intent = new Intent(this, HintActivity.class);
        intent.putExtra("switchFlag", switchFlag);
        startActivity(intent);
    }

}