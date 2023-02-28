package com.softdrax.ezworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BMICalculatorActivity extends AppCompatActivity {
    private int weight, heightFt, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);
        EditText etWeight, etHeightFt, etHeightIn;
        Button btnCalc;

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("BMI calculator");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }

        etWeight = findViewById(R.id.etWeight);
        etHeightFt = findViewById(R.id.etHeightFt);
        etHeightIn = findViewById(R.id.etHeightIn);
        btnCalc = findViewById(R.id.btnCalculate);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight = Integer.parseInt(etWeight.getText().toString());
                double heightCm = Integer.parseInt(etHeightFt.getText().toString());
                age = Integer.parseInt(etHeightIn.getText().toString());
                double totalHeightM = heightCm / 100;
                double BMI = weight / (totalHeightM * totalHeightM);
                if (BMI > 25 && BMI < 30) {
                    Toast toast = new Toast(getApplicationContext());
                    View getToastView = getLayoutInflater()
                            .inflate(R.layout.simple_toast, (ViewGroup) findViewById(R.id.toastViewGroup));
                    toast.setView(getToastView);
                    TextView tvMessage = getToastView.findViewById(R.id.simpleToast);
                    tvMessage.setText(R.string.over_weight_msg);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, -100);
                    toast.show();
                } else if (BMI < 18.5) {
                    Toast toast = new Toast(getApplicationContext());
                    View getToastView = getLayoutInflater()
                            .inflate(R.layout.simple_toast, (ViewGroup) findViewById(R.id.toastViewGroup));
                    toast.setView(getToastView);
                    TextView tvMessage = getToastView.findViewById(R.id.simpleToast);
                    tvMessage.setText(R.string.under_weight_msg);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, -100);
                    toast.show();

                } else if (BMI > 30) {
                    Toast toast = new Toast(getApplicationContext());
                    View getToastView = getLayoutInflater()
                            .inflate(R.layout.simple_toast, (ViewGroup) findViewById(R.id.toastViewGroup));
                    toast.setView(getToastView);
                    TextView tvMessage = getToastView.findViewById(R.id.simpleToast);
                    tvMessage.setText(R.string.obesity_weight_msg);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, -100);
                    toast.show();

                } else {
                    Toast toast = new Toast(getApplicationContext());
                    View getToastView = getLayoutInflater()
                            .inflate(R.layout.simple_toast, (ViewGroup) findViewById(R.id.toastViewGroup));
                    toast.setView(getToastView);
                    TextView tvMessage = getToastView.findViewById(R.id.simpleToast);
                    tvMessage.setText(R.string.normal_weight_msg);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, -100);
                    toast.show();
                }
            }
        });

    }
}