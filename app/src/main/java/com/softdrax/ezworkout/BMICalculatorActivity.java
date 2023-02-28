package com.softdrax.ezworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BMICalculatorActivity extends AppCompatActivity {
    private int weight, heightFt, heightIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);
        EditText etWeight,etHeightFt,etHeightIn;
        Button btnCalc;

        if(getSupportActionBar()!=null)
            getSupportActionBar().setTitle("BMI calculator");

        etWeight=findViewById(R.id.etWeight);
        etHeightFt=findViewById(R.id.etHeightFt);
        etHeightIn=findViewById(R.id.etHeightIn);
        btnCalc=findViewById(R.id.btnCalculate);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight= Integer.parseInt( etWeight.getText().toString());
                heightFt=Integer.parseInt(etHeightFt.getText().toString());
                heightIn=Integer.parseInt(etHeightIn.getText().toString());

                int totalIn=heightFt*12+heightIn;
                double heightCm=totalIn*2.53;
                double totalHeightM=heightCm/100;
                double BMI=weight/(Math.pow(totalHeightM,2));
                if(BMI>25&&BMI<30){
                    tvRes.setText("You are over weight");
                }
                else if(BMI<18.5){
                    tvRes.setText("You are under weight");

                }
                else if(BMI>30){
                    tvRes.setText("You seem to be healthy");

                }else{

                }
            }
        });

    }
}